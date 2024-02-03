package com.shatun.autoartbot;

import com.shatun.autoartbot.tasks.TickTimer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Listener {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void OnClientSendMessage(ClientChatEvent event) {

    }
    @SubscribeEvent
    public static void OnTick(TickEvent.ClientTickEvent event){
        TickTimer.handleTick();
    }

    @SubscribeEvent
    public static void OnDisconnect(PlayerEvent.PlayerLoggedOutEvent event){

    }
}
