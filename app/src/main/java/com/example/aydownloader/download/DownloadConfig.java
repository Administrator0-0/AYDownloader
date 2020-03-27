package com.example.aydownloader.download;

public class DownloadConfig {

    int corePoolSize;
    int maxPoolSize;
    int maxAliveTime;
    boolean isMultipleThread;


    public DownloadConfig setThreadPool(int corePoolSize, int maxPoolSize){
        if (corePoolSize < maxPoolSize && corePoolSize > 0) {
            this.corePoolSize = corePoolSize;
            this.maxPoolSize = maxPoolSize;
        }
        return this;
    }

    public DownloadConfig setThreadPool(int corePoolSize, int maxPoolSize, int maxAliveTime){
        if (corePoolSize < maxPoolSize && corePoolSize > 0 && maxAliveTime > 0) {
            this.corePoolSize = corePoolSize;
            this.maxPoolSize = maxPoolSize;
            this.maxAliveTime = maxAliveTime;
        }
        return this;
    }

    public DownloadConfig setMultipleThread(boolean multipleThread) {
        this.isMultipleThread = multipleThread;
        return this;
    }
}
