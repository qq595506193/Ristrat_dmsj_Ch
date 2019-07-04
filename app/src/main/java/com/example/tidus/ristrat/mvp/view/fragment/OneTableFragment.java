package com.example.tidus.ristrat.mvp.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.QuestionAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.CommitDataBean;
import com.example.tidus.ristrat.bean.InfoBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.bean.SelectQuestionListBean;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;
import com.example.tidus.ristrat.mvp.presenter.RiskAssessmentPresenter;
import com.example.tidus.ristrat.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class OneTableFragment extends BaseMvpFragment<IRiskAssessmentContart.IRiskAssessmentModel, IRiskAssessmentContart.RiskAssessmentPresenter> implements IRiskAssessmentContart.IRiskAssessmentView {

    @BindView(R.id.rg_select)
    RadioGroup rg_select;
    @BindView(R.id.rb_score_01)
    RadioButton rb_score_01;
    @BindView(R.id.rb_score_02)
    RadioButton rb_score_02;
    @BindView(R.id.btn_log)
    Button btn_log;
    @BindView(R.id.rv_question_check)
    RecyclerView rv_question_check;
    @BindView(R.id.btn_sign_list)
    Button btn_sign_list;
    @BindView(R.id.tv_score_sum)
    TextView tv_score_sum;
    @BindView(R.id.tv_level)
    TextView tv_level;

    private RiskAssessmentBean.ServerParamsBean server_params;
    private String age;
    private int index = 1;//01分数
    private int allNum = 0; //01总和
    private int FORM_ID = 1;// 01问卷Id
    private LoginBean loginBean;
    private CommitDataBean commitDataBean;
    private InfoBean infoBean;
    private List<CommitDataBean.DataBean> dataBeans = new ArrayList<>();


    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> oneList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> twoList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> threeList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> fourList;
    private ArrayList<String> intentList;
    private SelectQuestionListBean selectQuestionListBean;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    private QuestionAdapter questionAdapter;

    @Override
    protected void init() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (isAdded()) {//判断Fragment已经依附Activity
            Bundle arguments = getArguments();
            assert getArguments() != null;
            selectQuestionListBean = (SelectQuestionListBean) arguments.getSerializable("selectQuestionListBean");
            serverParamsBean = (CaseControlBean.ServerParamsBean) arguments.getSerializable("serverParamsBean");

        }
    }

    @Override
    protected void initView() {
        // 展示题目适配器
        questionAdapter = new QuestionAdapter(App.getContext());
        rv_question_check.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_question_check.setAdapter(questionAdapter);


        //////////////
    }


    @Override
    protected void initData() {
        super.initData();
        initPresenterData();
    }

    private void initPresenterData() {
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("Type", "queryHZfengxianPG");
//        params.put("FORM_ID", selectQuestionListBean.getIndexTable());
//        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
//        presenter.getRiskAssessment(params);
    }

    @Override
    protected int setContentView() {
        return R.layout.one_table_fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public BasePresenter initPresenter() {
        return new RiskAssessmentPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void onCommitSuccess(Object result) {

    }

    @Override
    public void onRiskAssessmentSuccess(Object result) {
        if (result != null) {
            if (result instanceof RiskAssessmentBean) {
                if (((RiskAssessmentBean) result).getCode().equals("0")) {
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                        if (wenjuannameBean.getFORM_ID() == 1) {
                            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                                if (xuanxiangBean.getGROUP_TAB_ID() == 1) {
                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());// 按计分
                                } else {
                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());// 按临床
                                }

                            }
                        }
                    }

                } else {
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                }
            }
        }
    }

    @Override
    public void onFailed(Object error) {

    }
}
