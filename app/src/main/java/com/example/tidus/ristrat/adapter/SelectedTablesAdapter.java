package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.NowSelectTablesBean;
import com.example.tidus.ristrat.bean.SelectedTablesBean;

import java.util.List;

/**
 * Created by TriumphalSun
 * on 2019/7/15 0015
 */
public class SelectedTablesAdapter extends RecyclerView.Adapter<SelectedTablesAdapter.ViewHolder> {
    private Context context;
    private List<SelectedTablesBean.ServerParamsBean.BusinesslistBean> businesslist;

    public SelectedTablesAdapter(Context context, List<SelectedTablesBean.ServerParamsBean.BusinesslistBean> businesslist) {
        this.context = context;
        this.businesslist = businesslist;
    }

    @NonNull
    @Override
    public SelectedTablesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_tables, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedTablesAdapter.ViewHolder holder, final int position) {
        final SelectedTablesBean.ServerParamsBean.BusinesslistBean businesslistBean = businesslist.get(position);
        holder.tv_table_name.setText(businesslistBean.getASSESS_BUSINESS());// 评估项名字
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setSelectTables.onSelectTables(position, businesslistBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return businesslist == null ? 0 : businesslist.size();
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
        void onSelectTables(int position, SelectedTablesBean.ServerParamsBean.BusinesslistBean businesslistBean);
    }

    public void setSetSelectTables(SetSelectTables setSelectTables) {
        this.setSelectTables = setSelectTables;
    }
}
