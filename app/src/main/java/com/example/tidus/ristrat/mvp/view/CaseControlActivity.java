package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.CaseContrilAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.contract.ICaseControlContract;
import com.example.tidus.ristrat.mvp.presenter.CaseControlPresenter;
import com.example.tidus.ristrat.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class CaseControlActivity extends BaseMvpActivity<ICaseControlContract.ICaseControlModel, ICaseControlContract.CaseControlPresenter> implements ICaseControlContract.ICaseControlView, View.OnClickListener, View.OnFocusChangeListener {


    @BindView(R.id.et_hospital_id)
    EditText et_hospital_id;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.sp_sex)
    Spinner sp_sex;
    @BindView(R.id.et_sex)
    EditText et_sex;
    @BindView(R.id.et_danger_level)
    EditText et_danger_level;
    @BindView(R.id.et_bed)
    EditText et_bed;
    @BindView(R.id.sp_danger_level)
    Spinner sp_danger_level;
    @BindView(R.id.rg_check)
    RadioGroup rg_check;
    @BindView(R.id.rb_section)
    RadioButton rb_section;
    @BindView(R.id.rb_element)
    RadioButton rb_element;
    @BindView(R.id.rv_patient_list)
    RecyclerView rv_patient_list;
    private CaseContrilAdapter caseContrilAdapter;
    private List<CaseControlBean.ServerParamsBean> server_params;
    private ImageView iv_close;
    private ImageView iv_message;
    private String VISIT_SQ_NO;// 床位号
    private String PATIENT_NAME;// 患者名称
    private String PATIENT_SEX;// 性别
    private String BED_NUMBER;// 床位Id
    private String DEPARTMENT_ID;// 本科室/本单元
    private String sp_sex_str;// 性别的值
    private String sp_danger_level_str;// 危险等级的值


    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_message = findViewById(R.id.iv_message);
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
        initListener();
        rv_patient_list.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        caseContrilAdapter = new CaseContrilAdapter(App.getContext(), CaseControlActivity.this);
        rv_patient_list.setAdapter(caseContrilAdapter);


    }

    @Override
    protected void initData() {
        super.initData();
        initPresenterData();


    }

    private void initPresenterData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryPatient_Basic_InfoBybed");
        params.put("VISIT_SQ_NO", VISIT_SQ_NO);
        params.put("PATIENT_NAME", PATIENT_NAME);
        params.put("PATIENT_SEX", PATIENT_SEX);
        params.put("BED_NUMBER", BED_NUMBER);
        // 危险等级
        params.put("DEPARTMENT_ID", DEPARTMENT_ID);
        presenter.getCaseControl(params);
    }

    private void initListener() {
        VISIT_SQ_NO = et_hospital_id.getText().toString().trim();
        PATIENT_NAME = et_name.getText().toString().trim();
        BED_NUMBER = et_bed.getText().toString().trim();
        sp_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {// 监听获取Spinner的值

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被选择项的值
                sp_sex_str = (String) sp_sex.getSelectedItem();
                //把该值传给 TextView
                et_sex.setText(sp_sex_str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        sp_danger_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {// 监听获取Spinner的值

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                //拿到被选择项的值
                sp_danger_level_str = (String) sp_danger_level.getSelectedItem();
                //把该值传给 TextView
                et_danger_level.setText(sp_danger_level_str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        PATIENT_SEX = sp_sex_str;

        rg_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_section:
                        DEPARTMENT_ID = "";// 本科室
                        break;
                    case R.id.rb_element:
                        DEPARTMENT_ID = "";// 本单元
                        break;

                }
            }
        });

    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_case_control;
    }

    @Override
    public void onCaseControlSuccess(Object result) {

        if (result != null) {
            if (result instanceof CaseControlBean) {
                if (((CaseControlBean) result).getCode().equals("0")) {
                    List<CaseControlBean.ServerParamsBean> server_params = ((CaseControlBean) result).getServer_params();
                    this.server_params = server_params;
                    caseContrilAdapter.setCaseControlBean(server_params);
                } else {
                    LogUtils.e(((CaseControlBean) result).getMessage() + "没数据");
                }
            }
        }
    }

    @Override
    public void onFailed(Object error) {
        LogUtils.e(error + "");
    }

    @Override
    public BasePresenter initPresenter() {
        return new CaseControlPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void failure(String msg) {
        LogUtils.e(msg);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.et_hospital_id:
                if (!hasFocus) {
                    initPresenterData();
                }
                break;
            case R.id.et_name:
                if (!hasFocus) {
                    initPresenterData();
                }
                break;
            case R.id.et_bed:
                if (!hasFocus) {
                    initPresenterData();
                }
                break;
            case R.id.et_sex:
                if (!hasFocus) {
                    initPresenterData();
                }
                break;
            case R.id.et_danger_level:
                if (!hasFocus) {
                    initPresenterData();
                }
                break;
        }
    }
}
