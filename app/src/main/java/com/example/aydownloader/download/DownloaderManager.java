package com.example.aydownloader.download;


import android.content.Context;

import com.example.aydownloader.callback.DownloadListener;
import com.example.aydownloader.callback.DownloadTaskListener;
import com.example.aydownloader.util.TaskUtil;
import java.util.HashMap;

class DownloaderManager {
    private static DownloaderManager instance = null;
    private Context mContext;
    private HashMap<String, DownloadTaskInfo> infoMap = new HashMap<>();
    private HashMap<String, DownloadTaskHandler> handlerMap = new HashMap<>();
    private DownloadConfig mConfig;
    private DownloadListener mListener;

    private DownloaderManager(Context context){
        this.mContext = context;
    }

    static DownloaderManager getInstance(Context context) {
        if (instance == null){
            synchronized (DownloaderManager.class) {
                if (instance == null)
                    instance = new DownloaderManager(context.getApplicationContext());
            }
        }
        return instance;
    }

    void put(String url, String path, String name, int threadCount, DownloadTaskListener callback){
        DownloadTaskInfo info = new DownloadTaskInfo(url, path, name, threadCount);
        infoMap.put(info.getKey(), info);
    }

    void put(DownloadTaskInfo info){
        infoMap.put(info.getKey(), info);
    }

    private DownloaderManager setConfig(DownloadConfig config){
        mConfig = config;
        initThreadPool();
        return instance;
    }

    private void initThreadPool() {
        if (mConfig == null) mConfig = new DownloadConfig();
        ThreadPool.getInstance().setCORE_POOL_SIZE(mConfig.corePoolSize);
        ThreadPool.getInstance().setMAX_POOL_SIZE(mConfig.maxPoolSize);
        ThreadPool.getInstance().setMAX_ALIVE_TIME(mConfig.maxAliveTime);
    }

    public void startAll(){
        //TODO 全部开始
    }

    public void start(DownloadTaskInfo info) throws Exception {
        start(info.getKey());
    }

    public void start(String url, String path, String name) throws Exception {
        String key = TaskUtil.getKey(url, path, name);
        start(key);
    }

    private void  start(String key) throws Exception {
        mListener.onStart();
        if (!infoMap.containsKey(key)) {
            if (mListener != null){
                mListener.onError();
                return;
            }else {
                throw new Exception("Download task is not exist");
            }
        }
        execute(infoMap.get(key));
    }

    public void pauseAll(){
        //TODO 暂停全部
    }

    public void pause(DownloadTaskInfo info) throws Exception {
        pause(info.getKey());
    }

    public void pause(String url, String path, String name) throws Exception {
        String key = TaskUtil.getKey(url, path, name);
        pause(key);
    }

    private void pause(String key) throws Exception {
        mListener.onStart();
        if (!handlerMap.containsKey(key)) {
            if (mListener != null){
                mListener.onError();
                return;
            }else {
                throw new Exception("Download task is not exist");
            }
        }
        handlerMap.get(key).pause();
    }


    public DownloaderManager registerListener(DownloadListener listener){
        mListener = listener;
        return instance;
    }



    private synchronized void execute(DownloadTaskInfo info){
        ThreadPool.getInstance().initExecutor();
        DownloadTaskHandler handler = new DownloadTaskHandler(mContext, info);
        DownloadTask task = new DownloadTask(mContext, info, handler.getHandler());
        handler.setTask(task);
        handlerMap.put(info.getKey(), handler);
        ThreadPool.getInstance().getExecutor().execute(task);
//        if (ThreadPool.getInstance().getExecutor().getActiveCount() ==
//                ThreadPool.getInstance().getExecutor().getCorePoolSize()){
//            info.getCallback().onWait();
//        }
    }
}
