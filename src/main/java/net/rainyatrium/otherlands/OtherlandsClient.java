package net.rainyatrium.otherlands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.render.RenderLayer;
import net.rainyatrium.otherlands.block.render.HotlineStandRender;
import net.rainyatrium.otherlands.block.render.ItemDisplayBlockRender;
import net.rainyatrium.otherlands.block.render.TableRender;
import net.rainyatrium.otherlands.render.SkyboxObjects;

import static net.rainyatrium.otherlands.init.ModBlocks.*;

@Environment(EnvType.CLIENT)
public class OtherlandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        //Renderers
        BlockEntityRendererRegistry.register(HOTLINE_STAND_ENT, HotlineStandRender::new);
        BlockEntityRendererRegistry.register(TABLE_ENT, TableRender::new);
        //Block transparency and cutouts
        BlockRenderLayerMap.INSTANCE.putBlock(SHEEPLE1, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SHEEPLE2, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SHEEPLE3, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SHEEPLE4, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SHEEPLE5, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SHEEPLE_MERCHANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(POBER_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HOTLINE_STAND, RenderLayer.getCutout());
        //Event listeners
        WorldRenderEvents.END.register(SkyboxObjects::onRender);
    }
}
