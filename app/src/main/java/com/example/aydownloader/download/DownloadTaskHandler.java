package com.example.aydownloader.download;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.aydownloader.callback.DownloadTaskListener;
import com.example.aydownloader.util.TaskUtil;

import static com.example.aydownloader.download.DownloadStatus.CANCEL;
import static com.example.aydownloader.download.DownloadStatus.DESTROY;
import static com.example.aydownloader.download.DownloadStatus.DOWNLOADING;
import static com.example.aydownloader.download.DownloadStatus.ERROR;
import static com.example.aydownloader.download.DownloadStatus.FINISH;
import static com.example.aydownloader.download.DownloadStatus.NONE;
import static com.example.aydownloader.download.DownloadStatus.PAUSE;
import static com.example.aydownloader.download.DownloadStatus.RESTART;
import static com.example.aydownloader.download.DownloadStatus.RESUME;
import static com.example.aydownloader.download.DownloadStatus.START;
import static com.example.aydownloader.download.DownloadStatus.WAIT;

class DownloadTaskHandler {
    private Context mContext;
    private DownloadTask task;
    private TaskHandler mHandler;
    private String url;
    private String path;
    private String name;
    private int threadCount;
    private DownloadTaskListener callback;
    private int mStatus = NONE;
    private int currentLength;
    private int totalLength;
    private float progress;
    private long lastProgressUpdateTime;

    DownloadTaskHandler(Context context, DownloadTaskInfo info){
        this.mContext = context;
        this.url = info.getUrl();
        this.path = info.getPath();
        this.name = info.getName();
        this.threadCount = info.getThreadCount();
        this.callback = info.getCallback();
        mHandler = new TaskHandler();
    }

    void setTask(DownloadTask task) {
        this.task = task;
    }

    Handler getHandler() {
        return mHandler;
    }

    void pause(){
        if (mStatus == DOWNLOADING) task.pause();
    }

    private class TaskHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case START:
                    Bundle bundle = msg.getData();
                    totalLength = bundle.getInt("totalLength");
                    currentLength = bundle.getInt("currentLength");
                    if (callback != null) callback.onStart(currentLength, totalLength,
                            TaskUtil.getProgress(currentLength, totalLength));
                    break;
                case DOWNLOADING:
                    synchronized (this){
                        currentLength += msg.arg1;
                        progress = TaskUtil.getProgress(currentLength, totalLength);
                        if (callback != null &&
                                (System.currentTimeMillis() - lastProgressUpdateTime < 20 ||
                                        currentLength == totalLength))
                            callback.onDownloading(currentLength, totalLength, progress);
                        lastProgressUpdateTime = System.currentTimeMillis();
                        if (currentLength == totalLength) mHandler.sendEmptyMessage(FINISH);
                    }
                    break;
                case CANCEL:
                    synchronized (this){
                        currentLength = 0;
                        if (callback != null) callback.onDownloading(0, totalLength, 0);
                        TaskUtil.deleteFile(path, name);
                        if (callback != null) callback.onCancel();
                    }
                    break;
                case PAUSE:
                    synchronized (this){
                        if (callback != null) callback.onPause();
                    }
                    break;
                case RESUME:

                case RESTART:
                case FINISH:
                    if (callback != null) callback.onFinish();
                    break;
                case ERROR:
                    if (callback != null) callback.onError(msg.obj.toString());
                    break;
                case WAIT:
                case DESTROY:
            }
        }
    }
}
