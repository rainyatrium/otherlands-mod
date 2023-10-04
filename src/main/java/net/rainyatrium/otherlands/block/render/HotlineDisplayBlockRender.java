package net.rainyatrium.otherlands.block.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.rainyatrium.otherlands.Otherlands;
import net.rainyatrium.otherlands.block.custom.base.ItemDisplayBlock;
import net.rainyatrium.otherlands.block.entity.ItemDisplayBlockEnt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class HotlineDisplayBlockRender<T extends ItemDisplayBlockEnt> implements BlockEntityRenderer<T> {
    //public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public HotlineDisplayBlockRender(BlockEntityRendererFactory.Context ctx) {
    }

    @Override
    public void render(T blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
    //Farmer's Delight cutting board saving my ass!
        ItemStack itemStack = blockEntity.getItem();
        int intPos = (int)blockEntity.getPos().asLong();
        Direction direction = blockEntity.getCachedState().get(ItemDisplayBlock.FACING).getOpposite();
        if (!itemStack.isEmpty()) {
            matrices.push();
            ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
            boolean blockItem = itemRenderer.getModel(itemStack, blockEntity.getWorld(), null, intPos).hasDepth();

            if (blockItem) {
                renderBlock(matrices, direction);
            } else {
                renderItemLayingDown(matrices, direction);
            }

            int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
            //MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, lightAbove, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 0);
            MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.HEAD, light, overlay, matrices, vertexConsumers, intPos);
            matrices.pop();

        }
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(Otherlands.MOD_ID);

    protected void renderItemLayingDown(MatrixStack matrixStackIn, Direction direction) {
        // Center item above the anvil
        matrixStackIn.translate(0d, 1.2d, 0d);

        // Rotate item to face the anvil's front side
        float f = -direction.asRotation();
        Direction d = direction;
        matrixStackIn.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(f));
        switch (d) {
            case NORTH:
                matrixStackIn.translate(-.5d, 0d, -0.3d);
                break;
            case EAST:
                matrixStackIn.translate(-0.5d, 0d, 0.7d);
                break;
            case SOUTH:
                matrixStackIn.translate(0.5d, 0d, 0.7d);
                break;
            case WEST:
                matrixStackIn.translate(0.5d, 0d, -0.3d);
                break;
        }

        // Rotate item flat on the anvil. Use X and Y from now on
        matrixStackIn.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.f));

        // Resize the item
        matrixStackIn.scale(.6f, .6f, .6f);
    }

    protected void renderBlock(MatrixStack matrixStackIn, Direction direction) {
        // Center item above the anvil
        matrixStackIn.translate(0d, 0.5d, 0d);

        // Rotate block to face the anvil's front side
        float f = -direction.asRotation();
        matrixStackIn.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(f));
        Direction d = direction;
        switch (d) {
            case NORTH:
                matrixStackIn.translate(-.5d, 0d, -0.3d);
                break;
            case EAST:
                matrixStackIn.translate(-0.5d, 0d, 0.7d);
                break;
            case SOUTH:
                matrixStackIn.translate(0.5d, 0d, 0.7d);
                break;
            case WEST:
                matrixStackIn.translate(0.5d, 0d, -0.3d);
                break;
        }


        // Resize the block
        matrixStackIn.scale(.63f, .63f, .63f);
    }
}
