package com.example.tidus.ristrat.mvp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.example.tidus.ristrat.application.App.getContext;

public class HistoryAssessActivity extends BaseMvpActivity<IHistoryAssessContract.IHistoryAssessModel, IHistoryAssessContract.HistoryAssessPresenter> implements IHistoryAssessContract.IHistoryAssessView {


    private List<LineChartData> dataList1 = new ArrayList<>();
    private List<LineChartData> dataList2 = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private List<Integer> pointsList = new ArrayList<>();
    private LoginBean loginBean;
    private CaseControlBean.ServerParamsBean serverParamsBean;
    private ArrayList<Integer> colors = new ArrayList<Integer>();
    private ArrayList<Integer> colors_2 = new ArrayList<Integer>();

    @BindView(R.id.rv_table_list)
    RecyclerView rv_table_list;
    @BindView(R.id.chartview)
    LineChart chartview;


    @BindView(R.id.chartview_02)
    LineChart chartview_02;


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
    @BindView(R.id.iv_yuan_02)
    ImageView iv_yuan_02;
    @BindView(R.id.iv_yuan)
    ImageView iv_yuan;
    @BindView(R.id.btn_log_wu)
    Button btn_log_wu;
    @BindView(R.id.txt_other)
    TextView txt_other;
    @BindView(R.id.txt_other_02)
    TextView txt_other_02;
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
    private List<Entry> entries_02;
    private List<String> listX;
    private List<String> listX_02;
    private List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean> wenjuan;
    private int position;
    private int form_id;

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

        // 条目点击接口回调
        historyTableListAdapter.setSetTableItem(new HistoryTableListAdapter.SetTableItem() {
            @Override
            public void setOnClickTableItem(int form_id, HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean1) {
                HistoryAssessActivity.this.form_id = form_id;
                initPresenterData(form_id);
                if (form_id == 1) {
                    cly_visib.setVisibility(View.VISIBLE);
                    cly_visib_02_02.setVisibility(View.GONE);
                    cly_visib_02.setVisibility(View.GONE);

                } else if (form_id == 2) {
                    cly_visib.setVisibility(View.GONE);
                    cly_visib_02_02.setVisibility(View.VISIBLE);
                    cly_visib_02.setVisibility(View.GONE);
                }


            }
        });


        //是否缩放X轴
        chartview.setScaleXEnabled(false);
        //是否缩放Y轴
        chartview.setScaleYEnabled(false);

        //是否缩放X轴
        chartview_02.setScaleXEnabled(false);
        //是否缩放Y轴
        chartview_02.setScaleYEnabled(false);
        /*//设置是否可以拖拽
        chartview.setDragEnabled(true);*/
        //设置是否可以通过双击屏幕放大图表。默认是true
        //chartview.setDoubleTapToZoomEnabled(false);


        //准备好每个点对应的x轴数
        listX = new ArrayList<>();
        //listX.add("");
        listX_02 = new ArrayList<>();
        //listX_02.add("");


        //准备好每个点对应的y轴数值
        List<String> listY = new ArrayList<>();
        listY.add("");
        listY.add("低危");
        listY.add("中危");
        listY.add("高危");
        listY.add("极高危");
        listY.add("确诊");

        List<String> listY_02 = new ArrayList<>();
        listY.add("");
        listY.add("地出血");
        listY.add("高出血");


        //设置样式
        YAxis rightAxis = chartview.getAxisRight();
        YAxis rightAxis_02 = chartview_02.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        rightAxis_02.setEnabled(false);

        YAxis leftAxis = chartview.getAxisLeft();
        leftAxis.setValueFormatter(new IndexAxisValueFormatter(listY));
        //设置Y极值，我这里没设置最大值，因为项目需要没有设置最大值
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(6f);
        leftAxis.setDrawGridLines(false);

        YAxis leftAxis_02 = chartview_02.getAxisLeft();
        leftAxis_02.setValueFormatter(new IndexAxisValueFormatter(listY_02));
        //设置Y极值，我这里没设置最大值，因为项目需要没有设置最大值
        leftAxis_02.setAxisMinimum(0f);
        leftAxis_02.setAxisMaximum(3f);
        leftAxis_02.setDrawGridLines(false);

