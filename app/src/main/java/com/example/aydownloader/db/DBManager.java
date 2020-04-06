package com.example.aydownloader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aydownloader.download.DownloadTaskInfo;

import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private static final String DB_NAME = "AYDownloader";
    private static final int VERSION = 2;
    private static final String TABLE_NAME = "Download_Info";
    private static DBManager instance;
    private SQLiteDatabase db;

    private DBManager(Context context){
        DBHelper helper = new DBHelper(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context){
        if (instance == null){
            synchronized (DBManager.class){
                instance = new DBManager(context);
            }
        }
        return instance;
    }

    public void insertData(List<DownloadTaskInfo>ifs){
        for (DownloadTaskInfo info : ifs){
            insertData(info);
        }
    }

    public void insertData(DownloadTaskInfo info){
        ContentValues values = new ContentValues();
        values.put("url", info.getUrl());
        values.put("path", info.getPath());
        values.put("name", info.getName());
        values.put("child_task_count", info.getChildTaskCount());
        values.put("child_task_length", info.getChildTaskLength());
        values.put("total_length", info.getTotalLength());
        values.put("current_length", info.getCurrentLength());
        values.put("percentage", info.getPercentage());
        values.put("last_modify", info.getLastModify());
        values.put("status", info.getStatus());
        values.put("date", info.getDate());
        db.insert(TABLE_NAME, null, values);
    }

    public List<DownloadTaskInfo> getAllInfo(){
        List<DownloadTaskInfo> infoList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                DownloadTaskInfo info = new DownloadTaskInfo();
                info.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                info.setPath(cursor.getString(cursor.getColumnIndex("path")));
                info.setName(cursor.getString(cursor.getColumnIndex("name")));
                info.setChildTaskCount(cursor.getInt(cursor.getColumnIndex("child_task_count")));
                info.setChildTaskLength(cursor.getInt(cursor.getColumnIndex("child_task_length")));
                info.setTotalLength(cursor.getInt(cursor.getColumnIndex("total_length")));
                info.setCurrentLength(cursor.getInt(cursor.getColumnIndex("current_length")));
                info.setPercentage(cursor.getFloat(cursor.getColumnIndex("percentage")));
                info.setLastModify(cursor.getString(cursor.getColumnIndex("last_modify")));
                info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
                info.setDate(cursor.getLong(cursor.getColumnIndex("date")));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return infoList;
    }

    public DownloadTaskInfo getInfo(String url){
        Cursor cursor = db.query(TABLE_NAME, null, "url = ?", new String[]{url}, null, null, null, null);
        if (!cursor.moveToFirst()){
            return null;
        }
        DownloadTaskInfo info = new DownloadTaskInfo();
        info.setUrl(url);
        info.setPath(cursor.getString(cursor.getColumnIndex("path")));
        info.setName(cursor.getString(cursor.getColumnIndex("name")));
        info.setChildTaskCount(cursor.getInt(cursor.getColumnIndex("child_task_count")));
        info.setChildTaskLength(cursor.getInt(cursor.getColumnIndex("child_task_length")));
        info.setTotalLength(cursor.getInt(cursor.getColumnIndex("total_length")));
        info.setCurrentLength(cursor.getInt(cursor.getColumnIndex("current_length")));
        info.setPercentage(cursor.getFloat(cursor.getColumnIndex("percentage")));
        info.setLastModify(cursor.getString(cursor.getColumnIndex("last_modify")));
        info.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
        info.setDate(cursor.getLong(cursor.getColumnIndex("date")));
        cursor.close();
        return info;
    }

}
