package com.example.tidus.ristrat.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.RiskGroupAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.CommitBean;
import com.example.tidus.ristrat.bean.CommitDataBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.NowSelectTablesBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.bean.SelectQuestionListBean;
import com.example.tidus.ristrat.bean.SelectedTablesBean;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;
import com.example.tidus.ristrat.mvp.presenter.RiskAssessmentPresenter;
import com.example.tidus.ristrat.utils.DateUtil;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by TriumphalSun
 * on 2019/7/17 0017
 */
public class RiskAssessFragment extends BaseMvpFragment<IRiskAssessmentContart.IRiskAssessmentModel, IRiskAssessmentContart.RiskAssessmentPresenter> implements IRiskAssessmentContart.IRiskAssessmentView {

    public static final String TAG = "RiskAssessFragment";

    @BindView(R.id.rg_select)
    RadioGroup rg_select;
    @BindView(R.id.rb_score_jifen)
    RadioButton rb_score_jifen;
    @BindView(R.id.rb_score_linchuang)
    RadioButton rb_score_linchuang;
    // 外层题目列表
    @BindView(R.id.rv_question_check)
    RecyclerView rv_question_check;
    // 模型总分
    @BindView(R.id.tv_score_sum)
    TextView tv_score_sum;
    // 危险等级
    @BindView(R.id.tv_level)
    TextView tv_level;
    // 暂时保存
    @BindView(R.id.btn_save)
    Button btn_save;
    // 提交
    @BindView(R.id.btn_sign_list)
    Button btn_sign_list;

    private RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean;
    private RiskGroupAdapter riskGroupAdapter;
    // 组ID
    private int GROUP_TAB_ID = 1;
    // 总分数
    private int gross_score = 0;
    // 存题ID
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans = new ArrayList<>();// itemId
    private AlertDialog.Builder builder;// dialog
    private CaseControlBean.ServerParamsBean serverParamsBean;// 患者信息
    private SelectedTablesBean.ServerParamsBean.BusinesslistBean businesslistBean;// 业务信息
    private NowSelectTablesBean.ServerParamsBean.BusinesslistBean businesslistBean_now;// 业务信息
    private LoginBean loginBean;// 用户信息
    private QueryHMBean.ServerParamsBean.TixingLISTBean tixingListBean;// 继续评估后接过来的对象
    private SelectQuestionListBean selectQuestionListBean;// 一共有多少个表
    private String commit_form_id;// 记录提交了哪个表

    @Override
    protected void init() {

    }

    // 视图
    @Override
    protected void initView() {
        // 接值
        initGetBundle();
        // 设置值
        initSetValue();
        // 题目
        initRiskGroup();
        // 算分数回调
        initCalculate();
        // 点击提交
        initCommit();


    }

