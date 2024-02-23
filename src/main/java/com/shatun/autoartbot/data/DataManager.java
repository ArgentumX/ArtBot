package com.shatun.autoartbot.data;

import com.shatun.autoartbot.utils.PlayerUtils;

import java.io.File;
import java.io.IOException;

public class DataManager {
    public static void initialize(){
        createDirectories();
    }
    public static File getFile(String name){
        File file = new File("ArtBot/" + name);
        if (!file.exists()){
            throw new IllegalArgumentException("There is no file with same name");
        }
        return file;
    }
    public static void createDirectories(){
        File myObj = new File("ArtBot/");
        myObj.mkdirs();
    }
}
