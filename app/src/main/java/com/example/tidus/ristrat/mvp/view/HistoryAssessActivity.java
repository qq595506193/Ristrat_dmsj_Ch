package com.example.tidus.ristrat.mvp.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.HistoryTableListAdapter;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.contract.IHistoryAssessContract;
import com.example.tidus.ristrat.mvp.presenter.HistoryAssessPresenter;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.TimeChangeUtil;
import com.example.tidus.ristrat.weight.ChartView;
import com.example.tidus.ristrat.weight.LineChartData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.example.tidus.ristrat.application.App.getContext;

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

    @BindView(R.id.rv_table_list)
    RecyclerView rv_table_list;
    @BindView(R.id.chartview)
    ChartView chartView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_office)
    TextView tv_office;
    @BindView(R.id.tv_mark)
    TextView tv_mark;
    @BindView(R.id.txt_coding)
    TextView txt_coding;// 报告编码
    @BindView(R.id.txt_time)
    TextView txt_time;// 评估时间
    @BindView(R.id.txt_total)
    TextView txt_total;// 评估总分
    @BindView(R.id.txt_grade)
    TextView txt_grade;// 风险等级
    @BindView(R.id.txt_show)
    TextView txt_show;// 评估说明
    @BindView(R.id.txt_element)
    TextView txt_element;// 风险因素
    @BindView(R.id.txt_advise)
    TextView txt_advise;// 预防处理建议
    @BindView(R.id.txt_nursing)
    TextView txt_nursing;// 护士处理建议
    @BindView(R.id.txt_note)
    TextView txt_note;// 注意事项和温馨提示
    private HistoryTableListAdapter historyTableListAdapter;
    private LineChartData data;
    private TextView tv_login_name;
    private ImageView iv_message;
    private ImageView iv_close;
    //x轴坐标对应的数据
    private List<String> xValue = new ArrayList<>();
    //y轴坐标对应的数据
    private List<Integer> yValue = new ArrayList<>();
    //折线对应的数据
    private Map<String, Integer> value = new HashMap<>();
    // 日期对应的数据
    private HistoryAssessBean historyAssessBean;

    @Override
    protected void init() {

    }


    @Override
    protected void initView(Intent intent) {
        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) intent.getSerializableExtra("serverParamsBean");
        data = new LineChartData();
        tv_login_name = findViewById(R.id.tv_login_name);
        iv_message = findViewById(R.id.iv_message);
        iv_close = findViewById(R.id.iv_close);
        rv_table_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        historyTableListAdapter = new HistoryTableListAdapter(getContext());
        rv_table_list.setAdapter(historyTableListAdapter);

    }


    @Override
    protected void initData() {
        super.initData();
        initPresenterData();

        for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : historyAssessBean.getServer_params().getReportList()) {
            for (int i = 0; i < reportListBean.getWENJUAN().size(); i++) {
                xValue.add(reportListBean.getWENJUAN().get(i).getREPORT_TIME());
                value.put((i + 1) + "日", (int) (Math.random() * 181 + 60));//60--240
            }
        }
        for (int i = 0; i < 6; i++) {
            yValue.add(i);
        }
        chartView.setValue(value, xValue, yValue);

    }

    private void initPresenterData() {
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
                historyAssessBean = (HistoryAssessBean) result;
                if (((HistoryAssessBean) result).getCode().equals("0")) {
                    LogUtils.e(((HistoryAssessBean) result).getMessage());
                    tv_name.setText(historyAssessBean.getServer_params().getPATIENT_NAME());
                    if (historyAssessBean.getServer_params().getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    tv_office.setText(historyAssessBean.getServer_params().getIN_DEPT_NAME());
                    tv_mark.setText(historyAssessBean.getServer_params().getMEDICAL_REC_NUMBER());
                    historyTableListAdapter.setWenjuannameBeans(historyAssessBean.getServer_params().getReportList());

                    for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : ((HistoryAssessBean) result).getServer_params().getReportList()) {
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean : reportListBean.getWENJUAN()) {
                            txt_coding.setText(wenjuanBean.getREPORT_CODE());
                            String strTime = TimeChangeUtil.getStrTime(wenjuanBean.getREPORT_TIME());
                            txt_time.setText(strTime);


                            for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
                                txt_total.setText(sublistBean.getCURRENT_RISK_VALUE() + "分");
                                switch (sublistBean.getCURRENT_RISK_LEVEL()) {
                                    case "5":
                                        txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));
                                        txt_grade.setText(sublistBean.getRISK_NAME());
                                        break;
                                    case "6":
                                        txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelBlueColor));
                                        txt_grade.setText(sublistBean.getRISK_NAME());
                                        break;
                                    case "7":
                                        txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelYellowColor));
                                        txt_grade.setText(sublistBean.getRISK_NAME());
                                        break;
                                    case "8":
                                        txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelOrangeColor));
                                        txt_grade.setText(sublistBean.getRISK_NAME());
                                        break;
                                    case "9":
                                        txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));
                                        txt_grade.setText(sublistBean.getRISK_NAME());
                                        break;
                                    default:
                                        txt_grade.setText("暂无危险等级");
                                        break;
                                }
                                if (sublistBean.getRISK_DETAIL_DESC() != null) {
                                    txt_show.setText(sublistBean.getRISK_DETAIL_DESC() + "");// 报告
                                }
                            }
                            if (wenjuanBean.getPATIENT_ADVICE().size() != 0 && wenjuanBean.getPATIENT_ADVICE() != null) {
                                for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientAdvice : wenjuanBean.getPATIENT_ADVICE()) {
                                    txt_note.setText(patientAdvice.getADVICE_CONTENT());
                                }
                            } else {
                                txt_note.setText("暂无处理建议");
                            }
                            if (wenjuanBean.getDOCTOR_ADVICE().size() != 0 && wenjuanBean.getDOCTOR_ADVICE() != null) {
                                for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean doctorAdvice : wenjuanBean.getDOCTOR_ADVICE()) {
                                    txt_advise.setText(doctorAdvice.getADVICE_CONTENT());
                                }
                            } else {
                                txt_advise.setText("暂无处理建议");
                            }

                            if (wenjuanBean.getNURSE_ADVICE().size() != 0 && wenjuanBean.getNURSE_ADVICE() != null) {
                                for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean nurseAdvice : wenjuanBean.getNURSE_ADVICE()) {
                                    txt_nursing.setText(nurseAdvice.getADVICE_CONTENT());
                                }
                            } else {
                                txt_nursing.setText("暂无处理建议");
                            }
                        }
                    }

                }

            }
        }
    }

    @Override
    public void onFailed(Object error) {

    }
}