package com.example.tidus.ristrat.mvp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lib_core.base.mvp.BaseMvpActivity;
import com.example.lib_core.base.mvp.BasePresenter;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.adapter.HistoryTableListAdapter;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.example.tidus.ristrat.bean.LoginBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.contract.IHistoryAssessContract;
import com.example.tidus.ristrat.mvp.presenter.HistoryAssessPresenter;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.TimeChangeUtil;
import com.example.tidus.ristrat.weight.LineChartData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    LineChart chartview;
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
    @BindView(R.id.txt_coding_02)
    TextView txt_coding_02;// 报告编码
    @BindView(R.id.txt_time)
    TextView txt_time;// 评估时间
    @BindView(R.id.txt_time_02)
    TextView txt_time_02;// 评估时间
    @BindView(R.id.txt_total)
    TextView txt_total;// 评估总分
    @BindView(R.id.txt_total_02)
    TextView txt_total_02;// 评估总分
    @BindView(R.id.txt_grade)
    TextView txt_grade;// 风险等级
    @BindView(R.id.txt_grade_02)
    TextView txt_grade_02;// 风险等级
    @BindView(R.id.txt_show)
    TextView txt_show;// 评估说明
    @BindView(R.id.txt_show_02)
    TextView txt_show_02;// 评估说明
    @BindView(R.id.txt_element)
    TextView txt_element;// 风险因素
    @BindView(R.id.txt_element_02)
    TextView txt_element_02;// 风险因素
    @BindView(R.id.txt_advise)
    TextView txt_advise;// 预防处理建议
    @BindView(R.id.txt_advise_02)
    TextView txt_advise_02;// 预防处理建议
    @BindView(R.id.txt_nursing)
    TextView txt_nursing;// 护士处理建议
    @BindView(R.id.txt_nursing_02)
    TextView txt_nursing_02;// 护士处理建议
    @BindView(R.id.txt_note)
    TextView txt_note;// 注意事项和温馨提示
    @BindView(R.id.txt_note_02)
    TextView txt_note_02;// 注意事项和温馨提示
    @BindView(R.id.btn_log)
    Button btn_log;// 评估按钮
    @BindView(R.id.btn_log_02)
    Button btn_log_02;// 评估按钮
    @BindView(R.id.tv_zhenduan_content)
    TextView tv_zhenduan_content;
    @BindView(R.id.cly_visib_02)
    ConstraintLayout cly_visib_02;
    @BindView(R.id.cly_visib)
    ConstraintLayout cly_visib;
    @BindView(R.id.cly_02)
    ConstraintLayout cly_02;
    @BindView(R.id.cly_visib_02_02)
    ConstraintLayout cly_visib_02_02;
    @BindView(R.id.tv_table_name_02)
    TextView tv_table_name_02;
    @BindView(R.id.tv_table_name)
    TextView tv_table_name;
    @BindView(R.id.scroll_view)
    ScrollView scroll_view;
    @BindView(R.id.scroll_view_02)
    ScrollView scroll_view_02;
    @BindView(R.id.iv_yuan_02)
    ImageView iv_yuan_02;
    @BindView(R.id.iv_yuan)
    ImageView iv_yuan;
    @BindView(R.id.btn_log_wu)
    Button btn_log_wu;
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

    @Override
    protected void init() {

    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initView(Intent intent) {
        loginBean = (LoginBean) intent.getSerializableExtra("loginBean");
        serverParamsBean = (CaseControlBean.ServerParamsBean) intent.getSerializableExtra("serverParamsBean");
        listBean = (QueryHMBean.ServerParamsBean.LISTBean) intent.getSerializableExtra("listBean");
        data = new LineData();
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_back = findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_login_name = findViewById(R.id.tv_login_name);
        tv_login_name.setText(loginBean.getServer_params().getUSER_NAME());
        iv_message = findViewById(R.id.iv_message);
        iv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), MessageActivity.class);
                intent.putExtra("loginBean", loginBean);
                startActivity(intent);
            }
        });
        iv_close = findViewById(R.id.iv_close);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), UserLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_log_wu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), SelectQuestionActivity.class);
                intent.putExtra("listBean", listBean);
                intent.putExtra("loginBean", loginBean);
                intent.putExtra("serverParamsBean", serverParamsBean);

                startActivity(intent);
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getContext(), SelectQuestionActivity.class);
                intent.putExtra("listBean", listBean);
                intent.putExtra("loginBean", loginBean);
                intent.putExtra("serverParamsBean", serverParamsBean);

                startActivity(intent);
            }
        });
        rv_table_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        historyTableListAdapter = new HistoryTableListAdapter(getContext());
        rv_table_list.setAdapter(historyTableListAdapter);


        //解决滑动冲突
        chartview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        scroll_view.requestDisallowInterceptTouchEvent(true);
                        scroll_view_02.requestDisallowInterceptTouchEvent(true);
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        // 抬起事件


                        scroll_view.requestDisallowInterceptTouchEvent(false);
                        scroll_view_02.requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                }
                return false;
            }
        });

        historyTableListAdapter.setSetTableItem(new HistoryTableListAdapter.SetTableItem() {
            @Override
            public void setOnClickTableItem(int position, HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean1) {
                if (position == 0) {
                    cly_visib.setVisibility(View.VISIBLE);
                    cly_visib_02_02.setVisibility(View.GONE);
                    cly_visib_02.setVisibility(View.GONE);
                } else if (position == 1) {
                    cly_visib.setVisibility(View.GONE);
                    cly_visib_02_02.setVisibility(View.VISIBLE);
                    cly_visib_02.setVisibility(View.GONE);
                }


            }
        });




    }


    @Override
    protected void initData() {
        super.initData();
        initPresenterData();
    }

    private void initPresenterData() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("Type", "queryJibingReportLIST");
        params.put("PATIENT_ID", serverParamsBean.getPATIENT_ID());
        presenter.getHistoryAssess(params);


