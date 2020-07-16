package com.example.administrator.epgiskotlin.activity

import android.animation.ValueAnimator
import android.app.PictureInPictureParams
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Rational
import android.view.View
import com.example.administrator.epgiskotlin.R
import com.example.administrator.epgiskotlin.utils.LogUtils
import kotlinx.android.synthetic.main.activity_animator_kt.*

class AnimatorKtActivity : AppCompatActivity(), ValueAnimator.AnimatorUpdateListener, View.OnClickListener {
    override fun onClick(v: View) {
        if (v.id == R.id.kt_bt_startanimate) {
            valueAnimator.start()
        } else if (v.id == R.id.kt_bt_pauseanimate) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                valueAnimator.pause()
            }
        } else if (v.id == R.id.kt_bt_continue) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                valueAnimator.resume()
            }
        } else if (v.id == R.id.kt_bt_pip) {
            var builder: PictureInPictureParams.Builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                PictureInPictureParams.Builder()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    builder.setAspectRatio(Rational(1, 2))
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                enterPictureInPictureMode(builder.build())
            }
        }
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        LogUtils.v("liu", "")
        kt_animatestatelist.alpha = animation.animatedFraction
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_kt)
        initView();
        initData();
    }

    lateinit var valueAnimator: ValueAnimator;

    private fun initData() {
        valueAnimator = ValueAnimator.ofInt(0, 1)
        valueAnimator.repeatMode = ValueAnimator.RESTART
        valueAnimator.repeatCount = 5
        valueAnimator.setDuration(2000)
        valueAnimator.setTarget(kt_animatestatelist)
        //实现接口
//        valueAnimator.addUpdateListener(this)
        //匿名
//        valueAnimator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
//            override fun onAnimationUpdate(animation: ValueAnimator?) {
//                kt_animatestatelist.alpha = animation!!.animatedFraction
//                LogUtils.v("liu", "animator update listener")
//            }
//        })
        //lambda 表达式
        valueAnimator.addUpdateListener {
            kt_animatestatelist.alpha = it!!.animatedFraction
            LogUtils.v("liu", "animator update listener")
        };
    }

    private fun initView() {
        kt_bt_startanimate.setOnClickListener(this)
        kt_bt_pauseanimate.setOnClickListener(this)
        kt_bt_resuemanimate.setOnClickListener(this)
        kt_bt_pip.setOnClickListener(this)
    }
}
