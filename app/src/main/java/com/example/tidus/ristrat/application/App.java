package com.example.tidus.ristrat.application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //initLeakCanary();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()//监测所有内容
                .penaltyLog()//违规对log日志
                .penaltyDeath()//违规Crash
                .build());
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }

    /**
     * Explain : 初始化内存泄漏检测
     *
     * @author LiXaing
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
