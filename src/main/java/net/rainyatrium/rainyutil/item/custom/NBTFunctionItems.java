package net.rainyatrium.rainyutil.item.custom;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import org.apache.logging.log4j.core.jmx.Server;

import static net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.getServer;


/*
========= NOTES =========
*/
public class NBTFunctionItems {
    public static void registerUseItemCallback() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!world.isClient && hand == Hand.MAIN_HAND) {
                //player.sendMessage(new LiteralText("You right clicked!").formatted(Formatting.GOLD), false);
                ItemStack heldItem = player.getMainHandStack();
                NbtCompound tagcopy = heldItem.getOrCreateNbt().copy(); // Create a copy of the tag compound; checking directly breaks shit somehow (idk lol)
                String mcfunction = tagcopy.getString("mcfunction"); //Get the value of the tag
                if (mcfunction.length() > 0) {
                    player.sendMessage(new LiteralText("nbt I grabbed: " + mcfunction).formatted(Formatting.GOLD), false);
                    mcfunction = mcfunction.replaceAll("_"," ");
                    player.sendMessage(new LiteralText("nbt I fixed: " + mcfunction).formatted(Formatting.GOLD), false);
                    //Now run the command
                    MinecraftServer server = world.getServer();
                    String playerName = player.getName().getString();
                    server.getCommandManager().execute(server.getCommandSource(),"execute as " + playerName + " run " + mcfunction);
                    player.sendMessage(new LiteralText("execute as " + playerName + " run " + mcfunction).formatted(Formatting.GOLD), false);
                }
                // Player is right-clicking while sneaking
                // Do something here*/
            }
            return TypedActionResult.pass(ItemStack.EMPTY);
        });
    }
}
