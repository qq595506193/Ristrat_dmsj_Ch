package com.example.tidus.ristrat.mvp.presenter;

import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.contract.ICaseControlContract;
import com.example.tidus.ristrat.mvp.model.CaseControlModel;

import java.util.HashMap;

public class CaseControlPresenter extends ICaseControlContract.CaseControlPresenter {
    private CaseControlModel caseControlModel;
    private ICaseControlContract.ICaseControlView iCaseControlView;

    public CaseControlPresenter(ICaseControlContract.ICaseControlView iCaseControlView) {
        caseControlModel = new CaseControlModel();
        this.iCaseControlView = iCaseControlView;
    }

    @Override
    public void getCaseControl(HashMap<String, Object> params) {
        caseControlModel.getCaseControl(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) {
                if (iCaseControlView != null) {
                    iCaseControlView.onCaseControlSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (iCaseControlView != null) {
                    iCaseControlView.onFailed(error);
                }
            }
        });
    }

    @Override
    public void getQueryHM(HashMap<String, Object> params) {
        caseControlModel.getQueryHM(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) {
                if (iCaseControlView != null) {
                    iCaseControlView.onQueryHMSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (iCaseControlView != null) {
                    iCaseControlView.onFailed(error);
                }
            }
        });
    }
}
