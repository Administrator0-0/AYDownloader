package com.example.aydownloader.util;

import java.io.File;

public class TaskUtil {

    public static String getKey(String url, String path, String name){
        //TODO 待写使用MD5生成key
        return url + path + name;
    }

    public static float getProgress(int current, int total){
        return current > total ? 0 : ((int) (current * 10000.0 / total)) * 1.0f / 100;
    }

    public static boolean deleteFile(File file) {
        if (file != null && file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static void deleteFile(File... files) {
        for (File file : files) {
            deleteFile(file);
        }
    }

    public static void deleteFile(String path, String name) {
        deleteFile(new File(path, name));
    }
}
