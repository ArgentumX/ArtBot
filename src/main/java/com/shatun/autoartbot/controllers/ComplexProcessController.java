package com.shatun.autoartbot.controllers;

import baritone.api.BaritoneAPI;
import com.mojang.blaze3d.platform.InputConstants;
import com.shatun.autoartbot.controllers.interfaces.IComplexProcessController;
import com.shatun.autoartbot.utils.PlayerUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ComplexProcessController implements IComplexProcessController {

    private boolean isEatButtonPressed = false;
    @Override
    public boolean isGoing() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getCustomGoalProcess().isActive();
    }

    @Override
    public boolean isBuilding() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getBuilderProcess().isActive() && !BaritoneAPI.getProvider().getPrimaryBaritone().getBuilderProcess().isPaused();
    }

    @Override
    public boolean isFollowing() {
        return BaritoneAPI.getProvider().getPrimaryBaritone().getFollowProcess().isActive();
    }

    @Override
    public boolean isEating() {
        return isEatButtonPressed;
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
            stop();
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

    @Override
    public void eat(boolean enable) {
        isEatButtonPressed = enable;
        KeyMapping.click(InputConstants.Type.KEYSYM.getOrCreate(InputConstants.KEY_9));
        KeyMapping.set(InputConstants.Type.MOUSE.getOrCreate(1), enable);
    }

    @Override
    public void build(String schemName, Vec3 topLeftCorner) {
        fixBaritoneShitBuildingCode(getNoIgnoreList());
        PlayerUtils.chat(String.format("#build %s %s %s %s", schemName, topLeftCorner.x, topLeftCorner.y-2, topLeftCorner.z-1));
    }

    private void fixBaritoneShitBuildingCode(List<String> noDelete){

        List<String> toDeleteRaw = new ArrayList<>();

        toDeleteRaw.addAll(List.of(
                "white_carpet",
                "light_gray_carpet",
                "gray_carpet",
                "black_carpet",
                "magenta_carpet",
                "blue_carpet",
                "cyan_carpet",
                "light_blue_carpet",
                "lime_carpet",
                "green_carpet",
                "brown_carpet",
                "red_carpet",
                "purple_carpet",
                "pink_carpet",
                "orange_carpet",
                "yellow_carpet"
        ));
        for (String block : noDelete){
            if (toDeleteRaw.contains(block)){
                toDeleteRaw.remove(block);
            }
        }
        List<Block> toDeleteFinal = new ArrayList<>();
        for(String block : toDeleteRaw){
            toDeleteFinal.add(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("minecraft", block)));
        }
        BaritoneAPI.getSettings().buildSkipBlocks.value = toDeleteFinal;
    }

    private List<String> getNoIgnoreList(){

        List<String> result = new ArrayList<>();
        for (ItemStack item : Minecraft.getInstance().player.inventoryMenu.getItems()){
            String name = ForgeRegistries.ITEMS.getKey(item.getItem()).getPath();
            if (name.contains("_carpet") && !result.contains(name)){
                result.add(name);
            }
        }

        return result;
    }
}
