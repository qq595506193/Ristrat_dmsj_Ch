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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.BusinessListAdalter;
import com.example.tidus.ristrat.adapter.SelectedTablesAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.SelectedTablesBean;
import com.example.tidus.ristrat.contract.ISelectedTablesContract;
import com.example.tidus.ristrat.fragment.SelectedTablesFragment;
import com.example.tidus.ristrat.mvp.presenter.SelectedTablesPresenter;
import com.example.tidus.ristrat.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by TriumphalSun
 * on 2019/7/15 0015
 */
public class SelectedTablesActivity extends BaseMvpActivity<ISelectedTablesContract.ISelectedTablesModel, ISelectedTablesContract.SelectedTablesPresenter> implements ISelectedTablesContract.ISelectedTablesView {

    // 姓名
    @BindView(R.id.tv_name)
    TextView tv_name;
    // 性别
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    // 科室
    @BindView(R.id.tv_office)
    TextView tv_office;
    // 床位号
    @BindView(R.id.tv_bed_id)
    TextView tv_bed_id;
    // 业务列表
    @BindView(R.id.rv_select_business)
    RecyclerView rv_select_business;
    // 选题布局
    @BindView(R.id.lly_select_type)
    LinearLayout lly_select_type;
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
    private SelectedTablesFragment selectTablesFragment;
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
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryBusinessForms");
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        presenter.getSelectedTables(params);
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

    private void initViewPagerFragment(List<SelectedTablesBean.ServerParamsBean.BusinesslistBean> businesslist) {

        SelectedTablesAdapter selectedTablesAdapter = new SelectedTablesAdapter(App.getContext(), businesslist);
        rv_select_tables.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_select_tables.setAdapter(selectedTablesAdapter);
        selectedTablesAdapter.notifyDataSetChanged();
        selectedTablesAdapter.setSetSelectTables(new SelectedTablesAdapter.SetSelectTables() {
            @Override
            public void onSelectTables(int position, SelectedTablesBean.ServerParamsBean.BusinesslistBean businesslistBean) {
                // fragment
                selectTablesFragment = new SelectedTablesFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, selectTablesFragment);
                // 通过bundle传值给MyFragment
                Bundle bundle = new Bundle();
                bundle.putSerializable(SelectedTablesFragment.TAG, businesslistBean);
                selectTablesFragment.setArguments(bundle);
                fragmentTransaction.commit();

                selectTablesFragment.setSetFormId(new SelectedTablesFragment.SetFormId() {
                    @Override
                    public void onFormId(boolean checked, int form_id) {

                        LogUtils.e("form_id===" + form_id);
                        if (checked) {
                            list_form_id.add(form_id);
                        } else {
                            list_form_id.remove(form_id);
                        }
                        LogUtils.e("选了===" + list.size() + "个表");
                        initButAssess(list_form_id);// 选了几个表
                    }
                });
            }
        });
//        SelectTablesAdapter selectTablesAdapter = new SelectTablesAdapter(App.getContext(), list);
//        rv_select_tables.setLayoutManager(new LinearLayoutManager(App.getContext()));
//        rv_select_tables.setAdapter(selectTablesAdapter);
//        selectTablesAdapter.setSetSelectTables(new SelectTablesAdapter.SetSelectTables() {
//            @Override
//            public void onSelectTables(int position, List<NowSelectTablesBean.ServerParamsBean.BusinesslistBean.ListformsBean> listforms) {
//                // fragment
//                selectTablesFragment = new SelectTablesFragment();
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, selectTablesFragment);
//                // 通过bundle传值给MyFragment
//                Bundle bundle = new Bundle();
//                bundle.putString(SelectTablesFragment.TAG, strs[position]);
//                selectTablesFragment.setArguments(bundle);
//                fragmentTransaction.commit();
//            }
//        });
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
        return R.layout.activity_select_tables;
    }

    @Override
    public BasePresenter initPresenter() {
        return new SelectedTablesPresenter();
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
     * 查询成功回调
     *
     * @param result
     */
    @Override
    public void onSelectedTablesSuccess(Object result) {
        if (result != null) {
            if (result instanceof SelectedTablesBean) {
                if (((SelectedTablesBean) result).getCode().equals("0")) {
                    // 业务列表
                    BusinessListAdalter businessListAdalter = new BusinessListAdalter();
                    rv_select_business.setLayoutManager(new LinearLayoutManager(App.getContext()));
                    rv_select_business.setAdapter(businessListAdalter);
                    businessListAdalter.notifyDataSetChanged();
                    // 切换设置
                    initViewPagerFragment(((SelectedTablesBean) result).getServer_params().getBusinesslist());
                }
            }
        }
    }
}
