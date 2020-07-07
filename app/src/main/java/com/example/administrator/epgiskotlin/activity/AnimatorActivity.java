package com.example.administrator.epgiskotlin.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.TypeConverter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.PictureInPictureParams;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Property;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.epgiskotlin.R;
import com.example.administrator.epgiskotlin.bean.Rectangle;

import static com.baidu.location.e.l.A;

public class AnimatorActivity extends AppCompatActivity {
    private ValueAnimator valueAnimator;
    ValueAnimator valueAnimator1;
    private TextView tv, tv2, tv3;
    private Button bt_satartanimate, bt_pauseanimator, bt_resumeanimator, bt_stopanimator, bt_pip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        tv = findViewById(R.id.p1);
        tv2 = findViewById(R.id.p2);
        tv3 = findViewById(R.id.p3);
        setFont();

        bt_pip = findViewById(R.id.bt_pip);
        bt_pip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
                        builder.setAspectRatio(new Rational(1, 2));//设置宽高比例
                        enterPictureInPictureMode(builder.build());
                    }
                }
            }
        });
        bt_satartanimate = findViewById(R.id.bt_startanimate);
        bt_pauseanimator = findViewById(R.id.bt_pauseanimate);
        bt_resumeanimator = findViewById(R.id.bt_resuemanimate);
        bt_stopanimator = findViewById(R.id.bt_stopmanimate);
        bt_stopanimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != valueAnimator) valueAnimator.cancel();
            }
        });
        bt_resumeanimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != valueAnimator)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        valueAnimator.resume();
                    }
            }
        });
        bt_pauseanimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != valueAnimator)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        valueAnimator.pause();
                    }
            }
        });
        bt_satartanimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != valueAnimator) valueAnimator.start();
                if (null != valueAnimator1) {
                    valueAnimator1.start();
                }
                bt_pauseanimator.animate().rotationX(360);
                objectAnimator.start();
                animatorSet.start();
                doLet();
            }

        });
        initAnimator();
    }

    private void doLet() {
    }

    //设置字体,最新设置按照android 8设置字体R.font.**** 来
    private void setFont() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Typeface typeface = getResources().getFont(R.font.huawenxinsong);
            tv.setTypeface(typeface);
        }
        Typeface t = ResourcesCompat.getFont(getApplicationContext(), R.font.huawenxinsong);
    }

    /**
     * reverse 反转动画，restart，重新开始
     * 不建议对alpha使用动画，消耗太多性能
     * AccelerateDecelerateInterpolator 开始慢，中间快，结束慢
     * AccelerateInterpolator 开始慢，然后加速
     * DecelerateInterpolator 开始快，然后减速
     * AnticipateInterpolator 隐藏到显示
     * BounceInterpolator 跳动
     * CycleIntepolator 指定循环次数
     * LinearInterpolator 线性，匀速
     * OvershootInterpolator   加速-到达特定值——返回
     * TimeInterpolator 自定义实现
     */

    ObjectAnimator objectAnimator;
    AnimatorSet animatorSet;

    private void initAnimator() {
        objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.animator_ob_bt);
        objectAnimator.setTarget(tv3);
        animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.visabiledisvisable);
        animatorSet.setTarget(bt_resumeanimator);
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("alpha", 0.0f, 0.5f, 1.0f);
//        Property property = new TemProperty(tv.getClass(),"");
//        propertyValuesHolder.setProperty(property);
        valueAnimator = ValueAnimator.ofPropertyValuesHolder(propertyValuesHolder);
//        valueAnimator = ValueAnimator.ofInt(0, 5, 3);
        valueAnimator.setDuration(5000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
//        valueAnimator.setEvaluator(new IntEvaluator());
//        valueAnimator.setInterpolator(new TimeInterpolator() {
//            @Override
//            public float getInterpolation(float input) {
//                return 0;
//            }
//        });
//        valueAnimator.setCurrentFraction(0.5f);//设置动画进度
//        valueAnimator.setCurrentPlayTime(100);//设置动画时间进度
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv.setAlpha(animation.getAnimatedFraction());
                tv2.setAlpha(animation.getAnimatedFraction());
            }
        });
        valueAnimator.setTarget(tv);
        valueAnimator.setTarget(tv2);

        valueAnimator1 = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.test_animator);
//        valueAnimator1.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationCancel(Animator animation) {
//                super.onAnimationCancel(animation);
//            }
//        });
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.v("lwh", "");
//                tv2.setAlpha(animation.getAnimatedFraction());
                tv2.setTranslationX((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator1.setTarget(tv2);
    }

    class MyAnimatorListener extends AnimatorListenerAdapter {
        @Override
        public void onAnimationCancel(Animator animation) {
            super.onAnimationCancel(animation);
        }

        public void onNo() {
        }

    }

    class TemProperty extends Property {
        /**
         * A constructor that takes an identifying name and {@link #getType() type} for the property.
         *
         * @param type
         * @param name
         */
        public TemProperty(Class type, String name) {
            super(type, name);
        }

        @Override
        public Object get(Object object) {
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != valueAnimator) {
            valueAnimator.removeAllUpdateListeners();
        }
        if (null != valueAnimator1) valueAnimator1.removeAllUpdateListeners();
    }
}
