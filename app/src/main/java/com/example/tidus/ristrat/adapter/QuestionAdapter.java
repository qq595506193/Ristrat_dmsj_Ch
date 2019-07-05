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

    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuanBeans;


    public QuestionAdapter(Context context) {
        wenjuanBeans = new ArrayList<>();
        this.context = context;
    }

    public void setWenjuanBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuanBeans) {
        if (wenjuanBeans != null) {
            this.wenjuanBeans = wenjuanBeans;
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean = wenjuanBeans.get(position);
        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
            holder.tv_xiaobiao_name.setText(sublistBean.getFACTOR_GROUP_NAME());
            final TopicAdapter topicAdapter = new TopicAdapter(context);
            holder.rv_question_check.setLayoutManager(new GridLayoutManager(context, 4));
            holder.rv_question_check.setAdapter(topicAdapter);
            topicAdapter.setSublistBeans(wenjuanBean.getSublist(), wenjuanBean.getFACTOR_GROUP_SEQ());
            topicAdapter.setWenjuanBeans(wenjuanBeans);
            topicAdapter.setSetItemCheckListener(new TopicAdapter.SetItemCheckListener() {
                @Override
                public void onItemCheck(View itemView, boolean isChecked, int position, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                    if (isChecked) {
                        if (setItemGroupCheckListener != null) {

                            setItemGroupCheckListener.setItemGroupCheck(isChecked, position, sublistBean);

                        }
                    }
                }
            });
            topicAdapter.notifyDataSetChanged();

        }


    }

    @Override
    public int getItemCount() {
        return wenjuanBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_xiaobiao_name;
        private final RecyclerView rv_question_check;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_xiaobiao_name = itemView.findViewById(R.id.tv_xiaobiao_name);
            rv_question_check = itemView.findViewById(R.id.rv_question_check);
        }
    }

    private SetItemGroupCheckListener setItemGroupCheckListener;

    public interface SetItemGroupCheckListener {
        void setItemGroupCheck(boolean isChecked, int position, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean);
    }

    public void setSetItemGroupCheckListener(SetItemGroupCheckListener setItemGroupCheckListener) {
        this.setItemGroupCheckListener = setItemGroupCheckListener;
    }
}
