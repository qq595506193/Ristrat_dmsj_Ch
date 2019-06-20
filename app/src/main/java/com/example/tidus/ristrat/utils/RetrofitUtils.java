package com.example.tidus.ristrat.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.lib_core.utils.LogUtils;
import com.example.lib_network.api.ApiService;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.callback.IOkHttpCallback;
import com.example.tidus.ristrat.callback.IRetrofitService;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import static com.example.lib_network.api.ApiService.BASE_URL;

public class RetrofitUtils {
    private static RetrofitUtils instance;
    private static Context context1;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    public static String cookie = null;

    private static class SingleHolder {
        private static final RetrofitUtils _INSTANT = new RetrofitUtils(BASE_URL);
    }

    public static RetrofitUtils getDefault(Context context) {
        context1 = context;
        return SingleHolder._INSTANT;
    }

    private RetrofitUtils(String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor(new AddCookiesInterceptor(App.getContext()))
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(LenientGsonConverterFactory.create(new Gson()))
                .baseUrl(ApiService.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    // 单例双重检验锁
    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils(BASE_URL);
                }
            }
        }
        return instance;
    }

    public boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    @SuppressLint("CheckResult")
    public void doGet(String apiUrl, HashMap<String, Object> parmas, final IOkHttpCallback iOkHttpCallback) {
        IRetrofitService retrofitService = retrofit.create(IRetrofitService.class);
        LogUtils.e(apiUrl);
        retrofitService.doGet(apiUrl, parmas)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CaseControlBean>() {
                    @Override
                    public void accept(CaseControlBean caseControlBean) throws Exception {
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onSuccess(caseControlBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onFailed(throwable);
                        }
                    }
                });
    }



    /*@SuppressLint("CheckResult")
    public void doPut(String apiUrl, HashMap<String, String> parmas, final IOkHttpCallback iOkHttpCallback) {
        IRetrofitService retrofitService = retrofit.create(IRetrofitService.class);
        retrofitService.putReg(apiUrl, parmas).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onSuccess(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onFailed(throwable + "");
                        }
                    }
                });
    }

    @SuppressLint("CheckResult")
    public void upload(String apiUrl, File file, final IOkHttpCallback iOkHttpCallback) {
        IRetrofitService retrofitService = retrofit.create(IRetrofitService.class);
        retrofitService.upload(apiUrl, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String result = responseBody.string();
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onSuccess(result);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onFailed(throwable + "");
                        }
                    }
                });
    }*/
}
