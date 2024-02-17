package com.shatun.autoartbot.controllers;

import com.shatun.autoartbot.controllers.interfaces.IInventoryController;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class InventoryController implements IInventoryController {
    @Override
    public boolean isInventoryEmpty() {
        Minecraft mc = Minecraft.getInstance();
        for (int i = 54; i < 89; i++){
            if (!mc.player.containerMenu.slots.get(i).getItem().isEmpty()) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void openChest(Vec3 position) {
        Minecraft.getInstance().player.lookAt(EntityAnchorArgument.Anchor.EYES, position.add(0.5, 0.5, 0.5));
        BlockHitResult hit = (BlockHitResult) Minecraft.getInstance().player.pick(20.0D, 0.0F, false);
        Minecraft.getInstance().getConnection().send(new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, hit, 0));
    }

    @Override
    public void putOneStackInOpenedChest() {
        Minecraft mc = Minecraft.getInstance();
        int menu_id = mc.player.containerMenu.containerId;
        for (int i = 54; i < 89; i++){
            if (!mc.player.containerMenu.slots.get(i).getItem().isEmpty()) {
                Minecraft.getInstance().gameMode.handleInventoryMouseClick(menu_id, i, 0, ClickType.QUICK_MOVE, Minecraft.getInstance().player);
                break;
            }
        }
    }
}
