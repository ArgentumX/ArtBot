package com.shatun.autoartbot.controllers.interfaces;

import net.minecraft.world.phys.Vec3;

public interface IComplexProcessController {
    boolean isGoing();
    boolean isBuilding();
    boolean isFollowing();
    boolean isEating();
    boolean isClearingArtArea();
    void goTo(Vec3 position);
    void build(String schemName, Vec3 topLeftCorner);
    void clearArtSegment(Vec3 artTopLeftCorner, int xBreakingStage, int zBreakingStage);
    void collectItems(boolean enable);
    void allowBreak(boolean enable);
    void eat(boolean enable);
    void stop();
}
