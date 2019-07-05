package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    private Context context;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuanBeans;
    private static int temp = -1;
    private int factor_group_seq;
    private boolean childItem;
    private final int CHECK_BOX = 0;
    private final int WENHAO = 1;
    private final int SHURU = 2;

    public TopicAdapter(Context context) {
        wenjuanBeans = new ArrayList<>();
        sublistBeans = new ArrayList<>();
        this.context = context;
    }

    public void setSublistBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans, int factor_group_seq) {
        if (sublistBeans != null || factor_group_seq != 0) {
            this.sublistBeans = sublistBeans;
            this.factor_group_seq = factor_group_seq;
        }
        notifyDataSetChanged();
    }

    public void setWenjuanBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuanBeans) {
        if (wenjuanBeans != null) {
            this.wenjuanBeans = wenjuanBeans;
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override

    public TopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.risk_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopicAdapter.ViewHolder holder, final int position) {
        final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean = sublistBeans.get(position);
        if (sublistBean.getIsslect().equals("1")) {
            holder.cb_checked.setChecked(true);
            holder.iv_wenhao.setVisibility(View.VISIBLE);
        } else {
            holder.cb_checked.setChecked(false);
            holder.iv_wenhao.setVisibility(View.GONE);
        }
        holder.cb_checked.setText(sublistBean.getRISK_FACTOR_NAME());
        holder.cb_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setItemCheckListener != null) {
                    setItemCheckListener.onItemCheck(holder.itemView, holder.cb_checked.isChecked(), position, sublistBean);
                }

            }
        });
        // 输入框
        holder.cb_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (sublistBean.getSCORE_SHOW_TYPE() == 30) {
                        holder.et_shuru.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (sublistBean.getSCORE_SHOW_TYPE() == 30) {
                        holder.et_shuru.setVisibility(View.GONE);
                    }
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return sublistBeans.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb_checked;
        private final ImageView iv_wenhao;
        private final EditText et_shuru;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_checked = itemView.findViewById(R.id.cb_checked);
            iv_wenhao = itemView.findViewById(R.id.iv_wenhao);
            et_shuru = itemView.findViewById(R.id.et_shuru);
        }
    }

    private SetItemCheckListener setItemCheckListener;

    public interface SetItemCheckListener {
        void onItemCheck(View itemView, boolean isChecked, int position, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean);
    }

    public void setSetItemCheckListener(SetItemCheckListener setItemCheckListener) {
        this.setItemCheckListener = setItemCheckListener;
    }
}
