package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
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
import com.example.tidus.ristrat.adapter.QuestionAdapter;
import com.example.tidus.ristrat.adapter.QuestionChuxueAdapter;
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

public class RiskAssessment_02Activity extends BaseMvpActivity<IRiskAssessmentContart.IRiskAssessmentModel, IRiskAssessmentContart.RiskAssessmentPresenter> implements IRiskAssessmentContart.IRiskAssessmentView {

    @BindView(R.id.rv_question_list)
    RecyclerView rv_question_list;
    @BindView(R.id.btn_log)
    Button btn_log;
    @BindView(R.id.lly_01)
    LinearLayout mLly01;

    @BindView(R.id.rv_question_check_01)
    RecyclerView rv_question_check_01;// 第一题列表

    @BindView(R.id.cly_table_01)
    ConstraintLayout cly_table_01;
    @BindView(R.id.cly_table_02)
    ConstraintLayout cly_table_02;


    @BindView(R.id.rv_question_check_01_02)
    RecyclerView rv_question_check_01_02;


    @BindView(R.id.tv_level)
    TextView tv_level;
    @BindView(R.id.tv_level_02)
    TextView tv_level_02;

    @BindView(R.id.tv_score_sum)
    TextView tv_score_sum;
    @BindView(R.id.tv_score_sum_02)
    TextView tv_score_sum_02;

    @BindView(R.id.btn_sign_list)
    Button btn_sign_list;
    @BindView(R.id.btn_sign_list_02)
    Button btn_sign_list_02;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_office)
    TextView tv_office;
    @BindView(R.id.tv_mark)
    TextView tv_mark;

    @BindView(R.id.rg_select)
    RadioGroup rg_select;
    @BindView(R.id.rb_score_jifen)
    RadioButton rb_score_jifen;
    @BindView(R.id.rb_score_linchuang)
    RadioButton rb_score_linchuang;


    private CaseControlBean.ServerParamsBean serverParamsBean;
    private RiskTableListAdapter riskTableListAdapter;
    private RiskAssessmentBean.ServerParamsBean server_params;
    private String age;
    private QuestionAdapter questionAdapter;
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
    private int allNum_02 = 0; //01总和
    private int FORM_ID = 1;// 01问卷Id
    private int FORM_ID_02 = 2;// 01问卷Id
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
    private int form_id;
    private RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean;
    private QuestionChuxueAdapter questionChuxueAdapter;


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

        // 判断选择了几个表
        for (String s : selectQuestionListBean.getIndexTable()) {
            if (s.equals("1")) {
                cly_table_01.setVisibility(View.GONE);
                cly_table_02.setVisibility(View.VISIBLE);
            } else {
                cly_table_01.setVisibility(View.VISIBLE);
                cly_table_02.setVisibility(View.GONE);
            }
        }

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
        // 展示题目适配器1表
        questionAdapter = new QuestionAdapter(App.getContext());
        rv_question_check_01.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_question_check_01.setAdapter(questionAdapter);

        // 展示题目适配器2表

        rv_question_check_01_02.setLayoutManager(new LinearLayoutManager(App.getContext()));


        // 展示合并症或并发症适配器
        otherQuestionOneAdapter = new OtherQuestionOneAdapter(App.getContext(), age);
        rv_question_check_01_02.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_question_check_01_02.setAdapter(otherQuestionOneAdapter);


