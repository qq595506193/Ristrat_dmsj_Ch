package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.contract.IHistoryAssessContract;
import com.example.tidus.ristrat.mvp.presenter.HistoryAssessPresenter;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.TimeChangeUtil;
import com.example.tidus.ristrat.weight.LineChart;
import com.example.tidus.ristrat.weight.LineChartData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class HistoryAssessActivity extends BaseMvpActivity<IHistoryAssessContract.IHistoryAssessModel, IHistoryAssessContract.HistoryAssessPresenter> implements IHistoryAssessContract.IHistoryAssessView {


    private String[] mDayItems = new String[]{"31日", "1日", "2日", "3日", "4日", "5日", "6日"};
    private int[] mDayPoints = new int[]{0, 2, 7, 4, 0, 1, -1};

    private String[] mMonthItems = new String[]{"5月", "6月", "7月", "8月", "9月", "10月", "11月"};
    private int[] mMonthPoints = new int[]{0, 2, 1, 0, 0, 0, 8};
    private List<LineChartData> dataList1 = new ArrayList<>();
    private List<LineChartData> dataList2 = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private List<Integer> pointsList = new ArrayList<>();
    private LoginBean loginBean;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private HistoryAssessBean historyAssessBean;

    @BindView(R.id.line_chart_assess)
    LineChart line_chart_assess;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_office)
    TextView tv_office;
    @BindView(R.id.tv_mark)
    TextView tv_mark;
    @BindView(R.id.tv_diwei_tag)
    TextView tv_diwei_tag;
    @BindView(R.id.tv_zhongwei_tag)
    TextView tv_zhongwei_tag;
    @BindView(R.id.tv_gaowei_tag)
    TextView tv_gaowei_tag;
    @BindView(R.id.tv_jigaowei_tag)
    TextView tv_jigaowei_tag;
    @BindView(R.id.tv_quezhen_tag)
    TextView tv_quezhen_tag;

    @Override
    protected void init() {

    }

    // View宽，高
    public int[] getLocation(View v) {
        int[] loc = new int[4];
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        loc[0] = location[0];
        loc[1] = location[1];
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(w, h);

        loc[2] = v.getMeasuredWidth();
        loc[3] = v.getMeasuredHeight();

        //base = computeWH();
        return loc;
    }

    @Override
    protected void initView(Intent intent) {
        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) intent.getSerializableExtra("serverParamsBean");
        int[] location = getLocation(tv_diwei_tag);
        LogUtils.e("控件宽高=====" + Arrays.toString(location));


    }

    @Override
    protected void initData() {
        super.initData();
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryJibingReportLIST");
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        presenter.getHistoryAssess(params);
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_history_assess;
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
                    this.historyAssessBean = (HistoryAssessBean) result;
                    tv_name.setText(historyAssessBean.getServer_params().getPATIENT_NAME());
                    if (historyAssessBean.getServer_params().getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    tv_office.setText(historyAssessBean.getServer_params().getIN_DEPT_NAME());
                    tv_mark.setText(historyAssessBean.getServer_params().getMEDICAL_REC_NUMBER());


                    // X轴的值
                    for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : historyAssessBean.getServer_params().getReportList()) {
                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = reportListBean.getWENJUAN().get(0);
                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean1 = reportListBean.getWENJUAN().get(1);
                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean2 = reportListBean.getWENJUAN().get(2);
                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean3 = reportListBean.getWENJUAN().get(3);
                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean4 = reportListBean.getWENJUAN().get(4);
                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean5 = reportListBean.getWENJUAN().get(5);
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
                            String report_time = sublistBean.getREPORT_TIME();
                            String strTime = TimeChangeUtil.getStrTime(report_time);
                            switch (sublistBean.getRISK_NAME()) {
                                case "低危":
                                    pointsList.add(0, 1);
                                    break;
                                case "中危":
                                    pointsList.add(0, 2);
                                    break;
                                case "高危":
                                    pointsList.add(0, 3);
                                    break;
                                case "极高危":
                                    pointsList.add(0, 4);
                                    break;
                                case "确诊":
                                    pointsList.add(0, 5);
                                    break;
                            }
                            timeList.add(0, strTime);
                        }
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean1.getSublist()) {
                            String report_time = sublistBean.getREPORT_TIME();
                            String strTime = TimeChangeUtil.getStrTime(report_time);
                            switch (sublistBean.getRISK_NAME()) {
                                case "低危":
                                    pointsList.add(0, 1);
                                    break;
                                case "中危":
                                    pointsList.add(0, 2);
                                    break;
                                case "高危":
                                    pointsList.add(0, 3);
                                    break;
                                case "极高危":
                                    pointsList.add(0, 4);
                                    break;
                                case "确诊":
                                    pointsList.add(0, 5);
                                    break;
                            }
                            timeList.add(1, strTime);
                        }
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean2.getSublist()) {
                            String report_time = sublistBean.getREPORT_TIME();
                            String strTime = TimeChangeUtil.getStrTime(report_time);
                            switch (sublistBean.getRISK_NAME()) {
                                case "低危":
                                    pointsList.add(0, 1);
                                    break;
                                case "中危":
                                    pointsList.add(0, 2);
                                    break;
                                case "高危":
                                    pointsList.add(0, 3);
                                    break;
                                case "极高危":
                                    pointsList.add(0, 4);
                                    break;
                                case "确诊":
                                    pointsList.add(0, 5);
                                    break;
                            }
                            timeList.add(2, strTime);
                        }
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean3.getSublist()) {
                            String report_time = sublistBean.getREPORT_TIME();
                            String strTime = TimeChangeUtil.getStrTime(report_time);
                            switch (sublistBean.getRISK_NAME()) {
                                case "低危":
                                    pointsList.add(0, 1);
                                    break;
                                case "中危":
                                    pointsList.add(0, 2);
                                    break;
                                case "高危":
                                    pointsList.add(0, 3);
                                    break;
                                case "极高危":
                                    pointsList.add(0, 4);
                                    break;
                                case "确诊":
                                    pointsList.add(0, 5);
                                    break;
                            }
                            timeList.add(3, strTime);
                        }
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean4.getSublist()) {
                            String report_time = sublistBean.getREPORT_TIME();
                            String strTime = TimeChangeUtil.getStrTime(report_time);
                            switch (sublistBean.getRISK_NAME()) {
                                case "低危":
                                    pointsList.add(0, 1);
                                    break;
                                case "中危":
                                    pointsList.add(0, 2);
                                    break;
                                case "高危":
                                    pointsList.add(0, 3);
                                    break;
                                case "极高危":
                                    pointsList.add(0, 4);
                                    break;
                                case "确诊":
                                    pointsList.add(0, 5);
                                    break;
                            }
                            timeList.add(4, strTime);
                        }
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean5.getSublist()) {
                            String report_time = sublistBean.getREPORT_TIME();
                            String strTime = TimeChangeUtil.getStrTime(report_time);
                            switch (sublistBean.getRISK_NAME()) {
                                case "低危":
                                    pointsList.add(0, 1);
                                    break;
                                case "中危":
                                    pointsList.add(0, 2);
                                    break;
                                case "高危":
                                    pointsList.add(0, 3);
                                    break;
                                case "极高危":
                                    pointsList.add(0, 4);
                                    break;
                                case "确诊":
                                    pointsList.add(0, 5);
                                    break;
                            }
                            timeList.add(5, strTime);
                        }


                    }

                    LogUtils.e(timeList.toString());

                    int[] mWeekPoints = new int[]{pointsList.get(0), pointsList.get(1), pointsList.get(2), pointsList.get(3), pointsList.get(4), pointsList.get(5)};
                    String[] mWeekItems = new String[]{timeList.get(0), timeList.get(1), timeList.get(2), timeList.get(3), timeList.get(4), timeList.get(5)};
                    for (int i = 0; i < mWeekItems.length; i++) {
                        LineChartData data = new LineChartData();
                        data.setItem(mWeekItems[i]);
                        data.setPoint(mWeekPoints[i]);
                        dataList1.add(data);
                    }
                    line_chart_assess.setData(dataList1);
                }


            }
        }
    }

    @Override
    public void onFailed(Object error) {

    }
}
