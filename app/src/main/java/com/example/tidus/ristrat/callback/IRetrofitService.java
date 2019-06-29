package com.example.tidus.ristrat.callback;

import com.example.tidus.ristrat.bean.CancelAssessBean;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.CommitBean;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.example.tidus.ristrat.bean.MessageBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface IRetrofitService {

    // 患者列表展示
    @GET
    Observable<CaseControlBean> doCaseControlGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    // 消息列表展示
    @GET
    Observable<MessageBean> doMessageListGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    // 查询题目表格列表
    @GET
    Observable<RiskAssessmentBean> doRiskTableListGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    // 历史评估查询
    @GET
    Observable<HistoryAssessBean> doHistoryAssessGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    // 提交表格
    @GET
    Observable<CommitBean> doCommitGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    // 提醒变色
    @GET
    Observable<QueryHMBean> doQueryHMGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);

    // 取消评估
    @GET
    Observable<CancelAssessBean> doCancelAssessGet(@Url String apiUrl, @QueryMap HashMap<String, Object> params);
}
