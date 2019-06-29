package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.PopWindowUtil;

import java.util.List;


public class PatientListAdapter extends BaseMultiItemQuickAdapter<CaseControlBean.ServerParamsBean, BaseViewHolder> {

    private Context context;
    private QueryHMBean.ServerParamsBean queryHMBean;
    private PopWindowUtil popWindowUtil = new PopWindowUtil();

    public PatientListAdapter(List<CaseControlBean.ServerParamsBean> data, Context context) {
        super(data);
        this.context = context;
        queryHMBean = new QueryHMBean.ServerParamsBean();
        addItemType(1, R.layout.item_patient_list);
        addItemType(2, R.layout.item_patient_wu_list);
    }

    public void setQueryHMBean(QueryHMBean.ServerParamsBean queryHMBean) {
        if (queryHMBean != null) {
            this.queryHMBean = queryHMBean;
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, CaseControlBean.ServerParamsBean item) {
        View view_pop = (View) LayoutInflater.from(context).inflate(R.layout.pop_child, null);
        switch (helper.getItemViewType()) {
            case 1:
                helper.setText(R.id.tv_icon_name, item.getPATIENT_NAME());
                helper.setText(R.id.tv_age, item.getBIRTHDAY() + "岁");
                helper.setText(R.id.tv_bed_id, item.getBED_NUMBER() + "床");
                helper.setText(R.id.tv_accommodation_id, item.getVISIT_SQ_NO());
                helper.setText(R.id.tv_section, item.getIN_DEPT_NAME());
                helper.setText(R.id.tv_doctor_name, item.getDOCTOR_NAME());
                String patient_sex = item.getPATIENT_SEX();
                if (patient_sex.equals("M")) {
                    helper.setImageResource(R.id.iv_icon, R.mipmap.m_01);
                } else {
                    helper.setImageResource(R.id.iv_icon, R.mipmap.w_02);
                }

                for (QueryHMBean.ServerParamsBean.LISTBean listBean : queryHMBean.getLIST()) {
                    if (item.getVISIT_SQ_NO().equals(listBean.getVISIT_SQ_NO())) {
                        // 设置提醒颜色
                        helper.setBackgroundColor(R.id.card_view, Color.parseColor(listBean.getREMINDE_COLOR()));
                    }
                }
                // 就诊信息
                helper.setText(R.id.tv_content, item.getJibinlist().get(0).getDIAGNOSIS_DISEASE_NAME());


                if (item.getLevlist().size() != 0 && item.getCURRENT_RISK_LEVEL() != null) {
                    if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("5")) {
                        helper.setVisible(R.id.iv_assess, true);
                        helper.setImageResource(R.id.iv_assess, R.mipmap.vet_green);
                    } else if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("6")) {
                        helper.setVisible(R.id.iv_assess, true);
                        helper.setImageResource(R.id.iv_assess, R.mipmap.vet_blue);
                    } else if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("7")) {
                        helper.setVisible(R.id.iv_assess, true);
                        helper.setImageResource(R.id.iv_assess, R.mipmap.vet_yellow);
                    } else if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("8")) {
                        helper.setVisible(R.id.iv_assess, true);
                        helper.setImageResource(R.id.iv_assess, R.mipmap.vet_orange);
                    } else if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("9")) {
                        helper.setVisible(R.id.iv_assess, true);
                        helper.setImageResource(R.id.iv_assess, R.mipmap.vet_red);
                    } else if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("21")) {
                        helper.setVisible(R.id.iv_chuxue, true);
                        helper.setImageResource(R.id.iv_chuxue, R.mipmap.chuxuedi);
                    } else if (item.getLevlist().get(0).getCURRENT_RISK_LEVEL().equals("22")) {
                        helper.setVisible(R.id.iv_chuxue, true);
                        helper.setImageResource(R.id.iv_chuxue, R.mipmap.chuxuegao);
                    } else {
                        helper.setVisible(R.id.iv_assess, false);
                        helper.setVisible(R.id.iv_chuxue, false);
                    }
                } else {
                    helper.setVisible(R.id.iv_assess, true);
                    helper.setVisible(R.id.iv_chuxue, false);
                }

                initListener(helper, view_pop, item, helper.getAdapterPosition());// 事件监听

                break;
            case 2:
                helper.setText(R.id.tv_wu_bed_id, item.getBED_NUMBER() + "床");
                break;
        }
    }

    private void initListener(BaseViewHolder helper, final View view_pop, final CaseControlBean.ServerParamsBean serverParamsBean, final int position) {


        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ////////////popupWindow
                popWindowUtil.setSetOnIntentActivityHistoryPop(new PopWindowUtil.SetOnIntentActivityHistoryPop() {
                    @Override
                    public void onIntentActivityPop() {
                        setOnIntentActivityHistory.onStratActivity(serverParamsBean, queryHMBean, position);// 接口回调
                    }
                });

                popWindowUtil.setSetOnIntentActivityPop(new PopWindowUtil.SetOnIntentActivityPop() {
                    @Override
                    public void onIntentActivityPop(View view, int position) {
                        setOnIntentActivity.onStartActivity(serverParamsBean, queryHMBean, view, position);// 接口回调
                    }
                });

                popWindowUtil.setSetOnIntentActivityCancelPop(new PopWindowUtil.SetOnIntentActivityCancelPop() {
                    @Override
                    public void onIntentActivityPop() {
                        setOnIntentActivityCancel.onStartActivity(queryHMBean, position);// 接口回调
                    }
                });

                ////////////////////
                LogUtils.e("坐标===================" + position);
                for (QueryHMBean.ServerParamsBean.LISTBean listBean : queryHMBean.getLIST()) {
                    if (listBean.getVISIT_SQ_NO().equals(serverParamsBean.getVISIT_SQ_NO())) {
                        popWindowUtil.showAssessPopupWindow(context, queryHMBean, serverParamsBean, v, position, view_pop, 180, -260, R.style.PopupWindow);
                        return;
                    }
                }

                setOnIntentStartActivity.onStartActivity(serverParamsBean, queryHMBean, position);


            }
        });
    }

    private SetOnIntentActivity setOnIntentActivity;

    public interface SetOnIntentActivity {
        void onStartActivity(CaseControlBean.ServerParamsBean serverParamsBean, QueryHMBean.ServerParamsBean queryHMBean, View view, int position);
    }

    public void setSetOnIntentActivity(SetOnIntentActivity setOnIntentActivity) {
        this.setOnIntentActivity = setOnIntentActivity;
    }

    private SetOnIntentActivityCancel setOnIntentActivityCancel;

    public interface SetOnIntentActivityCancel {
        void onStartActivity(QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnIntentActivityCancel(SetOnIntentActivityCancel setOnIntentActivityCancel) {
        this.setOnIntentActivityCancel = setOnIntentActivityCancel;
    }

    private SetOnIntentActivityHistory setOnIntentActivityHistory;

    public interface SetOnIntentActivityHistory {
        void onStratActivity(CaseControlBean.ServerParamsBean serverParamsBean, QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnIntentActivityHistory(SetOnIntentActivityHistory setOnIntentActivityHistory) {
        this.setOnIntentActivityHistory = setOnIntentActivityHistory;
    }

    private SetOnIntentStartActivity setOnIntentStartActivity;

    public interface SetOnIntentStartActivity {
        void onStartActivity(CaseControlBean.ServerParamsBean serverParamsBean, QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnIntentStartActivity(SetOnIntentStartActivity setOnIntentStartActivity) {
        this.setOnIntentStartActivity = setOnIntentStartActivity;
    }
}
