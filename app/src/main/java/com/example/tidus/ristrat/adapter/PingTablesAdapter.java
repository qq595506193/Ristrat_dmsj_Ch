package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.NowSelectTablesBean;
import com.example.tidus.ristrat.bean.SelectedTablesBean;

import java.util.List;

/**
 * Created by TriumphalSun
 * on 2019/7/15 0015
 */
public class PingTablesAdapter extends RecyclerView.Adapter<PingTablesAdapter.ViewHolder> {
    private Context context;
    private List<SelectedTablesBean.ServerParamsBean.BusinesslistBean.ListformsBean> listforms;

    public PingTablesAdapter(Context context, List<SelectedTablesBean.ServerParamsBean.BusinesslistBean.ListformsBean> listforms) {
        this.context = context;
        this.listforms = listforms;
    }

    @NonNull
    @Override
    public PingTablesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tables_checkbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PingTablesAdapter.ViewHolder holder, int position) {
        final SelectedTablesBean.ServerParamsBean.BusinesslistBean.ListformsBean listformsBean = listforms.get(position);
        holder.ck_tables.setText(listformsBean.getFORM_NAME());
        holder.ck_tables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckboxFormId.onCheckboxFormId(holder.ck_tables.isChecked(),listformsBean.getFORM_ID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listforms == null ? 0 : listforms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox ck_tables;

        public ViewHolder(View itemView) {
            super(itemView);
            ck_tables = itemView.findViewById(R.id.ck_tables);
        }
    }

    private SetCheckboxFormId setCheckboxFormId;

    public interface SetCheckboxFormId {
        void onCheckboxFormId(boolean checked, int form_id);
    }

    public void setSetCheckboxFormId(SetCheckboxFormId setCheckboxFormId) {
        this.setCheckboxFormId = setCheckboxFormId;
    }
}
