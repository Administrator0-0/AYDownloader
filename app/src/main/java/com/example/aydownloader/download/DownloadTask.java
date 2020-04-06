package com.example.aydownloader.download;

import android.content.Context;
import android.os.Handler;

import static com.example.aydownloader.download.DownloadStatus.CANCEL;
import static com.example.aydownloader.download.DownloadStatus.PAUSE;
import static com.example.aydownloader.download.DownloadStatus.RESTART;
import static com.example.aydownloader.download.DownloadStatus.RESUME;


public class DownloadTask implements Runnable{

    private Context mContext;
    private DownloadTaskInfo mInfo;
    private Handler mHandler;
    private int mStatus;

    DownloadTask(Context context, DownloadTaskInfo info, Handler handler){
        this.mContext = context;
        this.mInfo = info;
        this.mHandler = handler;
    }

    @Override
    public void run() {
        //TODO 网络逻辑
    }

    public String getUrl() {
        return mInfo.getUrl();
    }

    public String getPath(){
        return mInfo.getPath();
    }

    public String getName(){
        return mInfo.getName();
    }

    void pause(){
        mStatus = PAUSE;
    }

    void cancel(){
        mStatus = CANCEL;
    }

    void resume(){
        mStatus = RESUME;
    }

    void restart(){
        mStatus = RESTART;
    }
}
