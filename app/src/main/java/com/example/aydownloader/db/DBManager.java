package com.example.aydownloader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    public void insertData(){

    }

}
