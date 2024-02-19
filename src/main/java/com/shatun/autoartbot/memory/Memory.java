package com.shatun.autoartbot.memory;

import net.minecraft.world.phys.Vec3;

public class Memory {
    private Vec3 dropChest;
    private Vec3 carpetChest;
    private Vec3 paintTopLeftCorner;
    private Vec3 dropTransferPosition;
    private String[] colorsOrder = {
            "white_carpet",
            "light_gray_carpet",
            "gray_carpet",
            "black_carpet",
            "purple_carpet",
            "blue_carpet",
            "cyan_carpet",
            "light_blue_carpet",
            "lime_carpet",
            "green_carpet",
            "brown_carpet",
            "red_carpet",
            "magenta_carpet",
            "pink_carpet",
            "orange_carpet",
            "yellow_carpet"};
    public Memory(){
        dropChest = new Vec3(868, 70, -330);
        paintTopLeftCorner = new Vec3(832, 63, -320);
        carpetChest = new Vec3(856, 67, -397);
        dropTransferPosition = new Vec3(867, 68 , -326);
    }
    public Vec3 getDropChest() {
        return dropChest;
    }

    public Vec3 getPaintTopLeftCorner() {
        return paintTopLeftCorner;
    }

    public Vec3 getCarpetChest() {
        return carpetChest;
    }

    public Vec3 getDropTransferPosition() {
        return dropTransferPosition;
    }

    public String[] getColorsOrder() {
        return colorsOrder;
    }

    public Vec3 getLootChest(String carpetId){
        for (int i = 0; i < colorsOrder.length; i++){
            if (colorsOrder[i].equalsIgnoreCase(carpetId)){

                return carpetChest.add(i, 0, 0);
            }
        }
        return carpetChest;
    }
}
