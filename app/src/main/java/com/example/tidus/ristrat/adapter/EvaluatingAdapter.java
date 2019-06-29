package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tidus.ristrat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluatingAdapter extends RecyclerView.Adapter<EvaluatingAdapter.ViewHolder> {
    private Context context;

    public EvaluatingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EvaluatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evaluating, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EvaluatingAdapter.ViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_serial)// 序号
                TextView tv_serial;
        @BindView(R.id.tv_name)// 姓名
                TextView tv_name;
        @BindView(R.id.tv_explain)// 说明
                TextView tv_explain;
        @BindView(R.id.btn_termination_assess)// 终止评估
                Button btn_termination_assess;
        @BindView(R.id.btn_anew_assess)// 重新评估
                Button btn_anew_assess;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);

        }
    }
}
