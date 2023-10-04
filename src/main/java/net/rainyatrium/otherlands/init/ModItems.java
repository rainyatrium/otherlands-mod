package net.rainyatrium.otherlands.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.rainyatrium.otherlands.Otherlands;
import net.minecraft.util.registry.Registry;
import net.rainyatrium.otherlands.item.custom.OtherMusicDisc;
import net.rainyatrium.otherlands.item.custom.Pober;
import net.rainyatrium.otherlands.item.custom.StunGun;

/*
========= NOTES =========
*/
public class ModItems {
    // This is a method we're making for registering items more conveniently. Now we can plug things in elsewhere.
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Otherlands.MOD_ID, name), item);
    }
    //Now the actual items:
    //Registering it here puts it in the game, but it needs textures and behaviors and such. Go to the resources folder
    //for that.
    public static final Item HAORI = registerItem("haori",
            new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST,(new FabricItemSettings().group(ItemGroup.COMBAT))));
    public static final Item STUNGUN = registerItem("stun_gun",
            new StunGun(new FabricItemSettings().group(ItemGroup.COMBAT)));
    public static final Item POBER = registerItem("pober",
            new Pober(new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    public static final Item VAI_VEDRAI_MUSIC_DISC = registerItem("vai_vedrai_music_disc",
            new OtherMusicDisc(7, Otherlands.recvaivedrai, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
    public static final Item TAYLOR_SWIFT_MUSIC_DISC = registerItem("taylor_swift_music_disc",
            new OtherMusicDisc(7, Otherlands.recyoubelongwithme, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
    public static void registerModItems() {
        Otherlands.LOGGER.info("[" + Otherlands.MOD_ID + "] It's raining items!");
    }
}
