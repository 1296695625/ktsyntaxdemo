package com.example.administrator.epgiskotlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.epgiskotlin.bean.DownloadInfo;

/**
 * Created by liu on 2020/7/10.
 */

public class DaoMaster implements BaseDao {
    public DaoMaster(Context context) {
        openHelper=new DownloadInfoOpenHelper(context,"",null,1);
        sqLiteDatabase=openHelper.getWritableDatabase();
    }

    private DownloadInfoOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    public void insert(DownloadInfo downloadInfo) {
        ContentValues contentValues=new ContentValues();
        contentValues.put("size",downloadInfo.getSize());
        contentValues.put("tempsize",downloadInfo.getTempsize());
        sqLiteDatabase.insert(DownloadInfoOpenHelper.DBNAME,"",contentValues);
    }

    @Override
    public void update(DownloadInfo downloadInfo) {

    }

    @Override
    public void delete(DownloadInfo downloadInfo) {

    }

    @Override
    public void search(DownloadInfo downloadInfo) {

    }
}
