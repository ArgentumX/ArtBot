package com.shatun.autoartbot.art;

public enum CarpetType {
    WHITE_CARPET("white_carpet"),
    LIGHT_GRAY_CARPET("light_gray_carpet"),
    GRAY_CARPET("gray_carpet"),
    BLACK_CARPET("black_carpet"),
    BROWN_CARPET("brown_carpet"),
    RED_CARPET("red_carpet"),
    ORANGE_CARPET("orange_carpet"),
    YELLOW_CARPET("yellow_carpet"),
    LIME_CARPET("lime_carpet"),
    GREEN_CARPET("green_carpet"),
    CYAN_CARPET("cyan_carpet"),
    LIGHT_BLUE_CARPET("light_blue_carpet"),
    BLUE_CARPET("blue_carpet"),
    PURPLE_CARPET("purple_carpet"),
    MAGENTA_CARPET("magenta_carpet"),
    PINK_CARPET("pink_carpet");

    CarpetType(String id) {
        if(id == null){
            throw new NullPointerException();
        }
        this.id = id.toLowerCase();
    }
    private final String id;
    @Override
    public String toString() {
        return id;
    }

    public static CarpetType getById(String id){
        for (CarpetType carpet : CarpetType.values()){
            if (carpet.id.equalsIgnoreCase(id)){
                return carpet;
            }
        }
        return null;
    }
}
