package com.example.aydownloader.download;

class DownloadTaskInfo {

    private String url;
    private String path;
    private String name;
    private String key;
    private int threadCount;

    DownloadTaskInfo(String url, String path, String name, int threadCount){
        this.url = url;
        this.path = path;
        this.name = name;
        this.threadCount = threadCount;
    }

    String getKey() {
        return key;
    }
}
