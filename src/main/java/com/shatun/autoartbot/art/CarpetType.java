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

    CarpetType(String stringValue) {
        if(stringValue == null){
            throw new NullPointerException();
        }
        this.stringValue = stringValue.toLowerCase();
    }
    private final String stringValue;
    @Override
    public String toString() {
        return stringValue;
    }
}
