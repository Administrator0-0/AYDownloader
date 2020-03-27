package com.example.aydownloader.download;

import android.content.Context;
import android.os.Handler;


public class DownloadTask implements Runnable{

    private Context mContext;
    private DownloadTaskInfo mInfo;
    private Handler mHandler;

    DownloadTask(Context context, DownloadTaskInfo info, Handler handler){
        this.mContext = context;
        this.mInfo = info;
        this.mHandler = handler;
    }

    @Override
    public void run() {
        //TODO 网络逻辑
    }
}
