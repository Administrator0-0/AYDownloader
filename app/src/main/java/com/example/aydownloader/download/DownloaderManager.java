package com.example.aydownloader.download;

import java.util.HashMap;

class DownloaderManager {
    private static DownloaderManager instance = null;
    private HashMap<String, DownloadTaskInfo> infoMap = new HashMap<>();

    static DownloaderManager getInstance() {
        if (instance == null){
            instance = new DownloaderManager();
        }
        return instance;
    }

    boolean put(String url, String path, String name, int threadCount){
        DownloadTaskInfo info = new DownloadTaskInfo(url, path, name, threadCount);
        infoMap.put(info.getKey(), info);
        return true;
    }

    private void init(){

    }

    public DownloaderManager setThreadPool(int corePoolSize, int maxPoolSize){
        if (corePoolSize < maxPoolSize) {
            ThreadPool.getInstance().setCORE_POOL_SIZE(corePoolSize);
            ThreadPool.getInstance().setMAX_POOL_SIZE(maxPoolSize);
        }
        return instance;
    }

    public DownloaderManager setThreadPool(int corePoolSize, int maxPoolSize, int maxAliveTime){
        if (corePoolSize < maxPoolSize) {
            ThreadPool.getInstance().setCORE_POOL_SIZE(corePoolSize);
            ThreadPool.getInstance().setMAX_POOL_SIZE(maxPoolSize);
            ThreadPool.getInstance().setMAX_ALIVE_TIME(maxAliveTime);
        }
        return instance;
    }

    public DownloaderManager start(){

        return instance;
    }

    public DownloaderManager registerListener(){
        return instance;
    }
}
