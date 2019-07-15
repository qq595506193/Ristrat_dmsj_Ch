package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.SelectedTablesBean;

import java.util.List;

/**
 * Created by TriumphalSun
 * on 2019/7/15 0015
 */
public class BusinessListAdalter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SelectedTablesBean.ServerParamsBean.RemindlistBean> remindlistBeans;
    private final static int ONE = 0;
    private final static int OTHER = 1;

    public BusinessListAdalter(Context context, List<SelectedTablesBean.ServerParamsBean.RemindlistBean> remindlistBeans) {
        this.context = context;
        this.remindlistBeans = remindlistBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (getItemViewType(viewType) == ONE) {
            view = LayoutInflater.from(context).inflate(R.layout.item_business_list_title, parent, false);
            return new TitleViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_business_list_other, parent, false);
            return new OtherViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ONE) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;

        } else {
            OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == ONE) {
            return ONE;
        } else {
            return OTHER;
        }
    }

    @Override
    public int getItemCount() {
        return remindlistBeans == null ? 0 : remindlistBeans.size();
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(View itemView) {
            super(itemView);

        }
    }

    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        public OtherViewHolder(View itemView) {
            super(itemView);

        }
    }


}