        //不能让缩放，不然有bug，所以接口也没实现
    /*    chartview.setScaleEnabled(false);
        chartview.setPinchZoom(true);*/


        //1.设置x轴和y轴的点
        entries = new ArrayList<>();
        entries_02 = new ArrayList<>();

       /* entries.add(new Entry(1.0f, 1));
        entries.add(new Entry(2.0f, 4));
        entries.add(new Entry(3.0f, 2));
        entries.add(new Entry(4.0f, 3));
        entries.add(new Entry(5.0f, 5));
        entries.add(new Entry(6.0f, 1));
*/
        // 设置点击时高亮的点的颜色


    }


    public void setDiscounting(List<Entry> entries) {

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        // 设置数据内容的样式
        dataSet.setColor(Color.WHITE);                     // 设置数据中线的颜色
        dataSet.setDrawValues(false);                     // 设置是否显示数据点的值
        dataSet.setDrawCircleHole(false);                 // 设置数据点是空心还是实心，默认空心
        dataSet.setCircleColors(colors);              // 设置数据点的颜色
        dataSet.setCircleSize(10);                         // 设置数据点的大小
        dataSet.setHighLightColor(Color.WHITE);

        //chart设置数据
        LineData lineData = new LineData(dataSet);
        //是否绘制线条上的文字
        lineData.setDrawValues(false);
        chartview.setData(lineData);
        chartview.invalidate(); // refresh


    }

    public void setDiscounting_02(List<Entry> entries) {

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        // 设置数据内容的样式
        dataSet.setColor(Color.WHITE);                     // 设置数据中线的颜色
        dataSet.setDrawValues(false);                     // 设置是否显示数据点的值
        dataSet.setDrawCircleHole(false);                 // 设置数据点是空心还是实心，默认空心
        dataSet.setCircleColors(colors_2);              // 设置数据点的颜色
        dataSet.setCircleSize(10);                         // 设置数据点的大小
        dataSet.setHighLightColor(Color.WHITE);

        //chart设置数据
        LineData lineData = new LineData(dataSet);
        //是否绘制线条上的文字
        lineData.setDrawValues(false);

        chartview_02.setData(lineData);
        chartview_02.invalidate(); // refresh

    }


    @Override
    protected void initData() {
        super.initData();
        initPresenterData(form_id);
    }

    private void initPresenterData(int form_id) {
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
                    final HistoryAssessBean.ServerParamsBean server_params = ((HistoryAssessBean) result).getServer_params();
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
                    tv_mark.setText(historyAssessBean.getServer_params().getVISIT_SQ_NO());
                    //
                    historyTableListAdapter.setWenjuannameBeans(historyAssessBean.getServer_params().getReportList());
                    // 判断有无报告
                    if (((HistoryAssessBean) result).getServer_params().getReportList().size() != 0) {
                        cly_visib_02.setVisibility(View.GONE);
                        cly_visib.setVisibility(View.VISIBLE);
                        cly_visib_02_02.setVisibility(View.VISIBLE);



                        // 判断是第几个表
                        if (form_id == 2) {
                            for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : ((HistoryAssessBean) result).getServer_params().getReportList()) {

                                if (reportListBean.getFORM_ID() == 2) {
                                    listX.clear();
                                    // 第二个表
                                    cly_visib.setVisibility(View.GONE);
                                    cly_visib_02_02.setVisibility(View.VISIBLE);
                                    cly_visib_02.setVisibility(View.GONE);
                                    wenjuan = reportListBean.getWENJUAN();
                                    for (int i = 0; i < wenjuan.size(); i++) {
                                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = wenjuan.get(i);
                                        final String strTime = TimeChangeUtil.ms2DateOnlyDay(Long.parseLong(wenjuanBean.getREPORT_TIME()));
                                        // x轴数据
                                        listX_02.add(strTime);

                                        List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean> sublist = wenjuanBean.getSublist();
                                        for (int i1 = 0; i1 < sublist.size(); i1++) {
                                            HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean = sublist.get(i1);


                                            String current_risk_level = sublistBean.getCURRENT_RISK_LEVEL();

                                            final int s = Integer.parseInt(current_risk_level);

                                            if (21 == s) {
                                                entries_02.add(new Entry(i + 1, 1));
                                                colors_2.add(Color.parseColor("#05a558"));
                                            } else if (22 == s) {
                                                entries_02.add(new Entry(i + 1, 5));
                                                colors_2.add(Color.parseColor("#ff4e6b"));
                                            }


                                            chartview_02.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                                                private float valEntry;
                                                private int iEntry;

                                                @Override
                                                public void onValueSelected(Entry e, Highlight h) {
                                                    // 获取Entry
                                                    iEntry = (int) e.getX();
                                                    valEntry = e.getY();
                                                    LogUtils.e("e.getX() = " + iEntry + "     e.getY() = " + valEntry);
                                                    for (int i2 = 0; i2 <= wenjuan.size(); i2++) {
                                                        if (i2 == iEntry) {
                                                            txt_coding_02.setText(wenjuan.get(i2 - 1).getREPORT_CODE());
                                                            txt_time_02.setText(wenjuan.get(i2 - 1).getREPORT_TIME() + "");
                                                            txt_total_02.setText(wenjuan.get(i2 - 1).getSublist().get(0).getCURRENT_RISK_VALUE() + "分");
                                                            txt_grade_02.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                            switch (wenjuan.get(i2 - 1).getSublist().get(0).getCURRENT_RISK_LEVEL()) {
                                                                case "21":
                                                                    iv_yuan_02.setImageResource(R.mipmap.chuxue_diwei);
                                                                    txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));
                                                                    txt_grade_02.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                case "22":
                                                                    iv_yuan_02.setImageResource(R.mipmap.chuxue_gaowei);
                                                                    txt_grade_02.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));
                                                                    txt_grade_02.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                default:
                                                                    txt_grade_02.setText("暂无危险等级");
                                                                    break;
                                                            }
                                                            if (server_params.getJibinlist().size() != 0) {
                                                                txt_show_02.setText("");
                                                            }
                                                            // 危险因素
                                                            if (wenjuan.get(i2 - 1).getWxys().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean> wxys = wenjuan.get(i2 - 1).getWxys();
                                                                for (int i3 = 0; i3 < wxys.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean wxy = wxys.get(i3);
                                                                    if (i3 == wxys.size() - 1) {
                                                                        s += wxy.getRISK_FACTOR_NAME();
                                                                    } else {
                                                                        s += wxy.getRISK_FACTOR_NAME() + "\n";
                                                                    }


                                                                }
                                                                txt_element_02.setText(s);
                                                            } else {
                                                                txt_element_02.setText("暂无风险因素");
                                                            }


                                                            //  医生建议
                                                            if (wenjuan.get(i2 - 1).getDOCTOR_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean> doctor_advice = wenjuan.get(i2 - 1).getDOCTOR_ADVICE();
                                                                for (int i3 = 0; i3 < doctor_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean doctoradviceBean = doctor_advice.get(i3);
                                                                    if (i3 == doctor_advice.size() - 1) {
                                                                        s += doctoradviceBean.getADVICE_CONTENT();
                                                                    } else {
                                                                        s += doctoradviceBean.getADVICE_CONTENT() + "\n";
                                                                    }
                                                                }
                                                                txt_advise_02.setText(s);
                                                            } else {
                                                                txt_advise_02.setText("暂无建议");

                                                            }

                                                            //  护士建议
                                                            if (wenjuan.get(i2 - 1).getDOCTOR_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean> nurse_advice = wenjuan.get(i2 - 1).getNURSE_ADVICE();
                                                                for (int i3 = 0; i3 < nurse_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean nurseadviceBean = nurse_advice.get(i3);
                                                                    if (i3 == nurse_advice.size() - 1) {
                                                                        s += nurseadviceBean.getADVICE_CONTENT();
                                                                    } else {
                                                                        s += nurseadviceBean.getADVICE_CONTENT() + "\n";
                                                                    }
                                                                }
                                                                txt_nursing_02.setText(s);
                                                            } else {
                                                                txt_nursing_02.setText("暂无建议");

                                                            }

                                                            // 患者建议
                                                            if (wenjuan.get(i2 - 1).getPATIENT_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean> patient_advice = wenjuan.get(i2 - 1).getPATIENT_ADVICE();
                                                                for (int i3 = 0; i3 < patient_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientadviceBean = patient_advice.get(i3);
                                                                    if (i3 == patient_advice.size() - 1) {
                                                                        s += patientadviceBean.getADVICE_CONTENT();
                                                                    } else {

                                                                        s += patientadviceBean.getADVICE_CONTENT() + "\n";
                                                                    }

                                                                }
                                                                txt_note_02.setText(s);
                                                            } else {
                                                                txt_note_02.setText("暂无建议");

                                                            }

                                                            // 其他
                                                            if (wenjuan.get(i2 - 1).getPATIENT_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean> patient_advice = wenjuan.get(i2 - 1).getPATIENT_ADVICE();
                                                                for (int i3 = 0; i3 < patient_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientadviceBean = patient_advice.get(i3);
                                                                    if (i3 == patient_advice.size() - 1) {
                                                                        s += patientadviceBean.getADVICE_CONTENT();
                                                                    } else {

                                                                        s += patientadviceBean.getADVICE_CONTENT() + "\n";
                                                                    }

                                                                }
                                                                txt_other_02.setText(s);
                                                            } else {
                                                                txt_other_02.setText("暂无建议");

                                                            }

                                                        }
                                                    }
//                                                // 获取选中value的坐标
//                                                MPPointD p = this.getPixelForValues(e.getX(), e.getY(), YAxis.AxisDependency.LEFT);
//                                                xValuePos = p.x;
//                                                yValuePos = p.y;
//                                                Log.i(TAG, "xValuePos = " + xValuePos + "     yValuePos = " + yValuePos);

                                                }

                                                @Override
                                                public void onNothingSelected() {

                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        } else {
                            for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : ((HistoryAssessBean) result).getServer_params().getReportList()) {

                                if (reportListBean.getFORM_ID() == 1) {
                                    listX.clear();
                                    // 第一个表
                                    cly_visib.setVisibility(View.VISIBLE);
                                    cly_visib_02_02.setVisibility(View.GONE);
                                    cly_visib_02.setVisibility(View.GONE);
                                    wenjuan = reportListBean.getWENJUAN();
                                    for (int i = 0; i < wenjuan.size(); i++) {
                                        HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = wenjuan.get(i);
                                        final String strTime = TimeChangeUtil.ms2DateOnlyDay(Long.parseLong(wenjuanBean.getREPORT_TIME()));
                                        // 设置小圆点
                                        listX.add(strTime);

                                        List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean> sublist = wenjuanBean.getSublist();
                                        for (int i1 = 0; i1 < sublist.size(); i1++) {
                                            HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean = sublist.get(i1);


                                            String current_risk_level = sublistBean.getCURRENT_RISK_LEVEL();

                                            final int s = Integer.parseInt(current_risk_level);

                                            if (5 == s) {
                                                entries.add(new Entry(i + 1, 1));
                                                colors.add(Color.parseColor("#05a558"));
                                            } else if (6 == s) {
                                                entries.add(new Entry(i + 1, 2));
                                                colors.add(Color.parseColor("#6cbdfe"));
                                            } else if (7 == s) {
                                                entries.add(new Entry(i + 1, 3));
                                                colors.add(Color.parseColor("#fed700"));
                                            } else if (8 == s) {
                                                entries.add(new Entry(i + 1, 4));
                                                colors.add(Color.parseColor("#ff7e00"));
                                            } else if (9 == s) {
                                                entries.add(new Entry(i + 1, 5));
                                                colors.add(Color.parseColor("#ff4e6b"));
                                            }


                                            chartview.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

                                                private float valEntry;
                                                private int iEntry;

                                                @Override
                                                public void onValueSelected(Entry e, Highlight h) {
                                                    // 获取Entry
                                                    iEntry = (int) e.getX();
                                                    valEntry = e.getY();
                                                    LogUtils.e("e.getX() = " + iEntry + "     e.getY() = " + valEntry);
                                                    for (int i2 = 0; i2 <= wenjuan.size(); i2++) {
                                                        if (i2 == iEntry) {
                                                            txt_coding.setText(wenjuan.get(i2 - 1).getREPORT_CODE());
                                                            txt_time.setText(wenjuan.get(i2 - 1).getREPORT_TIME() + "");
                                                            txt_total.setText(wenjuan.get(i2 - 1).getSublist().get(0).getCURRENT_RISK_VALUE() + "分");
                                                            txt_grade.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                            switch (wenjuan.get(i2 - 1).getSublist().get(0).getCURRENT_RISK_LEVEL()) {
                                                                case "5":
                                                                    iv_yuan.setImageResource(R.mipmap.diwei_yuan);
                                                                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));
                                                                    txt_grade.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                case "6":
                                                                    iv_yuan.setImageResource(R.mipmap.zhongwei_yuan);
                                                                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelBlueColor));
                                                                    txt_grade.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                case "7":
                                                                    iv_yuan.setImageResource(R.mipmap.gaowei_yuan);
                                                                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelYellowColor));
                                                                    txt_grade.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                case "8":
                                                                    iv_yuan.setImageResource(R.mipmap.jigaowei_yuan);
                                                                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelOrangeColor));
                                                                    txt_grade.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                case "9":
                                                                    iv_yuan.setImageResource(R.mipmap.quezhen_yuan);
                                                                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));
                                                                    txt_grade.setText(wenjuan.get(i2 - 1).getSublist().get(0).getRISK_NAME());
                                                                    break;
                                                                default:
                                                                    txt_grade.setText("暂无危险等级");
                                                                    break;
                                                            }
                                                            if (server_params.getJibinlist().size() != 0) {
                                                                txt_show.setText("");
                                                            }
                                                            if (wenjuan.get(i2 - 1).getWxys().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean> wxys = wenjuan.get(i2 - 1).getWxys();
                                                                for (int i3 = 0; i3 < wxys.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean wxy = wxys.get(i3);
                                                                    if (i3 == wxys.size() - 1) {
                                                                        s += wxy.getRISK_FACTOR_NAME();
                                                                    } else {
                                                                        s += wxy.getRISK_FACTOR_NAME() + "\n";
                                                                    }


                                                                }
                                                                txt_element.setText(s);
                                                            } else {
                                                                txt_element.setText("暂无风险因素");
                                                            }


                                                            if (wenjuan.get(i2 - 1).getDOCTOR_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean> doctor_advice = wenjuan.get(i2 - 1).getDOCTOR_ADVICE();
                                                                for (int i3 = 0; i3 < doctor_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean doctoradviceBean = doctor_advice.get(i3);
                                                                    if (i3 == doctor_advice.size() - 1) {
                                                                        s += doctoradviceBean.getADVICE_CONTENT();
                                                                    } else {
                                                                        s += doctoradviceBean.getADVICE_CONTENT() + "\n";
                                                                    }
                                                                }
                                                                txt_advise.setText(s);
                                                            } else {
                                                                txt_advise.setText("暂无建议");

                                                            }

                                                            if (wenjuan.get(i2 - 1).getPATIENT_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean> patient_advice = wenjuan.get(i2 - 1).getPATIENT_ADVICE();
                                                                for (int i3 = 0; i3 < patient_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientadviceBean = patient_advice.get(i3);
                                                                    if (i3 == patient_advice.size() - 1) {
                                                                        s += patientadviceBean.getADVICE_CONTENT();
                                                                    } else {

                                                                        s += patientadviceBean.getADVICE_CONTENT() + "\n";
                                                                    }

                                                                }
                                                                txt_note.setText(s);
                                                            } else {
                                                                txt_note.setText("暂无建议");

                                                            }

                                                            if (wenjuan.get(i2 - 1).getNURSE_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean> nurse_advice = wenjuan.get(i2 - 1).getNURSE_ADVICE();
                                                                for (int i3 = 0; i3 < nurse_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean nurseadviceBean = nurse_advice.get(i3);
                                                                    if (i3 == nurse_advice.size() - 1) {
                                                                        s += nurseadviceBean.getADVICE_CONTENT();
                                                                    } else {

                                                                        s += nurseadviceBean.getADVICE_CONTENT() + "\n";
                                                                    }

                                                                }
                                                                txt_nursing.setText(s);
                                                            } else {
                                                                txt_nursing.setText("暂无建议");

                                                            }

                                                            if (wenjuan.get(i2 - 1).getPATIENT_ADVICE().size() != 0) {
                                                                String s = "";
                                                                List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean> patient_advice = wenjuan.get(i2 - 1).getPATIENT_ADVICE();
                                                                for (int i3 = 0; i3 < patient_advice.size(); i3++) {
                                                                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientadviceBean = patient_advice.get(i3);
                                                                    if (i3 == patient_advice.size() - 1) {
                                                                        s += patientadviceBean.getADVICE_CONTENT();
                                                                    } else {
                                                                        s += patientadviceBean.getADVICE_CONTENT() + "\n";
                                                                    }
                                                                }
                                                                txt_other.setText(s);
                                                            } else {
                                                                txt_other.setText("暂无建议");

                                                            }

                                                        }
                                                    }
