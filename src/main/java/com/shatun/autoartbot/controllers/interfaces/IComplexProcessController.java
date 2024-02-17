package com.shatun.autoartbot.controllers.interfaces;

import net.minecraft.world.phys.Vec3;

public interface IComplexProcessController {
    boolean isGoing();
    boolean isBuilding();
    boolean isFollowing();
    boolean isClearingArtArea();
    void goTo(Vec3 position);
    void clearArtSegment(Vec3 artTopLeftCorner, int xBreakingStage, int zBreakingStage);
    void collectItems(boolean enable);
    void allowBreak(boolean enable);
    void stop();
}
