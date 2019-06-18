package com.example.tidus.ristrat.mvp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.base.BaseActivity;
import com.example.tidus.ristrat.bean.Infrom;
import com.example.tidus.ristrat.bean.Patient;
import com.example.tidus.ristrat.mvp.presenter.InfromPresenter;
import com.example.tidus.ristrat.mvp.view.iview.IinfromView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 患者报告页面
 */
public class InfromActivity extends BaseActivity<InfromPresenter> implements IinfromView {

    @BindView(R.id.title_back) ImageView titleBack;
    @BindView(R.id.title_left_lable) TextView titleLeftLable;
    @BindView(R.id.title_lable) TextView titleLable;
    @BindView(R.id.txti_name) TextView txtiName;
    //    @BindView(R.id.txti_dep) TextView txtiDep;
    @BindView(R.id.txti_age) TextView txtiAge;
    //    @BindView(R.id.txti_doctor) TextView txtiDoctor;
    @BindView(R.id.txti_sex) TextView txtiSex;
    //    @BindView(R.id.txti_nurse) TextView txtiNurse;
    @BindView(R.id.txti_num) TextView txtiNum;
    //    @BindView(R.id.txti_state) TextView txtiState;
    @BindView(R.id.txt_time) TextView txtTime;
    @BindView(R.id.txt_product) TextView txtProduct;
    @BindView(R.id.txt_total) TextView txtTotal;
    @BindView(R.id.txt_grade) TextView txtGrade;
    @BindView(R.id.txt_show) TextView txtShow;
    //    @BindView(R.id.txt_harm) TextView txtHarm;
//    @BindView(R.id.txt_contr) TextView txtContr;
    @BindView(R.id.txt_element) TextView txtElement;
    @BindView(R.id.txt_advise) TextView txtAdvise;
    @BindView(R.id.txt_nursing) TextView txtNursing;
    @BindView(R.id.txt_note) TextView txtNote;
    @BindView(R.id.img_tu) ImageView imgTu;
    private Patient.ServerParamsBean.WillpushListBean listben;
    private String patient_id;

    @Override
    protected void initData() {
        listben = (Patient.ServerParamsBean.WillpushListBean) getIntent().getSerializableExtra("listben");
        SharedPreferences sp = getSharedPreferences("model", MODE_PRIVATE);
        if (listben == null) {
            Gson gson = new Gson();
            String json = sp.getString("pa", null);
            Type type = new TypeToken<Patient.ServerParamsBean.WillpushListBean>() {
            }.getType();
            listben = gson.fromJson(json, type);
            patient_id = listben.getPATIENT_ID();
        }
        String leftable = getIntent().getStringExtra("leftable");
        int topic_id = getIntent().getIntExtra("topic_id", 0);
        patient_id = getIntent().getStringExtra("PATIENT_ID");
        titleLeftLable.setText(leftable);
        titleLable.setText("风险评估报告");
        Map<String, String> params = new HashMap<>();
        params.put("PATIENT_ID", patient_id);
        params.put("SERVICE_PLAN_ID", topic_id + "");
        params.put("Type", "queryJibingReport");

        presenter.getInfrom(params);


    }

    @Override
    protected InfromPresenter getProduct() {
        return new InfromPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_infrom;
    }

