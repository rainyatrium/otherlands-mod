package net.rainyatrium.otherlands.block.custom.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.rainyatrium.otherlands.block.entity.ItemDisplayBlockEnt;
import net.rainyatrium.otherlands.init.ModBlocks;


public class TableBlock extends ItemDisplayBlock {
    public TableBlock(Settings settings) {
        super(settings);
    }

    //Create the grate block entity
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ItemDisplayBlockEnt(ModBlocks.TABLE_ENT, pos, state);
    }
    //Render layer

    //Hitbox things
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return TABLE;
    }
    private static final VoxelShape TOP;
    private static final VoxelShape LEG1;
    private static final VoxelShape LEG2;
    private static final VoxelShape LEG3;
    private static final VoxelShape LEG4;
    private static final VoxelShape TABLE;

    static {
        TOP = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D);  // top
                LEG1 = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 15.0D, 4.0D);     // leg
                LEG2 = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 15.0D, 4.0D);   // leg
                LEG3 = Block.createCuboidShape(12.0D, 0.0D, 12.0D, 16.0D, 15.0D, 16.0D); // leg
                LEG4 = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 4.0D, 15.0D, 16.0D);  // leg
        TABLE = VoxelShapes.union(TOP, new VoxelShape[]{LEG1, LEG2, LEG3, LEG4});
    }

}
