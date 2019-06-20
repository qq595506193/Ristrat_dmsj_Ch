package com.example.tidus.ristrat.callback;

import com.example.tidus.ristrat.bean.CaseControlBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface IRetrofitService {

    @GET
    Observable<CaseControlBean> doGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> doPost(@Url String apiUrl, @FieldMap HashMap<String, Object> params);


}
