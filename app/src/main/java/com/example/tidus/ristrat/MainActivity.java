package com.example.tidus.ristrat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tidus.ristrat.adapter.PlanAdapter;
import com.example.tidus.ristrat.base.BaseActivity;
import com.example.tidus.ristrat.bean.PlanBean;
import com.example.tidus.ristrat.mvp.presenter.MainPresenter;
import com.example.tidus.ristrat.mvp.view.RuestionnaireActivity;
import com.example.tidus.ristrat.mvp.view.iview.IMainView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 计划列表
 */
public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.title_back) ImageView titleBack;
    @BindView(R.id.title_lable) TextView titleLable;
    @BindView(R.id.title_left_lable) TextView titleLeftLable;
    @BindView(R.id.ed_main_search) EditText edMainSearch;
    @BindView(R.id.btn_main_search) Button btnMainSearch;
    @BindView(R.id.rl_plan) XRecyclerView rlPlan;
    private String leftLable = "计划管理";
    private String search;
    private Map<String, String> params;
    private PlanAdapter planAdapter;
    private List<PlanBean.ServerParamsBean.ListBean> list;
    private int page=1;
    private boolean isload=false;

    @Override
    protected void initData() {
        titleLable.setText(leftLable);
        final String leftable = getIntent().getStringExtra("leftable");
        titleLeftLable.setText(leftable);
        search = edMainSearch.getText().toString().trim();
        list=new ArrayList<>();
        params = new HashMap<>();
        params.put("Type", "queryPlanBycondition");
        params.put("SERVICE_PLAN", search);
        params.put("page", ""+page);
        params.put("pageCount", ""+15);
        params.put("GATHER_TYPE",""+20);
        presenter.getInfrom(params);
        planAdapter=new PlanAdapter(list);
        final SharedPreferences sp = getSharedPreferences("model", MODE_PRIVATE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rlPlan.setLayoutManager(layoutManager);
        planAdapter.setOnItemClickListener(new PlanAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(MainActivity.this, RuestionnaireActivity.class);
                intent.putExtra("leftable",leftLable);
                intent.putExtra("SERVICE_PLAN_ID",list.get(position).getSERVICE_PLAN_ID());
                sp.edit().putInt("SERVICE_PLAN_ID",list.get(position).getSERVICE_PLAN_ID()).commit();
                startActivity(intent);
            }
        });
        rlPlan.setPullRefreshEnabled(true);
        rlPlan.setLoadingMoreEnabled(true);
        rlPlan.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                isload=false;
                params.put("Type", "queryPlanBycondition");
                params.put("SERVICE_PLAN", search);
                params.put("page", ""+page);
                params.put("pageCount", ""+15);
                params.put("GATHER_TYPE",""+20);
                presenter.getInfrom(params);
                rlPlan.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                isload=true;
                params.put("Type", "queryPlanBycondition");
                params.put("SERVICE_PLAN", search);
                params.put("page", ""+page);
                params.put("pageCount", ""+15);
                params.put("GATHER_TYPE",""+20);
                presenter.getInfrom(params);
                rlPlan.loadMoreComplete();
            }
        });
        rlPlan.setAdapter(planAdapter);
    }

    @Override
    protected MainPresenter getProduct() {
        return new MainPresenter();
    }


    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Context context() {
        return this;
    }


    @Override
    public void Success(PlanBean planBean) {
        if(planBean!=null){
            if(!isload){
                list.clear();
            }
            list.addAll(planBean.getServer_params().getList());
            planAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void Faild(Throwable e) {
        Log.e("main", "Faild: "+e.getMessage() );
    }



    @OnClick({R.id.title_back, R.id.btn_main_search,R.id.title_left_lable})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.btn_main_search:
                params = new HashMap<>();
                params.put("Type", "queryPlanBycondition");
                params.put("SERVICE_PLAN", search);
                params.put("page", ""+page);
                params.put("pageCount", ""+15);
                params.put("GATHER_TYPE",""+20);
                presenter.getInfrom(params);

                break;
            case  R.id.title_left_lable:
                finish();
                break;
        }
    }
}
