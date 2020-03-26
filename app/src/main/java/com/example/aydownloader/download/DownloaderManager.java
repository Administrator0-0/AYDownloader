package com.example.aydownloader.download;

import java.util.HashMap;

class DownloaderManager {
    private static DownloaderManager instance = null;
    private HashMap<String, DownloadTaskInfo> infoMap = new HashMap<>();
    private DownloadConfig mConfig;

    private DownloaderManager(){

    }

    static DownloaderManager getInstance() {
        if (instance == null){
            synchronized (DownloaderManager.class) {
                if (instance == null)
                    instance = new DownloaderManager();
            }
        }
        return instance;
    }

    void put(String url, String path, String name, int threadCount){
        DownloadTaskInfo info = new DownloadTaskInfo(url, path, name, threadCount);
        getInstance().infoMap.put(info.getKey(), info);
    }

    private DownloaderManager setConfig(DownloadConfig config){
        getInstance().mConfig = config;
        init();
        return instance;
    }

    private void init() {
        ThreadPool.getInstance().setCORE_POOL_SIZE(getInstance().mConfig.corePoolSize);
        ThreadPool.getInstance().setMAX_POOL_SIZE(getInstance().mConfig.maxPoolSize);
        ThreadPool.getInstance().setMAX_ALIVE_TIME(getInstance().mConfig.maxAliveTime);
    }



    public DownloaderManager start(){

        return instance;
    }

    public DownloaderManager registerListener(){
        return instance;
    }
}
