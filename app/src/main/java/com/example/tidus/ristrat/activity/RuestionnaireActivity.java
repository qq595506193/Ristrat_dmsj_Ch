package com.example.tidus.ristrat.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.RuestAdapter1;
import com.example.tidus.ristrat.base.BaseActivity;
import com.example.tidus.ristrat.bean.Patient;
import com.example.tidus.ristrat.mvp.presenter.PatientPresenter;
import com.example.tidus.ristrat.mvp.view.iview.IRuestionnaireView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 点击计划列表进入的页面   患者列表
 */
public class RuestionnaireActivity extends BaseActivity<PatientPresenter> implements IRuestionnaireView {


    @BindView(R.id.ll) LinearLayout ll;
    @BindView(R.id.title_back) ImageView titleBack;
    @BindView(R.id.title_lable) TextView titleLable;
    @BindView(R.id.btn_senior) Button btnSenior;
    @BindView(R.id.btn_search) Button btnSearch;
    @BindView(R.id.rlv_not_assessed) XRecyclerView rlvNotAssessed;
    @BindView(R.id.txt_all_num) TextView txtAllNum;
    private RuestAdapter1 ruestAdapter;//患者适配器
    @BindView(R.id.title_left_lable) TextView titleLeftLable;
    private PopupWindow mPopupWindow;//高级的Popupwindow
    @BindView(R.id.ed_search) EditText edSearch;
    Map<String, String> params = new HashMap<>();
    private String leftLable = "静脉血栓(VTE)风险评估患者列表";
    private int service_plan_id;
    private String popName;
    private String popSex;
    private String popage;
    private int page = 1;
    private boolean isload = false;
    private List<Patient.ServerParamsBean.WillpushListBean> willpushListBeanList;
    private Button btnPopSearch;
    private TextView edPopSex;
    private View headerView;

