package com.example.administrator.epgiskotlin

import android.os.Parcel
import android.os.Parcelable

class Bean : Parcelable {
    public var str: String? = null

    constructor(s: String) {
        this.str = s
    }

    protected constructor(parcel: Parcel) {
        str = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(str)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bean> {
        override fun createFromParcel(parcel: Parcel): Bean {
            return Bean(parcel)
        }

        override fun newArray(size: Int): Array<Bean?> {
            return arrayOfNulls(size)
        }
    }
}