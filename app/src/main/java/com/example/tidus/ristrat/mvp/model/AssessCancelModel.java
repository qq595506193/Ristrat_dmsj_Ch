package com.example.tidus.ristrat.mvp.model;

import android.annotation.SuppressLint;

import com.example.lib_network.api.ApiService;
import com.example.tidus.ristrat.bean.AssessCancelBean;
import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.callback.IRetrofitService;
import com.example.tidus.ristrat.contract.IAssessCancelContract;
import com.example.tidus.ristrat.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AssessCancelModel implements IAssessCancelContract.IAssessCancelModel {
    @SuppressLint("CheckResult")
    @Override
    public void getAssessCancel(HashMap<String, Object> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().createService(IRetrofitService.class)
                .doAssessCancelGet(ApiService.ASSESS_CANCEL, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AssessCancelBean>() {
                    @Override
                    public void accept(AssessCancelBean assessCancelBean) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onSuccess(assessCancelBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onFailed(throwable);
                        }
                    }
                });
    }
}
