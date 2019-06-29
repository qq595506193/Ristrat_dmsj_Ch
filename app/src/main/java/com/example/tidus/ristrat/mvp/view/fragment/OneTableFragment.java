package com.example.tidus.ristrat.mvp.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.QuestionFourAdapter;
import com.example.tidus.ristrat.adapter.QuestionAdapter;
import com.example.tidus.ristrat.adapter.QuestionThreeAdapter;
import com.example.tidus.ristrat.adapter.QuestionTwoAdapter;
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
    @BindView(R.id.tv_score_01)
    TextView tv_score_01;
    @BindView(R.id.tv_score_02)
    TextView tv_score_02;
    @BindView(R.id.tv_score_03)
    TextView tv_score_03;
    @BindView(R.id.tv_score_04)
    TextView tv_score_04;
    @BindView(R.id.rv_question_check_01)
    RecyclerView rv_question_check_01;
    @BindView(R.id.rv_question_check_02)
    RecyclerView rv_question_check_02;
    @BindView(R.id.rv_question_check_03)
    RecyclerView rv_question_check_03;
    @BindView(R.id.rv_question_check_04)
    RecyclerView rv_question_check_04;
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

    private QuestionAdapter questionOneAdapter;
    private QuestionTwoAdapter questionTwoAdapter;
    private QuestionThreeAdapter questionThreeAdapter;
    private QuestionFourAdapter questionFourAdapter;

    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> oneList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> twoList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> threeList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> fourList;
    private ArrayList<String> intentList;
    private SelectQuestionListBean selectQuestionListBean;
    private CaseControlBean.ServerParamsBean serverParamsBean;

    @Override
    protected void init() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (isAdded()) {//判断Fragment已经依附Activity
            selectQuestionListBean = (SelectQuestionListBean) getArguments().getSerializable("selectQuestionListBean");
            serverParamsBean = (CaseControlBean.ServerParamsBean) getArguments().getSerializable("serverParamsBean");
        }
    }

    @Override
    protected void initView() {
        // 展示题目适配器1分
        questionOneAdapter = new QuestionAdapter(App.getContext(), age);
        rv_question_check_01.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        rv_question_check_01.setAdapter(questionOneAdapter);
        // 展示题目适配器2分
        questionTwoAdapter = new QuestionTwoAdapter(App.getContext(), age);
        rv_question_check_02.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        rv_question_check_02.setAdapter(questionTwoAdapter);
        ////////////////
        // 展示题目适配器3分
        questionThreeAdapter = new QuestionThreeAdapter(App.getContext(), age);
        rv_question_check_03.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        rv_question_check_03.setAdapter(questionThreeAdapter);
        ///////////////
        // 展示题目适配器5分
        questionFourAdapter = new QuestionFourAdapter(App.getContext(), age);
        rv_question_check_04.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        rv_question_check_04.setAdapter(questionFourAdapter);
        //////////////
    }


    @Override
    protected void initData() {
        super.initData();
        initPresenterData();
    }

    private void initPresenterData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryHZfengxianPG");
        params.put("FORM_ID", selectQuestionListBean.getIndexTable());
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        presenter.getRiskAssessment(params);
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
