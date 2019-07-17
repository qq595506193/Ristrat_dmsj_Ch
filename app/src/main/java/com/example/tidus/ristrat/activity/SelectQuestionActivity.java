package com.example.tidus.ristrat.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.CheckRiskBean;
import com.example.tidus.ristrat.bean.EvaluatingBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.bean.SelectQuestionListBean;
import com.example.tidus.ristrat.contract.ICheckRiskContract;
import com.example.tidus.ristrat.contract.IEvaluatingContract;
import com.example.tidus.ristrat.mvp.presenter.CheckRiskPresenter;
import com.example.tidus.ristrat.mvp.presenter.EvaluatingPresenter;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectQuestionActivity extends AppCompatActivity implements IEvaluatingContract.IEvaluatingView, ICheckRiskContract.ICheckRiskView {
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.ck_question_01)
    CheckBox ck_question_01;
    @BindView(R.id.ck_question_02)
    CheckBox ck_question_02;
    @BindView(R.id.tv_user_id_01)
    TextView tv_user_id_01;
    @BindView(R.id.tv_user_id_02)
    TextView tv_user_id_02;
    @BindView(R.id.btn_sure)
    Button btn_sure;
    @BindView(R.id.ctl_select_question)
    ConstraintLayout ctl_select_question;
    private boolean question_01 = false;
    private boolean question_02 = false;
    private List<String> selectQuestionList = new ArrayList<>();
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private LoginBean loginBean;
    private QueryHMBean.ServerParamsBean.LISTBean listBean;
    private SelectQuestionListBean selectQuestionListBean;
    private EvaluatingPresenter evaluatingPresenter;
    private List<List<Integer>> departmentList = new ArrayList<>();
    private List<Integer> keshi_id = new ArrayList<>();
    private CheckRiskPresenter checkRiskPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_select_pop);
        selectQuestionListBean = new SelectQuestionListBean();
        evaluatingPresenter = new EvaluatingPresenter(this);
        checkRiskPresenter = new CheckRiskPresenter(this);

        initView();
        initData();
        initListener();
    }

    private void initData() {
        // 加勾选请求
        initCheckData();
    }

    private void initCheckData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryWENJUANNames");
        params.put("DEPARTMENT", departmentList.get(0));
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        LogUtils.e("加勾选请求接口" + params.toString());
        checkRiskPresenter.getCheckRisk(params);
    }

    private void initPresenterEvalutingData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "isAssess");
        params.put("FORM_IDS", selectQuestionListBean.getIndexTable());
        params.put("VISIT_SQ_NO", serverParamsBean.getVISIT_SQ_NO());
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        params.put("BED_NUMBER", serverParamsBean.getBED_NUMBER());
        evaluatingPresenter.getEvaluating(params);
    }

    private void initListener() {
        ck_question_01.setOnCheckedChangeListener(myCheckChangelistener);

        ck_question_02.setOnCheckedChangeListener(myCheckChangelistener);

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!question_01 && !question_02) {
                    ToastUtils.show("请至少选择一项");
                    return;
                }
                if (ck_question_01.isChecked()) {
                    selectQuestionList.add("1");
                    //selectQuestionListBean.setIndexTable(selectQuestionList);
                }
                if (ck_question_02.isChecked()) {
                    selectQuestionList.add("2");
                    //selectQuestionListBean.setIndexTable(selectQuestionList);
                }
                Intent intent = new Intent(SelectQuestionActivity.this, RiskAssessment_02Activity.class);

                intent.putExtra("loginBean", loginBean);
                intent.putExtra("listBean", listBean);
                intent.putExtra("selectQuestionListBean", selectQuestionListBean);
                intent.putExtra("serverParamsBean", serverParamsBean);


                startActivity(intent);
                initPresenterEvalutingData();/// 开始监控评估中

                finish();
            }
        });
    }

    CompoundButton.OnCheckedChangeListener myCheckChangelistener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            //设置TextView的内容显示CheckBox的选择结果
            int i = buttonView.getId();
            if (i == R.id.ck_question_01) {
                question_01 = true;
            } else if (i == R.id.ck_question_02) {
                question_02 = true;
            } else {
                question_02 = false;
                question_01 = false;
            }
        }
    };

    private void initView() {
        ButterKnife.bind(this);
        ctl_select_question.getBackground().setAlpha(150);
        listBean = (QueryHMBean.ServerParamsBean.LISTBean) getIntent().getSerializableExtra("listBean");
        loginBean = (LoginBean) getIntent().getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) getIntent().getSerializableExtra("serverParamsBean");
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        keshi_id.add(loginBean.getServer_params().getDEPARTMENT());
        departmentList.add(keshi_id);
    }


    @Override
    public void onEvaluatingSuccess(Object result) {
        if (result != null) {
            if (result instanceof EvaluatingBean) {
                if (((EvaluatingBean) result).getCode().equals("0")) {
                    LogUtils.e("监控中");
                } else {
                    LogUtils.e("监控失败");
                }
            }
        }
    }

    @Override
    public void onFailed(Object error) {

    }

    /**
     * 加勾选成功回调
     *
     * @param result
     */
    @Override
    public void onCheckRiskSuccess(Object result) {
        if (result != null) {
            if (result instanceof CheckRiskBean) {
                if (((CheckRiskBean) result).getCode().equals("0")) {
                    LogUtils.e(((CheckRiskBean) result).getMessage());
                    // 检测加勾选成功
//                    for (CheckRiskBean.ServerParamsBean server_param : ((CheckRiskBean) result).getServer_params()) {
//                        if (server_param.getFORM_ID() == 1) {
//                            if (server_param.getSublist().size() != 0) {
//                                ck_question_01.setEnabled(false);
//                                ck_question_01.setTextColor(Color.GRAY);
//                                tv_user_id_01.setVisibility(View.VISIBLE);
//                                for (CheckRiskBean.ServerParamsBean.SublistBean sublistBean : server_param.getSublist()) {
//                                    tv_user_id_01.setText(sublistBean.getUSER_NAME() + "正在评估");
//                                }
//                            } else {
//                                tv_user_id_01.setVisibility(View.GONE);
//                                ck_question_01.setEnabled(true);
//                                ck_question_01.setTextColor(Color.BLACK);
//                            }
//                        } else if (server_param.getFORM_ID() == 2) {
//                            if (server_param.getSublist().size() != 0) {
//                                ck_question_02.setEnabled(false);
//                                ck_question_02.setTextColor(Color.GRAY);
//                                tv_user_id_02.setVisibility(View.VISIBLE);
//                                for (CheckRiskBean.ServerParamsBean.SublistBean sublistBean : server_param.getSublist()) {
//                                    tv_user_id_02.setText(sublistBean.getUSER_NAME() + "正在评估");
//                                }
//                            } else {
//                                tv_user_id_02.setVisibility(View.GONE);
//                                ck_question_02.setEnabled(true);
//                                ck_question_02.setTextColor(Color.BLACK);
//                            }
//                        }
//
//                    }

                } else {

                    LogUtils.e(((CheckRiskBean) result).getMessage());
                }
            }
        }
    }

    @Override
    public void onCheckRiskFailed(Object error) {

    }
}
