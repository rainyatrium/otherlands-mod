package net.rainyatrium.otherlands.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.rainyatrium.otherlands.block.custom.base.TableBlock;
import net.rainyatrium.otherlands.block.entity.ItemDisplayBlockEnt;
import net.rainyatrium.otherlands.init.ModBlocks;

public class QuartzPlinth extends TableBlock {
    public QuartzPlinth(Settings settings) {
        super(settings);
    }

    //Hitbox things
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16, 16.0 );
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
