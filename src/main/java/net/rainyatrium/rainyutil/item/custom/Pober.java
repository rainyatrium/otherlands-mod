package net.rainyatrium.rainyutil.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rainyatrium.rainyutil.RainyUtil;
import java.util.Random;

public class Pober extends Item {
    public Pober(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            Random rand = new Random();
            BlockPos userpos = user.getBlockPos();
            float volume = 1.0F;
            float pitch = 1.0f;
            if (rand.nextInt(101) == 0) {
                world.playSound(null, userpos, RainyUtil.bonersecret, SoundCategory.MASTER, volume, pitch);
                user.sendMessage(new LiteralText("PENIS!").formatted(Formatting.GOLD), false);
            } else {
                world.playSound(null, userpos, RainyUtil.boner, SoundCategory.MASTER, volume, pitch);
                user.sendMessage(new LiteralText("Boner.").formatted(Formatting.WHITE), false);
            }
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}
