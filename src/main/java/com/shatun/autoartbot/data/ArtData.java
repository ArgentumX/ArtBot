package com.shatun.autoartbot.data;

import baritone.utils.schematic.schematica.SchematicaHelper;
import com.shatun.autoartbot.art.CarpetType;
import com.shatun.autoartbot.utils.PlayerUtils;
import org.apache.commons.io.IOUtils;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArtData {
    public static Map<CarpetType, Integer> getArtResources(String schemName){

        File file = DataManager.getFile(schemName);
        Map<CarpetType, Integer> result = new HashMap<>();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            String everything = IOUtils.toString(inputStream).strip();
            for (String s : everything.split("\r\n")){
                String[] splitted = s.replaceAll("[^a-zA-Z0-9_-]", " ").split(" ");
                CarpetType type = CarpetType.getById(splitted[0]);
                if (type == null){
                    continue;
                }
                result.put(type, Integer.valueOf(splitted[splitted.length-1]));
            }
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (CarpetType type : CarpetType.values()){
            if (!result.containsKey(type)){
                result.put(type, 0);
            }
        }
        return result;
    }
}
