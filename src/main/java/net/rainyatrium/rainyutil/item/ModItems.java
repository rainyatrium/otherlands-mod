package net.rainyatrium.rainyutil.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.rainyatrium.rainyutil.RainyUtil;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.rainyatrium.rainyutil.item.custom.Pober;

/*
========= NOTES =========
*/
public class ModItems {
    // This is a method we're making for registering items more conveniently. Now we can plug things in elsewhere.
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(RainyUtil.MOD_ID, name), item);
    }
    //Now the actual items:
    //Registering it here puts it in the game, but it needs textures and behaviors and such. Go to the resources folder
    //for that.
    public static final Item POBER = registerItem("pober",
            new Pober(new FabricItemSettings().group(ItemGroup.MISC)));
    public static void registerModItems() {
        RainyUtil.LOGGER.info("[" + RainyUtil.MOD_ID + "] It's raining items!");
    }
}
