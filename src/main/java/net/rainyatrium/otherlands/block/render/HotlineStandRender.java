package net.rainyatrium.otherlands.block.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.rainyatrium.otherlands.block.custom.base.ItemDisplayBlock;
import net.rainyatrium.otherlands.block.entity.HotlineStandEnt;


public class HotlineStandRender<T extends HotlineStandEnt> extends HotlineDisplayBlockRender<T> {
    public HotlineStandRender(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

}
