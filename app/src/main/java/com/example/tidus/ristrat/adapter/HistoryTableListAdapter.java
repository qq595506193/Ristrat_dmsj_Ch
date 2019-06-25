package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.HistoryAssessBean;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;

import java.util.ArrayList;
import java.util.List;

public class HistoryTableListAdapter extends RecyclerView.Adapter<HistoryTableListAdapter.ViewHolder> {
    private Context context;
    private List<HistoryAssessBean.ServerParamsBean.ReportListBean> wenjuannameBeans;

    public HistoryTableListAdapter(Context context) {
        wenjuannameBeans = new ArrayList<>();
        this.context = context;
    }

    public void setWenjuannameBeans(List<HistoryAssessBean.ServerParamsBean.ReportListBean> wenjuannameBeans) {
        if (wenjuannameBeans != null) {
            this.wenjuannameBeans = wenjuannameBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryTableListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_risk_table, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryTableListAdapter.ViewHolder holder, int position) {
        HistoryAssessBean.ServerParamsBean.ReportListBean reportListBean = wenjuannameBeans.get(position);
        holder.tv_risk_table.setText(reportListBean.getFORM_NAME());
    }

    @Override
    public int getItemCount() {
        return wenjuannameBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_risk_table;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_risk_table = itemView.findViewById(R.id.tv_risk_table);
        }
    }
}
