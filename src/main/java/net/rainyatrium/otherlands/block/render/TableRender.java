package net.rainyatrium.otherlands.block.render;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.rainyatrium.otherlands.block.entity.TableEnt;

public class TableRender<T extends TableEnt> extends ItemDisplayBlockRender<T> {
    public TableRender(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }
}
