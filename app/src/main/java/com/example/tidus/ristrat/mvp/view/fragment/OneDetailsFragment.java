package com.example.tidus.ristrat.mvp.view.fragment;

import android.widget.Button;
import android.widget.TextView;

import com.example.lib_core.base.BaseFragment;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class OneDetailsFragment extends BaseFragment {


    @BindView(R.id.btn_log)
    Button btn_log;// 评估按钮
    @BindView(R.id.tv_table_name)
    TextView tv_table_name;
    @BindView(R.id.chartview)
    LineChart chartview;

    private CaseControlBean.ServerParamsBean serverParamsBean;
    private List<Entry> entries;
    private HistoryAssessBean.ServerParamsBean server_params;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

        if (this.server_params.getReportList().size() != 0) {
            for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : this.server_params.getReportList()) {
                if (reportListBean.getFORM_ID() == 1) {
                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean : reportListBean.getWENJUAN()) {

                    }
                } else if (reportListBean.getFORM_ID() == 2) {

                }
            }
        }


    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_details_one;
    }

    @Override
    protected void lazyLoad() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void setHistoryInfo(HistoryAssessBean.ServerParamsBean server_params) {
        this.server_params = server_params;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
