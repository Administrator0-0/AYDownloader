package com.example.aydownloader.download;

public class DownloadLoader {
    private String url;
    private String path;
    private String name;
    private int threadCount;

    public DownloadLoader(){

    }

    public String getUrl() {
        return url;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public DownloadLoader setUrl(String url) {
        this.url = url;
        return this;
    }

    public DownloadLoader setName(String name) {
        this.name = name;
        return this;
    }

    public DownloadLoader setPath(String path) {
        this.path = path;
        return this;
    }

    public DownloadLoader setThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public DownloaderManager build(){
        DownloaderManager.getInstance().put(url, path, name, threadCount);
        return DownloaderManager.getInstance();
    }
}
