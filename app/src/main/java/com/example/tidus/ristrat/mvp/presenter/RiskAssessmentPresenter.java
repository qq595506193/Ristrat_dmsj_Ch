package com.example.tidus.ristrat.mvp.presenter;

import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;

import java.text.ParseException;
import java.util.HashMap;

public class RiskAssessmentPresenter extends IRiskAssessmentContart.RiskAssessmentPresenter {

    @Override
    public void getRiskAssessment(HashMap<String, Object> params) {
        model.getRiskAssessment(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) {
                if (view != null) {
                    view.onRiskAssessmentSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onFailed(error);
                }
            }
        });
    }

    @Override
    public void getCommit(HashMap<String, Object> params) {
        model.getCommit(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) {
                if (view != null) {
                    view.onCommitSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onFailed(error);
                }
            }
        });
    }

    @Override
    public void getSave(HashMap<String, Object> params) {
        model.getSave(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) throws ParseException {
                if (view != null) {
                    view.onSaveSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (view != null) {
                    view.onFailed(error);
                }
            }
        });
    }
}
