package com.shatun.autoartbot;

import com.mojang.logging.LogUtils;
import com.shatun.autoartbot.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AutoArtBot.MODID)
public class AutoArtBot
{
    public static final String MODID = "autoartbot";
    private static final Logger LOGGER = LogUtils.getLogger();
    private Bot bot;

    public AutoArtBot()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        MinecraftForge.EVENT_BUS.register(ClientListener.class);
        bot = Bot.getInstance();
    }
    public static class ClientListener {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void OnClientSendMessage(ClientChatEvent event) {
            if (event.getMessage().startsWith("*")){
                String[] args = event.getMessage().replace("*", "").split(" ");
                switch (args[0]) {
                    case "pos":
                        Player p = Minecraft.getInstance().player;
                        assert p != null;
                        PlayerUtils.send(p.position().toString());
                        break;
                    case "clear":
                        Bot.getInstance().clearArtArea();
                        break;
                    case "transfer":
                        Bot.getInstance().transferItems();
                        break;
                    default:
                        PlayerUtils.send("Unregistered command");
                        break;
                }
            }
        }
        @SubscribeEvent
        public static void OnTick(TickEvent.ClientTickEvent event){
            if (Minecraft.getInstance().player == null){
                return;
            }
            if (!Bot.getInstance().isActive()){
                return;
            }
            Bot.getInstance().handleTick();
        }

        @SubscribeEvent
        public static void OnDisconnect(PlayerEvent.PlayerLoggedOutEvent event){

        }
    }
}
