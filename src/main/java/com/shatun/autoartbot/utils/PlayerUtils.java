package com.shatun.autoartbot.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class PlayerUtils {
    public static void chat(String message){
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.connection.sendChat(message);
    }
    public static void send(String message){
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.sendSystemMessage(Component.translatable(message));
    }
}
