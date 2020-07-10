package com.example.administrator.epgiskotlin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.LruCache;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * Created by liu on 2020/7/7.
 */

public class CustomThreadPool {


    private WeakReference<Context> weakReference;
//    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static CustomThreadPool getInstance(Context context) {
//        if(null==custom) {
//            weakReference = new WeakReference<Context>(context);
//        }
//
//        Bitmap bitmap = BitmapFactory.decodeFile("");
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        return new CustomThreadPool();
    }

    private CustomThreadPool customThreadPool;

    private CustomThreadPool() {
//        Executors.newSingleThreadExecutor()//线程按顺序执行
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);//指定时间
//        Executors.newFixedThreadPool()//固定同时执行线程
//        Executors.newCachedThreadPool()//execute many short-lived asynchronous tasks，如果线程池缓存有可用线程，就复用，没有就新建，60s后移除
    }

    public void setContext(Context context) {
        if (null == weakReference) {
            weakReference = new WeakReference<Context>(context);
        }
    }

    public void exectThread(Thread thread) {
//        executor.execute(thread);
    }

    public void exectThread(Thread thread, Callable callable) {
    }
}
