package net.rainyatrium.otherlands.block.custom.base;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.rainyatrium.otherlands.block.entity.ItemDisplayBlockEnt;
import net.rainyatrium.otherlands.init.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ItemDisplayBlock extends Block implements BlockEntityProvider {
    //Block properties: directional variants and whether it has an item
    public static final IntProperty VARIANT = IntProperty.of("variant", 0, 5);
    public static final BooleanProperty HAS_ITEM = BooleanProperty.of("has_item");
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, VARIANT);
        builder.add(new Property[]{HAS_ITEM});
    }
    //Default settings & render type
    public ItemDisplayBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(VARIANT,0).with(HAS_ITEM, false));
    }
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    //Create the anvil block entity
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ItemDisplayBlockEnt(ModBlocks.HOTLINE_STAND_ENT, pos, state);
    }
    //Check if it has an item on placement in world
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        NbtCompound nbtCompound = BlockItem.getBlockEntityNbt(itemStack);
        if (nbtCompound != null) {
            world.setBlockState(pos, (BlockState)state.with(HAS_ITEM, true), 2);
        }

    }

    //ADDING AND REMOVING ITEMS
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);
        ItemDisplayBlockEnt itemDisplayBlockEnt = (ItemDisplayBlockEnt)blockEntity;
        ItemStack itemStack = itemDisplayBlockEnt.getItem();
        ItemStack heldItem = player.getMainHandStack();
        //if the player has something in their hand
        if (!player.getStackInHand(hand).isEmpty()) {
            //and there is nothing in the block
            if (itemStack.isEmpty()) {
                //Take one item from the player's held item stack and insert it in the block
                ItemStack takenItem = heldItem.split(1);
                ((ItemDisplayBlockEnt) blockEntity).setItem(takenItem.copy());
                world.setBlockState(blockPos, blockState.with(HAS_ITEM, true));
            } else if (itemStack.isItemEqual(heldItem) && heldItem.isStackable()){
                // If their hand matches the item inside, stack it with the item
                givePlayerItem(player, itemDisplayBlockEnt, world, blockPos, blockState);
            }
        }
        //if the player has nothing in their hand
        else {
            //and there's something in the block
            if (!itemStack.isEmpty()) {
                // Give the player the stack in the inventory
                givePlayerHand(player, itemDisplayBlockEnt, world, blockPos, blockState);

            }
        }
        blockEntity.markDirty();
        //player.sendMessage(new LiteralText("Item: " + itemStack.toString()), false);
        return ActionResult.SUCCESS;
    }

    //Cute remove item functionality (pops out of the block)
    private void removeItem(World world, BlockPos pos) {
        if (!world.isClient) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ItemDisplayBlockEnt) {
                ItemDisplayBlockEnt itemDisplayBlockEnt = (ItemDisplayBlockEnt)blockEntity;
                ItemStack itemStack = itemDisplayBlockEnt.getItem();
                if (!itemStack.isEmpty()) {
                    world.syncWorldEvent(1010, pos, 0);
                    itemDisplayBlockEnt.clear();
                    float f = 0.7F;
                    double d = (double)(world.random.nextFloat() * 0.7F) + 0.15000000596046448;
                    double e = (double)(world.random.nextFloat() * 0.7F) + 0.06000000238418579 + 0.6;
                    double g = (double)(world.random.nextFloat() * 0.7F) + 0.15000000596046448;
                    ItemStack itemStack2 = itemStack.copy();
                    ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + g, itemStack2);
                    itemEntity.setToDefaultPickupDelay();
                    world.spawnEntity(itemEntity);
                }
            }
        }
    }

    //If moved or destroyed, spawn the item or pop it off the block
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            this.removeItem(world, pos);
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    //Give player item method (specific to this use case)
    private void givePlayerItem(PlayerEntity player, ItemDisplayBlockEnt itemDisplayBlockEnt, World world, BlockPos blockPos, BlockState blockState) {
        ItemStack itemStack = itemDisplayBlockEnt.getItem();
        player.getInventory().offerOrDrop(itemStack);
        itemDisplayBlockEnt.clear();
        world.setBlockState(blockPos, blockState.with(HAS_ITEM, false));
    }

    //Give player hand item method (specific to this use case)
    private void givePlayerHand(PlayerEntity player, ItemDisplayBlockEnt itemDisplayBlockEnt, World world, BlockPos blockPos, BlockState blockState) {
        ItemStack itemStack = itemDisplayBlockEnt.getItem();
        player.setStackInHand(Hand.MAIN_HAND,itemStack);
        itemDisplayBlockEnt.clear();
        world.setBlockState(blockPos, blockState.with(HAS_ITEM, false));
    }
    //Placement state/direction
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }


}
