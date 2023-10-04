package net.rainyatrium.otherlands.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;

public class HotlineStandEnt extends ItemDisplayBlockEnt implements Clearable {
    public HotlineStandEnt(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}


