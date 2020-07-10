package com.example.administrator.epgiskotlin.dao;

import com.example.administrator.epgiskotlin.bean.DownloadInfo;

/**
 * Created by liu on 2020/7/10.
 */

public interface BaseDao {
    void insert(DownloadInfo downloadInfo);

    void update(DownloadInfo downloadInfo);

    void delete(DownloadInfo downloadInfo);

    void search(DownloadInfo downloadInfo);
}
