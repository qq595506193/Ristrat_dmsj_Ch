package com.example.tidus.ristrat.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
//        builder.addHeader("USER_ID", SpUtil.getString(App.getContext(), "USER_ID", ""));
//        builder.addHeader("SITE_ID", SpUtil.getString(App.getContext(), "SITE_ID", ""));
        Request build = builder.build();
        Response response = chain.proceed(build);
        return response;
    }
}
