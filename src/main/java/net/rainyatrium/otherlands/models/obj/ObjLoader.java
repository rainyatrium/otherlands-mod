package net.rainyatrium.otherlands.models.obj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.rainyatrium.otherlands.Otherlands;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mojang.datafixers.util.Either;


/**
 * A loader for {@link ObjModel OBJ models}.
 * <p>
 * Allows the user to enable automatic face culling, toggle quad shading, flip UVs, render emissively and specify a
 * {@link ObjMaterialLibrary material library} override.
 */
public class ObjLoader implements ModelLoadingPlugin, IGeometryLoader<ObjModel> {
	public static final Identifier ID = PortingLib.id("obj");
	public static final ObjLoader INSTANCE = new ObjLoader();
	public static final String OBJ_MARKER = PortingLib.id("obj_marker").toString();

	private final Map<ObjModel.ModelSettings, ObjModel> modelCache = Maps.newConcurrentMap();
	private final Map<Identifier, ObjMaterialLibrary> materialCache = Maps.newConcurrentMap();

	@Override
	public void onInitializeModelLoader(Context ctx) {
		// called every reload, clear caches
		modelCache.clear();
		materialCache.clear();

		findModels(ctx::addModels);
		ctx.resolveModel().register(new Resolver());
	}

	/**
	 * models/misc is automatically scanned for OBJ models.
	 */
	private void findModels(Consumer<Identifier> out) {
		ResourceManager manager = getResourceManager();
		manager.listResources("models/misc", id -> {
			if (id.getPath().endsWith(".json")) {
				manager.getResource(id).ifPresent(resource -> {
					if (tryLoadModelJson(id, resource) != null) {
						out.accept(id);
					}
				});
			}
			return true;
		});
	}

	private JsonObject tryLoadModelJson(Identifier id, Resource resource) {
		try {
			JsonObject json = JsonParser.parseReader(resource.openAsReader()).getAsJsonObject();
			if (json.has(OBJ_MARKER)) {
				return json;
			}
		} catch (IOException | IllegalStateException e) {
			PortingLib.LOGGER.error("Error loading obj model from models/misc: " + id, e);
		}
		return null;
	}

	private Either<ObjModel.ModelSettings, RuntimeException> tryReadSettings(JsonObject json) {
		try {
			Identifier objLocation = new Identifier(GsonHelper.getAsString(json, "model"));
			return Either.left(new ObjModel.ModelSettings(objLocation,
					GsonHelper.getAsBoolean(json, "automaticCulling", true),
					GsonHelper.getAsBoolean(json, "shadeQuads", true),
					GsonHelper.getAsBoolean(json, "flipV", true),
					GsonHelper.getAsBoolean(json, "emissiveAmbient", true),
					GsonHelper.getAsString(json, "mtlOverride", null)
			));
		} catch (RuntimeException e) { // ID parse fail, json parse fail
			return Either.right(e);
		}
	}

	/**
	 * Load an OBJ model as a block model's geometry.
	 */
	@Override
	public ObjModel read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) {
		return tryReadSettings(jsonObject).map(this::loadModel, exception -> {
			throw new JsonParseException("Error loading OBJ model settings", exception);
		});
	}

	private ObjModel loadModel(ObjModel.ModelSettings settings) {
		Identifier id = settings.modelLocation();
		Resource resource = getResourceManager().getResource(id).orElseThrow(() -> new NoSuchElementException(id.toString()));
		return loadModel(resource, settings);
	}

	private ObjModel loadModel(Resource resource, ObjModel.ModelSettings settings) {
		return modelCache.computeIfAbsent(settings, data -> {
			try (ObjTokenizer tokenizer = new ObjTokenizer(resource.open())) {
				return ObjParser.parse(tokenizer, settings);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("Could not find OBJ model", e);
			} catch (Exception e) {
				throw new RuntimeException("Could not read OBJ model", e);
			}
		});
	}

	public ObjMaterialLibrary loadMaterialLibrary(Identifier materialLocation) {
		return materialCache.computeIfAbsent(materialLocation, location -> {
			Resource resource = getResourceManager().getResource(location).orElseThrow();
			try (ObjTokenizer rdr = new ObjTokenizer(resource.open())) {
				return new ObjMaterialLibrary(rdr);
			} catch (FileNotFoundException e) {
				throw new RuntimeException("Could not find OBJ material library", e);
			} catch (Exception e) {
				throw new RuntimeException("Could not read OBJ material library", e);
			}
		});
	}

	private static ResourceManager getResourceManager() {
		return MinecraftClient.getInstance().getResourceManager();
	}

	private class Resolver implements ModelResolver {
		@Override
		@Nullable
		public UnbakedModel resolveModel(Context context) {
			Identifier id = context.id();
			Identifier fileId = ModelBakery.MODEL_LISTER.idToFile(id);
			return getResourceManager().getResource(fileId).map(resource -> {
				JsonObject json = tryLoadModelJson(id, resource);
				return json == null ? null : tryReadSettings(json).map(settings -> loadModel(resource, settings), exception -> {
					Otherlands.LOGGER.error("Error loading obj model: " + id, exception);
					return null;
				});
			}).orElse(null);
		}
	}
}