//                                                // 获取选中value的坐标
//                                                MPPointD p = this.getPixelForValues(e.getX(), e.getY(), YAxis.AxisDependency.LEFT);
//                                                xValuePos = p.x;
//                                                yValuePos = p.y;
//                                                Log.i(TAG, "xValuePos = " + xValuePos + "     yValuePos = " + yValuePos);

                                                }

                                                @Override
                                                public void onNothingSelected() {

                                                }
                                            });
                                        }
                                    }
                                }
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
        initX();
        initY();
    }

    private void initY() {
        XAxis xAxis = chartview_02.getXAxis();
        xAxis.setGranularity(1);     //这个很重要
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                LogUtils.e("----->getFormattedValue: " + value);
                //设置 xAxis.setGranularity(1);后 value是从0开始的，每次加1，
                int v = (int) value;
                if (v <= wenjuan.size() && v >= 0) {
                    String st = wenjuan.get(v).getREPORT_TIME();
                    return TimeChangeUtil.ms2DateOnlyDay(Long.parseLong(st));
                } else {
                    return null;
                }
            }
        };

        xAxis.setValueFormatter(formatter);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(listX_02));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
      /*  //设置X轴高度
        xAxis.setAxisLineWidth(1);*/
        //设置Y极值，我这里没设置最大值，因为项目需要没有设置最大值
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(wenjuan.size());
        xAxis.setDrawGridLines(false);
        setDiscounting_02(entries_02);
    }

    private void initX() {
        listX.add("");
        XAxis xAxis = chartview.getXAxis();
        xAxis.setGranularity(1);     //这个很重要
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                LogUtils.e("----->getFormattedValue: " + value);
                //设置 xAxis.setGranularity(1);后 value是从0开始的，每次加1，
                int v = (int) value;
                if (v <= wenjuan.size() && v >= 0) {
                    String st = wenjuan.get(v).getREPORT_TIME();
                    return TimeChangeUtil.ms2DateOnlyDay(Long.parseLong(st));
                } else {
                    return null;
                }
            }
        };

        xAxis.setValueFormatter(formatter);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(listX));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
      /*  //设置X轴高度
        xAxis.setAxisLineWidth(1);*/
        //设置Y极值，我这里没设置最大值，因为项目需要没有设置最大值
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(wenjuan.size());
        xAxis.setDrawGridLines(false);
        setDiscounting(entries);
    }


    @Override
    public void onFailed(Object error) {

    }
}
