package com.shatun.autoartbot.controllers;

import com.shatun.autoartbot.controllers.interfaces.IComplexProcessController;
import net.minecraft.world.phys.Vec3;

public class ComplexProcessController implements IComplexProcessController {
    @Override
    public boolean isGoing() {
        return false;
    }

    @Override
    public boolean isBuilding() {
        return false;
    }

    @Override
    public boolean isFollowing() {
        return false;
    }

    @Override
    public boolean isClearingArtArea() {
        return false;
    }

    @Override
    public void goTo(Vec3 position) {

    }
    @Override
    public void clearArtSegment(Vec3 artTopLeftCorner, int xBreakingStage, int zBreakingStage) {

    }

    @Override
    public void collectItems(boolean enable) {

    }

    @Override
    public void stop() {

    }
}
