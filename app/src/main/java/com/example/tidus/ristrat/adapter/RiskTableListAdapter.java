package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;

import java.util.ArrayList;
import java.util.List;

public class RiskTableListAdapter extends RecyclerView.Adapter<RiskTableListAdapter.ViewHolder> {
    private Context context;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean> wenjuannameBeans;

    public RiskTableListAdapter(Context context) {
        wenjuannameBeans = new ArrayList<>();
        this.context = context;
    }

    public void setWenjuannameBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean> wenjuannameBeans) {
        if (wenjuannameBeans != null) {
            this.wenjuannameBeans = wenjuannameBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RiskTableListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_risk_table, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RiskTableListAdapter.ViewHolder holder, final int position) {
        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean = wenjuannameBeans.get(position);
        holder.tv_risk_table.setText(wenjuannameBean.getFORM_NAME());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectTableListener.onClickSelectTable(position);
            }
        });
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

    private SetSelectTableListener setSelectTableListener;

    public interface SetSelectTableListener {
        void onClickSelectTable(int position);
    }

    public void setSetSelectTableListener(SetSelectTableListener setSelectTableListener) {
        this.setSelectTableListener = setSelectTableListener;
    }
}