//        for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : historyAssessBean.getServer_params().getReportList()) {
//            for (int i = 0; i < reportListBean.getWENJUAN().size(); i++) {
//                HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = reportListBean.getWENJUAN().get(i);
//                String operate_time = wenjuanBean.getREPORT_TIME();
//                String strTime = TimeChangeUtil.getStrTime(operate_time);
//
//            }
//        }


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
                    HistoryAssessBean.ServerParamsBean server_params = ((HistoryAssessBean) result).getServer_params();
                    EventBus.getDefault().postSticky(server_params);
                    historyAssessBean = (HistoryAssessBean) result;
                    LogUtils.e(((HistoryAssessBean) result).getMessage());
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
                    // 判断有无报告
                    if (((HistoryAssessBean) result).getServer_params().getReportList().size() != 0) {
                        cly_visib_02.setVisibility(View.GONE);
                        cly_visib.setVisibility(View.VISIBLE);
                        cly_visib_02_02.setVisibility(View.GONE);
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : ((HistoryAssessBean) result).getServer_params().getReportList()) {
                            // 判断是第几个表
                            if (reportListBean.getFORM_ID() == 1) {
                                cly_visib.setVisibility(View.VISIBLE);
                                cly_visib_02_02.setVisibility(View.GONE);
                                cly_visib_02.setVisibility(View.GONE);

                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean> wenjuan = reportListBean.getWENJUAN();
                                for (int i = 0; i < wenjuan.size(); i++) {
                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = wenjuan.get(i);
                                    txt_coding.setText(wenjuanBean.getREPORT_CODE());
                                    final String strTime = TimeChangeUtil.getStrTime(wenjuanBean.getREPORT_TIME());
                                    txt_time.setText(strTime);
                                    // 设置小圆点
                                    entries = new ArrayList<>();
                                    entries.add(new Entry(i, new Random().nextInt(300)));
                                    // add entries to dataset
                                    dataSet = new LineDataSet(entries, "Label");
                                    dataSet.setColor(Color.parseColor("#7d7d7d"));//线条颜色
                                    dataSet.setCircleColor(Color.parseColor("#7d7d7d"));//圆点颜色
                                    dataSet.setLineWidth(1f);//线条宽度
                                    //设置样式
                                    YAxis rightAxis = chartview.getAxisRight();

                                    //设置图表右边的y轴禁用
                                    rightAxis.setEnabled(false);
                                    YAxis leftAxis = chartview.getAxisLeft();
                                    //设置图表左边的y轴禁用
                                    leftAxis.setEnabled(true);
                                    //设置x轴
                                    XAxis xAxis = chartview.getXAxis();
                                    xAxis.setTextColor(Color.parseColor("#333333"));
                                    xAxis.setTextSize(11f);
                                    xAxis.setAxisMinimum(0f);
                                    xAxis.setDrawAxisLine(true);//是否绘制轴线
                                    xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
                                    xAxis.setDrawLabels(false);//绘制标签  指x轴上的对应数值
                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
                                    xAxis.setGranularity(1f);//禁止放大后x轴标签重绘
                                    //透明化图例
                                    Legend legend = chartview.getLegend();
                                    legend.setForm(Legend.LegendForm.NONE);
                                    legend.setTextColor(Color.WHITE);
                                    //隐藏x轴描述
                                    Description description = new Description();
                                    description.setEnabled(false);
                                    chartview.setDescription(description);
                                    xAxis.setValueFormatter(new IAxisValueFormatter() {
                                        @Override
                                        public String getFormattedValue(float value, AxisBase axis) {
                                            return strTime;
                                        }
                                    });

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
                                        txt_total.setText(sublistBean.getCURRENT_RISK_VALUE() + "分");
                                        switch (sublistBean.getCURRENT_RISK_LEVEL()) {
                                            case "5":
                                                iv_yuan.setImageResource(R.mipmap.diwei_yuan);
                                                txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));
                                                txt_grade.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "6":
                                                iv_yuan.setImageResource(R.mipmap.zhongwei_yuan);
                                                txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelBlueColor));
                                                txt_grade.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "7":
                                                iv_yuan.setImageResource(R.mipmap.gaowei_yuan);
                                                txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelYellowColor));
                                                txt_grade.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "8":
                                                iv_yuan.setImageResource(R.mipmap.jigaowei_yuan);
                                                txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelOrangeColor));
                                                txt_grade.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "9":
                                                iv_yuan.setImageResource(R.mipmap.quezhen_yuan);
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

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean wxy : wenjuanBean.getWxys()) {
                                        if (wxy.getFACTOR_GROUP_NAME() != null) {
                                            txt_element.setText(wxy.getRISK_FACTOR_NAME());
                                        } else {
                                            txt_element.setText("暂无风险因素");
                                        }
                                    }

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientAdvice : wenjuanBean.getPATIENT_ADVICE()) {
                                        if (patientAdvice.getADVICE_CONTENT() != null) {
                                            txt_note.setText(patientAdvice.getADVICE_CONTENT());
                                        } else {
                                            txt_note.setText("暂无处理建议");
                                        }
                                    }

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean doctorAdvice : wenjuanBean.getDOCTOR_ADVICE()) {
                                        if (doctorAdvice.getADVICE_CONTENT() != null) {
                                            txt_advise.setText(doctorAdvice.getADVICE_CONTENT());
                                        } else {
                                            txt_advise.setText("暂无处理建议");
                                        }
                                    }


                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean nurseAdvice : wenjuanBean.getNURSE_ADVICE()) {
                                        if (nurseAdvice.getADVICE_CONTENT() != null) {
                                            txt_nursing.setText(nurseAdvice.getADVICE_CONTENT());
                                        } else {
                                            txt_nursing.setText("暂无处理建议");
                                        }
                                    }
                                }
                                tv_table_name.setText(reportListBean.getFORM_NAME());
                                //3.chart设置数据
                                LineData lineData = new LineData(dataSet);
                                chartview.setData(lineData);
                                chartview.invalidate(); // refresh
                            } else if (reportListBean.getFORM_ID() == 2) {
                                // 第二个表
                                cly_visib.setVisibility(View.VISIBLE);
                                cly_visib_02_02.setVisibility(View.GONE);
                                cly_visib_02.setVisibility(View.GONE);
                                tv_table_name_02.setText(reportListBean.getFORM_NAME());
                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean> wenjuan = reportListBean.getWENJUAN();
                                for (int i = 0; i < wenjuan.size(); i++) {
                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = wenjuan.get(i);
                                    txt_coding_02.setText(wenjuanBean.getREPORT_CODE());
                                    final String strTime = TimeChangeUtil.getStrTime(wenjuanBean.getREPORT_TIME());
                                    txt_time_02.setText(strTime);
                                    // 设置小圆点
                                    entries = new ArrayList<>();
                                    entries.add(new Entry(i, new Random().nextInt(300)));
                                    // add entries to dataset
                                    dataSet = new LineDataSet(entries, "Label");
                                    dataSet.setColor(Color.parseColor("#7d7d7d"));//线条颜色
                                    dataSet.setCircleColor(Color.parseColor("#7d7d7d"));//圆点颜色
                                    dataSet.setLineWidth(1f);//线条宽度
                                    //设置样式
                                    YAxis rightAxis = chartview.getAxisRight();

                                    //设置图表右边的y轴禁用
                                    rightAxis.setEnabled(false);
                                    YAxis leftAxis = chartview.getAxisLeft();
                                    //设置图表左边的y轴禁用
                                    leftAxis.setEnabled(true);
                                    //设置x轴
                                    XAxis xAxis = chartview.getXAxis();
                                    xAxis.setTextColor(Color.parseColor("#333333"));
                                    xAxis.setTextSize(11f);
                                    xAxis.setAxisMinimum(0f);
                                    xAxis.setDrawAxisLine(true);//是否绘制轴线
                                    xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
                                    xAxis.setDrawLabels(false);//绘制标签  指x轴上的对应数值
                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
                                    xAxis.setGranularity(1f);//禁止放大后x轴标签重绘
                                    //透明化图例
                                    Legend legend = chartview.getLegend();
                                    legend.setForm(Legend.LegendForm.NONE);
                                    legend.setTextColor(Color.WHITE);
                                    //隐藏x轴描述
                                    Description description = new Description();
                                    description.setEnabled(false);
                                    chartview.setDescription(description);
                                    xAxis.setValueFormatter(new IAxisValueFormatter() {
                                        @Override
                                        public String getFormattedValue(float value, AxisBase axis) {
                                            return strTime;
                                        }
                                    });

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
                                        txt_total_02.setText(sublistBean.getCURRENT_RISK_VALUE() + "分");
                                        switch (sublistBean.getCURRENT_RISK_LEVEL()) {
                                            case "5":
                                                txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));
                                                txt_grade_02.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "6":
                                                txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelBlueColor));
                                                txt_grade_02.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "7":
                                                txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelYellowColor));
                                                txt_grade_02.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "8":
                                                txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelOrangeColor));
                                                txt_grade_02.setText(sublistBean.getRISK_NAME());
                                                break;
                                            case "9":
                                                txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));
                                                txt_grade_02.setText(sublistBean.getRISK_NAME());
                                                break;
                                            default:
                                                txt_grade_02.setText("暂无危险等级");
                                                break;
                                        }
                                        if (sublistBean.getRISK_DETAIL_DESC() != null) {
                                            txt_show_02.setText(sublistBean.getRISK_DETAIL_DESC() + "");// 报告
                                        }
                                    }

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean wxy : wenjuanBean.getWxys()) {
                                        if (wxy.getFACTOR_GROUP_NAME() != null) {
                                            txt_element.setText(wxy.getRISK_FACTOR_NAME());
                                        } else {
                                            txt_element.setText("暂无风险因素");
                                        }
                                    }

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientAdvice : wenjuanBean.getPATIENT_ADVICE()) {
                                        if (patientAdvice.getADVICE_CONTENT() != null) {
                                            txt_note_02.setText(patientAdvice.getADVICE_CONTENT());
                                        } else {
                                            txt_note_02.setText("暂无处理建议");
                                        }
                                    }

                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean doctorAdvice : wenjuanBean.getDOCTOR_ADVICE()) {
                                        if (doctorAdvice.getADVICE_CONTENT() != null) {
                                            txt_advise_02.setText(doctorAdvice.getADVICE_CONTENT());
                                        } else {
                                            txt_advise_02.setText("暂无处理建议");
                                        }
                                    }


                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean nurseAdvice : wenjuanBean.getNURSE_ADVICE()) {
                                        if (nurseAdvice.getADVICE_CONTENT() != null) {
                                            txt_nursing_02.setText(nurseAdvice.getADVICE_CONTENT());
                                        } else {
                                            txt_nursing_02.setText("暂无处理建议");
                                        }
                                    }
                                }
                                tv_table_name.setText(reportListBean.getFORM_NAME());
                                //3.chart设置数据
                                LineData lineData = new LineData(dataSet);
                                chartview.setData(lineData);
                                chartview.invalidate(); // refresh
                            }
                        }
                    } else {
                        cly_visib_02.setVisibility(View.VISIBLE);
                        cly_visib.setVisibility(View.GONE);
                        cly_02.setVisibility(View.GONE);
                        cly_visib_02_02.setVisibility(View.GONE);
                    }
                }
            }
        }
    }


    @Override
    public void onFailed(Object error) {

    }
}
