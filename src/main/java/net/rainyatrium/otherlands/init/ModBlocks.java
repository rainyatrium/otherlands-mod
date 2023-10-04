package net.rainyatrium.otherlands.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.rainyatrium.otherlands.Otherlands;
import net.rainyatrium.otherlands.block.custom.QuartzPlinth;
import net.rainyatrium.otherlands.block.custom.base.ItemDisplayBlock;
import net.rainyatrium.otherlands.block.custom.base.OtherStairsBlock;
import net.rainyatrium.otherlands.block.custom.base.PropBlock;
import net.rainyatrium.otherlands.block.custom.Sheeple;
import net.rainyatrium.otherlands.block.custom.HotlineStand;
import net.rainyatrium.otherlands.block.custom.base.TableBlock;
import net.rainyatrium.otherlands.block.entity.HotlineStandEnt;
import net.rainyatrium.otherlands.block.entity.ItemDisplayBlockEnt;
import net.rainyatrium.otherlands.block.entity.TableEnt;

import java.util.Queue;

public class ModBlocks {
    //Architect Hotline
    public static final Block HOTLINE_STAND = registerBlock("hotline_stand",
            new HotlineStand(FabricBlockSettings.of(Material.METAL).nonOpaque().hardness(1000).resistance(1800000)), ItemGroup.MISC);
    public static final BlockEntityType<HotlineStandEnt> HOTLINE_STAND_ENT = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(Otherlands.MOD_ID, "hotline_stand_ent"),
            FabricBlockEntityTypeBuilder.create((pos, state)->new HotlineStandEnt(ModBlocks.HOTLINE_STAND_ENT, pos, state), HOTLINE_STAND).build()
    );

    //Tables
    public static final Block OAK_TABLE = registerBlock("oak_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1)), ItemGroup.MISC);
    public static final Block SPRUCE_TABLE = registerBlock("spruce_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block ACACIA_TABLE = registerBlock("acacia_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block ARAUCARIA_TABLE = registerBlock("araucaria_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block BIRCH_TABLE = registerBlock("birch_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block CRIMSON_TABLE = registerBlock("crimson_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block DARK_OAK_TABLE = registerBlock("dark_oak_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block JUNGLE_TABLE = registerBlock("jungle_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block WARPED_TABLE = registerBlock("warped_table",
            new TableBlock(FabricBlockSettings.of(Material.WOOD).resistance(5).hardness(1).nonOpaque()), ItemGroup.MISC);
    public static final Block QUARTZ_PLINTH = registerBlock("quartz_plinth",
            new QuartzPlinth(FabricBlockSettings.of(Material.STONE).resistance(6).hardness(2).nonOpaque()), ItemGroup.MISC);

    public static final BlockEntityType<TableEnt> TABLE_ENT = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(Otherlands.MOD_ID, "oak_table_ent"),
            FabricBlockEntityTypeBuilder.create((pos, state) -> new TableEnt(ModBlocks.TABLE_ENT, pos, state),
                    OAK_TABLE,
                    SPRUCE_TABLE,
                    ACACIA_TABLE,
                    ARAUCARIA_TABLE,
                    BIRCH_TABLE,
                    CRIMSON_TABLE,
                    DARK_OAK_TABLE,
                    JUNGLE_TABLE,
                    WARPED_TABLE,
                    QUARTZ_PLINTH).build()
    );

    //Pober block
    public static final Block POBER_BLOCK = registerBlock("pober_block",
        new PropBlock(FabricBlockSettings.of(Material.GOURD).nonOpaque()), ItemGroup.MISC);
    //Sheeple
    public static final Block SHEEPLE1 = registerBlock("sheeple1",
            new Sheeple(FabricBlockSettings.of(Material.GOURD).hardness(999).nonOpaque()), ItemGroup.MISC);
    public static final Block SHEEPLE2 = registerBlock("sheeple2",
            new Sheeple(FabricBlockSettings.of(Material.GOURD).hardness(999).nonOpaque()), ItemGroup.MISC);
    public static final Block SHEEPLE3 = registerBlock("sheeple3",
            new Sheeple(FabricBlockSettings.of(Material.GOURD).hardness(999).nonOpaque()), ItemGroup.MISC);
    public static final Block SHEEPLE4 = registerBlock("sheeple4",
            new Sheeple(FabricBlockSettings.of(Material.GOURD).hardness(999).nonOpaque()), ItemGroup.MISC);
    public static final Block SHEEPLE5 = registerBlock("sheeple5",
            new Sheeple(FabricBlockSettings.of(Material.GOURD).hardness(999).nonOpaque()), ItemGroup.MISC);
    public static final Block SHEEPLE_MERCHANT = registerBlock("sheeple_merchant",
            new Sheeple(FabricBlockSettings.of(Material.GOURD).hardness(999).nonOpaque()), ItemGroup.MISC);
    //Shrines
    public static final Block SHRINE_HYDRO = registerBlock("shrine_hydro",
            new PropBlock(FabricBlockSettings.of(Material.STONE).resistance(6).hardness(2).nonOpaque()), ItemGroup.MISC);
    public static final Block SHRINE_CATALYST = registerBlock("shrine_catalyst",
            new PropBlock(FabricBlockSettings.of(Material.STONE).resistance(6).hardness(2).nonOpaque()), ItemGroup.MISC);
    public static final Block SHRINE_IRMINSUL = registerBlock("shrine_irminsul",
            new PropBlock(FabricBlockSettings.of(Material.STONE).resistance(6).hardness(2).nonOpaque()), ItemGroup.MISC);
    public static final Block SHRINE_MNEMON = registerBlock("shrine_mnemon",
            new PropBlock(FabricBlockSettings.of(Material.STONE).resistance(6).hardness(2).nonOpaque()), ItemGroup.MISC);
    public static final Block SHRINE_OBSCURO = registerBlock("shrine_obscuro",
            new PropBlock(FabricBlockSettings.of(Material.STONE).resistance(6).hardness(2).nonOpaque()), ItemGroup.MISC);
    //Catalyst Bricks
    public static final Block MOON_BRICKS = registerBlock("moon_bricks",
            new Block(FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block MOON_BRICK_SLAB = registerBlock("moon_brick_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block MOON_BRICK_STAIRS = registerBlock("moon_brick_stairs",
            new OtherStairsBlock(ModBlocks.MOON_BRICKS.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block MOON_LANTERN = registerBlock("moon_lantern",
            new Block(FabricBlockSettings.of(Material.STONE).hardness(999).luminance(12)), ItemGroup.MISC);
    public static final Block CAGED_MOON_LANTERN = registerBlock("caged_moon_lantern",
            new Block(FabricBlockSettings.of(Material.STONE).hardness(999).luminance(12)), ItemGroup.MISC);
    public static final Block MOON_ROCK = registerBlock("moon_rock",
            new Block(FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block MOON_ROCK_SLAB = registerBlock("moon_rock_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block MOON_ROCK_STAIRS = registerBlock("moon_rock_stairs",
            new OtherStairsBlock(ModBlocks.MOON_ROCK.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block MOON_TILE = registerBlock("moon_tile",
            new Block(FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);
    public static final Block RED_MOON_TILE = registerBlock("red_moon_tile",
            new Block(FabricBlockSettings.of(Material.STONE).hardness(999)), ItemGroup.MISC);


    //Methods for block creation
    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Otherlands.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM,new Identifier(Otherlands.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }
    public static void registerModBlocks() {
        Otherlands.LOGGER.info("[" + Otherlands.MOD_ID + "] It's raining blocks!");
    }
}
