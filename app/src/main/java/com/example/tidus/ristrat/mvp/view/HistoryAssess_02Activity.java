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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.example.tidus.ristrat.application.App.getContext;

public class HistoryAssess_02Activity extends BaseMvpActivity<IHistoryAssessContract.IHistoryAssessModel, IHistoryAssessContract.HistoryAssessPresenter> implements IHistoryAssessContract.IHistoryAssessView {


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

    @BindView(R.id.btn_log)
    Button btn_log;// 评估按钮

    @BindView(R.id.tv_zhenduan_content)
    TextView tv_zhenduan_content;
    @BindView(R.id.cly_visib_02)
    ConstraintLayout cly_visib_02;
    @BindView(R.id.cly_visib)
    ConstraintLayout cly_visib;
    @BindView(R.id.tv_table_name)
    TextView tv_table_name;
    @BindView(R.id.iv_yuan)
    ImageView iv_yuan;
    @BindView(R.id.btn_log_wu)
    Button btn_log_wu;
    @BindView(R.id.txt_other)
    TextView txt_other;
    @BindView(R.id.cly_02)
    ConstraintLayout cly_02;
    private HistoryTableListAdapter historyTableListAdapter;
    private LineData data;
    private TextView tv_login_name;
    private ImageView iv_message;
    private ImageView iv_close;

