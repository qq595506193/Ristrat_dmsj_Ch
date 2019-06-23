package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.QuestionFourAdapter;
import com.example.tidus.ristrat.adapter.QuestionOneAdapter;
import com.example.tidus.ristrat.adapter.QuestionThreeAdapter;
import com.example.tidus.ristrat.adapter.QuestionTwoAdapter;
import com.example.tidus.ristrat.adapter.RiskTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;
import com.example.tidus.ristrat.mvp.presenter.RiskAssessmentPresenter;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class RiskAssessmentActivity extends BaseMvpActivity<IRiskAssessmentContart.IRiskAssessmentModel, IRiskAssessmentContart.RiskAssessmentPresenter> implements IRiskAssessmentContart.IRiskAssessmentView {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.iv_message)
    ImageView iv_message;
    @BindView(R.id.tv_login_name)
    TextView tv_login_name;
    @BindView(R.id.iv_login_icon)
    ImageView iv_login_icon;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_office)
    TextView tv_office;
    @BindView(R.id.tv_mark)
    TextView tv_mark;
    @BindView(R.id.rv_question_list)
    RecyclerView rv_question_list;
    @BindView(R.id.rb_score_01)
    RadioButton rb_score_01;
    @BindView(R.id.rb_score_02)
    RadioButton rb_score_02;
    @BindView(R.id.rg_select)
    RadioGroup rg_select;
    @BindView(R.id.btn_log)
    Button btn_log;
    @BindView(R.id.tv_score_01)
    TextView tv_score_01;
    @BindView(R.id.rv_question_check_01)
    RecyclerView rv_question_check_01;
    @BindView(R.id.tv_score_02)
    TextView tv_score_02;
    @BindView(R.id.rv_question_check_02)
    RecyclerView rv_question_check_02;
    @BindView(R.id.tv_score_03)
    TextView tv_score_03;
    @BindView(R.id.rv_question_check_03)
    RecyclerView rv_question_check_03;
    @BindView(R.id.tv_score_04)
    TextView tv_score_04;
    @BindView(R.id.rv_question_check_04)
    RecyclerView rv_question_check_04;
    @BindView(R.id.btn_sign_list)
    Button btn_sign_list;
    @BindView(R.id.tv_score_sum)
    TextView tv_score_sum;
    @BindView(R.id.tv_level)
    TextView tv_level;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private RiskTableListAdapter riskTableListAdapter;
    private RiskAssessmentBean.ServerParamsBean server_params;
    private String age;
    private QuestionOneAdapter questionOneAdapter;
    private QuestionTwoAdapter questionTwoAdapter;
    private QuestionThreeAdapter questionThreeAdapter;
    private QuestionFourAdapter questionFourAdapter;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> oneList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> twoList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> threeList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> fourList;

    private ArrayList<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> intentList;
    private int index = 1;//分数
    private int allNum = 0; //总和
    private int FORM_ID = 1;// 问卷Id
    private ArrayList<String> logList = new ArrayList<>();


    @Override
    protected void init() {

    }

    @Override
    protected void initView(Intent intent) {
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        serverParamsBean = (CaseControlBean.ServerParamsBean) getIntent().getSerializableExtra("serverParamsBean");
        age = serverParamsBean.getBIRTHDAY();// 年龄
        // 展示表格适配器
        riskTableListAdapter = new RiskTableListAdapter(App.getContext());
        rv_question_list.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_question_list.setAdapter(riskTableListAdapter);
        /////////////
        // 展示题目适配器1分
        questionOneAdapter = new QuestionOneAdapter(App.getContext(), age);
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
        initOnclick();
    }

    private void initOnclick() {
        btn_sign_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (allNum == 0) {
                    ToastUtils.show("最少勾选一个");
                    return;
                }
                initPresenterCommint();
                LogUtils.e("FORM_ID=====" + FORM_ID);
            }
        });
    }

    private void initPresenterCommint() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "saveReportCommit");
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        params.put("INTEGRAL", allNum);
        params.put("OPTIONS", "[{\"CURRENT_OPTION_ID\":\"2\",\"CURRENT_VALUE\":\"\",\"RISK_FACTOR_ID\":\"1016\"}]");
        params.put("FORM_ID", FORM_ID);
    }

    @Override
    protected void initData() {
        super.initData();


        initPresenterData();


        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_score_01:
                        // 按计分

                        break;
                    case R.id.rb_score_02:
                        // 按临床
                        break;
                }
            }
        });
    }

    private void initPresenterData() {
        oneList = new ArrayList<>();
        twoList = new ArrayList<>();
        threeList = new ArrayList<>();
        fourList = new ArrayList<>();
        intentList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("1");
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryHZfengxianPG");
        params.put("FORM_ID", list);
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        presenter.getRiskAssessment(params);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_risk_assessment;
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
    public void onRiskAssessmentSuccess(Object result) {
        if (result != null) {
            if (result instanceof RiskAssessmentBean) {
                if (((RiskAssessmentBean) result).getCode().equals("0")) {
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                    RiskAssessmentBean.ServerParamsBean server_params = ((RiskAssessmentBean) result).getServer_params();
                    this.server_params = server_params;
                    riskTableListAdapter.setWenjuannameBeans(server_params.getWENJUANNAME());
                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean : server_params.getWENJUANNAME()) {
                        this.FORM_ID = wenjuannameBean.getFORM_ID();
                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                switch (wenjuanBean.getFACTOR_GROUP_ID()) {
                                    case "1":
                                        questionOneAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值1的list
                                        questionOneAdapter.setOnCheckedClickListener(new QuestionOneAdapter.onCheckedClickListener() {
                                            @Override
                                            public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                logList.add(itemText);
                                                index = 1;
                                                sum(isChecked);
                                                if (allNum > 0 && allNum <= 20) {
                                                    tv_level.setText("低危");
                                                } else if (allNum > 20 && allNum <= 40) {
                                                    tv_level.setText("中危");
                                                } else if (allNum > 40 && allNum <= 60) {
                                                    tv_level.setText("高危");
                                                } else if (allNum > 60 && allNum <= 80) {
                                                    tv_level.setText("极高危");
                                                } else if (allNum > 80 && allNum <= 100) {
                                                    tv_level.setText("确诊");
                                                }
                                            }
                                        });
                                        break;
                                    case "2":
                                        questionTwoAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值2的list
                                        questionTwoAdapter.setOnCheckedClickListener(new QuestionTwoAdapter.onCheckedClickListener() {
                                            @Override
                                            public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                logList.add(itemText);
                                                index = 2;
                                                sum(isChecked);
                                                if (allNum > 0 && allNum <= 20) {
                                                    tv_level.setText("低危");
                                                } else if (allNum > 20 && allNum <= 40) {
                                                    tv_level.setText("中危");
                                                } else if (allNum > 40 && allNum <= 60) {
                                                    tv_level.setText("高危");
                                                } else if (allNum > 60 && allNum <= 80) {
                                                    tv_level.setText("极高危");
                                                } else if (allNum > 80 && allNum <= 100) {
                                                    tv_level.setText("确诊");
                                                }
                                            }
                                        });
                                        break;
                                    case "3":
                                        questionThreeAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值3的list
                                        questionThreeAdapter.setOnCheckedClickListener(new QuestionThreeAdapter.onCheckedClickListener() {
                                            @Override
                                            public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                logList.add(itemText);
                                                index = 3;
                                                sum(isChecked);
                                                if (allNum > 0 && allNum <= 20) {
                                                    tv_level.setText("低危");
                                                } else if (allNum > 20 && allNum <= 40) {
                                                    tv_level.setText("中危");
                                                } else if (allNum > 40 && allNum <= 60) {
                                                    tv_level.setText("高危");
                                                } else if (allNum > 60 && allNum <= 80) {
                                                    tv_level.setText("极高危");
                                                } else if (allNum > 80 && allNum <= 100) {
                                                    tv_level.setText("确诊");
                                                }
                                            }
                                        });
                                        break;
                                    case "4":
                                        questionFourAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值5的list
                                        questionFourAdapter.setOnCheckedClickListener(new QuestionFourAdapter.onCheckedClickListener() {
                                            @Override
                                            public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                logList.add(itemText);
                                                index = 5;
                                                sum(isChecked);
                                                if (allNum > 0 && allNum <= 20) {
                                                    tv_level.setText("低危");
                                                } else if (allNum > 20 && allNum <= 40) {
                                                    tv_level.setText("中危");
                                                } else if (allNum > 40 && allNum <= 60) {
                                                    tv_level.setText("高危");
                                                } else if (allNum > 60 && allNum <= 80) {
                                                    tv_level.setText("极高危");
                                                } else if (allNum > 80 && allNum <= 100) {
                                                    tv_level.setText("确诊");
                                                }
                                            }
                                        });
                                        break;
                                }
                            }
                        }
                    }

                    tv_name.setText(server_params.getPATIENT_NAME());
                    if (server_params.getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    tv_office.setText(server_params.getIN_DEPT_NAME());
                    tv_mark.setText(server_params.getMEDICAL_REC_NUMBER());
                    btn_log.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 日志
                            Intent intent = new Intent(App.getContext(), LogActivity.class);
                            intent.putStringArrayListExtra("logList", logList);
                            startActivity(intent);
                        }
                    });

                    /////////////////////////////

                }
            }
        }
        Integer integer = Integer.valueOf(age);

        if (oneList.get(0).getRISK_FACTOR_ID() == 1001 && integer > 40 && integer < 60) {
            index = 1;
            sum(true);
            intentList.add(oneList.get(0));
        }
        if (twoList.get(0).getRISK_FACTOR_ID() == 1018 && integer > 60 && integer < 65) {
            index = 2;
            sum(true);
            intentList.add(twoList.get(0));
        }
        if (integer > 76 && threeList.get(0).getRISK_FACTOR_ID() == 1026) {
            index = 3;
            sum(true);
            intentList.add(threeList.get(0));
        }
        if (fourList.get(0).getRISK_FACTOR_NAME().equals("脑卒中") && fourList.get(0).getRISK_FACTOR_ID() == 1036) {
            index = 5;
            sum(true);
            intentList.add(fourList.get(0));
        }


    }

    @Override
    public void onFailed(Object error) {

    }

    private void sum(boolean isChecked) {
        if (isChecked) {
            allNum = index + allNum;
        } else {
            allNum = allNum - index;
        }
        tv_score_sum.setText(allNum + "分");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        oneList.clear();
        oneList = null;
        twoList.clear();
        twoList = null;
        threeList.clear();
        threeList = null;
        fourList.clear();
        fourList = null;
        logList.clear();
        logList = null;

    }
}
