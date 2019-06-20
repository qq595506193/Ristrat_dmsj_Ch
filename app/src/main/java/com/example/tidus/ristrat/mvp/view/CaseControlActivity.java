package com.example.tidus.ristrat.mvp.view;

import android.os.Build;
import android.support.annotation.RequiresApi;
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

public class CaseControlActivity extends BaseMvpActivity<ICaseControlContract.ICaseControlModel, ICaseControlContract.CaseControlPresenter> implements ICaseControlContract.ICaseControlView, View.OnClickListener {


    @BindView(R.id.et_hospital_id)
    EditText et_hospital_id;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.sp_sex)
    Spinner sp_sex;
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
        initListener();
        rv_patient_list.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        caseContrilAdapter = new CaseContrilAdapter(App.getContext());
        rv_patient_list.setAdapter(caseContrilAdapter);


    }

    @Override
    protected void initData() {
        super.initData();
        String PATIENT_NAME = "";// 患者名称
        String PATIENT_SEX = "";// 性别
        String BED_NUMBER = "";// 床位Id
        String DEPARTMENT_ID = "";// 本科室/本单元
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryPatient_Basic_InfoBybed");
        params.put("PATIENT_NAME", PATIENT_NAME);
        params.put("PATIENT_SEX", PATIENT_SEX);
        params.put("BED_NUMBER", BED_NUMBER);
        params.put("DEPARTMENT_ID", DEPARTMENT_ID);
        presenter.getCaseControl(params);


    }

    private void initListener() {
        sp_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                List mList = new ArrayList();
//                mList.add(parent.getAccessibilityClassName().toString());
//                //选择列表项的操作
//                ArrayAdapter arrayAdapter = new ArrayAdapter(App.getContext(), R.layout.item_select, mList);
//                //传入的参数分别为 Context , 未选中项的textview , 数据源List
//                //单独设置下拉的textview
//                arrayAdapter.setDropDownViewResource(R.layout.item_drop);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //未选中时候的操作

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
        switch (v.getId()) {
            case R.id.iv_close:// 点击关闭
                finish();
                break;
            case R.id.iv_message:// 点击消息
                break;

        }
    }
}
