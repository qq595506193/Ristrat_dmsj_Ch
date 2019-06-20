package com.example.tidus.ristrat.adapter;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class CaseContrilAdapter extends RecyclerView.Adapter<CaseContrilAdapter.ViewHolder> {
    private Context context;
    private List<CaseControlBean.ServerParamsBean> serverParamsBeans;

    public CaseContrilAdapter(Context context) {
        serverParamsBeans = new ArrayList<>();
        this.context = context;
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
        CaseControlBean.ServerParamsBean serverParamsBean = serverParamsBeans.get(position);

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
        String reminde_level = serverParamsBean.getREMINDE_LEVEL();
        if (reminde_level.equals("10")) {
            holder.card_view.setBackgroundResource(R.color.color_common);
        } else if (reminde_level.equals("20")) {
            holder.card_view.setBackgroundResource(R.color.color_high_risk);
        } else {
            holder.card_view.setBackgroundResource(R.color.white);
        }

        initListener(holder);// 事件监听

    }

    private void initListener(ViewHolder holder) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        }
    }
}
