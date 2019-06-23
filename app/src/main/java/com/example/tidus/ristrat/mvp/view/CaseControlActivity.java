package com.example.tidus.ristrat.mvp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.contract.ICaseControlContract;
import com.example.tidus.ristrat.mvp.presenter.CaseControlPresenter;
import com.example.tidus.ristrat.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class CaseControlActivity extends BaseMvpActivity<ICaseControlContract.ICaseControlModel, ICaseControlContract.CaseControlPresenter> implements ICaseControlContract.ICaseControlView {


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
    private List<String> DEPARTMENT_ID;// 本科室/本单元
    private String CURRENT_RISK_LEVEL;// 危险等级
    private String sp_sex_str;// 性别的值
    private String sp_danger_level_str;// 危险等级的值
    private LoginBean loginBean;


    @Override
    protected void init() {

    }

    @Override
    protected void initView(Intent intent) {

        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");

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
                intent.putExtra("loginBean", loginBean);
                startActivity(intent);
            }
        });
        initListener();
        rv_patient_list.setLayoutManager(new GridLayoutManager(App.getContext(), 4));
        caseContrilAdapter = new CaseContrilAdapter(App.getContext(), CaseControlActivity.this);
        rv_patient_list.setAdapter(caseContrilAdapter);

        caseContrilAdapter.setSetOnIntentActivity(new CaseContrilAdapter.SetOnIntentActivity() {
            @Override
            public void onStartActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, View view, int position) {

                CaseControlBean.ServerParamsBean serverParamsBean = serverParamsBeans.get(position);
                Intent intent = new Intent(App.getContext(), SelectQuestionActivity.class);
                intent.putExtra("loginBean", loginBean);
                intent.putExtra("serverParamsBean", serverParamsBean);
                startActivity(intent);
            }
        });

        caseContrilAdapter.setSetOnIntentActivityCancel(new CaseContrilAdapter.SetOnIntentActivityCancel() {
            @Override
            public void onStartActivity() {
                Intent intent = new Intent(App.getContext(), CancelQuestionActivity.class);
                intent.putExtra("loginBean", loginBean);
                startActivity(intent);
            }
        });

        caseContrilAdapter.setSetOnIntentActivityHistory(new CaseContrilAdapter.SetOnIntentActivityHistory() {
            @Override
            public void onStratActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, int position) {
                CaseControlBean.ServerParamsBean serverParamsBean = serverParamsBeans.get(position);
                Intent intent = new Intent(App.getContext(), HistoryAssessActivity.class);
                intent.putExtra("loginBean", loginBean);
                intent.putExtra("serverParamsBean", serverParamsBean);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        et_hospital_id.addTextChangedListener(new MyTextWatcher());////////////////////////////
        et_bed.addTextChangedListener(new MyTextWatcher());
        et_name.addTextChangedListener(new MyTextWatcher());
        et_danger_level.addTextChangedListener(new MyTextWatcher());
        et_sex.addTextChangedListener(new MyTextWatcher());
        initPresenterData();


    }

    private void initPresenterData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryPatient_Basic_InfoBybed");
        params.put("VISIT_SQ_NO", VISIT_SQ_NO);
        params.put("PATIENT_NAME", PATIENT_NAME);
        params.put("PATIENT_SEX", PATIENT_SEX);
        params.put("BED_NUMBER", BED_NUMBER);
        params.put("CURRENT_RISK_LEVEL", CURRENT_RISK_LEVEL);
//        params.put("DEPARTMENT_ID", DEPARTMENT_ID);
        presenter.getCaseControl(params);
    }

    private void initListener() {
        VISIT_SQ_NO = et_hospital_id.getText().toString().trim();
        PATIENT_NAME = et_name.getText().toString().trim();
        BED_NUMBER = et_bed.getText().toString().trim();
        sp_sex_str = et_sex.getText().toString().trim();
        sp_danger_level_str = et_danger_level.getText().toString().trim();
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
        if (sp_sex_str.equals("男")) {
            PATIENT_SEX = "M";
        } else if (sp_sex_str.equals("女")) {
            PATIENT_SEX = "W";
        } else {
            PATIENT_SEX = "";
        }

        if (sp_danger_level_str.equals("低危")) {
            CURRENT_RISK_LEVEL = "5";
        } else if (sp_danger_level_str.equals("中危")) {
            CURRENT_RISK_LEVEL = "6";
        } else if (sp_danger_level_str.equals("高危")) {
            CURRENT_RISK_LEVEL = "7";
        } else if (sp_danger_level_str.equals("极高危")) {
            CURRENT_RISK_LEVEL = "8";
        } else if (sp_danger_level_str.equals("确诊")) {
            CURRENT_RISK_LEVEL = "9";
        } else {
            CURRENT_RISK_LEVEL = "";
        }

        final String section_str = rb_section.getText().toString().trim();
        final String element_str = rb_element.getText().toString().trim();
        DEPARTMENT_ID = new ArrayList<>();

//        if (rb_section.isChecked()) {
//            if (section_str.equals("本科室")) {
//                DEPARTMENT_ID.add(loginBean.getServer_params().getDEPARTMENT() + "");
//            }
//        } else if (rb_element.isChecked()) {
//            if (element_str.equals("本单元")) {
//                DEPARTMENT_ID.add(loginBean.getServer_params().getCARE_UNIT() + "");
//            }
//        } else {
//            DEPARTMENT_ID = Collections.singletonList("");
//        }

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
                    LogUtils.e(((CaseControlBean) result).getServer_params() + "");
                    LogUtils.e(((CaseControlBean) result).getMessage());
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


    private final int searchWhat = 1;
    private int pageNum;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == searchWhat) {
                if (msg.obj.toString().equals(et_hospital_id.getText().toString())) {//进行判断
                    pageNum = 1;
                    initPresenterData();
                } else if (msg.obj.toString().equals(et_bed.getText().toString())) {
                    pageNum += 1;
                    initPresenterData();
                } else if (msg.obj.toString().equals(et_danger_level.getText().toString())) {
                    pageNum += 1;
                    initPresenterData();
                } else if (msg.obj.toString().equals(et_name.getText().toString())) {
                    pageNum += 1;
                    initPresenterData();
                } else if (msg.obj.toString().equals(et_sex.getText().toString())) {
                    pageNum += 1;
                    initPresenterData();
                }
            }
        }
    };


    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() != 0 && s.toString().trim().length() != 0) {
                Message msg = Message.obtain();
                msg.what = searchWhat;
                msg.obj = s.toString(); //携带当前值
                mHandler.sendMessageDelayed(msg, 1000);//隔一小段时间发送msg
            } else {
                initPresenterData();
                caseContrilAdapter.notifyDataSetChanged();
            }
        }
    }
}
