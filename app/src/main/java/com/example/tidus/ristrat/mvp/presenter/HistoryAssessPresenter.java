package com.example.tidus.ristrat.mvp.presenter;

import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.contract.IHistoryAssessContract;
import com.example.tidus.ristrat.mvp.model.HistoryAssessModel;

import java.text.ParseException;
import java.util.HashMap;

public class HistoryAssessPresenter extends IHistoryAssessContract.HistoryAssessPresenter {
    private HistoryAssessModel historyAssessModel;
    private IHistoryAssessContract.IHistoryAssessView iHistoryAssessView;

    public HistoryAssessPresenter(IHistoryAssessContract.IHistoryAssessView iHistoryAssessView) {
        historyAssessModel = new HistoryAssessModel();
        this.iHistoryAssessView = iHistoryAssessView;
    }

    @Override
    public void getHistoryAssess(HashMap<String, Object> params) {
        historyAssessModel.getHistoryAssess(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) throws ParseException {
                if (iHistoryAssessView != null) {
                    iHistoryAssessView.onHistoryAssessSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (iHistoryAssessView != null) {
                    iHistoryAssessView.onFailed(error);
                }
            }
        });
    }
}
