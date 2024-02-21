package com.shatun.autoartbot.controllers;

import com.shatun.autoartbot.controllers.interfaces.IInventoryController;
import com.shatun.autoartbot.utils.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class InventoryController implements IInventoryController {
    @Override
    public boolean isInventoryEmpty() {
        Minecraft mc = Minecraft.getInstance();
        for (int i = 54; i < 89; i++){
            if (!mc.player.containerMenu.slots.get(i).getItem().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isInventoryFull(){
        return Minecraft.getInstance().player.getInventory().getFreeSlot() == -1;
    }
    @Override
    public void openChest(Vec3 position) {
        Minecraft.getInstance().player.lookAt(EntityAnchorArgument.Anchor.EYES, position.add(0.5, 0.5, 0.5));
        BlockHitResult hit = (BlockHitResult) Minecraft.getInstance().player.pick(20.0D, 0.0F, false);
        Minecraft.getInstance().getConnection().send(new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, hit, 0));
    }

    @Override
    public void closeChest(){
        Minecraft.getInstance().player.closeContainer();
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

    @Override
    public String getAnyCarpetIdInInventory() {
        for (ItemStack item : Minecraft.getInstance().player.inventoryMenu.getItems()){
            String name = ForgeRegistries.ITEMS.getKey(item.getItem()).getPath();
            if (name.contains("_carpet")){
                return name;
            }
        }
        return null;
    }

    // Returns true if bot can take more items and false if inventory is full.
    @Override
    public boolean takeCarpets() {
        Minecraft mc = Minecraft.getInstance();
        int menu_id = mc.player.containerMenu.containerId;
        for (int i = 0; i < 54; i++){
            if (ForgeRegistries.ITEMS.getKey(mc.player.containerMenu.getSlot(i).getItem().getItem()).getPath().contains("_carpet")){
                Minecraft.getInstance().gameMode.handleInventoryMouseClick(menu_id, i, 0, ClickType.QUICK_MOVE, Minecraft.getInstance().player);
            }
            if (i != 53 && isInventoryFull())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public void putOneStackInOpenedChest(String itemId) {
        Minecraft mc = Minecraft.getInstance();
        int menu_id = mc.player.containerMenu.containerId;
        for (int i = 54; i < 89; i++){
            if (ForgeRegistries.ITEMS.getKey(mc.player.containerMenu.getSlot(i).getItem().getItem()).getPath().equalsIgnoreCase(itemId)){
                Minecraft.getInstance().gameMode.handleInventoryMouseClick(menu_id, i, 0, ClickType.QUICK_MOVE, Minecraft.getInstance().player);
                break;
            }
        }
    }

    @Override
    public int getFreeContainerSpace() {
        int result = 0;
        Minecraft mc = Minecraft.getInstance();
        for (int i = 0; i < 54; i++){
            if (mc.player.containerMenu.getSlot(i).getItem().isEmpty()){
                result++;
            }
        }
        return result;
    }

    @Override
    public int getItemAmount(String itemId) {
        int result = 0;
        Minecraft mc = Minecraft.getInstance();
        for (int i = 54; i < 90; i++){
            if (ForgeRegistries.ITEMS.getKey(mc.player.containerMenu.getSlot(i).getItem().getItem()).getPath().equalsIgnoreCase(itemId)){
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean inventoryContains(String itemId) {
        return getItemAmount(itemId) > 0;
    }

    @Override
    public void takeOneItemStackFromContainer() {
        Minecraft mc = Minecraft.getInstance();
        int menu_id = mc.player.containerMenu.containerId;
        for (int i = 0; i < 54; i++){
            if (mc.player.containerMenu.slots.get(i).getItem().getCount() == 64){
                Minecraft.getInstance().gameMode.handleInventoryMouseClick(menu_id, i, 0, ClickType.QUICK_MOVE, Minecraft.getInstance().player);
                break;
            }
        }
    }
}