    private void initCommit() {
        // 点击提交
        btn_sign_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.tixing).setTitle("提醒")
                        .setMessage("确认提交吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                initPresenterCommint(wenjuannameBean.getFORM_ID());// 提交问卷
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
            }
        });

        // 点击暂时保存
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(getActivity()).setIcon(R.mipmap.tixing).setTitle("提醒")
                        .setMessage("确认暂时保存吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                initPresenterSave(wenjuannameBean.getFORM_ID());// 暂时保存
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }

    /**
     * 暂时保存
     *
     * @param form_id
     */
    private void initPresenterSave(int form_id) {
        CommitDataBean commitDataBean = new CommitDataBean();
        JSONStringer jsonStringer = new JSONStringer();

        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "saveReportCommit");
        if (serverParamsBean != null) {
            params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());// 患者ID
        } else {
            params.put("PATIENT_ID", tixingListBean.getPATIENT_ID());// 患者ID
        }

        params.put("INTEGRAL", gross_score);
        try {
            String currentDatetime = DateUtil.currentDatetime();// 获取系统当前时间
            jsonStringer.array();
            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean : sublistBeans) {
                jsonStringer.object().key("CURRENT_OPTION_ID").value("").key("CURRENT_VALUE").value("").key("RISK_FACTOR_ID").value(sublistBean.getRISK_FACTOR_ID()).key("ANALYSIS_SOURCE_STR").value(sublistBean.getANALYSIS_SOURCE_STR()).endObject();
            }
            jsonStringer.endArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("FORM_ID", form_id);// 表ID
        params.put("OPTIONS", jsonStringer);// json
        if (businesslistBean != null) {
            params.put("BUSINESS_ID", businesslistBean.getBUSINESS_ID());// 业务ID
        }
        if (businesslistBean_now != null) {
            params.put("BUSINESS_ID", businesslistBean_now.getBUSINESS_ID());// 业务ID
        }
        if (tixingListBean != null) {
            params.put("BUSINESS_ID", tixingListBean.getBUSINESS_ID());// 业务ID
        }
        //params.put("REMINDE_ID", "");// 提醒ID
        //params.put("REPORT_ID", "");// 报告ID
        params.put("BC", 5);// 暂时保存
        LogUtils.e("选过的json=========" + params.toString());
        presenter.getSave(params);
    }


    /**
     * 提交
     *
     * @param form_id
     */
    private void initPresenterCommint(int form_id) {
        commit_form_id = form_id + "";
        CommitDataBean commitDataBean = new CommitDataBean();
        JSONStringer jsonStringer = new JSONStringer();

        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "saveReportCommit");
        if (serverParamsBean != null) {
            params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());// 患者ID
        } else {
            params.put("PATIENT_ID", tixingListBean.getPATIENT_ID());// 患者ID
        }

        params.put("INTEGRAL", gross_score);// 分数
        try {
            String currentDatetime = DateUtil.currentDatetime();// 获取系统当前时间
            jsonStringer.array();
            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean : sublistBeans) {
                if (sublistBean.getANALYSIS_SOURCE_STR() == null || sublistBean.getANALYSIS_SOURCE_STR().equals("")) {
                    jsonStringer.object().key("CURRENT_OPTION_ID").value("").key("CURRENT_VALUE").value("").key("RISK_FACTOR_ID").value(sublistBean.getRISK_FACTOR_ID()).key("ANALYSIS_SOURCE_STR").value(loginBean.getServer_params().getUSER_NAME() + currentDatetime + "确认").endObject();
                } else {
                    jsonStringer.object().key("CURRENT_OPTION_ID").value("").key("CURRENT_VALUE").value("").key("RISK_FACTOR_ID").value(sublistBean.getRISK_FACTOR_ID()).key("ANALYSIS_SOURCE_STR").value(sublistBean.getANALYSIS_SOURCE_STR() + "；" + loginBean.getServer_params().getUSER_NAME() + currentDatetime + "确认").endObject();
                }
            }
            jsonStringer.endArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("FORM_ID", form_id);// 表ID
        params.put("OPTIONS", jsonStringer);// json
        if (businesslistBean != null) {
            params.put("BUSINESS_ID", businesslistBean.getBUSINESS_ID());// 业务ID
        }
        if (businesslistBean_now != null) {
            params.put("BUSINESS_ID", businesslistBean_now.getBUSINESS_ID());// 业务ID
        }
        if (tixingListBean != null) {
            params.put("BUSINESS_ID", tixingListBean.getBUSINESS_ID());// 业务ID
        }
        //params.put("REMINDE_ID", "");// 提醒ID
        //params.put("REPORT_ID", "");// 报告ID
        //params.put("BC", 5);// 暂时保存
        LogUtils.e("选过的json=========" + params.toString());
        presenter.getCommit(params);
    }


    private void initCalculate() {
        riskGroupAdapter.setSetGroupGradeListener(new RiskGroupAdapter.SetGroupGradeListener() {
            @Override
            public void onGroupGradeListener(boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                number(isChecked, sublistBean);
            }
        });
    }

    /**
     * 算分数
     *
     * @param isChecked
     * @param sublistBean
     */
    private void number(boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {

        if (isChecked) {
            sublistBeans.add(sublistBean);
            if (sublistBean.getFACTOR_GROUP_ID() == 1) {
                gross_score += 1;
            } else if (sublistBean.getFACTOR_GROUP_ID() == 2) {
                gross_score += 2;
            } else if (sublistBean.getFACTOR_GROUP_ID() == 3) {
                gross_score += 3;
            } else if (sublistBean.getFACTOR_GROUP_ID() == 4) {
                gross_score += 5;
            } else {
                gross_score += 1;
            }

        } else {
            sublistBeans.remove(sublistBean);
            if (gross_score > 0) {
                if (sublistBean.getFACTOR_GROUP_ID() == 1) {
                    gross_score -= 1;
                } else if (sublistBean.getFACTOR_GROUP_ID() == 2) {
                    gross_score -= 2;
                } else if (sublistBean.getFACTOR_GROUP_ID() == 3) {
                    gross_score -= 3;
                } else if (sublistBean.getFACTOR_GROUP_ID() == 4) {
                    gross_score -= 5;
                } else {
                    gross_score -= 1;
                }
            }
        }
        LogUtils.e("选过的itemId===" + sublistBean.getRISK_FACTOR_ID());
        LogUtils.e("选过的问号内容===" + sublistBean.getANALYSIS_SOURCE_STR());
        LogUtils.e("选过的存入集合的个数===" + sublistBeans.size());

        // 危险等级
        if (gross_score >= 1 && gross_score < 1.5) {
            tv_level.setText("低危");
        } else if (gross_score >= 1.5 && gross_score < 3.0) {
            tv_level.setText("中危");
        } else if (gross_score >= 3.0 && gross_score < 4.5) {
            tv_level.setText("高危");
        } else if (gross_score >= 4.5) {
            tv_level.setText("极高危");
        } else if (gross_score == 0) {
            tv_level.setText("");
        }
        LogUtils.e(gross_score + "分");
        // 设置分数
        tv_score_sum.setText(gross_score + "分");
    }

    private void initSetValue() {

        riskGroupAdapter = new RiskGroupAdapter(getActivity());// 内部有popupwindow弹出框，所以使用Activity上下文
        rv_question_check.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_question_check.setAdapter(riskGroupAdapter);
        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
            // 给单选框赋值
            if (xuanxiangBean.getGROUP_TAB_ID() == 1) {
                rb_score_jifen.setText(xuanxiangBean.getGROUP_TAB());
            } else if (xuanxiangBean.getGROUP_TAB_ID() == 2) {
                rb_score_linchuang.setText(xuanxiangBean.getGROUP_TAB());
            } else {
                rb_score_jifen.setText(xuanxiangBean.getGROUP_TAB());
            }
        }


    }

    private void initRiskGroup() {
        // 两个组的数据
        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
            if (xuanxiangBean.getGROUP_TAB_ID() == GROUP_TAB_ID) {
                riskGroupAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
            }
            if (xuanxiangBean.getGROUP_TAB_ID() == 3) {
                riskGroupAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
            }
        }
        // 分组切换
        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_score_jifen:
                        // 切换分组清空分数
                        gross_score = 0;
                        tv_score_sum.setText(gross_score + "分");
                        // 切换分组清空itemId
                        sublistBeans.clear();
                        //
                        GROUP_TAB_ID = 1;
                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                            if (xuanxiangBean.getGROUP_TAB_ID() == GROUP_TAB_ID) {
                                riskGroupAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                            }
                        }
                        break;
                    case R.id.rb_score_linchuang:
                        // 切换分组清空分数
                        gross_score = 0;
                        tv_score_sum.setText(gross_score + "分");
                        // 切换分组清空itemId
                        sublistBeans.clear();
                        //
                        GROUP_TAB_ID = 2;
                        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                            if (xuanxiangBean.getGROUP_TAB_ID() == GROUP_TAB_ID) {
                                riskGroupAdapter.setWenjuanBeans(xuanxiangBean.getWENJUAN());
                            }
                        }
                        break;
                }
            }
        });

    }

    private void initGetBundle() {
        // 从Activity接过来的问卷对象
        wenjuannameBean = (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean) getArguments().getSerializable(TAG);
        // 每次接到新数据清空集合
        sublistBeans.clear();
        // 接业务对象
        businesslistBean = (SelectedTablesBean.ServerParamsBean.BusinesslistBean) getArguments().getSerializable("businesslistBean");
        businesslistBean_now = (NowSelectTablesBean.ServerParamsBean.BusinesslistBean) getArguments().getSerializable("businesslistBean_now");
        // 接患者信息对象
        serverParamsBean = (CaseControlBean.ServerParamsBean) getArguments().getSerializable("serverParamsBean");
        // 用户信息
        loginBean = (LoginBean) getArguments().getSerializable("loginBean");
        // 提醒信息
        tixingListBean = (QueryHMBean.ServerParamsBean.TixingLISTBean) getArguments().getSerializable("tixingListBean");
        // 一共有多少个表
        selectQuestionListBean = (SelectQuestionListBean) getArguments().getSerializable("selectQuestionListBean");
    }

    // 请求数据
    @Override
    protected void initData() {
        super.initData();
    }

    /**
     * 布局
     *
     * @return
     */
    @Override
    protected int setContentView() {
        return R.layout.fragment_risk_assess;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public BasePresenter initPresenter() {
        return new RiskAssessmentPresenter();
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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCommitSuccess(Object result) {
        if (result != null) {
            if (result instanceof CommitBean) {
                if (((CommitBean) result).getCode().equals("0")) {
                    LogUtils.e("提交" + ((CommitBean) result).getMessage());
                    ToastUtils.show("提交" + ((CommitBean) result).getMessage());
                    // 提交成功后清空分数
                    gross_score = 0;
                    tv_score_sum.setText(gross_score + "分");
                    //setCommitedListener.onCommitedListener(wenjuannameBean.getFORM_ID());
                    riskGroupAdapter.setCommit(true);
                    for (int i = 0; i < selectQuestionListBean.getIndexTable().size(); i++) {
                        String s = selectQuestionListBean.getIndexTable().get(i);
                        if (commit_form_id.equals(s)) {
                            selectQuestionListBean.getIndexTable().remove(i);
                        }
                    }
                    if (selectQuestionListBean.getIndexTable().size() == 0) {
                        getActivity().finish();
                    }

                } else {
                    LogUtils.e("提交" + ((CommitBean) result).getMessage());
                    ToastUtils.show("提交" + ((CommitBean) result).getMessage());
                }
            }
        }
    }

    @Override
    public void onRiskAssessmentSuccess(Object result) {

    }

    /**
     * 保存成功回调
     *
     * @param result
     */
    @Override
    public void onSaveSuccess(Object result) {
        if (result != null) {
            if (result instanceof CommitBean) {
                if (((CommitBean) result).getCode().equals("0")) {
                    LogUtils.e("保存" + ((CommitBean) result).getMessage());
                    ToastUtils.show("保存" + ((CommitBean) result).getMessage());
                } else {
                    LogUtils.e("保存" + ((CommitBean) result).getMessage());
                    ToastUtils.show("保存" + ((CommitBean) result).getMessage());
                }
            }
        }
    }

    @Override
    public void onFailed(Object error) {

    }

    // 提交成功后判断时候还有其他表
    private SetCommitedListener setCommitedListener;

    public interface SetCommitedListener {
        void onCommitedListener(int form_id);
    }

    public void setSetCommitedListener(SetCommitedListener setCommitedListener) {
        this.setCommitedListener = setCommitedListener;
    }

}
