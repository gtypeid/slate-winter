package com.kosta.winter.def;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    public final static String resourcePath = "\\src\\main\\java\\com\\kosta\\winter\\res\\";

    public static void fileRead(StringBuilder strb, String path) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader(path);
            int ch;
            while ((ch = reader.read()) != -1) {
                strb.append((char) ch);
            }

        } catch (IOException e) {

        }
        finally {
            reader.close();
        }
    }

    public static String dirResPath(String source){
        return Util.dirResAppendPath("", source);
    }

    public static String dirResAppendPath(String append, String source, String extend){
        String extendSource = source + "." + extend;
        return Util.dirResAppendPath(append, extendSource);
    }

    public static String dirResAppendPath(String append, String source){
        Path currentPath = Paths.get("");
        String appendPath = resourcePath + append;
        String path = currentPath.toAbsolutePath().toString() + appendPath + source;
        return path;
    }

    public static JSONObject readJson(String path){
        try {
            StringBuilder strb = new StringBuilder();
            FileReader reader = new FileReader(path);
            int ch;
            while ((ch = reader.read()) != -1) {
                strb.append((char) ch);
            }
            return new JSONObject(strb.toString());
        }
        catch (Exception e) {
        }
        return null;
    }
}
