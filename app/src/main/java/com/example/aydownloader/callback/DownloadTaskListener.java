package com.example.aydownloader.callback;

public interface DownloadTaskListener {
    void onStart(long current, long total, float progress);
    void onDownloading(long current, long total, float progress);
    void onPause();
    void onCancel();
    void onWait();
    void onFinish();
    void onError(String error);
}
