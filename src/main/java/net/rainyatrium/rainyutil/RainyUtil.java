package net.rainyatrium.rainyutil;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.rainyatrium.rainyutil.item.ModItems;
import net.rainyatrium.rainyutil.item.custom.NBTFunctionItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RainyUtil implements ModInitializer {
	// This logger is used to write text to the console and the log file. Remember to include the mod info.
	public static final String MOD_ID = "rainyutil";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private final NBTFunctionItems nbtFunctionItems = new NBTFunctionItems();
	//SOUNDS ======================
	//Boner
	public static final Identifier mtr2boner = new Identifier("rainyutil:mtr2boner");
	public static SoundEvent boner = new SoundEvent(mtr2boner);
	//PENIS!
	public static final Identifier mtr2bonersecret = new Identifier("rainyutil:mtr2boner_secret");
	public static SoundEvent bonersecret = new SoundEvent(mtr2bonersecret);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state. Some stuff isn't ready yet though.
		LOGGER.info("Oof. Right on the spoppegt!");
		ModItems.registerModItems();
		Registry.register(Registry.SOUND_EVENT, RainyUtil.mtr2boner, boner);
		Registry.register(Registry.SOUND_EVENT, RainyUtil.mtr2bonersecret, bonersecret);
		//Check for nbt on right click (there has to be a better way)
		NBTFunctionItems.registerUseItemCallback();
	}
}