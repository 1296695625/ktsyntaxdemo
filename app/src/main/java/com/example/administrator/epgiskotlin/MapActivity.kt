package com.example.administrator.epgiskotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapPoi
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.model.LatLng
import com.example.administrator.epgiskotlin.utils.LogUtils
import com.yinglan.scrolllayout.ScrollLayout
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(), ScrollLayout.OnScrollChangedListener, View.OnClickListener, BaiduMap.OnMapClickListener {
    override fun onMapClick(p0: LatLng?) {
        LogUtils.v("tfhr",""+p0.toString())
    }

    override fun onMapPoiClick(p0: MapPoi?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(v: View) {
        if (v.id == R.id.map_search) {
            scrollLayout?.visibility = View.VISIBLE
            scrollLayout?.setToClosed()
        }
        if(v.id==R.id.scrolllayout){

        }
    }

    override fun onScrollProgressChanged(currentProgress: Float) {
        if (currentProgress >= 0) {
            var percent: Float? = 255 * currentProgress
            if (percent!! > 255) {
                percent = 255f
            } else if (percent!! < 0) {
                percent = 0f
            }
            scrollLayout?.background?.alpha = 255 - percent.toInt()
        }
    }

    override fun onScrollFinished(currentStatus: ScrollLayout.Status?) {
        //退出，即scrolllayout隐藏显示
        if (currentStatus!!.equals(ScrollLayout.Status.EXIT)) {

        }
        //打开，
        if (ScrollLayout.Status.CLOSED.equals(currentStatus)) {

        }
    }

    override fun onChildScroll(top: Int) {
    }

    private var adapter: RecylerViewAdapter? = null
    private var scrollLayout: ScrollLayout? = null;
    private var recyclerView: RecyclerView? = null
    private var mapView: MapView? = null
    private var baidumap: BaiduMap? = null
    private var search: Button? = null
    private var datas: ArrayList<Bean>? = null
    private var ll:LinearLayout?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        initData()
        initView()
        mapView?.onCreate(this, savedInstanceState)
    }

    private fun initData() {
        datas = ArrayList<Bean>();
        for (i: Int in 0..2) {
            datas?.add(Bean("haha1"))
            datas?.add(Bean("haha2"))
            datas?.add(Bean("haha3"))
            datas?.add(Bean("haha4"))
            datas?.add(Bean("haha5"))
            datas?.add(Bean("haha6"))
        }
    }

    private fun initView() {
        ll=findViewById(R.id.ll_click)
        ll?.setOnClickListener(this)
        mapView = findViewById(R.id.baidumap)
        mapView?.showScaleControl(true)
        baidumap = mapView?.map
        baidumap?.setOnMapClickListener(this)
        search = findViewById(R.id.map_search)
        search?.setOnClickListener(this)
        scrollLayout = findViewById(R.id.scrolllayout)
        scrollLayout?.setIsSupportExit(false)
        scrollLayout?.setExitOffset(0)
        scrollLayout?.setOnScrollChangedListener(this)
        recyclerView?.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        adapter = RecylerViewAdapter(this)
        adapter?.setData(datas)
        recyclerView?.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }
}