//        for (String s : selectQuestionListBean.getIndexTable()) {
//            if (s.equals("2")) {
//                cly_table_02.setVisibility(View.VISIBLE);
//                cly_table_01.setVisibility(View.GONE);
//
//            }
//            if (s.equals("1")) {
//                cly_table_01.setVisibility(View.VISIBLE);
//                cly_table_02.setVisibility(View.GONE);
//            }
//        }


        tv_name.setText(serverParamsBean.getPATIENT_NAME());
        if (serverParamsBean.getPATIENT_SEX().equals("M")) {
            tv_sex.setText("男");
        } else {
            tv_sex.setText("女");
        }
        tv_office.setText(serverParamsBean.getIN_DEPT_NAME());
        tv_mark.setText(serverParamsBean.getVISIT_SQ_NO());
        initOnclick();
    }


    private void initOnclick() {
        btn_sign_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                initPresenterCommint();
                LogUtils.e("FORM_ID=====" + FORM_ID);
                String s = selectQuestionListBean.getIndexTable().get(0);
                if (allNum_02 == 0 && s.equals("2")) {
                    cly_table_02.setVisibility(View.VISIBLE);
                    cly_table_01.setVisibility(View.GONE);
                } else {
                    finish();
                }

            }
        });
        btn_sign_list_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initPresenterCommint_02();
                LogUtils.e("FORM_ID=====" + FORM_ID_02);
                String s = selectQuestionListBean.getIndexTable().get(0);
                if (allNum == 0 && s.equals("1")) {
                    cly_table_02.setVisibility(View.GONE);
                    cly_table_01.setVisibility(View.VISIBLE);
                } else {
                    finish();
                }

            }
        });

        riskTableListAdapter.setSetSelectTableListener(new RiskTableListAdapter.SetSelectTableListener() {
            @Override
            public void onClickSelectTable(RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean, int form_id, View v) {
                initPresenterData();
                RiskAssessment_02Activity.this.form_id = form_id;
                if (form_id == 1) {
                    cly_table_01.setVisibility(View.VISIBLE);
                    cly_table_02.setVisibility(View.GONE);
                } else {
                    cly_table_01.setVisibility(View.GONE);
                    cly_table_02.setVisibility(View.VISIBLE);
                }
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

    private void initPresenterCommint_02() {
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
            params.put("FORM_ID", FORM_ID_02);

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
        return R.layout.activity_risk_assessment_02;
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

                } else {
                    ToastUtils.show("提交失败");
                }
            }
        }
    }

    @Override
    public void onRiskAssessmentSuccess(final Object result) {
        if (result != null) {
            if (result instanceof RiskAssessmentBean) {
                if (((RiskAssessmentBean) result).getCode().equals("0")) {
                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : bean.getXUANXIANG()) {
                            if (bean.getFORM_ID() == form_id && xuanxiangBean.getGROUP_TAB_ID() == 1) {
                                // 判断哪个表
                                bean.che_color = true;
                                for (final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {

                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                                    questionAdapter.setSetItemNumber(new QuestionAdapter.SetItemNumber() {
                                        @Override
                                        public void onCheckedClick(View view, int position, boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                            switch (sublistBean.getFACTOR_GROUP_SEQ()) {
                                                case 1:
                                                    index = 1;
                                                    sum(isChecked, sublistBean);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
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

                                                    break;
                                                case 2:
                                                    index = 2;
                                                    sum(isChecked, sublistBean);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
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
                                                    break;
                                                case 3:
                                                    index = 3;
                                                    sum(isChecked, sublistBean);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
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
                                                    break;
                                                case 4:
                                                    index = 5;
                                                    sum(isChecked, sublistBean);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
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
                                                    break;
                                            }


                                        }

                                        @Override
                                        public void onMorenSelect(boolean checked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                            index = 1;
                                            allNum = 0;
                                            sum(checked, sublistBean);
                                        }
                                    });

                                    questionAdapter.notifyDataSetChanged();


                                }
                            } else if (bean.getFORM_ID() == form_id && xuanxiangBean.getGROUP_TAB_ID() == 3) {
                                bean.che_color = true;
                                // 判断哪个表
                                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                    if (questionChuxueAdapter == null) {
                                        questionChuxueAdapter = new QuestionChuxueAdapter(App.getContext(), xuanxiangBean.getWENJUAN());
                                        rv_question_check_01_02.setAdapter(questionChuxueAdapter);
                                    }
                                    questionChuxueAdapter.setSetItemNumber(new QuestionChuxueAdapter.SetItemNumber() {
                                        @Override
                                        public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                            switch (sublistBean.getFACTOR_GROUP_SEQ()) {
                                                case 1:
                                                    index = 1;
                                                    sum_02(isChecked);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level_02.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
                                                    if (allNum_02 > 0 && allNum_02 <= 20) {
                                                        tv_level_02.setText("低危");
                                                    } else if (allNum_02 > 20 && allNum_02 <= 40) {
                                                        tv_level_02.setText("中危");
                                                    } else if (allNum_02 > 40 && allNum_02 <= 60) {
                                                        tv_level_02.setText("高危");
                                                    } else if (allNum_02 > 60 && allNum_02 <= 80) {
                                                        tv_level_02.setText("极高危");
                                                    } else if (allNum_02 > 80 && allNum_02 <= 100) {
                                                        tv_level_02.setText("确诊");
                                                    }

                                                    break;
                                                case 2:
                                                    index = 1;
                                                    sum_02(isChecked);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level_02.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
                                                    if (allNum > 0 && allNum <= 20) {
                                                        tv_level_02.setText("低危");
                                                    } else if (allNum_02 > 20 && allNum_02 <= 40) {
                                                        tv_level_02.setText("中危");
                                                    } else if (allNum_02 > 40 && allNum_02 <= 60) {
                                                        tv_level_02.setText("高危");
                                                    } else if (allNum_02 > 60 && allNum_02 <= 80) {
                                                        tv_level_02.setText("极高危");
                                                    } else if (allNum_02 > 80 && allNum_02 <= 100) {
                                                        tv_level_02.setText("确诊");
                                                    }
                                                    break;
                                                case 3:
                                                    index = 1;
                                                    sum_02(isChecked);
                                                    if (isChecked) {
                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                    } else {
                                                        tv_level_02.setText("");
                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                    }
                                                    if (allNum_02 > 0 && allNum_02 <= 20) {
                                                        tv_level_02.setText("低危");
                                                    } else if (allNum_02 > 20 && allNum_02 <= 40) {
                                                        tv_level_02.setText("中危");
                                                    } else if (allNum_02 > 40 && allNum_02 <= 60) {
                                                        tv_level_02.setText("高危");
                                                    } else if (allNum_02 > 60 && allNum_02 <= 80) {
                                                        tv_level_02.setText("极高危");
                                                    } else if (allNum_02 > 80 && allNum_02 <= 100) {
                                                        tv_level_02.setText("确诊");
                                                    }
                                                    break;
                                            }
                                        }

                                        @Override
                                        public void onMorenSelect(boolean checked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                            index = 1;
                                            allNum_02 = 0;
                                            sum_02(checked);
                                        }
                                    });
                                    questionChuxueAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    }
                    rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.rb_score_jifen:
                                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : bean.getXUANXIANG()) {
                                            if (bean.getFORM_ID() == 1 && xuanxiangBean.getGROUP_TAB_ID() == 1) {// 判断哪个表
                                                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                                                    questionAdapter.setSetItemNumber(new QuestionAdapter.SetItemNumber() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                                            switch (sublistBean.getFACTOR_GROUP_SEQ()) {
                                                                case 1:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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

                                                                    break;
                                                                case 2:
                                                                    index = 2;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 3:
                                                                    index = 3;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 4:
                                                                    index = 5;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                            }


                                                        }

                                                        @Override
                                                        public void onMorenSelect(boolean checked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                                            index = 1;
                                                            allNum = 0;
                                                            sum(checked, sublistBean);
                                                        }
                                                    });
                                                }
                                            }

                                        }
                                    }
                                    break;
                                case R.id.rb_score_linchuang:
                                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : bean.getXUANXIANG()) {
                                            if (bean.getFORM_ID() == 1 && xuanxiangBean.getGROUP_TAB_ID() == 2) {// 判断哪个表
                                                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                                                    questionAdapter.setSetItemNumber(new QuestionAdapter.SetItemNumber() {
                                                        @Override
                                                        public void onCheckedClick(View view, int position, boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                                            switch (sublistBean.getFACTOR_GROUP_SEQ()) {
                                                                case 1:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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

                                                                    break;
                                                                case 2:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 3:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 4:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 5:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 6:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 7:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                                case 8:
                                                                    index = 1;
                                                                    sum(isChecked, sublistBean);
                                                                    if (isChecked) {
                                                                        intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    } else {
                                                                        tv_level.setText("");
                                                                        intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                                    }
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
                                                                    break;
                                                            }


                                                        }

                                                        @Override
                                                        public void onMorenSelect(boolean checked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                                            index = 1;
                                                            allNum = 0;
                                                            sum(checked, sublistBean);
                                                        }
                                                    });
                                                }
                                            }

                                        }
                                    }
                                    break;
                            }
                        }
                    });
                }
            }
        }


    }

    @Override
    public void onFailed(Object error) {

    }

    private void sum(boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
        if (isChecked) {
            allNum = index + allNum;
        } else {
            if (allNum > 0) {
                allNum = allNum - index;
            } else {
                allNum = 0;
            }
        }
        tv_score_sum.setText(allNum + "分");

    }

    private void sum_02(boolean isChecked) {
        if (isChecked) {
            allNum_02 = index + allNum_02;
        } else {
            allNum_02 = allNum_02 - index;
        }
        tv_score_sum_02.setText(allNum_02 + "分");
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
