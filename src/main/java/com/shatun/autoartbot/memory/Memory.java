package com.shatun.autoartbot.memory;

import net.minecraft.world.phys.Vec3;

public class Memory {
    private Vec3 dropChest;
    private Vec3 carpetChest;
    private Vec3 paintTopLeftCorner;
    private Vec3 dropTransferPosition;
    private String[] colorsOrder = {
            "white",
            "light_gray",
            "gray",
            "black",
            "purple",
            "blue",
            "cyan",
            "light_blue",
            "lime",
            "green",
            "brown",
            "red",
            "magenta",
            "pink",
            "orange",
            "yellow"};
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

    public Vec3 getLootChest(String carpetColor){
        for (int i = 0; i < colorsOrder.length; i++){
            if (colorsOrder[i].equalsIgnoreCase(carpetColor)){

                return carpetChest.add(i, 0, 0);
            }
        }
        return carpetChest;
    }
}
