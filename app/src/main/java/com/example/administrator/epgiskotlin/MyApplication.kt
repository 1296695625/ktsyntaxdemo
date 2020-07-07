package com.example.administrator.epgiskotlin

import android.app.Application
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.BaiduMap

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //百度地图注册初始化
        SDKInitializer.initialize(this)
//        百度地图坐标系
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }

}