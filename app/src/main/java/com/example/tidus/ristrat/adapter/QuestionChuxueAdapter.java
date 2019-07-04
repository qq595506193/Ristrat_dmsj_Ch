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

public class QuestionChuxueAdapter extends RecyclerView.Adapter<QuestionChuxueAdapter.ViewHolder> {

    private Context context;

    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuanBeans;


    public QuestionChuxueAdapter(Context context, List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean> wenjuanBeans) {
        this.context = context;
        this.wenjuanBeans = wenjuanBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean wenjuanBean = wenjuanBeans.get(position);
        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean : wenjuanBean.getSublist()) {
            holder.tv_xiaobiao_name.setText(sublistBean.getFACTOR_GROUP_NAME());
            TopicChuxueAdapter topicChuxueAdapter = new TopicChuxueAdapter(context);
            holder.rv_question_check.setLayoutManager(new GridLayoutManager(context, 4));
            holder.rv_question_check.setAdapter(topicChuxueAdapter);
            topicChuxueAdapter.setSublistBeans(wenjuanBean.getSublist());
            topicChuxueAdapter.setOnCheckedClickListener(new TopicChuxueAdapter.onCheckedClickListener() {
                @Override
                public void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                    setItemNumber.onCheckedClick(view, position, itemText, initNowTime, isChecked, sublistBean);
                }

                @Override
                public void onMorenSelect(boolean checked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean) {
                    setItemNumber.onMorenSelect(checked, sublistBean);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return wenjuanBeans.size();
    }


    private SetItemNumber setItemNumber;

    public interface SetItemNumber {
        void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean);

        void onMorenSelect(boolean checked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean);

    }

    public void setSetItemNumber(SetItemNumber setItemNumber) {
        this.setItemNumber = setItemNumber;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_xiaobiao_name;
        private final RecyclerView rv_question_check;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_xiaobiao_name = itemView.findViewById(R.id.tv_xiaobiao_name);
            rv_question_check = itemView.findViewById(R.id.rv_question_check);

        }
    }
}
