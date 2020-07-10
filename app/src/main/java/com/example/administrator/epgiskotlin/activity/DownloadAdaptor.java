package com.example.administrator.epgiskotlin.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liu on 2020/7/10.
 */

public class DownloadAdaptor extends RecyclerView.Adapter<DownloadAdaptor.DownloadListViewHolder> {
    public void setData() {
    }

    @Override
    public DownloadListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DownloadListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DownloadListViewHolder extends RecyclerView.ViewHolder {
        public DownloadListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
