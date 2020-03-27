package com.example.aydownloader.download;

import android.content.Context;
import android.os.Handler;

class DownloadTaskHandler {
    private Context mContext;
    private DownloadTaskInfo mInfo;
    private Handler mHandler;

    DownloadTaskHandler(Context context, DownloadTaskInfo info){
        this.mContext = context;
        this.mInfo = info;
    }

    Handler getHandler() {
        return mHandler;
    }

    //TODO Handler 消息处理逻辑
}