    private ImageView iv_back;
    private TextView tv_back;
    private Object operate_time;
    private QueryHMBean.ServerParamsBean.LISTBean listBean;
    private HistoryAssessBean historyAssessBean;
    private List<Entry> entries = new ArrayList<>();
    private List<String> listX;
    private int position;
    private int form_id = 1;
    private List<String> listY;
    private List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean> wenjuan;
    private String report_time;
    private HistoryAssessBean.ServerParamsBean server_params;

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
                HistoryAssess_02Activity.this.form_id = form_id;
                initPresenterData(form_id);
                if (form_id == 1) {
                    entries.clear();
                    listX.clear();
                    listY.clear();
                    colors.clear();
                    cly_visib.setVisibility(View.VISIBLE);
                    cly_visib_02.setVisibility(View.GONE);
                } else if (form_id == 2) {
                    entries.clear();
                    listX.clear();
                    listY.clear();
                    colors.clear();
                    cly_visib.setVisibility(View.VISIBLE);
                    cly_visib_02.setVisibility(View.GONE);
                }


            }
        });


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
        params.put("CONFIRM_FLAG", "20");
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
        return R.layout.activity_history_assess_02;
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
                    server_params = ((HistoryAssessBean) result).getServer_params();
                    tv_zhenduan_content.setText(((HistoryAssessBean) result).getServer_params().getJibinlist().get(0).getDIAGNOSIS_DISEASE_NAME());

                    tv_name.setText(((HistoryAssessBean) result).getServer_params().getPATIENT_NAME());
                    if (((HistoryAssessBean) result).getServer_params().getPATIENT_SEX().equals("M")) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    tv_office.setText(((HistoryAssessBean) result).getServer_params().getIN_DEPT_NAME());
                    tv_mark.setText(((HistoryAssessBean) result).getServer_params().getVISIT_SQ_NO());
                    if (((HistoryAssessBean) result).getServer_params().getReportList().size() != 0) {
                        cly_visib.setVisibility(View.VISIBLE);
                        cly_visib_02.setVisibility(View.GONE);
                        historyTableListAdapter.setWenjuannameBeans(((HistoryAssessBean) result).getServer_params().getReportList());
                        for (HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean : ((HistoryAssessBean) result).getServer_params().getReportList()) {
                            if (reportListBean.getFORM_ID() == form_id) {
                                reportListBean.che_color = true;// 按钮颜色
                                tv_table_name.setText(reportListBean.getFORM_NAME());
                                wenjuan = reportListBean.getWENJUAN();
                                // 表
                                // 预生成报告没有暂无报告
                                if (reportListBean.getWENJUAN().size() != 0) {
                                    for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean : reportListBean.getWENJUAN()) {
                                        initInfo(wenjuanBean);
                                    }
                                } else {
                                    cly_02.setVisibility(View.GONE);
                                    cly_visib.setVisibility(View.GONE);
                                    cly_visib_02.setVisibility(View.VISIBLE);
                                }
                            }

                        }
                    } else {
                        cly_visib.setVisibility(View.GONE);
                        cly_visib_02.setVisibility(View.VISIBLE);
                    }


                }
            }

            initViewChart();
        }
    }


    @Override
    public void onFailed(Object error) {

    }

    private void initViewChart() {
        if (form_id == 1) {
            //准备好每个点对应的y轴数值
            listY = new ArrayList<>();
            listY.add("");
            listY.add("低危");
            listY.add("中危");
            listY.add("高危");
            listY.add("极高危");
            listY.add("确诊");
        } else if (form_id == 2) {
            listY = new ArrayList<>();
            listY.add("");
            listY.add("地出血");
            listY.add("");
            listY.add("");
            listY.add("高出血");
        }
        //散点图
        //是否缩放X轴
        chartview.setScaleXEnabled(false);
        //是否缩放Y轴
        chartview.setScaleYEnabled(false);
        //准备好每个点对应的x轴数
        LogUtils.e(wenjuan.size() + "问卷长度");
        listX = new ArrayList<>();
        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean : wenjuan) {
            listX.add(wenjuanBean.getREPORT_TIME());
        }

        //设置样式
        YAxis rightAxis = chartview.getAxisRight();
        //设置图表右边的y轴禁用
        rightAxis.setEnabled(false);
        YAxis leftAxis = chartview.getAxisLeft();
        leftAxis.setValueFormatter(new IndexAxisValueFormatter(listY));
        //设置Y极值，我这里没设置最大值，因为项目需要没有设置最大值
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(6f);
        leftAxis.setDrawGridLines(false);
        //1.设置x轴和y轴的点

        for (int i = 0; i < wenjuan.size(); i++) {
            HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = wenjuan.get(i);
            List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean> sublist = wenjuanBean.getSublist();
            for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : sublist) {
                LogUtils.e("subList长度==" + sublist.size() + "");
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
                } else if (21 == s) {
                    entries.add(new Entry(i + 1, 1));
                    colors.add(Color.parseColor("#05a558"));
                } else if (22 == s) {
                    entries.add(new Entry(i + 1, 3));
                    colors.add(Color.parseColor("#ff4e6b"));
                }
            }
        }

        // 点击事件
        chartview.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            private float valEntry;
            private int iEntry;

            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // 获取Entry
                iEntry = (int) e.getX();
                valEntry = e.getY();
                LogUtils.e("e.getX() = " + iEntry + "     e.getY() = " + valEntry);
                for (int i = 0; i < wenjuan.size(); i++) {
                    HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean = wenjuan.get(i);
                    if (i + 1 == iEntry) {
                        if (wenjuanBean.getFORM_ID() == form_id) {
                            initInfo(wenjuanBean);
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        initX();

    }

    private void initInfo(HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean wenjuanBean) {
        // 危险等级sub
        for (HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
            report_time = sublistBean.getREPORT_TIME();
            txt_coding.setText(sublistBean.getREPORT_CODE());// 报告编码
            txt_time.setText(sublistBean.getREPORT_TIME());// 评估时间
            // 评分
            txt_total.setText(sublistBean.getCURRENT_RISK_VALUE() + "分");
            if (sublistBean.getRISK_DETAIL_DESC() != null) {
                txt_show.setText(sublistBean.getRISK_DETAIL_DESC());
            }

            switch (sublistBean.getCURRENT_RISK_LEVEL()) {
                case "5":
                    iv_yuan.setImageResource(R.mipmap.diwei_yuan);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));
                    break;
                case "6":
                    iv_yuan.setImageResource(R.mipmap.zhongwei_yuan);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelBlueColor));

                    break;
                case "7":
                    iv_yuan.setImageResource(R.mipmap.gaowei_yuan);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelYellowColor));

                    break;
                case "8":
                    iv_yuan.setImageResource(R.mipmap.jigaowei_yuan);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelOrangeColor));

                    break;
                case "9":
                    iv_yuan.setImageResource(R.mipmap.quezhen_yuan);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));

                    break;
                case "21":
                    iv_yuan.setImageResource(R.mipmap.chuxue_diwei);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelGreenColor));

                    break;
                case "22":
                    iv_yuan.setImageResource(R.mipmap.chuxue_gaowei);
                    txt_grade.setText(sublistBean.getRISK_NAME());
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));

                    break;
                default:
                    txt_grade.setText("暂无危险等级");
                    txt_grade.setTextColor(getContext().getResources().getColorStateList(R.color.levelRedColor));
                    break;
            }// 危险等级
            txt_show.setText("暂无评估说明");
        }
        // 危险因素集合
        if (wenjuanBean.getWxys().size() != 0) {
            List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean> wxys = wenjuanBean.getWxys();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < wxys.size(); i++) {
                HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.WxysBean wxy = wxys.get(i);

                s.append(wxy.getRISK_FACTOR_NAME()).append("\n");

            }
            txt_element.setText(s.toString());
        } else {
            txt_element.setText("暂无风险因素");
        }

        // 预防处理建议
        if (wenjuanBean.getDOCTOR_ADVICE().size() != 0) {
            List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean> doctor_advice = wenjuanBean.getDOCTOR_ADVICE();
            String s = "";
            for (int i = 0; i < doctor_advice.size(); i++) {
                HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.DOCTORADVICEBean doctoradviceBean = doctor_advice.get(i);
                s += doctoradviceBean.getADVICE_CONTENT() + "\n";
            }
            txt_advise.setText(s);
        } else {
            txt_advise.setText("暂无建议");
        }

        // 护理处理建议
        if (wenjuanBean.getNURSE_ADVICE().size() != 0) {
            List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean> nurse_advice = wenjuanBean.getNURSE_ADVICE();
            String s = "";
            for (int i = 0; i < nurse_advice.size(); i++) {
                HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.NURSEADVICEBean nurseadviceBean = nurse_advice.get(i);
                s += nurseadviceBean.getADVICE_CONTENT() + "\n";
            }
            txt_nursing.setText(s);
        } else {
            txt_nursing.setText("暂无建议");
        }

        // 患者温馨提示
        if (wenjuanBean.getPATIENT_ADVICE().size() != 0) {
            List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean> patient_advice = wenjuanBean.getPATIENT_ADVICE();
            String s = "";
            for (int i = 0; i < patient_advice.size(); i++) {
                HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.PATIENTADVICEBean patientadviceBean = patient_advice.get(i);
                if (patientadviceBean.getADVICE_CONTENT() != null) {
                    s += patientadviceBean.getADVICE_CONTENT() + "\n";
                } else {
                    s = "暂无建议";
                }

            }
            txt_note.setText(s);
        } else {
            txt_note.setText("暂无建议");
        }

        // 其他
        if (wenjuanBean.getELSE_ADVICE().size() != 0) {
            List<HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.ELSE_ADVICEBean> else_advice = wenjuanBean.getELSE_ADVICE();
            String s = "";
            for (int i = 0; i < else_advice.size(); i++) {
                HistoryAssessBean.ServerParamsBean.ReportListBean.WENJUANBean.ELSE_ADVICEBean else_adviceBean = else_advice.get(i);
                if (else_adviceBean.getADVICE_CONTENT() != null) {
                    s += else_adviceBean.getADVICE_CONTENT() + "\n";
                } else {
                    s = "暂无建议";
                }
            }
            txt_note.setText(s);
        } else {
            txt_note.setText("暂无建议");
        }
    }

    private void initX() {
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
        xAxis.setAxisMaximum(10f);
        xAxis.setDrawGridLines(false);
        setDiscounting(entries);
    }

    public void setDiscounting(List<Entry> entries) {

        LineDataSet dataSet = new LineDataSet(entries, "");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        entries.clear();
        listY.clear();
    }
}
