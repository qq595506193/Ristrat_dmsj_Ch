package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.QueryHMBean;

import java.util.ArrayList;
import java.util.List;

public class EvaluatingAdapter extends RecyclerView.Adapter<EvaluatingAdapter.ViewHolder> {
    private Context context;
    private List<QueryHMBean.ServerParamsBean.TixingListBean> tixingListBeans;
    private AlertDialog.Builder builder;
    private boolean isChecked = false;

    public EvaluatingAdapter(Context context) {
        tixingListBeans = new ArrayList<>();
        this.context = context;
    }

    public void setTixingListBean(List<QueryHMBean.ServerParamsBean.TixingListBean> tixingListBeans) {
        if (tixingListBeans != null) {
            this.tixingListBeans = tixingListBeans;
        }
        notifyDataSetChanged();
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EvaluatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evaluating, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EvaluatingAdapter.ViewHolder holder, final int position) {
        if (tixingListBeans.size() != 0) {
            final QueryHMBean.ServerParamsBean.TixingListBean tixingListBean = tixingListBeans.get(position);
            holder.tv_serial.setText(tixingListBean.getBED_NUMBER());
            holder.tv_name.setText(tixingListBean.getPATIENT_NAME());
            holder.tv_questionnaire.setText(tixingListBean.getFORM_NAME());
            holder.tv_explain.setText("上次评估未完成");


            holder.btn_anew_assess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder = new AlertDialog.Builder(context).setIcon(R.mipmap.tixing).setTitle("提醒")
                            .setMessage("确认要继续评估吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 继续评估
                                    setAssessAnewListener.onAssessAnew(tixingListBean);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();

                }
            });
            // 全选
            tixingListBean.setCheckBox(isChecked);
            holder.ck_selected.setChecked(tixingListBean.isCheckBox());
            // 终止评估
            holder.btn_termination_assess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder = new AlertDialog.Builder(context).setIcon(R.mipmap.tixing).setTitle("提醒")
                            .setMessage("确认要终止评估吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 终止评估
                                    setAssessCancelListener.onAssessCancel(tixingListBean, tixingListBeans, position);
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();

                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return tixingListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_serial;
        private final TextView tv_name;
        private final TextView tv_explain;
        private final TextView btn_termination_assess;
        private final TextView btn_anew_assess;
        private final TextView tv_questionnaire;
        private final CheckBox ck_selected;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_serial = itemView.findViewById(R.id.tv_serial);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_explain = itemView.findViewById(R.id.tv_explain);
            btn_termination_assess = itemView.findViewById(R.id.btn_termination_assess);
            btn_anew_assess = itemView.findViewById(R.id.btn_anew_assess);
            tv_questionnaire = itemView.findViewById(R.id.tv_questionnaire);
            ck_selected = itemView.findViewById(R.id.ck_selected);
        }
    }

    private SetAssessCancelListener setAssessCancelListener;

    public interface SetAssessCancelListener {
        void onAssessCancel(QueryHMBean.ServerParamsBean.TixingListBean tixingListBean, List<QueryHMBean.ServerParamsBean.TixingListBean> tixingListBeans, int position);
    }

    public void setSetAssessCancelListener(SetAssessCancelListener setAssessCancelListener) {
        this.setAssessCancelListener = setAssessCancelListener;
    }

    private SetAssessAnewListener setAssessAnewListener;

    public interface SetAssessAnewListener {
        void onAssessAnew(QueryHMBean.ServerParamsBean.TixingListBean tixingListBean);
    }

    public void setSetAssessAnewListener(SetAssessAnewListener setAssessAnewListener) {
        this.setAssessAnewListener = setAssessAnewListener;
    }
}
