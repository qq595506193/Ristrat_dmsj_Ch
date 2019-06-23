package com.example.tidus.ristrat.mvp.presenter;

import com.example.tidus.ristrat.callback.IRequestCallback;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;
import com.example.tidus.ristrat.mvp.model.RiskAssessmentModel;

import java.util.HashMap;

public class RiskAssessmentPresenter extends IRiskAssessmentContart.RiskAssessmentPresenter {
    private RiskAssessmentModel riskAssessmentModel;
    private IRiskAssessmentContart.IRiskAssessmentView iRiskAssessmentView;

    public RiskAssessmentPresenter(IRiskAssessmentContart.IRiskAssessmentView iRiskAssessmentView) {
        riskAssessmentModel = new RiskAssessmentModel();
        this.iRiskAssessmentView = iRiskAssessmentView;
    }

    @Override
    public void getRiskAssessment(HashMap<String, Object> params) {
        riskAssessmentModel.getRiskAssessment(params, new IRequestCallback() {
            @Override
            public void onSuccess(Object result) {
                if (iRiskAssessmentView != null) {
                    iRiskAssessmentView.onRiskAssessmentSuccess(result);
                }
            }

            @Override
            public void onFailed(Object error) {
                if (iRiskAssessmentView != null) {
                    iRiskAssessmentView.onFailed(error);
                }
            }
        });
    }
}
