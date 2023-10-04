package net.rainyatrium.otherlands.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class ItemDisplayBlockEnt extends BlockEntity implements Clearable {
    //BlockEntity inventory.
    private ItemStack item;

    public ItemDisplayBlockEnt(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.item = ItemStack.EMPTY;
    }

    //read/write nbt tools
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("Item", 10)) {
            this.setItem(ItemStack.fromNbt(nbt.getCompound("Item")));
        }

    }
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.getItem().isEmpty()) {
            nbt.put("Item", this.getItem().writeNbt(new NbtCompound()));
        }
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setItem(ItemStack stack) {
        this.item = stack;
        this.markDirty();
    }

    public void clear() {
        this.setItem(ItemStack.EMPTY);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

}
