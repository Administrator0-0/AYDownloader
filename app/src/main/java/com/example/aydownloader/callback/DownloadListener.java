package com.example.aydownloader.callback;

public interface DownloadListener {

    void onStart();
    void onDownloading();
    void onSuccess();
    void onFailure();
}
