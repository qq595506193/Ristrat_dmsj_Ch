package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.OtherQuestionOneAdapter;
import com.example.tidus.ristrat.adapter.OtherQuestionThreeAdapter;
import com.example.tidus.ristrat.adapter.OtherQuestionTwoAdapter;
import com.example.tidus.ristrat.adapter.QuestionAdapter;
import com.example.tidus.ristrat.adapter.QuestionFourAdapter;
import com.example.tidus.ristrat.adapter.QuestionThreeAdapter;
import com.example.tidus.ristrat.adapter.QuestionTwoAdapter;
import com.example.tidus.ristrat.adapter.RiskTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.CommitBean;
import com.example.tidus.ristrat.bean.CommitDataBean;
import com.example.tidus.ristrat.bean.LogMessageBean;
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
    @BindView(R.id.rv_risk)
    RecyclerView rv_risk;

    private CaseControlBean.ServerParamsBean serverParamsBean;
    private RiskTableListAdapter riskTableListAdapter;
    private RiskAssessmentBean.ServerParamsBean server_params;
    private String age;
    private QuestionAdapter questionOneAdapter;
    private QuestionTwoAdapter questionTwoAdapter;
    private QuestionThreeAdapter questionThreeAdapter;
    private QuestionFourAdapter questionFourAdapter;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> oneList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> twoList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> threeList;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> fourList;
    private ArrayList<String> intentList;
    private int index = 1;//01分数
    private int allNum = 0; //01总和
    private int FORM_ID = 1;// 01问卷Id
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
    private LogMessageBean logMessageBean;


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

        logMessageBean = new LogMessageBean();

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
        questionOneAdapter = new QuestionAdapter(App.getContext(), age);
        rv_risk.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_risk.setAdapter(questionOneAdapter);


        /*// 展示合并症或并发症适配器
        otherQuestionOneAdapter = new OtherQuestionOneAdapter(App.getContext(), age);
        mRvQuestionCheck01_02.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        mRvQuestionCheck01_02.setAdapter(otherQuestionOneAdapter);


        for (String s : selectQuestionListBean.getIndexTable()) {
            if (s.equals("2")) {
                cly_table_02.setVisibility(View.VISIBLE);
                cly_table_01.setVisibility(View.GONE);

            }
            if (s.equals("1")) {
                cly_table_01.setVisibility(View.VISIBLE);
                cly_table_02.setVisibility(View.GONE);
            }


        }*/

        riskTableListAdapter.setSetSelectTableListener(new RiskTableListAdapter.SetSelectTableListener() {
            @Override
            public void onClickSelectTable(int position, View v) {
                if (position == 0) {
//                    cly_table_02.setVisibility(View.GONE);
//                    cly_table_01.setVisibility(View.VISIBLE);
                } else if (position == 1) {
//                    cly_table_02.setVisibility(View.VISIBLE);
//                    cly_table_01.setVisibility(View.GONE);
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
//        btn_sign_list_02.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (allNum == 0) {
//                    ToastUtils.show("最少勾选一个");
//                    return;
//                }
//                initPresenterCommint();
//                LogUtils.e("FORM_ID=====" + FORM_ID);
//            }
//        });
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
                for (String s : intentList) {
                    jsonStringer.object().key("CURRENT_OPTION_ID").value("").key("CURRENT_VALUE").value("").key("RISK_FACTOR_ID").value(s).endObject();
                }
                jsonStringer.endArray();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.put("FORM_ID", FORM_ID);

        }
        LogUtils.e("==========json=========" + jsonStringer);
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
                    final Integer integer = Integer.valueOf(age);
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                    questionOneAdapter.setSublistBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());

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

//    private void sum_02(boolean isChecked) {
//        if (isChecked) {
//            allNum = index + allNum;
//        } else {
//            allNum = allNum - index;
//        }
//        mTvScoreSum_02.setText(allNum + "分");
//    }

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
