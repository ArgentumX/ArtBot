package com.shatun.autoartbot.controllers.interfaces;

import net.minecraft.world.phys.Vec3;

public interface IInventoryController {
    boolean isInventoryEmpty();
    void openChest(Vec3 position);
    void putOneStackInOpenedChest();
}
