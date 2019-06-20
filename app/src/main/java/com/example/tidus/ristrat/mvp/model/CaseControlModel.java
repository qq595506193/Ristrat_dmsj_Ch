package com.example.tidus.ristrat.mvp.model;

import com.example.lib_network.api.ApiService;
import com.example.tidus.ristrat.callback.IOkHttpCallback;
import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.contract.ICaseControlContract;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.RetrofitUtils;

import java.util.HashMap;

public class CaseControlModel implements ICaseControlContract.ICaseControlModel {

    @Override
    public void getCaseControl(HashMap<String, Object> params, final IRequestCallback iRequestCallback) {
        LogUtils.e(ApiService.BASE_URL + ApiService.CASE_LIST + params + "");
        RetrofitUtils.getInstance().doGet(ApiService.CASE_LIST, params, new IOkHttpCallback() {

            @Override
            public void onSuccess(Object result) {
                if (iRequestCallback != null) {

                    iRequestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (iRequestCallback != null) {
                    iRequestCallback.onFailed(error);
                    LogUtils.e(error + "");
                }
            }
        });
    }
}