    @Override
    protected void initData() {
        String leftable = getIntent().getStringExtra("leftable");
        titleLeftLable.setText(leftable);
        titleLable.setText(leftLable);
        willpushListBeanList = new ArrayList<>();
        service_plan_id = getIntent().getIntExtra("SERVICE_PLAN_ID", 0);
        ruestAdapter = new RuestAdapter1(willpushListBeanList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rlvNotAssessed.setLayoutManager(layoutManager);
//        headerView = LayoutInflater.from(this).inflate(R.layout.ruest_header, null);
        final SharedPreferences sp = getSharedPreferences("model", MODE_PRIVATE);
//        ruestAdapter.setHeaderView(headerView);
        rlvNotAssessed.setAdapter(ruestAdapter);
        ruestAdapter.setListener(new RuestAdapter1.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int pg = willpushListBeanList.get(position).getPG();
                if(pg==1){
                    if (willpushListBeanList.get(position).getRISK_NAME() == null) {
                        Intent intent = new Intent(RuestionnaireActivity.this, RiskActivity.class);
                        intent.putExtra("topic_id", "8001");
                        intent.putExtra("leftable", leftLable);
                        intent.putExtra("age", willpushListBeanList.get(position).getBIRTHDAY());
                        sp.edit().putString("PATIENT_ID", willpushListBeanList.get(position).getPATIENT_ID()).commit();
                        SharedPreferences.Editor editor = sp.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(willpushListBeanList.get(position));
                        editor.putString("pa", json);
                        editor.commit();
                        startActivity(intent);
                    } else {
                        String patient_id = willpushListBeanList.get(position).getPATIENT_ID();
                        Patient.ServerParamsBean.WillpushListBean willpushListBean = willpushListBeanList.get(position);
                        Intent intent = new Intent(RuestionnaireActivity.this, InfromActivity.class);
                        intent.putExtra("leftable", leftLable);
                        intent.putExtra("topic_id", service_plan_id);
                        intent.putExtra("listben", willpushListBean);
                        intent.putExtra("PATIENT_ID", patient_id);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(RuestionnaireActivity.this, "非VTE风险评估计划,无法进行评估", Toast.LENGTH_SHORT).show();
                }
               
            }
        });
        rlvNotAssessed.setPullRefreshEnabled(true);
        rlvNotAssessed.setLoadingMoreEnabled(true);
        rlvNotAssessed.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isload = false;
                params.put("Type", "queryJibingpinggulist");
                params.put("plan_id", service_plan_id + "");
                params.put("Page", "" + page);
                params.put("PageSize", "" + 50);
                params.put("flag", "" + 1);
                presenter.getPatient(params);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rlvNotAssessed.refreshComplete();
                    }
                }, 200);
            }

            @Override
            public void onLoadMore() {
                page++;
                isload = true;
                params.put("Type", "queryJibingpinggulist");
                params.put("plan_id", service_plan_id + "");
                params.put("Page", "" + page);
                params.put("PageSize", "" + 50);
                params.put("flag", "" + 1);
                presenter.getPatient(params);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rlvNotAssessed.loadMoreComplete();
                    }
                }, 200);

            }
        });
    }


    @Override
    protected PatientPresenter getProduct() {
        return new PatientPresenter();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, String> params = new HashMap<>();
        params.put("Type", "queryJibingpinggulist");
        params.put("plan_id", service_plan_id + "");
        params.put("Page", "" + page);
        params.put("PageSize", "" + 50);
        params.put("flag", "" + 1);
        presenter.getPatient(params);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ruestionnaire;
    }

    @Override
    public Context context() {
        return this;
    }

    @OnClick({R.id.title_back, R.id.btn_senior, R.id.btn_search, R.id.title_left_lable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_left_lable:
                finish();
                break;
            case R.id.btn_senior:
                senior();
                break;
            case R.id.btn_search:
                search();
                break;
        }
    }

    //高级搜索
    private void senior() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflatedView = inflater.inflate(R.layout.senior_pop, null, false);//引入弹窗布局
        mPopupWindow = new PopupWindow(inflatedView, 1000, 690);

        final EditText edPopName = (EditText) inflatedView.findViewById(R.id.edpop_name);
        edPopSex = (TextView) inflatedView.findViewById(R.id.edpop_sex);
        final EditText edPopage = (EditText) inflatedView.findViewById(R.id.edpop_age);
        final EditText edPopDepartment = (EditText) inflatedView.findViewById(R.id.edpop_department);
        final EditText edPopDoctor = (EditText) inflatedView.findViewById(R.id.edpop_doctor);
        final EditText edPopNurse = (EditText) inflatedView.findViewById(R.id.edpop_nurse);
        final EditText edPopNum = (EditText) inflatedView.findViewById(R.id.edpop_num);
        btnPopSearch = (Button) inflatedView.findViewById(R.id.btnpop_search);
        edPopSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexChooseDialog();
            }
        });
        btnPopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popName = edPopName.getText().toString();
                popSex = edPopSex.getText().toString();
                popage = edPopage.getText().toString();
                String popDepartment = edPopDepartment.getText().toString().trim();
                String popDoctor = edPopDoctor.getText().toString().trim();
                String popNurse = edPopNurse.getText().toString().trim();
                String popNum = edPopNum.getText().toString().trim();
                params = new HashMap<>();
                params.put("Type", "queryJibingpinggulist");
                params.put("plan_id", service_plan_id + "");
                params.put("Page", "" + page);
                params.put("PageSize", "" + 50);
                params.put("flag", "" + 1);
                params.put("PATIENT_NAME", popName);
                params.put("PATIENT_SEX", popSex);
                params.put("BIRTHDAYST", popage);
                presenter.getPatient(params);
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.setTouchable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        mPopupWindow.showAtLocation(inflatedView, Gravity.CENTER, 0, 0);


    }

    //性别选择器
    private String[] sexArry = new String[]{"男", "女"};// 性别选择

    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
//                 showToast(which+"");
                edPopSex.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }


    //搜索
    private void search() {
        String trim = edSearch.getText().toString().trim();
        params.put("Type", "queryJibingpinggulist");
        params.put("plan_id", service_plan_id + "");
        params.put("Page", "" + page);
        params.put("PageSize", "" + 50);
        params.put("flag", "" + 1);
        params.put("PATIENT_NAME", trim);
        presenter.getPatient(params);
    }


    @Override
    public void Success(Patient patient) {
        if (patient != null) {
            if (!isload) {
                willpushListBeanList.clear();
            }
            willpushListBeanList.addAll(patient.getServer_params().getWillpush_list());
            txtAllNum.setText("全部" + patient.getServer_params().getWillpush_count() + "人");
            ruestAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void Faild(Throwable e) {
        Log.e("ruest", "Faild: " + e.getMessage());
    }


}
