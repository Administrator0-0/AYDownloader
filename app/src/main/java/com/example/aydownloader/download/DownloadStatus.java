package com.example.aydownloader.download;

class DownloadStatus {
    static final int NONE = 0x1000;
    static final int START = 0x1001;
    static final int DOWNLOADING = 0x1002;
    static final int PAUSE = 0x1003;
    static final int RESUME = 0x1004;
    static final int CANCEL = 0x1005;
    static final int RESTART = 0x1006;
    static final int FINISH = 0x1007;
    static final int ERROR = 0x1008;
    static final int WAIT = 0x1009;
    static final int DESTROY = 0x1010;
}
