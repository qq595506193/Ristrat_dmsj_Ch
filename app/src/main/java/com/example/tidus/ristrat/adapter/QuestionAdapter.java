package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private Context context;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean> wenjuannameBeans;
    private String age;

    public QuestionAdapter(Context context, String age) {
        wenjuannameBeans = new ArrayList<>();
        this.context = context;
        this.age = age;
    }

    public void setSublistBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean> wenjuannameBeans) {
        if (wenjuannameBeans != null) {
            this.wenjuannameBeans = wenjuannameBeans;
        }
        notifyDataSetChanged();
    }

    public interface onCheckedClickListener {
        void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked);
    }

    private onCheckedClickListener onCheckedClickListener;

    public void setOnCheckedClickListener(QuestionAdapter.onCheckedClickListener onCheckedClickListener) {
        this.onCheckedClickListener = onCheckedClickListener;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.risk_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionAdapter.ViewHolder holder, final int position) {
        RiskItemAdapter riskItemAdapter = new RiskItemAdapter(context, age);
        holder.rv_risk_list.setLayoutManager(new GridLayoutManager(context, 4));
        holder.rv_risk_list.setAdapter(riskItemAdapter);
        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean : wenjuannameBeans) {
            if (wenjuannameBean.getFORM_ID() == 1) {
                RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean = wenjuannameBean.getXUANXIANG().get(0);
                holder.tv_name.setText(xuanxiangBean.getWENJUAN().get(position).getFACTOR_GROUP_NAME());
                for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean : xuanxiangBean.getWENJUAN()) {
                    riskItemAdapter.setSublistBeans(wenjuanBean.getSublist());
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean : wenjuannameBeans) {
            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean xuanxiangBean : wenjuannameBean.getXUANXIANG()) {
                return xuanxiangBean.getWENJUAN().size();
            }
        }
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView tv_name;
        private final RecyclerView rv_risk_list;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            rv_risk_list = itemView.findViewById(R.id.rv_risk_list);
        }
    }
}
