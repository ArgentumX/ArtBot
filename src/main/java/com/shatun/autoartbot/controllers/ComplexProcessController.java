package com.shatun.autoartbot.controllers;

import baritone.api.BaritoneAPI;
import com.shatun.autoartbot.controllers.interfaces.IComplexProcessController;
import com.shatun.autoartbot.utils.PlayerUtils;
import net.minecraft.world.phys.Vec3;

public class ComplexProcessController implements IComplexProcessController {
    @Override
    public boolean isGoing() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().isActive();
    }

    @Override
    public boolean isBuilding() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getBuilderProcess().isActive() || BaritoneAPI.getProvider().getPrimaryBaritone().getBuilderProcess().isPaused();
    }

    @Override
    public boolean isFollowing() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getFollowProcess().isActive();
    }

    @Override
    public boolean isClearingArtArea() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getBuilderProcess().isActive();
    }

    @Override
    public void goTo(Vec3 position) {
        PlayerUtils.chat(String.format("#goto %s %s %s", position.x, position.y, position.z));
    }
    @Override
    public void clearArtSegment(Vec3 artTopLeftCorner, int xBreakingStage, int zBreakingStage) {
        PlayerUtils.chat(String.format("#sel 1 %s %s %s", artTopLeftCorner.x + xBreakingStage * 16, artTopLeftCorner.y, artTopLeftCorner.z + 16 * zBreakingStage));
        PlayerUtils.chat(String.format("#sel 2 %s %s %s", artTopLeftCorner.x + xBreakingStage * 16 + 15, artTopLeftCorner.y, artTopLeftCorner.z + 16 * zBreakingStage + 15));
        PlayerUtils.chat("#sel cleararea");
    }

    @Override
    public void collectItems(boolean enable) {
        if (enable) {
            PlayerUtils.chat("#follow entity item");
        }
        else {
            PlayerUtils.chat("#follow entity ");
        }
    }

    @Override
    public void stop() {
        PlayerUtils.chat("#stop");
    }

    @Override
    public void allowBreak(boolean enable) {
        BaritoneAPI.getSettings().allowBreak.value = enable;
    }
}
