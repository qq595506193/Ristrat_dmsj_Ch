package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lib_core.base.mvp.BaseMvpFragment;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.EvaluatingAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.QueryHMBean;

import butterknife.BindView;

public class EvaluatingActivity extends BaseMvpFragment {
    @BindView(R.id.rv_evaluating)
    RecyclerView rv_evaluating;
    private QueryHMBean.ServerParamsBean server_params;


    @Override
    protected void initView() {
        initView(new Intent());
    }


    @Override
    protected int setContentView() {
        return R.layout.activity_evaluating;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void init() {

    }

    private void initView(Intent intent) {
        server_params = (QueryHMBean.ServerParamsBean) intent.getSerializableExtra("server_params");
        EvaluatingAdapter evaluatingAdapter = new EvaluatingAdapter(App.getContext());
        rv_evaluating.setLayoutManager(new LinearLayoutManager(App.getContext()));
        rv_evaluating.setAdapter(evaluatingAdapter);
        evaluatingAdapter.setTixingListBean(server_params.getTixingLIST());
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
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
}
