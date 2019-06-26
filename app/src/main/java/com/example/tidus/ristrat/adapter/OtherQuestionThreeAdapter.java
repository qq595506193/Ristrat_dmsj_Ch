package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.utils.GetNowTime;

import java.util.ArrayList;
import java.util.List;

public class OtherQuestionThreeAdapter extends RecyclerView.Adapter<OtherQuestionThreeAdapter.ViewHolder> {
    private Context context;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans;
    private String age;

    public OtherQuestionThreeAdapter(Context context, String age) {
        sublistBeans = new ArrayList<>();
        this.context = context;
        this.age = age;
    }

    public void setSublistBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans) {
        if (sublistBeans != null) {
            this.sublistBeans = sublistBeans;
        }
        notifyDataSetChanged();
    }

    public interface onCheckedClickListener {
        void onCheckedClick(View view, int position, String itemText, String initNowTime, boolean isChecked);
    }

    private onCheckedClickListener onCheckedClickListener;

    public void setOnCheckedClickListener(OtherQuestionThreeAdapter.onCheckedClickListener onCheckedClickListener) {
        this.onCheckedClickListener = onCheckedClickListener;
    }

    @NonNull
    @Override
    public OtherQuestionThreeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.risk_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OtherQuestionThreeAdapter.ViewHolder holder, final int position) {
        RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean = sublistBeans.get(position);
        if (sublistBean.getFACTOR_GROUP_ID() == 22) {
            String risk_factor_name = sublistBean.getRISK_FACTOR_NAME();
            holder.cb_checked.setText(risk_factor_name);
            Integer integer = Integer.valueOf(age);
            holder.cb_checked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onCheckedClickListener != null) {
                        String initNowTime = GetNowTime.initNowTime();
                        String itemText = holder.cb_checked.getText().toString().trim();
                        onCheckedClickListener.onCheckedClick(holder.itemView, position, initNowTime, itemText, holder.cb_checked.isChecked());

                    }
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return sublistBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cb_checked;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_checked = itemView.findViewById(R.id.cb_checked);
        }
    }
}
