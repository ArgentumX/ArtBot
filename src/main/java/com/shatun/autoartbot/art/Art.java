package com.shatun.autoartbot.art;

import java.text.DecimalFormat;
import java.util.*;

public class Art {
    private final List<String> resources = new ArrayList<>();
    public Art(Map<CarpetType, Integer> rawResources){
        if (rawResources.size() != 16){
            throw new IllegalArgumentException("Wrong size of art resources amount map");
        }

        for (CarpetType carpetType : rawResources.keySet()){
            if (rawResources.get(carpetType) < 1){
                continue;
            }
            for (int i = 0; i < (rawResources.get(carpetType) / 64) + 1; i++){
                resources.add(carpetType.toString());
            }
        }
    }
    public String[] takeResourcePortion(){
        String[] result;
        if (resources.size() <= 35){
            result = new String[resources.size()];
            for (int i = 0; i < resources.size(); i++){
                result[i] = resources.get(i);
            }
            return result;
        }
        result = new String[35];
        for(int i = 0; i < 35; i++){
            result[i] = resources.get(resources.size()-1);
            resources.remove(result[i]);
        }
        return result;
    }


    public boolean allNeededResourcesTaken(){
        return resources.isEmpty();
    }
    public int getProgress(){
        double d = ((double) resources.size() / 256 * 100);
        return (int) d;
    }
}