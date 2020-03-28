package com.example.aydownloader.download;

import com.example.aydownloader.callback.DownloadTaskListener;
import com.example.aydownloader.util.TaskUtil;

class DownloadTaskInfo {

    private String url;
    private String path;
    private String name;
    private String key;
    private int threadCount;
    private DownloadTaskListener callback;

    DownloadTaskInfo(String url, String path, String name){
        this(url, path, name, 1);
    }
    DownloadTaskInfo(String url, String path, String name, int threadCount){
        this(url, path, name, threadCount, null);
    }
    DownloadTaskInfo(String url, String path, String name, int threadCount,
                     DownloadTaskListener callback){
        this.url = url;
        this.path = path;
        this.name = name;
        this.threadCount = threadCount;
        this.callback = callback;
        this.key = TaskUtil.getKey(url, path, name);
    }

    String getKey() {
        return key;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getThreadCount() {
        return threadCount;
    }

    DownloadTaskListener getCallback(){
        return callback;
    }
}