    @Override
    public void Success(Infrom infrom) {
        if (listben.getRISK_NAME().equals("确诊")) {
            Glide.with(this).load(R.drawable.quezhen).into(imgTu);
        } else if (listben.getRISK_NAME().equals("极高危")) {
            Glide.with(this).load(R.drawable.jigaowei).into(imgTu);
        } else if (listben.getRISK_NAME().equals("高危")) {
            Glide.with(this).load(R.drawable.gaowei).into(imgTu);
        } else if (listben.getRISK_NAME().equals("中危")) {
            Glide.with(this).load(R.drawable.zhongwei).into(imgTu);
        } else if (listben.getRISK_NAME().equals("低危")) {
            Glide.with(this).load(R.drawable.diwei).into(imgTu);
        }
        txtiName.setText("姓名:" + infrom.getServer_params().getPATIENT_NAME());
        txtiAge.setText("年龄：" + infrom.getServer_params().getBIRTHDAY());
        if(infrom.getServer_params().getPATIENT_SEX().equals("M")){
            txtiSex.setText("性别：" +"男" );
        }else{
            txtiSex.setText("性别：" +"女" );
        }

        txtiNum.setText("病历号：" + listben.getMEDICAL_REC_NUMBER());
//        txtiDoctor.setText("主管医生：张明亮");
//        txtiNurse.setText("主管护士：张晓楠");
//        if (listben.getRISK_NAME().equals("确诊")) {
//            SpannableString sp = new SpannableString("风险等级：" + listben.getRISK_NAME());
//            //设置背景颜色
//            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#f72e54")), 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), 3 ,5,SPAN_EXCLUSIVE_EXCLUSIVEannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            txtiState.setText(sp);
//        } else if (listben.getRISK_NAME().equals("极高危")) {
//            SpannableString sp = new SpannableString("评估状态：" + listben.getRISK_NAME());
//            //设置背景颜色
//            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#f56500")), 5, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            txtiState.setText(sp);
//        } else if (listben.getRISK_NAME().equals("高危")) {
//            SpannableString sp = new SpannableString("评估状态：" + listben.getRISK_NAME());
//            //设置背景颜色
//            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#f3ca00")), 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), 3 ,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            txtiState.setText(sp);
//        } else if (listben.getRISK_NAME().equals("中危")) {
//            SpannableString sp = new SpannableString("评估状态：" + listben.getRISK_NAME());
//            //设置背景颜色
//            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#54a5f5")), 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), 3 ,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            txtiState.setText(sp);
//        } else if (listben.getRISK_NAME().equals("低危")) {
//            SpannableString sp = new SpannableString("评估状态：" + listben.getRISK_NAME());
//            //设置背景颜色
//            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#009543")), 5, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
////		sp.setSpan(new ForegroundColorSpan(Color.YELLOW), 3 ,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            txtiState.setText(sp);
//        }
        String one = infrom.getServer_params().getSublist().get(0).getREPORT_TIME().substring(0, 4);
        String two = infrom.getServer_params().getSublist().get(0).getREPORT_TIME().substring(4, 6);
        String thr = infrom.getServer_params().getSublist().get(0).getREPORT_TIME().substring(6, 8);
        txtTime.setText(one + "-" + two + "-" + thr);
        if (infrom.getServer_params().getSublist().get(0).getREPORT_CODE() != null) {
            txtProduct.setText(String.valueOf(infrom.getServer_params().getSublist().get(0).getREPORT_CODE()));
        }


        txtTotal.setText("" + infrom.getServer_params().getSublist().get(0).getCURRENT_RISK_VALUE());
        txtTotal.setTextColor(Color.RED);
        if (infrom.getServer_params().getSublist().get(0).getRISK_NAME() != null) {
            txtGrade.setText(String.valueOf(infrom.getServer_params().getSublist().get(0).getRISK_NAME()));
            if (listben.getRISK_NAME().equals("确诊")) {
                txtGrade.setTextColor(Color.parseColor("#f72e54"));
            } else if (listben.getRISK_NAME().equals("极高危")) {
                txtGrade.setTextColor(Color.parseColor("#f56500"));
            } else if (listben.getRISK_NAME().equals("高危")) {
                txtGrade.setTextColor(Color.parseColor("#f3ca00"));
            } else if (listben.getRISK_NAME().equals("中危")) {
                txtGrade.setTextColor(Color.parseColor("#54a5f5"));
            } else if (listben.getRISK_NAME().equals("低危")) {
                txtGrade.setTextColor(Color.parseColor("#009543"));
            }
        }
        String element = "";
        for (int i = 0; i < infrom.getServer_params().getWxys().size(); i++) {
            element += "，" + infrom.getServer_params().getWxys().get(i).getRISK_FACTOR_NAME();
        }
        String substring = element.substring(1, element.length());
        txtElement.setText(substring);
        String doctor_advice = infrom.getServer_params().getSublist().get(0).getDOCTOR_ADVICE();
        txtAdvise.setText(doctor_advice);
        String nurse_advice = infrom.getServer_params().getSublist().get(0).getNURSE_ADVICE();
        txtNursing.setText(nurse_advice);
        String patient_advice = infrom.getServer_params().getSublist().get(0).getPATIENT_ADVICE();
        txtNote.setText(patient_advice);
        if (infrom.getServer_params().getSublist().get(0).getRISK_DETAIL_DESC() != null) {
            txtShow.setText(String.valueOf(infrom.getServer_params().getSublist().get(0).getRISK_DETAIL_DESC()));
        }

    }

    @Override
    public void Faild(Throwable e) {
        Log.e("222", "Faild: " + e.getMessage());
    }

    @Override
    public Context context() {
        return this;
    }




    @OnClick({R.id.title_back, R.id.title_left_lable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_left_lable:
                finish();
                break;
        }
    }
}
