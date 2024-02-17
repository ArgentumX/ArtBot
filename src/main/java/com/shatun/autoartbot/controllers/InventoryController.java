package com.shatun.autoartbot.controllers;

import com.shatun.autoartbot.controllers.interfaces.IInventoryController;
import net.minecraft.world.phys.Vec3;

public class InventoryController implements IInventoryController {
    @Override
    public boolean isInventoryEmpty() {
        return false;
    }

    @Override
    public void openChest(Vec3 position) {

    }

    @Override
    public void putOneStackInOpenedChest() {

    }
}
