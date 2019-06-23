package com.example.tidus.ristrat.contract;

import com.example.lib_core.base.mvp.BasePresenter;
import com.example.lib_core.base.mvp.IBaseModel;
import com.example.lib_core.base.mvp.IBaseView;
import com.example.tidus.ristrat.callback.IRequestCallback;

import java.util.HashMap;

public interface IRiskAssessmentContart {

    abstract class RiskAssessmentPresenter extends BasePresenter<IRiskAssessmentModel, IRiskAssessmentView> {
        public abstract void getRiskAssessment(HashMap<String, Object> params);

        @Override
        public IRiskAssessmentModel getModel() {
            return null;
        }
    }

    interface IRiskAssessmentModel extends IBaseModel {
        void getRiskAssessment(HashMap<String, Object> params, IRequestCallback iRequestCallback);
    }

    interface IRiskAssessmentView extends IBaseView {
        void onRiskAssessmentSuccess(Object result);

        void onFailed(Object error);
    }
}
