package net.rainyatrium.otherlands;

import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rainyatrium.otherlands.init.ModBlocks;
import net.rainyatrium.otherlands.init.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Otherlands implements ModInitializer {
	// This logger is used to write text to the console and the log file. Remember to include the mod info.
	public static final String MOD_ID = "otherlands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	//SOUNDS ======================
	//Boner
	public static final Identifier mtr2boner = new Identifier("otherlands:mtr2boner");
	public static SoundEvent boner = new SoundEvent(mtr2boner);
	//PENIS!
	public static final Identifier mtr2bonersecret = new Identifier("otherlands:mtr2boner_secret");
	public static SoundEvent bonersecret = new SoundEvent(mtr2bonersecret);
	//Vai Vedrai
	public static final Identifier vaivedrai = new Identifier("otherlands:vaivedrai");
	public static SoundEvent recvaivedrai = new SoundEvent(vaivedrai);
	//You Belong With Me
	public static final Identifier youbelongwithme = new Identifier("otherlands:youbelongwithme");
	public static SoundEvent recyoubelongwithme = new SoundEvent(youbelongwithme);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state. Some stuff isn't ready yet though.
		LOGGER.info("Oof. Right on the spoppegt!");
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		Registry.register(Registry.SOUND_EVENT, Otherlands.mtr2boner, boner);
		Registry.register(Registry.SOUND_EVENT, Otherlands.mtr2bonersecret, bonersecret);
		Registry.register(Registry.SOUND_EVENT, Otherlands.vaivedrai, recvaivedrai);
	}
}

