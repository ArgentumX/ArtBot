package com.shatun.autoartbot.controllers.interfaces;

import net.minecraft.world.phys.Vec3;

public interface IInventoryController {
    boolean isInventoryEmpty();
    void openChest(Vec3 position);
    void closeChest();
    void putOneStackInOpenedChest();
    void putOneStackInOpenedChest(String itemId);
    String getAnyCarpetIdInInventory();
    boolean takeCarpets();
    boolean isInventoryFull();
    int getFreeContainerSpace();
    int getItemAmount(String itemId);
    boolean inventoryContains(String itemId);
}
