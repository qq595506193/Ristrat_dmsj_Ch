package com.example.tidus.ristrat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.SelectTablesAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.NowSelectTablesBean;
import com.example.tidus.ristrat.contract.INowSelectTablesContract;
import com.example.tidus.ristrat.fragment.SelectTablesFragment;
import com.example.tidus.ristrat.mvp.presenter.NowSelectTablesPresenter;
import com.example.tidus.ristrat.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class NowSelectTablesActivity extends BaseMvpActivity<INowSelectTablesContract.INowSelectTablesModel, NowSelectTablesPresenter> implements INowSelectTablesContract.INowSelectTablesView {

    // 患者名字
    @BindView(R.id.tv_name)
    TextView tv_name;
    // 患者性别
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    // 患者科室
    @BindView(R.id.tv_office)
    TextView tv_office;
    // 患者床位
    @BindView(R.id.tv_bed_id)
    TextView tv_bed_id;
    // 选表列表
    @BindView(R.id.rv_select_tables)
    RecyclerView rv_select_tables;
    // 切换页签
    @BindView(R.id.fragment_container)
    FrameLayout fragment_container;
    // 立即评估按钮
    @BindView(R.id.btn_start_assess)
    Button btn_start_assess;

    private List<String> list = new ArrayList<>();
    private SelectTablesFragment selectTablesFragment;
    private LoginBean loginBean;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private List<Integer> list_form_id = new ArrayList<>();

    @Override
    protected void init() {

    }

    @Override
    protected void initView(Intent intent) {
        // 接取用户信息
        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");
        // 接取患者信息
        serverParamsBean = (CaseControlBean.ServerParamsBean) intent.getSerializableExtra("serverParamsBean");

        // title findViewById
        initFindViewById();
        // 患者信息赋值
        initPatientValue();

    }

    @Override
    protected void initData() {
        super.initData();
        // 网络请求入参
        initPresenterData();
    }

    private void initPresenterData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryBusinessForms");
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        presenter.getNowSelectTables(params);
    }

    private void initViewPagerFragment(List<NowSelectTablesBean.ServerParamsBean.BusinesslistBean> businesslist) {
        SelectTablesAdapter selectTablesAdapter = new SelectTablesAdapter(App.getContext(), businesslist);
        rv_select_tables.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_select_tables.setAdapter(selectTablesAdapter);
        selectTablesAdapter.notifyDataSetChanged();
//        // fragment
//        selectTablesFragment = new SelectTablesFragment();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, selectTablesFragment);
//        // 通过bundle传值给MyFragment
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(SelectTablesFragment.TAG, businesslist.get(0));
//        selectTablesFragment.setArguments(bundle);
//        fragmentTransaction.commit();
        selectTablesAdapter.setSetSelectTables(new SelectTablesAdapter.SetSelectTables() {
            @Override
            public void onSelectTables(int position, NowSelectTablesBean.ServerParamsBean.BusinesslistBean businesslistBean) {
                // fragment
                selectTablesFragment = new SelectTablesFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, selectTablesFragment);
                // 通过bundle传值给MyFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectTablesFragment.TAG, businesslistBean);
                selectTablesFragment.setArguments(bundle);
                fragmentTransaction.commit();

                selectTablesFragment.setSetFormId(new SelectTablesFragment.SetFormId() {
                    @Override
                    public void onFormId(boolean checked, int form_id) {

                        LogUtils.e("form_id===" + form_id);
                        if (checked) {
                            list_form_id.add(form_id);
                        } else {
                            list_form_id.remove(form_id);
                        }
                        LogUtils.e("选了==="+list.size()+"个表");
                        initButAssess(list_form_id);// 选了几个表
                    }
                });
            }
        });


    }

    private void initButAssess(List<Integer> list) {
        btn_start_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), RiskAssessment_02Activity.class);
                startActivity(intent);
            }
        });

    }

    private void initPatientValue() {
        tv_name.setText(serverParamsBean.getPATIENT_NAME());
        if (serverParamsBean.getPATIENT_SEX().equals("M")) {
            tv_sex.setText("男");
        } else {
            tv_sex.setText("女");
        }
        tv_office.setText(serverParamsBean.getIN_DEPT_NAME());
        tv_bed_id.setText(serverParamsBean.getBED_NUMBER() + "床");
    }

    private void initFindViewById() {
        // 上一页
        ImageView iv_back = findViewById(R.id.iv_back);
        TextView tv_back = findViewById(R.id.tv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // 用户名字
        TextView tv_login_name = findViewById(R.id.tv_login_name);
        tv_login_name.setText(loginBean.getServer_params().getUSER_NAME());
        // 消息数量
        TextView tv_message_num = findViewById(R.id.tv_message_num);
        // 消息按钮
        ImageView iv_message = findViewById(R.id.iv_message);
    }

    /**
     * 布局
     *
     * @return
     */
    @Override
    protected int bindLayoutId() {
        return R.layout.activity_now_select_tables;
    }

    @Override
    public BasePresenter initPresenter() {
        return new NowSelectTablesPresenter();
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

    // 立即查询表成功回调
    @Override
    public void onNowSelectTablesSuccess(Object result) {
        if (result != null) {
            if (result instanceof NowSelectTablesBean) {
                if (((NowSelectTablesBean) result).getCode().equals("0")) {
                    // 切换设置
                    initViewPagerFragment(((NowSelectTablesBean) result).getServer_params().getBusinesslist());
                }
            }
        }
    }
}
