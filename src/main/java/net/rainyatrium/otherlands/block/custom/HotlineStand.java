package net.rainyatrium.otherlands.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.rainyatrium.otherlands.block.custom.base.ItemDisplayBlock;
import net.rainyatrium.otherlands.block.entity.ItemDisplayBlockEnt;
import net.rainyatrium.otherlands.init.ModBlocks;

public class HotlineStand extends ItemDisplayBlock {
    public HotlineStand(Settings settings) {
        super(settings);
    }

    //Create the grate block entity
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ItemDisplayBlockEnt(ModBlocks.HOTLINE_STAND_ENT, pos, state);
    }
    //Render layer

    //Hitbox things
    protected static final VoxelShape NORTH = Block.createCuboidShape(2.0, 0.0, 1.0, 14.0, 7, 16.0 );
    protected static final VoxelShape WEST = Block.createCuboidShape(1.0, 0.0, 2.0, 16.0, 7, 14.0 );
    protected static final VoxelShape SOUTH = Block.createCuboidShape(2.0, 0.0, 0.0, 14.0, 7, 15.0 );
    protected static final VoxelShape EAST = Block.createCuboidShape(0.0, 0.0, 2.0, 15.0, 7, 14.0 );
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction d = (Direction) state.get(FACING);
        VoxelShape SHAPE = NORTH;
        switch (d) {
            case NORTH:
                SHAPE = NORTH;
            break;
            case EAST:
                SHAPE = EAST;
            break;
            case SOUTH:
                SHAPE = SOUTH;
            break;
            case WEST:
                SHAPE = WEST;
            break;
        }
        return SHAPE;
    }
}


