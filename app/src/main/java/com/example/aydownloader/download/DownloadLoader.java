package com.example.aydownloader.download;

import android.content.Context;

import com.example.aydownloader.callback.DownloadTaskListener;

public class DownloadLoader {

    private Context mContext;

    private DownloadLoader(Context context){
        mContext = context;
    }

    public DownloadLoader load(String url, String path, String name){
        return load(url, path, name, 1);
    }

    public DownloadLoader load(String url, String path, String name, DownloadTaskListener callback){
        return load(url, path, name, 1, callback);
    }

    public DownloadLoader load(String url, String path, String name, int threadCount){
        return load(url, path, name, threadCount, null);
    }

    public DownloadLoader load(String url, String path, String name, int threadCount,
                               DownloadTaskListener callback){
        DownloaderManager.getInstance(mContext).put(url, path, name, threadCount, callback);
        return this;
    }

    public DownloadLoader load(DownloadTaskInfo info){
        DownloaderManager.getInstance(mContext).put(info);
        return this;
    }

    public DownloaderManager build(){
        return DownloaderManager.getInstance(mContext);
    }
}
