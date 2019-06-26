package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.OtherQuestionOneAdapter;
import com.example.tidus.ristrat.adapter.OtherQuestionThreeAdapter;
import com.example.tidus.ristrat.adapter.OtherQuestionTwoAdapter;
import com.example.tidus.ristrat.adapter.QuestionFourAdapter;
import com.example.tidus.ristrat.adapter.QuestionOneAdapter;
import com.example.tidus.ristrat.adapter.QuestionThreeAdapter;
import com.example.tidus.ristrat.adapter.QuestionTwoAdapter;
import com.example.tidus.ristrat.adapter.RiskTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.CommitBean;
import com.example.tidus.ristrat.bean.CommitDataBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.bean.SelectQuestionListBean;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;
import com.example.tidus.ristrat.mvp.presenter.RiskAssessmentPresenter;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RiskAssessmentActivity extends BaseMvpActivity<IRiskAssessmentContart.IRiskAssessmentModel, IRiskAssessmentContart.RiskAssessmentPresenter> implements IRiskAssessmentContart.IRiskAssessmentView {

    @BindView(R.id.rv_question_list)
    RecyclerView rv_question_list;
    @BindView(R.id.btn_log)
    Button btn_log;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_office)
    TextView mTvOffice;
    @BindView(R.id.tv_mark)
    TextView mTvMark;
    @BindView(R.id.lly_01)
    LinearLayout mLly01;
    @BindView(R.id.btn_sign_list)
    Button mBtnSignList;
    @BindView(R.id.tv_score_sum)
    TextView mTvScoreSum;
    @BindView(R.id.tv_score_sum_02)
    TextView mTvScoreSum_02;
    @BindView(R.id.tv_level)
    TextView mTvLevel;
    @BindView(R.id.tv_level_02)
    TextView mTvLevel_02;
    @BindView(R.id.lly_02)
    LinearLayout mLly02;
    @BindView(R.id.rb_score_01)
    RadioButton mRbScore01;
    @BindView(R.id.rb_score_02)
    RadioButton mRbScore02;
    @BindView(R.id.rg_select)
    RadioGroup mRgSelect;
    @BindView(R.id.cly_01)
    ConstraintLayout mCly01;
    @BindView(R.id.tv_score_01)
    TextView mTvScore01;
    @BindView(R.id.rv_question_check_01)
    RecyclerView mRvQuestionCheck01;
    @BindView(R.id.tv_score_02)
    TextView mTvScore02;
    @BindView(R.id.rv_question_check_02)
    RecyclerView mRvQuestionCheck02;
    @BindView(R.id.tv_score_03)
    TextView mTvScore03;
    @BindView(R.id.rv_question_check_03)
    RecyclerView mRvQuestionCheck03;
    @BindView(R.id.tv_score_04)
    TextView mTvScore04;
    @BindView(R.id.rv_question_check_04)
    RecyclerView mRvQuestionCheck04;
    @BindView(R.id.cly_table_01)
    ConstraintLayout cly_table_01;
    ////////////////////////////第二张表
    @BindView(R.id.cly_table_02)
    ConstraintLayout cly_table_02;
    @BindView(R.id.rg_select_02)
    RadioGroup rg_select_02;
    @BindView(R.id.rv_question_check_01_02)
    RecyclerView mRvQuestionCheck01_02;
    @BindView(R.id.tv_score_02_02)
    TextView mTvScore02_02;
    @BindView(R.id.rv_question_check_02_02)
    RecyclerView mRvQuestionCheck02_02;
    @BindView(R.id.tv_score_03_02)
    TextView mTvScore03_02;
    @BindView(R.id.rv_question_check_03_02)
    RecyclerView mRvQuestionCheck03_02;
    @BindView(R.id.btn_sign_list_02)
    Button btn_sign_list_02;

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
    private ArrayList<String> intentList;
    private int index = 1;//分数
    private int allNum = 0; //总和
    private int FORM_ID = 1;// 问卷Id
    private ArrayList<String> logList = new ArrayList<>();
    private LoginBean loginBean;
    private ImageView iv_close;
    private TextView tv_login_name;
    private ImageView iv_message;
    private QueryHMBean.ServerParamsBean.LISTBean listBean;
    private SelectQuestionListBean selectQuestionListBean;
    private TextView tv_back;
    private ImageView iv_back;
    private CommitDataBean commitDataBean;
    private List<CommitDataBean.DataBean> dataBeans = new ArrayList<>();
    private OtherQuestionOneAdapter otherQuestionOneAdapter;
    private OtherQuestionTwoAdapter otherQuestionTwoAdapter;
    private OtherQuestionThreeAdapter otherQuestionThreeAdapter;


    @Override
    protected void init() {

    }

    @Override
    protected void initView(Intent intent) {
        tv_back = findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        selectQuestionListBean = (SelectQuestionListBean) intent.getSerializableExtra("selectQuestionListBean");
        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) getIntent().getSerializableExtra("serverParamsBean");
        listBean = (QueryHMBean.ServerParamsBean.LISTBean) intent.getSerializableExtra("listBean");

        tv_login_name = findViewById(R.id.tv_login_name);
        tv_login_name.setText(loginBean.getServer_params().getUSER_NAME());
        iv_message = findViewById(R.id.iv_message);
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), MessageActivity.class);
                intent.putExtra("loginBean", loginBean);
                startActivity(intent);
            }
        });
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), UserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ////////ViewPager


        age = serverParamsBean.getBIRTHDAY();// 年龄
        // 展示表格适配器
        riskTableListAdapter = new RiskTableListAdapter(App.getContext());
        rv_question_list.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_question_list.setAdapter(riskTableListAdapter);
        /////////////
        // 展示题目适配器1分
        questionOneAdapter = new QuestionOneAdapter(App.getContext(), age);
        mRvQuestionCheck01.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck01.setAdapter(questionOneAdapter);
        // 展示题目适配器2分
        questionTwoAdapter = new QuestionTwoAdapter(App.getContext(), age);
        mRvQuestionCheck02.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck02.setAdapter(questionTwoAdapter);
        ////////////////
        // 展示题目适配器3分
        questionThreeAdapter = new QuestionThreeAdapter(App.getContext(), age);
        mRvQuestionCheck03.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck03.setAdapter(questionThreeAdapter);
        ///////////////
        // 展示题目适配器5分
        questionFourAdapter = new QuestionFourAdapter(App.getContext(), age);
        mRvQuestionCheck04.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck04.setAdapter(questionFourAdapter);
        //////////////

        // 展示合并症或并发症适配器
        otherQuestionOneAdapter = new OtherQuestionOneAdapter(App.getContext(), age);
        mRvQuestionCheck01_02.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck01_02.setAdapter(otherQuestionOneAdapter);

        // 展示患者自身因素适配器
        otherQuestionTwoAdapter = new OtherQuestionTwoAdapter(App.getContext(), age);
        mRvQuestionCheck02_02.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck02_02.setAdapter(otherQuestionTwoAdapter);

        // 展示治疗相关因素适配器
        otherQuestionThreeAdapter = new OtherQuestionThreeAdapter(App.getContext(), age);
        mRvQuestionCheck03_02.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck03_02.setAdapter(otherQuestionThreeAdapter);

        for (String s : selectQuestionListBean.getIndexTable()) {
            if (s.equals("2")) {
                cly_table_02.setVisibility(View.VISIBLE);
                cly_table_01.setVisibility(View.GONE);

            }
            if (s.equals("1")) {
                cly_table_01.setVisibility(View.VISIBLE);
                cly_table_02.setVisibility(View.GONE);
            }


        }

        riskTableListAdapter.setSetSelectTableListener(new RiskTableListAdapter.SetSelectTableListener() {
            @Override
            public void onClickSelectTable(int position) {
                if (position == 1) {
                    cly_table_02.setVisibility(View.VISIBLE);
                } else {
                    cly_table_02.setVisibility(View.GONE);
                }
                if (position == 0) {
                    cly_table_01.setVisibility(View.VISIBLE);
                } else {
                    cly_table_01.setVisibility(View.GONE);
                }


            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_risk_assessment);
        ButterKnife.bind(this);
    }

    private void initOnclick() {
        mBtnSignList.setOnClickListener(new View.OnClickListener() {

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
        btn_sign_list_02.setOnClickListener(new View.OnClickListener() {
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
//        List<String> list = new ArrayList<>();
//        list.clear();
//
//        HashMap<String, String> commitFormBeanHashMap = new HashMap<>();
//        for (String s : intentList) {
//            list.add("[{\"CURRENT_OPTION_ID\":\"\",\"CURRENT_VALUE\":\"\",\"RISK_FACTOR_ID\":\"" + s + "\"}]");
//        }
        CommitDataBean commitDataBean = new CommitDataBean();
        JSONStringer jsonStringer = new JSONStringer();


        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "saveReportCommit");
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        params.put("INTEGRAL", allNum);
        for (int i = 0; i < intentList.size(); i++) {

            try {
                jsonStringer.array();
                jsonStringer.object().key("CURRENT_OPTION_ID").value("").key("CURRENT_VALUE").value("").key("RISK_FACTOR_ID").value(intentList.get(i)).endObject();
                jsonStringer.endArray();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.put("FORM_ID", FORM_ID);
            LogUtils.e("==========json=========" + jsonStringer);
        }
        params.put("OPTIONS", jsonStringer);

        presenter.getCommit(params);
    }

    @Override
    protected void initData() {
        super.initData();


        initPresenterData();

        LogUtils.e("intentList == " + this.intentList.toString());

    }

    private void initPresenterData() {
        oneList = new ArrayList<>();
        twoList = new ArrayList<>();
        threeList = new ArrayList<>();
        fourList = new ArrayList<>();
        intentList = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryHZfengxianPG");
        params.put("FORM_ID", selectQuestionListBean.getIndexTable());
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
    public void onCommitSuccess(Object result) {
        if (result != null) {
            if (result instanceof CommitBean) {
                if (((CommitBean) result).getCode().equals("0")) {
                    ToastUtils.show("提交成功");
                    finish();
                } else {
                    ToastUtils.show("提交失败");
                }
            }
        }
    }

    @Override
    public void onRiskAssessmentSuccess(Object result) {
        if (result != null) {
            if (result instanceof RiskAssessmentBean) {
                if (((RiskAssessmentBean) result).getCode().equals("0")) {
                    Integer integer = Integer.valueOf(age);
                    //////////////////////
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                    RiskAssessmentBean.ServerParamsBean server_params = ((RiskAssessmentBean) result).getServer_params();
                    this.server_params = server_params;
                    riskTableListAdapter.setWenjuannameBeans(server_params.getWENJUANNAME());
                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean : server_params.getWENJUANNAME()) {

                        if (wenjuannameBean.getFORM_SEQ() == 2) {
                            // 第二个表
                            this.FORM_ID = wenjuannameBean.getFORM_ID();
                            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                                for (final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                    switch (wenjuanBean.getFACTOR_GROUP_ID()) {
                                        case "21":

                                            otherQuestionOneAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值1的list
                                            otherQuestionOneAdapter.setOnCheckedClickListener(new OtherQuestionOneAdapter.onCheckedClickListener() {
                                                @Override
                                                public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                    logList.add(itemText);
                                                    index = 1;
                                                    sum(isChecked);

                                                    if (isChecked) {
                                                        intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        mTvLevel_02.setText("");
                                                        intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                    }
                                                    if (allNum > 0 && allNum <= 20) {
                                                        mTvLevel_02.setText("低危");
                                                    } else if (allNum > 20 && allNum <= 40) {
                                                        mTvLevel_02.setText("中危");
                                                    } else if (allNum > 40 && allNum <= 60) {
                                                        mTvLevel_02.setText("高危");
                                                    } else if (allNum > 60 && allNum <= 80) {
                                                        mTvLevel_02.setText("极高危");
                                                    } else if (allNum > 80 && allNum <= 100) {
                                                        mTvLevel_02.setText("确诊");
                                                    }
                                                }
                                            });
                                            otherQuestionOneAdapter.notifyDataSetChanged();
                                            break;
                                        case "20":
                                            twoList.addAll(wenjuanBean.getSublist());

                                            otherQuestionTwoAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值2的list
                                            otherQuestionTwoAdapter.setOnCheckedClickListener(new OtherQuestionTwoAdapter.onCheckedClickListener() {
                                                @Override
                                                public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                    logList.add(itemText);
                                                    index = 2;
                                                    sum(isChecked);
                                                    if (isChecked) {
                                                        intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        mTvLevel_02.setText("");
                                                        intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                    }
                                                    if (allNum > 0 && allNum <= 20) {
                                                        mTvLevel_02.setText("低危");
                                                    } else if (allNum > 20 && allNum <= 40) {
                                                        mTvLevel_02.setText("中危");
                                                    } else if (allNum > 40 && allNum <= 60) {
                                                        mTvLevel_02.setText("高危");
                                                    } else if (allNum > 60 && allNum <= 80) {
                                                        mTvLevel_02.setText("极高危");
                                                    } else if (allNum > 80 && allNum <= 100) {
                                                        mTvLevel_02.setText("确诊");
                                                    }
                                                }
                                            });
                                            otherQuestionTwoAdapter.notifyDataSetChanged();
                                            break;
                                        case "22":
                                            threeList.addAll(wenjuanBean.getSublist());

                                            otherQuestionThreeAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值3的list
                                            otherQuestionThreeAdapter.setOnCheckedClickListener(new OtherQuestionThreeAdapter.onCheckedClickListener() {
                                                @Override
                                                public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                    logList.add(itemText);
                                                    index = 3;
                                                    sum(isChecked);
                                                    if (isChecked) {
                                                        intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        mTvLevel_02.setText("");
                                                        intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                    }
                                                    if (allNum > 0 && allNum <= 20) {
                                                        mTvLevel_02.setText("低危");
                                                    } else if (allNum > 20 && allNum <= 40) {
                                                        mTvLevel_02.setText("中危");
                                                    } else if (allNum > 40 && allNum <= 60) {
                                                        mTvLevel_02.setText("高危");
                                                    } else if (allNum > 60 && allNum <= 80) {
                                                        mTvLevel_02.setText("极高危");
                                                    } else if (allNum > 80 && allNum <= 100) {
                                                        mTvLevel_02.setText("确诊");
                                                    }
                                                }
                                            });
                                            otherQuestionThreeAdapter.notifyDataSetChanged();
                                            break;

                                    }
                                }
                            }
                        }
                        if (wenjuannameBean.getFORM_SEQ() == 1) {
                            // 第一个表
                            this.FORM_ID = wenjuannameBean.getFORM_ID();
                            for (final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                                // 分组
                                // mRgSelect.check(R.id.rb_score_01);
                                if (mRbScore01.isChecked()) {
                                    if (xuanxiangBean.getGROUP_TAB().equals("按计分")) {
                                        for (final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                            switch (wenjuanBean.getFACTOR_GROUP_ID()) {
                                                case "1":
                                                    mTvScore01.setText("以下每项风险因素记 1 分");

                                                    questionOneAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值1的list
                                                    questionOneAdapter.setOnCheckedClickListener(new QuestionOneAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 1;
                                                            sum(isChecked);

                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionOneAdapter.notifyDataSetChanged();
                                                    break;
                                                case "2":
                                                    mTvScore02.setText("以下每项风险因素记 2 分");
                                                    twoList.addAll(wenjuanBean.getSublist());

                                                    questionTwoAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值2的list
                                                    questionTwoAdapter.setOnCheckedClickListener(new QuestionTwoAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 2;
                                                            sum(isChecked);
                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionTwoAdapter.notifyDataSetChanged();
                                                    break;
                                                case "3":
                                                    mTvScore03.setText("以下每项风险因素记 3 分");
                                                    threeList.addAll(wenjuanBean.getSublist());

                                                    questionThreeAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值3的list
                                                    questionThreeAdapter.setOnCheckedClickListener(new QuestionThreeAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 3;
                                                            sum(isChecked);
                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionThreeAdapter.notifyDataSetChanged();
                                                    break;
                                                case "4":
                                                    mTvScore04.setText("以下每项风险因素记 5 分");
                                                    fourList.addAll(wenjuanBean.getSublist());

                                                    questionFourAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值5的list
                                                    questionFourAdapter.setOnCheckedClickListener(new QuestionFourAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 5;
                                                            sum(isChecked);
                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionFourAdapter.notifyDataSetChanged();
                                                    break;
                                            }
                                        }
                                    }
                                } else if (mRbScore02.isChecked()) {
                                    if (xuanxiangBean.getGROUP_TAB().equals("按临床分组")) {
                                        for (final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                            switch (wenjuanBean.getFACTOR_GROUP_ID()) {
                                                case "1":
                                                    mTvScore01.setText("其他");

                                                    questionOneAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值1的list
                                                    questionOneAdapter.setOnCheckedClickListener(new QuestionOneAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 1;
                                                            sum_02(isChecked);

                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionOneAdapter.notifyDataSetChanged();
                                                    break;
                                                case "2":
                                                    mTvScore02.setText("治疗情况");
                                                    twoList.addAll(wenjuanBean.getSublist());

                                                    questionTwoAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值2的list
                                                    questionTwoAdapter.setOnCheckedClickListener(new QuestionTwoAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 2;
                                                            sum_02(isChecked);
                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionTwoAdapter.notifyDataSetChanged();
                                                    break;
                                                case "3":
                                                    mTvScore03.setText("检查情况");
                                                    threeList.addAll(wenjuanBean.getSublist());

                                                    questionThreeAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值3的list
                                                    questionThreeAdapter.setOnCheckedClickListener(new QuestionThreeAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 3;
                                                            sum_02(isChecked);
                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionThreeAdapter.notifyDataSetChanged();
                                                    break;
                                                case "4":
                                                    mTvScore03.setText("治疗情况");
                                                    fourList.addAll(wenjuanBean.getSublist());

                                                    questionFourAdapter.setSublistBeans(wenjuanBean.getSublist());// 设置问卷分值5的list
                                                    questionFourAdapter.setOnCheckedClickListener(new QuestionFourAdapter.onCheckedClickListener() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked) {
                                                            logList.add(itemText);
                                                            index = 5;
                                                            sum_02(isChecked);
                                                            if (isChecked) {
                                                                intentList.add(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            } else {
                                                                mTvLevel.setText("");
                                                                intentList.remove(wenjuanBean.getSublist().get(position).getRISK_FACTOR_ID() + "");
                                                            }
                                                            if (allNum > 0 && allNum <= 20) {
                                                                mTvLevel.setText("低危");
                                                            } else if (allNum > 20 && allNum <= 40) {
                                                                mTvLevel.setText("中危");
                                                            } else if (allNum > 40 && allNum <= 60) {
                                                                mTvLevel.setText("高危");
                                                            } else if (allNum > 60 && allNum <= 80) {
                                                                mTvLevel.setText("极高危");
                                                            } else if (allNum > 80 && allNum <= 100) {
                                                                mTvLevel.setText("确诊");
                                                            }
                                                        }
                                                    });
                                                    questionFourAdapter.notifyDataSetChanged();
                                                    break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    mTvName.setText(server_params.getPATIENT_NAME());
                    if (server_params.getPATIENT_SEX().equals("M")) {
                        mTvSex.setText("男");
                    } else {
                        mTvSex.setText("女");
                    }
                    mTvOffice.setText(server_params.getIN_DEPT_NAME());
                    mTvMark.setText(server_params.getMEDICAL_REC_NUMBER());
                    btn_log.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 日志
                            Intent intent = new Intent(App.getContext(), LogActivity.class);
                            intent.putStringArrayListExtra("logList", logList);
                            startActivity(intent);
                        }
                    });
                    initOnclick();// 点击提交

                    /////////////////////////////

                }
            }
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
        mTvScoreSum.setText(allNum + "分");

    }

    private void sum_02(boolean isChecked) {
        if (isChecked) {
            allNum = index + allNum;
        } else {
            allNum = allNum - index;
        }
        mTvScoreSum_02.setText(allNum + "分");
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
