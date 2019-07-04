package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.RiskTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.bean.SelectQuestionListBean;
import com.example.tidus.ristrat.contract.IRiskAssessmentContart;
import com.example.tidus.ristrat.mvp.presenter.RiskAssessmentPresenter;
import com.example.tidus.ristrat.mvp.view.fragment.OneTableFragment;
import com.example.tidus.ristrat.mvp.view.fragment.TwoTableFragment;
import com.example.tidus.ristrat.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;

public class RiskAssessActivity extends BaseMvpActivity<IRiskAssessmentContart.IRiskAssessmentModel, IRiskAssessmentContart.RiskAssessmentPresenter> implements IRiskAssessmentContart.IRiskAssessmentView {
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
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private TextView tv_back;
    private ImageView iv_back;
    private ImageView iv_close;
    private TextView tv_login_name;
    private ImageView iv_message;
    private String age;

    private RiskTableListAdapter riskTableListAdapter;

    private QueryHMBean.ServerParamsBean.LISTBean listBean;
    private SelectQuestionListBean selectQuestionListBean;
    private RiskAssessmentBean.ServerParamsBean server_params;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private LoginBean loginBean;


    @Override
    protected void init() {

    }

    @Subscribe
    @Override
    protected void initView(Intent intent) {
        selectQuestionListBean = (SelectQuestionListBean) intent.getSerializableExtra("selectQuestionListBean");
        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) getIntent().getSerializableExtra("serverParamsBean");
        listBean = (QueryHMBean.ServerParamsBean.LISTBean) intent.getSerializableExtra("listBean");


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

        age = serverParamsBean.getBIRTHDAY();// 年龄
        // 展示表格适配器
        riskTableListAdapter = new RiskTableListAdapter(App.getContext());
        rv_question_list.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_question_list.setAdapter(riskTableListAdapter);
        // viewPager添加fragment
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        OneTableFragment oneTableFragment = new OneTableFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selectQuestionListBean", selectQuestionListBean);
                        bundle.putSerializable("serverParamsBean", serverParamsBean);
                        oneTableFragment.setArguments(bundle);
                        return oneTableFragment;
                    case 1:
                        return new TwoTableFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });


        riskTableListAdapter.setSetSelectTableListener(new RiskTableListAdapter.SetSelectTableListener() {
            @Override
            public void onClickSelectTable(RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean, int position, View v) {
                switch (position) {
                    case 0:
                        view_pager.setCurrentItem(0);
                        break;
                    case 1:
                        view_pager.setCurrentItem(1);
                        break;
                }
            }
        });

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
    protected int bindLayoutId() {
        return R.layout.activity_risk_assess;
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
     * 提交成功返回
     *
     * @param result
     */
    @Override
    public void onCommitSuccess(Object result) {

    }

    /**
     * 拉去评估数据成功返回
     *
     * @param result
     */
    @Override
    public void onRiskAssessmentSuccess(Object result) {
        if (result != null) {
            if (result instanceof RiskAssessmentBean) {
                if (((RiskAssessmentBean) result).getCode().equals("0")) {
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                    final Integer integer = Integer.valueOf(age);
                    riskTableListAdapter.setWenjuannameBeans(((RiskAssessmentBean) result).getServer_params().getWENJUANNAME());
                    tv_name.setText(((RiskAssessmentBean) result).getServer_params().getPATIENT_NAME());
                    if (((RiskAssessmentBean) result).getServer_params().getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    tv_office.setText(((RiskAssessmentBean) result).getServer_params().getIN_DEPT_NAME());
                    tv_mark.setText(((RiskAssessmentBean) result).getServer_params().getMEDICAL_REC_NUMBER());
                } else {
                    LogUtils.e(((RiskAssessmentBean) result).getMessage());
                }
            }
        }
    }

    @Override
    public void onFailed(Object error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
