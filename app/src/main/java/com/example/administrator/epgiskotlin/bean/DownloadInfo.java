package com.example.administrator.epgiskotlin.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liu on 2020/7/10.
 */

public class DownloadInfo implements Parcelable {
    protected DownloadInfo(Parcel in) {
        if (in.readByte() == 0) {
            size = null;
        } else {
            size = in.readLong();
        }
        if (in.readByte() == 0) {
            tempsize = null;
        } else {
            tempsize = in.readLong();
        }
    }

    public static final Creator<DownloadInfo> CREATOR = new Creator<DownloadInfo>() {
        @Override
        public DownloadInfo createFromParcel(Parcel in) {
            return new DownloadInfo(in);
        }

        @Override
        public DownloadInfo[] newArray(int size) {
            return new DownloadInfo[size];
        }
    };

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getTempsize() {
        return tempsize;
    }

    public void setTempsize(Long tempsize) {
        this.tempsize = tempsize;
    }

    private Long size;
    private Long tempsize;

    public DownloadInfo(Long size, Long tempsize) {
        this.size = size;
        this.tempsize = tempsize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (size == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(size);
        }
        if (tempsize == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(tempsize);
        }
    }
}
