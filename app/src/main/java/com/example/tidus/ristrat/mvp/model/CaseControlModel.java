package com.example.tidus.ristrat.mvp.model;

import android.annotation.SuppressLint;

import com.example.lib_network.api.ApiService;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.callback.IRetrofitService;
import com.example.tidus.ristrat.contract.ICaseControlContract;
import com.example.tidus.ristrat.utils.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CaseControlModel implements ICaseControlContract.ICaseControlModel {

    @SuppressLint("CheckResult")
    @Override
    public void getCaseControl(HashMap<String, Object> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().createService(IRetrofitService.class)
                .doCaseControlGet(ApiService.CASE_LIST, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CaseControlBean>() {
                    @Override
                    public void accept(CaseControlBean caseControlBean) throws Exception {
                        if (iRequestCallback != null) {
                            iRequestCallback.onSuccess(caseControlBean);
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
