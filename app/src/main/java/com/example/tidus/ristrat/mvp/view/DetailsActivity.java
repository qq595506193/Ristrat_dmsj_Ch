package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.HistoryTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.contract.IHistoryAssessContract;
import com.example.tidus.ristrat.mvp.presenter.HistoryAssessPresenter;
import com.example.tidus.ristrat.mvp.view.fragment.OneDetailsFragment;
import com.example.tidus.ristrat.mvp.view.fragment.TwoDetailsFragment;
import com.example.tidus.ristrat.utils.LogUtils;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class DetailsActivity extends BaseMvpActivity<IHistoryAssessContract.IHistoryAssessModel, IHistoryAssessContract.HistoryAssessPresenter> implements IHistoryAssessContract.IHistoryAssessView {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_office)
    TextView tv_office;
    @BindView(R.id.tv_mark)
    TextView tv_mark;
    @BindView(R.id.tv_zhenduan_content)
    TextView tv_zhenduan_content;
    @BindView(R.id.rv_table_list)
    RecyclerView rv_table_list;
    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private HistoryTableListAdapter historyTableListAdapter;
    private LineData data;
    private TextView tv_login_name;
    private ImageView iv_message;
    private ImageView iv_close;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();
    private ImageView iv_back;
    private TextView tv_back;
    private Object operate_time;
    private QueryHMBean.ServerParamsBean.LISTBean listBean;
    private HistoryAssessBean historyAssessBean;
    private List<Entry> entries;
    private LineDataSet dataSet;
    private CaseControlBean.ServerParamsBean serverParamsBean;

    @Override
    protected void init() {

    }

    @Override
    protected void initView(Intent intent) {
        EventBus.getDefault().register(this);
        rv_table_list.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
        historyTableListAdapter = new HistoryTableListAdapter(App.getContext());
        rv_table_list.setAdapter(historyTableListAdapter);
        historyTableListAdapter.setSetTableItem(new HistoryTableListAdapter.SetTableItem() {
            @Override
            public void setOnClickTableItem(int position, HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean) {
                if (position == 0) {
                    view_pager.setCurrentItem(0);
                } else if (position == 1) {
                    view_pager.setCurrentItem(1);
                }
            }
        });
        view_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new OneDetailsFragment();
                    case 1:
                        return new TwoDetailsFragment();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryJibingReportLIST");
        params.put("PATIENT_ID", this.serverParamsBean.getPATIENT_ID());
        presenter.getHistoryAssess(params);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setInfo(CaseControlBean.ServerParamsBean serverParamsBean) {
        this.serverParamsBean = serverParamsBean;
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public BasePresenter initPresenter() {
        return new HistoryAssessPresenter(this);
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

    @Override
    public void onHistoryAssessSuccess(Object result) {
        if (result != null) {
            if (result instanceof HistoryAssessBean) {
                if (((HistoryAssessBean) result).getCode().equals("0")) {
                    LogUtils.e(((HistoryAssessBean) result).getMessage());
                    HistoryAssessBean.ServerParamsBean server_params = ((HistoryAssessBean) result).getServer_params();
                    EventBus.getDefault().postSticky(server_params);
                    tv_zhenduan_content.setText(((HistoryAssessBean) result).getServer_params().getJibinlist().get(0).getDIAGNOSIS_DISEASE_NAME());
                    tv_name.setText(historyAssessBean.getServer_params().getPATIENT_NAME());
                    if (historyAssessBean.getServer_params().getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    tv_office.setText(historyAssessBean.getServer_params().getIN_DEPT_NAME());
                    tv_mark.setText(historyAssessBean.getServer_params().getMEDICAL_REC_NUMBER());
                    historyTableListAdapter.setWenjuannameBeans(historyAssessBean.getServer_params().getReportList());


                } else {
                    LogUtils.e(((HistoryAssessBean) result).getMessage());
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
        EventBus.getDefault().unregister(this);
    }
}
