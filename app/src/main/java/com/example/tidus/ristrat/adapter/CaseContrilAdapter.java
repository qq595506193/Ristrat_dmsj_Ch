package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.mvp.view.CaseControlActivity;
import com.example.tidus.ristrat.utils.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

public class CaseContrilAdapter extends RecyclerView.Adapter<CaseContrilAdapter.ViewHolder> {
    private Context context;
    private List<CaseControlBean.ServerParamsBean> serverParamsBeans;
    private List<QueryHMBean.ServerParamsBean.LISTBean> listBeans;
    private CaseControlActivity caseControlActivity;
    private View view_pop;
    private PopWindowUtil popWindowUtil = new PopWindowUtil();
    private CaseControlBean.ServerParamsBean serverParamsBean;

    public CaseContrilAdapter(Context context, CaseControlActivity caseControlActivity) {
        serverParamsBeans = new ArrayList<>();
        listBeans = new ArrayList<>();
        this.context = context;
        this.caseControlActivity = caseControlActivity;
    }


    public void setListBeans(List<QueryHMBean.ServerParamsBean.LISTBean> listBeans) {
        if (listBeans != null) {
            this.listBeans = listBeans;
        }
        notifyDataSetChanged();
    }

    public void setCaseControlBean(List<CaseControlBean.ServerParamsBean> serverParamsBeans) {
        if (serverParamsBeans != null) {
            this.serverParamsBeans = serverParamsBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CaseContrilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_patient_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CaseContrilAdapter.ViewHolder holder, int position) {
        View view_pop = (View) LayoutInflater.from(context).inflate(R.layout.pop_child, null);
        serverParamsBean = serverParamsBeans.get(position);
        holder.tv_icon_name.setText(serverParamsBean.getPATIENT_NAME());
        holder.tv_age.setText(serverParamsBean.getBIRTHDAY() + "岁");
        holder.tv_bed_id.setText(serverParamsBean.getBED_NUMBER() + "床");
        holder.tv_accommodation_id.setText(serverParamsBean.getVISIT_SQ_NO());
        holder.tv_section.setText(serverParamsBean.getIN_DEPT_NAME());
        holder.tv_doctor_name.setText(serverParamsBean.getDOCTOR_NAME());

        String patient_sex = serverParamsBean.getPATIENT_SEX();
        if (patient_sex.equals("M")) {
            holder.iv_icon.setImageResource(R.mipmap.m_01);
        } else {
            holder.iv_icon.setImageResource(R.mipmap.w_02);
        }

        for (QueryHMBean.ServerParamsBean.LISTBean listBean : listBeans) {
            if (serverParamsBean.getVISIT_SQ_NO().equals(listBean.getVISIT_SQ_NO())) {
                if (listBean.getOPERATE_RESULT() == 10) {
                    holder.card_view.setBackgroundColor(Color.parseColor((String) listBean.getREMINDE_COLOR()));
                } else if (listBean.getOPERATE_RESULT() == 20) {
                    holder.card_view.setBackgroundColor(Color.parseColor((String) listBean.getREMINDE_COLOR()));
                } else {
                    holder.card_view.setBackgroundResource(R.color.white);
                }
            }


        }

        if (serverParamsBean.getLevlist().size() != 0 || serverParamsBean != null) {
            for (CaseControlBean.ServerParamsBean.LevlistBean levlistBean : serverParamsBean.getLevlist()) {
                if (levlistBean.getCURRENT_RISK_LEVEL().equals("5")) {
                    holder.iv_assess.setVisibility(View.VISIBLE);
                    holder.iv_assess.setImageResource(R.mipmap.vet_green);
                } else if (levlistBean.getCURRENT_RISK_LEVEL().equals("6")) {
                    holder.iv_assess.setVisibility(View.VISIBLE);
                    holder.iv_assess.setImageResource(R.mipmap.vet_blue);
                } else if (levlistBean.getCURRENT_RISK_LEVEL().equals("7")) {
                    holder.iv_assess.setVisibility(View.VISIBLE);
                    holder.iv_assess.setImageResource(R.mipmap.vet_yellow);
                } else if (levlistBean.getCURRENT_RISK_LEVEL().equals("8")) {
                    holder.iv_assess.setVisibility(View.VISIBLE);
                    holder.iv_assess.setImageResource(R.mipmap.vet_orange);
                } else if (levlistBean.getCURRENT_RISK_LEVEL().equals("9")) {
                    holder.iv_assess.setVisibility(View.VISIBLE);
                    holder.iv_assess.setImageResource(R.mipmap.vet_red);
                } else if (levlistBean.getCURRENT_RISK_LEVEL().equals("21")) {
                    holder.iv_chuxue.setVisibility(View.VISIBLE);
                    holder.iv_chuxue.setImageResource(R.mipmap.chuxuedi);
                } else if (levlistBean.getCURRENT_RISK_LEVEL().equals("22")) {
                    holder.iv_chuxue.setVisibility(View.VISIBLE);
                    holder.iv_chuxue.setImageResource(R.mipmap.chuxuegao);
                } else {
                    holder.iv_assess.setVisibility(View.GONE);
                    holder.iv_chuxue.setVisibility(View.GONE);
                }
            }
        } else {
            holder.iv_assess.setVisibility(View.GONE);
            holder.iv_chuxue.setVisibility(View.GONE);
        }


        initListener(holder, view_pop, serverParamsBean, position);// 事件监听

    }

    private void initListener(ViewHolder holder, final View view_pop, final CaseControlBean.ServerParamsBean serverParamsBean, final int position) {


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                popWindowUtil.showAssessPopupWindow(context, listBeans, serverParamsBean, caseControlActivity, v, position, view_pop, 180, -260, R.style.PopupWindow);


                popWindowUtil.setSetOnIntentActivityPop(new PopWindowUtil.SetOnIntentActivityPop() {
                    @Override
                    public void onIntentActivityPop(View view, int position) {
                        setOnIntentActivity.onStartActivity(serverParamsBeans, listBeans, view, position);// 接口回调
                    }
                });

                popWindowUtil.setSetOnIntentActivityCancelPop(new PopWindowUtil.SetOnIntentActivityCancelPop() {
                    @Override
                    public void onIntentActivityPop() {
                        setOnIntentActivityCancel.onStartActivity();// 接口回调
                    }
                });

                popWindowUtil.setSetOnIntentActivityHistoryPop(new PopWindowUtil.SetOnIntentActivityHistoryPop() {
                    @Override
                    public void onIntentActivityPop() {
                        setOnIntentActivityHistory.onStratActivity(serverParamsBeans, listBeans, position);// 接口回调
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return serverParamsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_icon;
        private final TextView tv_icon_name;
        private final TextView tv_age;
        private final TextView tv_bed_id;
        private final TextView tv_accommodation_id;
        private final TextView tv_section;
        private final TextView tv_doctor_name;
        private final TextView tv_content;
        private final ImageView iv_assess;
        private final CardView card_view;
        private final ImageView iv_chuxue;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_icon_name = itemView.findViewById(R.id.tv_icon_name);
            tv_age = itemView.findViewById(R.id.tv_age);
            tv_bed_id = itemView.findViewById(R.id.tv_bed_id);
            tv_accommodation_id = itemView.findViewById(R.id.tv_accommodation_id);
            tv_section = itemView.findViewById(R.id.tv_section);
            tv_doctor_name = itemView.findViewById(R.id.tv_doctor_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            iv_assess = itemView.findViewById(R.id.iv_assess);
            card_view = itemView.findViewById(R.id.card_view);
            iv_chuxue = itemView.findViewById(R.id.iv_chuxue);

        }
    }

    private SetOnIntentActivity setOnIntentActivity;

    public interface SetOnIntentActivity {
        void onStartActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, List<QueryHMBean.ServerParamsBean.LISTBean> listBeans, View view, int position);
    }

    public void setSetOnIntentActivity(SetOnIntentActivity setOnIntentActivity) {
        this.setOnIntentActivity = setOnIntentActivity;
    }

    private SetOnIntentActivityCancel setOnIntentActivityCancel;

    public interface SetOnIntentActivityCancel {
        void onStartActivity();
    }

    public void setSetOnIntentActivityCancel(SetOnIntentActivityCancel setOnIntentActivityCancel) {
        this.setOnIntentActivityCancel = setOnIntentActivityCancel;
    }

    private SetOnIntentActivityHistory setOnIntentActivityHistory;

    public interface SetOnIntentActivityHistory {
        void onStratActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, List<QueryHMBean.ServerParamsBean.LISTBean> listBeans, int position);
    }

    public void setSetOnIntentActivityHistory(SetOnIntentActivityHistory setOnIntentActivityHistory) {
        this.setOnIntentActivityHistory = setOnIntentActivityHistory;
    }
}
