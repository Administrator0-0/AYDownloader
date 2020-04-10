package com.example.aydownloader.download;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.aydownloader.callback.DownloadTaskListener;
import com.example.aydownloader.util.TaskUtil;

import static com.example.aydownloader.download.DownloadStatus.NONE;

public class DownloadTaskInfo implements Parcelable{

    private String url;
    private String path;
    private String name;
    private String key;
    private int currentLength;
    private int totalLength;
    private float percentage;
    private int status = NONE;
    private int childTaskCount;
    private int childTaskLength;
    private long date;
    private String lastModify;
    private int threadCount;

    public DownloadTaskInfo(){

    }

    DownloadTaskInfo(String url, String path, String name){
        this(url, path, name, 1);
    }

    DownloadTaskInfo(String url, String path, String name, int threadCount){
        this.url = url;
        this.path = path;
        this.name = name;
        this.threadCount = threadCount;
        this.key = TaskUtil.getKey(url, path, name);
    }

    DownloadTaskInfo(String url, String path, String name, int threadCount,
                     DownloadTaskListener callback, int currentLength, int totalLength,
                     String lastModify, long date, int childTaskCount, int childTaskLength){
        this.url = url;
        this.path = path;
        this.name = name;
        this.threadCount = threadCount;
        this.key = TaskUtil.getKey(url, path, name);
        this.currentLength = currentLength;
        this.totalLength = totalLength;
        this.lastModify = lastModify;
        this.childTaskCount = childTaskCount;
        this.childTaskLength = childTaskLength;
        this.date = date;
    }

    protected DownloadTaskInfo(Parcel in) {
        url = in.readString();
        path = in.readString();
        name = in.readString();
        key = in.readString();
        threadCount = in.readInt();
        currentLength = in.readInt();
        totalLength = in.readInt();
        lastModify = in.readString();
        status = in.readInt();
        percentage = in.readFloat();
        childTaskLength = in.readInt();
        childTaskCount = in.readInt();
        date = in.readLong();
    }

    public static final Creator<DownloadTaskInfo> CREATOR = new Creator<DownloadTaskInfo>() {
        @Override
        public DownloadTaskInfo createFromParcel(Parcel in) {
            return new DownloadTaskInfo(in);
        }

        @Override
        public DownloadTaskInfo[] newArray(int size) {
            return new DownloadTaskInfo[size];
        }
    };

    String getKey() {
        return key;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public int getChildTaskCount() {
        return childTaskCount;
    }

    public int getChildTaskLength() {
        return childTaskLength;
    }

    public long getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public String getLastModify() {
        return lastModify;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setChildTaskCount(int childTaskCount) {
        this.childTaskCount = childTaskCount;
    }

    public void setChildTaskLength(int childTaskLength) {
        this.childTaskLength = childTaskLength;
    }

    public void setCurrentLength(int currentLength) {
        this.currentLength = currentLength;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLastModify(String lastModify) {
        this.lastModify = lastModify;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public void setTotalLength(int totalLength) {
        this.totalLength = totalLength;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(path);
        dest.writeString(name);
        dest.writeString(key);
        dest.writeInt(currentLength);
        dest.writeInt(totalLength);
        dest.writeFloat(percentage);
        dest.writeInt(status);
        dest.writeInt(childTaskCount);
        dest.writeInt(childTaskLength);
        dest.writeLong(date);
        dest.writeString(lastModify);
        dest.writeInt(threadCount);
    }
}
