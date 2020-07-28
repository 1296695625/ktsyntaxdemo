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
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Property;
import android.util.Rational;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.epgiskotlin.R;
import com.example.administrator.epgiskotlin.bean.DownloadInfo;
import com.example.administrator.epgiskotlin.bean.Rectangle;
import com.example.administrator.epgiskotlin.model.LiveDataModel;
import com.example.administrator.epgiskotlin.utils.LogUtils;
import com.example.administrator.epgiskotlin.view.CustomView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Security;
import java.text.DecimalFormat;
import java.util.RandomAccess;

import static com.baidu.location.e.l.A;

/**
 * google 权限管理框架rxpermission
 * <p>
 * mapbox,使用原声带opengl来绘制，2d或者3d效果。先注册新建项目获取sdk的key value，添加到清单文件中（官网建议在本地读取，不要显式的放在清单文件）
 * 2种方式，添加依赖集成或者下载sdk集成，
 * <p>
 * （android 10）使用saf访问所有的存储文件，不需权限
 * <p>
 * 使用分区存储的应用对自己创建的文件始终拥有读/写权限，无论文件是否位于应用的专有目录内。因此，如果您的应用仅保存和访问自己创建的文件，则无需请求获得 READ_EXTERNAL_STORAGE 或 WRITE_EXTERNAL_STORAGE 权限。
 * <p>
 * android surpport(>=9 28及以上) 迁移到androidx 只是包名和maven 地址名改变，方法，类名都没改变
 * 在项目的peoperties下添加
 * android.useAndroidX=true
 * android.enableJetifier=true
 * <p>
 * 查找surpport对应的androidX 的版本，https://developer.android.google.cn/jetpack/androidx/migrate/artifact-mappings?hl=en
 * 迁移完成，所有的surpport包都会报错，修改包报错
 * <p>
 * retrofit 使用注解调用java接口,返回call类型，calladaptorfactory(这里使用rxjava的calladaptor),可以converter.factory转换
 */

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    private ValueAnimator valueAnimator;
    ValueAnimator valueAnimator1;
    private TextView tv, tv2, tv3, tv_percent;
    private Button bt_save,bt_reset, bt_satartanimate, bt_pauseanimator, bt_resumeanimator, bt_stopanimator, bt_pip, bt_read, bt_pause, bt_continue;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        tv = findViewById(R.id.p1);
        tv2 = findViewById(R.id.p2);
        tv3 = findViewById(R.id.p3);
        tv_percent = findViewById(R.id.tv_percent);
        bt_read = findViewById(R.id.bt_read);
        bt_pause = findViewById(R.id.bt_pause);
        bt_continue = findViewById(R.id.bt_continue);
        bt_read.setOnClickListener(this);
        bt_pause.setOnClickListener(this);
        bt_continue.setOnClickListener(this);
        customView = findViewById(R.id.customview);
        customView.setBitmapWidth(200);
        customView.setBitmapHeight(200);
        customView.setPathWidth(3f);
        bt_save=findViewById(R.id.bt_save);
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //保存到相册

            }
        });
        bt_reset = findViewById(R.id.bt_reset);
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != customView) {
                    customView.reset();
                }
            }
        });
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recyclerview_download);
//        recyclerView.setAdapter();
        LiveDataModel liveDataModel = ViewModelProviders.of(this).get(LiveDataModel.class);
        liveDataModel.liveData.observe(this, new Observer<DownloadInfo>() {
            @Override
            public void onChanged(@Nullable DownloadInfo downloadInfo) {
                //更新ui
            }
        });
        myHandler = new MyHandler();
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
//                animatorSet.start();
                doLet();
            }

        });
        initAnimator();
    }

    private void doLet() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getSystemService(StorageManager.class);
        }
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
//        animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.visabiledisvisable);
//        animatorSet.setTarget(bt_resumeanimator);
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

    private MyHandler myHandler;
    String url = Environment.getExternalStorageDirectory().getAbsolutePath() + "/random/" + "sbzlx.txt";
    String url_source = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/data.txt";
    File f = new File(url);
    Long size = 0l;

    DecimalFormat decimalFormat = new DecimalFormat("0");

    private final class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Bundle bundle = (Bundle) msg.obj;
                BigDecimal p = new BigDecimal(bundle.getLong("p"));
                BigDecimal size = new BigDecimal(bundle.getLong("size"));
                Double s = p.divide(size, 2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
//                decimalFormat.format()
                progressBar.setProgress(s.intValue());
                tv_percent.setText(s + "%");
            }
            if (msg.what == 2) {
                Toast.makeText(AnimatorActivity.this, "成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_read:
                Log.v("tag", (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) + "");
                if (f.exists()) {
//                    f.delete();
                } else {
                    if (!f.getParentFile().exists()) {
                        f.getParentFile().mkdirs();
                    }
//                    f.mkdir();
                }
                size = new File(url_source).length();
                DecimalFormat format = new DecimalFormat("0.00");
                BigDecimal bigDecimal = new BigDecimal(10);
//                bigDecimal.divide()
                Log.v("tag", f.exists() + "");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        byte[] bytes = new byte[500];//可根据文件的大小设置bytes的大小，控制进度条的进度
                        int len = -1;
                        try {
//                            InputStream fileInputStream = getAssets().open("data.txt");
                            FileInputStream fileInputStream = new FileInputStream(url_source);
                            RandomAccessFile file = new RandomAccessFile(f, "rwd");
                            if (file.length() > 0) {
                                file.seek(file.getFilePointer());
                            }
                            Log.v("tag", "read start");
                            long temp;
//            FileDescriptor fileDescriptor = file.getFD();
                            Bundle bundle = new Bundle();
                            while ((len = fileInputStream.read(bytes)) != -1) {
                                file.write(bytes, 0, len);
                                Thread.sleep(100);
                                Message m = myHandler.obtainMessage();
                                m.what = 0;
                                bundle.putLong("p", file.getFilePointer());
                                bundle.putLong("size", size);
                                m.obj = bundle;
                                myHandler.sendMessage(m);
                            }
                            Log.v("tag", "read end");
                            file.close();
                            fileInputStream.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.bt_pause:
//               todo 暂停，保存状态到数据库（文件大小，所有信息）,再次点击改变状态（继续下载）
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                           http://127.0.0.1:9000/fzgis2.0/pis_gis_nav/rest/queryCityTree/getBackStr
                            URL url1 = new URL("http://10.4.83.161:9000/fzgis2.0/pis_gis_nav/rest/queryCityTree/getBackStr");
                            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
                            urlConnection.setConnectTimeout(10000);
                            urlConnection.setDoInput(true);
                            urlConnection.setDoOutput(true);
                            urlConnection.setRequestProperty("Content-type", "application/json");
                            urlConnection.setDefaultUseCaches(false);
                            urlConnection.setRequestMethod("GET");
                            urlConnection.connect();
                            InputStream s = urlConnection.getInputStream();
                            byte bytes[] = new byte[1024];
                            int len = -1;
                            StringBuilder stringBuilder = new StringBuilder();
                            while ((len = s.read(bytes)) != -1) {
                                stringBuilder.append(new String(bytes, 0, len));
                            }
                            String ss = stringBuilder.toString();
                            Log.v("liu", ss);
//                            Message m=myHandler.obtainMessage();
//                            m.what=2;
                            myHandler.sendEmptyMessage(2);
                            s.close();
                            urlConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.bt_continue:
                break;

        }

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
