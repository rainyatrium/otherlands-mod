package net.rainyatrium.otherlands.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import org.lwjgl.opengl.GL11;

public class SkyboxObjects {
    public static void onRender(WorldRenderContext context) {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        GameRenderer gameRenderer = minecraftClient.gameRenderer;

        Camera camera = context.camera();
        Vec3d targetPosition = new Vec3d(0,10,0);
        Vec3d transformedPosition = targetPosition.subtract(camera.getPos());


        MatrixStack matrixStack = new MatrixStack();
        matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(camera.getPitch()));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(camera.getYaw() + 180.0F));
        matrixStack.translate(transformedPosition.x, transformedPosition.y, transformedPosition.z);
        matrixStack.scale(200,200,200);


        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        Matrix4f projectionMatrix = RenderSystem.getProjectionMatrix().copy();
        setClipPlanes(projectionMatrix,0.5f,3000f);

        //System.out.println(altProjectionMatrix);
        //System.out.println(projectionMatrix);
        gameRenderer.loadProjectionMatrix(projectionMatrix);

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE);
        buffer.vertex(positionMatrix, 0, 0, 0).color(1f, 1f, 1f, 1f).texture(0f, 0f).next();
        buffer.vertex(positionMatrix, 0, 1, 0).color(1f, 0f, 0f, 1f).texture(0f, 1f).next();
        buffer.vertex(positionMatrix, 1, 1, 0).color(0f, 1f, 0f, 1f).texture(1f, 1f).next();
        buffer.vertex(positionMatrix, 1, 0, 0).color(0f, 0f, 1f, 1f).texture(1f, 0f).next();


        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.setShaderTexture(0, new Identifier("examplemod", "icon.png"));
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableCull();
        RenderSystem.depthFunc(GL11.GL_ALWAYS);
        tessellator.draw();
        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        RenderSystem.enableCull();
    }

    /**
     * Changes the values that store the clipping planes.
     * Formula for calculating matrix values is the same that OpenGL uses when making matrices.
     *
     * @param nearClip New near clipping plane value.
     * @param farClip New far clipping plane value.
     */
    public static void setClipPlanes(Matrix4f matrix4f, float nearClip, float farClip)
    {
        //convert to matrix values, formula copied from a textbook / openGL specification.
        float matNearClip = -((farClip + nearClip) / (farClip - nearClip));
        float matFarClip = -((2 * farClip * nearClip) / (farClip - nearClip));
        //set new values for the clip planes.
        matrix4f.a22 = matNearClip;
        matrix4f.a23 = matFarClip;
    }
}

/*	private void setupProjectionMatrix(float partialTicks)
	{
		Matrix4f projectionMatrix =
				Matrix4f.perspective(
				getFov(partialTicks, true),
				(float)this.mc.getMainWindow().getFramebufferWidth() / (float)this.mc.getMainWindow().getFramebufferHeight(),
				0.5F,
				this.farPlaneDistance * LOD_CHUNK_DISTANCE_RADIUS * 2);

		gameRender.resetProjectionMatrix(projectionMatrix);

		return;
	}*/