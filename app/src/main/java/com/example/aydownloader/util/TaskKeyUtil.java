package com.example.aydownloader.util;

public class TaskKeyUtil {

    public static String getKey(String url, String path, String name){
        //TODO 待写使用MD5生成key
        return url + path + name;
    }
}
