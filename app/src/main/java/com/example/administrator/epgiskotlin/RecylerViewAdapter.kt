package com.example.administrator.epgiskotlin

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecylerViewAdapter(context: Context) : RecyclerView.Adapter<RecylerViewAdapter.ReViewholder>() {
    private val mContext: Context? = context
    private var mData: ArrayList<Bean>? = null
    fun setData(data: ArrayList<Bean>?) {
        this.mData = data
        Log.v("tfhr", "data size-" + mData?.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ReViewholder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_recycler, null);
        return ReViewholder(view)
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return mData?.size ?: 0
    }

    override fun onBindViewHolder(holder: ReViewholder?, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder?.tv?.setText("" + mData?.get(position)?.str)
    }

    class ReViewholder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tv: TextView? = itemView?.findViewById(R.id.layout_recycler_item)
    }

}