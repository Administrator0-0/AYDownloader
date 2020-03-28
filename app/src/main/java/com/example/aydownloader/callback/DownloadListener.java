package com.example.aydownloader.callback;

public interface DownloadListener {

    void onStart();
    void onDownloading();
    void onPause();
    void onFinish();
    void onError();
}
