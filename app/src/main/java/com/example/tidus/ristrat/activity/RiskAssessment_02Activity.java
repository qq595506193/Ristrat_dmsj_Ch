package com.example.tidus.ristrat.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
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
import com.example.tidus.ristrat.adapter.QuestionAdapter;
import com.example.tidus.ristrat.adapter.RiskTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.AnwyAssessBean;
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


    @BindView(R.id.tv_level)
    TextView tv_level;

    @BindView(R.id.tv_score_sum)
    TextView tv_score_sum;

    @BindView(R.id.btn_sign_list)
    Button btn_sign_list;

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
    @BindView(R.id.cly_01)
    ConstraintLayout cly_01;
    @BindView(R.id.rg_select_02)
    RadioGroup rg_select_02;


    //private CaseControlBean.ServerParamsBean serverParamsBean;
    private RiskTableListAdapter riskTableListAdapter;
    private String age;
    private QuestionAdapter questionAdapter;
    private ArrayList<String> intentList;
    private int index = 1;//01分数
    private int allNum = 0; //01总和
    private int FORM_ID = 1;// 01问卷Id
    private ArrayList<String> logList = new ArrayList<>();
    private LoginBean loginBean;
    private ImageView iv_close;
    private TextView tv_login_name;
    private ImageView iv_message;
    private SelectQuestionListBean selectQuestionListBean;
    private TextView tv_back;
    private ImageView iv_back;
    private CommitDataBean commitDataBean;
    private List<CommitDataBean.DataBean> dataBeans = new ArrayList<>();
    private int form_id = 1;
    private int zu_id = 1;
    private RiskAssessmentBean.ServerParamsBean serverParams;
    private QueryHMBean.ServerParamsBean.TixingListBean tixingListBean;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private AlertDialog.Builder builder;
    private List<String> isNum = new ArrayList<>();


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
        serverParamsBean = (CaseControlBean.ServerParamsBean) intent.getSerializableExtra("serverParamsBean");
        //serverParamsBean = (CaseControlBean.ServerParamsBean) getIntent().getSerializableExtra("serverParamsBean");

        // 判断选择了几个表
        if (selectQuestionListBean != null) {
            for (String s : selectQuestionListBean.getIndexTable()) {
                if (s.equals("1")) {
                    form_id = 1;
                    rg_select.setVisibility(View.VISIBLE);
                    rg_select_02.setVisibility(View.GONE);
                } else {
                    form_id = 2;
                    rg_select.setVisibility(View.GONE);
                    rg_select_02.setVisibility(View.VISIBLE);

                }
            }
        } else {
            tixingListBean = (QueryHMBean.ServerParamsBean.TixingListBean) intent.getSerializableExtra("tixingListBean");
            if (tixingListBean.getFORM_ID() == 1) {
                form_id = 1;
                rg_select.setVisibility(View.VISIBLE);
                rg_select_02.setVisibility(View.GONE);
            } else {
                form_id = 2;
                rg_select.setVisibility(View.GONE);
                rg_select_02.setVisibility(View.VISIBLE);
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


        //age = serverParamsBean.getBIRTHDAY();// 年龄
        // 展示表格适配器
        riskTableListAdapter = new RiskTableListAdapter(App.getContext());
        rv_question_list.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_question_list.setAdapter(riskTableListAdapter);
        /////////////
        // 展示题目适配器1表
        questionAdapter = new QuestionAdapter(RiskAssessment_02Activity.this);
        rv_question_check_01.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_question_check_01.setAdapter(questionAdapter);


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


        initOnclick();
    }


    private void initOnclick() {
        btn_sign_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(RiskAssessment_02Activity.this);
                builder.setIcon(R.mipmap.tixing).setTitle("提醒")
                        .setMessage("确认提交吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        initPresenterCommint(form_id);
                        LogUtils.e("FORM_ID=====" + FORM_ID);
                        //String s = selectQuestionListBean.getIndexTable().get(0);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ToDo: 你想做的事情
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });


        riskTableListAdapter.setSetSelectTableListener(new RiskTableListAdapter.SetSelectTableListener() {
            @Override
            public void onClickSelectTable(RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean, int form_id, View v) {
                initPresenterData();
                RiskAssessment_02Activity.this.form_id = form_id;
                if (form_id == 2) {
                    rg_select.setVisibility(View.GONE);
                    rg_select_02.setVisibility(View.VISIBLE);
                } else {
                    rg_select.setVisibility(View.VISIBLE);
                    rg_select_02.setVisibility(View.GONE);
                }
            }
        });


    }

    /**
     * 提交
     *
     * @param form_id
     */
    private void initPresenterCommint(int form_id) {
        CommitDataBean commitDataBean = new CommitDataBean();
        JSONStringer jsonStringer = new JSONStringer();

        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "saveReportCommit");
        if (serverParamsBean != null) {
            params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        } else {
            params.put("PATIENT_ID", tixingListBean.getPATIENT_ID());
        }

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


        }
        params.put("FORM_ID", form_id);
        params.put("OPTIONS", jsonStringer);
        LogUtils.e("==========json=========" + params.toString());
        presenter.getCommit(params);
    }


    @Override
    protected void initData() {
        super.initData();
        initPresenterData();// 选题网络请求
        LogUtils.e("intentList == " + this.intentList.toString());

    }

    /**
     * 选题网络请求
     */
    private void initPresenterData() {
        intentList = new ArrayList<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryHZfengxianPG");
        if (selectQuestionListBean != null) {
            params.put("FORM_ID", selectQuestionListBean.getIndexTable());
        } else {
            AnwyAssessBean anwyAssessBean = new AnwyAssessBean();
            List<String> anwyAssessList = new ArrayList<>();
            anwyAssessList.add(tixingListBean.getFORM_ID() + "");
            anwyAssessBean.setList(anwyAssessList);
            params.put("FORM_ID", anwyAssessBean.getList());
        }
        if (serverParamsBean != null) {
            params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        } else {
            params.put("PATIENT_ID", tixingListBean.getPATIENT_ID());
        }
        LogUtils.e("评估页面拼参===" + params.toString());
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


    /**
     * 提交成功回调
     *
     * @param result
     */
    @Override
    public void onCommitSuccess(Object result) {
        if (result != null) {
            if (result instanceof CommitBean) {
                if (((CommitBean) result).getCode().equals("0")) {
                    ToastUtils.show("提交成功");
                    questionAdapter.setCommit(true);
                    if (form_id == 1) {
                        allNum = 0;
                        tv_score_sum.setText(allNum + "分");
                    } else {
                        allNum = 0;
                        tv_score_sum.setText(allNum + "分");
                    }

                } else {
                    ToastUtils.show("提交失败");
                }
            }
        }
    }

    /**
     * 选题查询成功回调
     *
     * @param result
     */
    @Override
    public void onRiskAssessmentSuccess(final Object result) {
        if (result != null) {
            if (result instanceof RiskAssessmentBean) {
                if (((RiskAssessmentBean) result).getCode().equals("0")) {

                    this.serverParams = ((RiskAssessmentBean) result).getServer_params();
                    // 患者名字
                    tv_name.setText(serverParams.getPATIENT_NAME());
                    // 患者性别
                    if (serverParams.getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    // 患者科室
                    tv_office.setText(serverParams.getIN_DEPT_NAME());
                    // 患者流水号
                    tv_mark.setText(serverParams.getVISIT_SQ_NO());
                    // 表头适配器复制
                    final List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean> wenjuanname = ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME();
                    riskTableListAdapter.setWenjuannameBeans(wenjuanname);
                    // 互斥回调
                    /*questionAdapter.setSetItemChecked(new QuestionAdapter.SetItemChecked() {
                        @Override
                        public void onSetItemChecked(int groupPosition, int itemPosition) {
                            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean : wenjuanname) {
                                if (wenjuannameBean.getFORM_ID() == form_id) {
                                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                                        if (zu_id == xuanxiangBean.getGROUP_TAB_ID()) {
                                            List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuan = xuanxiangBean.getWENJUAN();
                                            for (int i1 = 0; i1 < wenjuan.size(); i1++) {
                                                RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean = wenjuan.get(i1);
                                                if (wenjuanBean.getFACTOR_GROUP_SEQ() == groupPosition) {
                                                    List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublist = wenjuanBean.getSublist();
                                                    for (int i = 0; i < sublist.size(); i++) {
                                                        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean = sublist.get(i);
                                                        if (sublistBean.getMUTEX_GROUP() == 1) {
                                                            sublistBean.setChecked(true);
                                                        }
                                                    }
                                                } else {
                                                    for (int i = 0; i < wenjuanBean.getSublist().size(); i++) {
                                                        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean = wenjuanBean.getSublist().get(i);
                                                        if (sublistBean.getMUTEX_GROUP() == 1) {
                                                            sublistBean.setChecked(false);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            questionAdapter.notifyDataSetChanged();
                        }
                    });*/
                    //
                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : bean.getXUANXIANG()) {
                            if (bean.getFORM_ID() == form_id) {
                                if (form_id == 2) {
                                    rg_select.setVisibility(View.GONE);
                                    rg_select_02.setVisibility(View.VISIBLE);
                                } else {
                                    rg_select.setVisibility(View.VISIBLE);
                                    rg_select_02.setVisibility(View.GONE);

                                }
                                bean.che_color = true;
                                if (1 == zu_id) {

                                    List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuan = xuanxiangBean.getWENJUAN();
                                    for (int i1 = 0; i1 < wenjuan.size(); i1++) {
                                        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean = wenjuan.get(i1);
                                        for (int i = 0; i < wenjuanBean.getSublist().size(); i++) {
                                            wenjuanBean.getSublist().get(i).setPos(i1);
                                            if (wenjuanBean.getSublist().get(i).getIsslect().equals("1")) {
                                                isNum.add(wenjuanBean.getSublist().get(i).getIsslect());
                                                wenjuanBean.getSublist().get(i).setChecked(true);
                                            }
                                        }

                                        questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                                        /*questionAdapter.setSetItemGroupCheckListener(new QuestionAdapter.SetItemGroupCheckListener() {
                                            @Override
                                            public void setItemGroupCheck(boolean isChecked, int position, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                                switch (sublistBean.getFACTOR_GROUP_SEQ()) {
                                                    case 1:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级

                                                        break;
                                                    case 2:
                                                        index = 2;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 3:
                                                        index = 3;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 4:
                                                        index = 5;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                }
                                            }
                                        });*/
                                        questionAdapter.notifyDataSetChanged();
                                    }
                                } else {
                                    List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuan = xuanxiangBean.getWENJUAN();
                                    for (int i1 = 0; i1 < wenjuan.size(); i1++) {
                                        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean = wenjuan.get(i1);
                                        for (int i = 0; i < wenjuanBean.getSublist().size(); i++) {
                                            wenjuanBean.getSublist().get(i).setPos(i1);
                                            if (wenjuanBean.getSublist().get(i).getIsslect().equals("1")) {
                                                wenjuanBean.getSublist().get(i).setChecked(true);
                                            }


                                        }
                                        questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                                        /*questionAdapter.setSetItemGroupCheckListener(new QuestionAdapter.SetItemGroupCheckListener() {
                                            @Override
                                            public void setItemGroupCheck(boolean isChecked, int position, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                                                switch (sublistBean.getFACTOR_GROUP_SEQ()) {
                                                    case 1:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级

                                                        break;
                                                    case 2:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 3:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 4:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 5:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 6:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 7:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;
                                                    case 8:
                                                        index = 1;
                                                        sum(isChecked);
                                                        if (isChecked) {
                                                            intentList.add(sublistBean.getRISK_FACTOR_ID() + "");
                                                        } else {
                                                            tv_level.setText("");
                                                            intentList.remove(sublistBean.getRISK_FACTOR_ID() + "");
                                                        }
                                                        // 危险等级
                                                        break;

                                                }
                                            }
                                        });*/
                                        questionAdapter.notifyDataSetChanged();
                                    }

                                }

                                if (zu_id == 1) {
                                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean1 : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean1 : bean1.getXUANXIANG()) {
                                            if (bean1.getFORM_ID() == form_id && xuanxiangBean1.getGROUP_TAB_ID() == zu_id) {// 判断哪个表
                                                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean1.getWENJUAN()) {
                                                    questionAdapter.setWenjuanBeans(xuanxiangBean1.getWENJUAN());
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            switch (checkedId) {
                                case R.id.rb_score_jifen:
                                    zu_id = 1;
                                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : bean.getXUANXIANG()) {
                                            if (bean.getFORM_ID() == 1 && xuanxiangBean.getGROUP_TAB_ID() == zu_id) {// 判断哪个表
                                                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                                                }
                                            }

                                        }
                                    }
                                    break;
                                case R.id.rb_score_linchuang:
                                    zu_id = 2;
                                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                                    for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean bean : ((RiskAssessmentBean) result).getServer_params().getWENJUANNAME()) {
                                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : bean.getXUANXIANG()) {
                                            if (bean.getFORM_ID() == 1 && xuanxiangBean.getGROUP_TAB_ID() == zu_id) {// 判断哪个表
                                                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                                                    questionAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
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

    private void sum(boolean isChecked) {

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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        logList.clear();
        logList = null;

    }

}
