package com.example.aydownloader.download;

import com.example.aydownloader.callback.DownloadTaskListener;
import com.example.aydownloader.util.TaskKeyUtil;

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
        this.key = TaskKeyUtil.getKey(url, path, name);
    }

    String getKey() {
        return key;
    }

    DownloadTaskListener getCallback(){
        return callback;
    }
}
