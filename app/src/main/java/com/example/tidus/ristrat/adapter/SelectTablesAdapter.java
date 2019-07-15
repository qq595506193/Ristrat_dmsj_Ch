package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.NowSelectTablesBean;

import java.util.List;

/**
 * Created by TriumphalSun
 * on 2019/7/15 0015
 */
public class SelectTablesAdapter extends RecyclerView.Adapter<SelectTablesAdapter.ViewHolder> {
    private Context context;
    private List<NowSelectTablesBean.ServerParamsBean.BusinesslistBean> businesslistBeans;

    public SelectTablesAdapter(Context context, List<NowSelectTablesBean.ServerParamsBean.BusinesslistBean> businesslistBeans) {
        this.context = context;
        this.businesslistBeans = businesslistBeans;
    }

    @NonNull
    @Override

    public SelectTablesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_tables, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectTablesAdapter.ViewHolder holder, final int position) {
        final NowSelectTablesBean.ServerParamsBean.BusinesslistBean businesslistBean = businesslistBeans.get(position);
        holder.tv_table_name.setText(businesslistBean.getASSESS_BUSINESS());// 评估项名字
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < businesslistBeans.size(); i++) {
                    NowSelectTablesBean.ServerParamsBean.BusinesslistBean businesslistBean = businesslistBeans.get(i);
                    if (i == position) {
                        businesslistBean.setChe_color(true);
                    } else {
                        businesslistBean.setChe_color(false);
                    }
                }
                setSelectTables.onSelectTables(position, businesslistBean);
            }
        });

        if (businesslistBean.isChe_color()) {
            holder.tv_table_name.setBackgroundColor(Color.parseColor("#28c48f"));
            holder.tv_table_name.setTextColor(Color.WHITE);
        } else {
            holder.tv_table_name.setBackgroundColor(Color.GRAY);
            holder.tv_table_name.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return businesslistBeans == null ? 0 : businesslistBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_table_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_table_name = itemView.findViewById(R.id.tv_table_name);
        }
    }

    // 选表切换回调
    private SetSelectTables setSelectTables;

    public interface SetSelectTables {
        void onSelectTables(int position, NowSelectTablesBean.ServerParamsBean.BusinesslistBean businesslistBean);
    }

    public void setSetSelectTables(SetSelectTables setSelectTables) {
        this.setSelectTables = setSelectTables;
    }
}
