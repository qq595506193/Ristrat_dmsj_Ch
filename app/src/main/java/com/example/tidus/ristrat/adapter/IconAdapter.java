package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {
    private Context context;
    private CaseControlBean.ServerParamsBean serverParamsBean;

    public IconAdapter(Context context, CaseControlBean.ServerParamsBean serverParamsBean) {
        this.context = context;
        this.serverParamsBean = serverParamsBean;
    }

    @NonNull
    @Override
    public IconAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAdapter.ViewHolder holder, int position) {
        CaseControlBean.ServerParamsBean.LevlistBean levlistBean1 = serverParamsBean.getLevlist().get(position);
        if (serverParamsBean.getLevlist().size() != 0 && serverParamsBean.getCURRENT_RISK_LEVEL() != null) {
            if (levlistBean1.getCURRENT_RISK_LEVEL().equals("5")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.vet_green);
            } else if (levlistBean1.getCURRENT_RISK_LEVEL().equals("6")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.vet_blue);
            } else if (levlistBean1.getCURRENT_RISK_LEVEL().equals("7")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.vet_yellow);
            } else if (levlistBean1.getCURRENT_RISK_LEVEL().equals("8")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.vet_orange);
            } else if (levlistBean1.getCURRENT_RISK_LEVEL().equals("9")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.vet_red);
            } else if (levlistBean1.getCURRENT_RISK_LEVEL().equals("21")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.chuxuedi);
            } else if (levlistBean1.getCURRENT_RISK_LEVEL().equals("22")) {
                holder.iv_icon.setVisibility(View.VISIBLE);
                holder.iv_icon.setImageResource(R.mipmap.chuxuegao);
            } else {
                holder.iv_icon.setVisibility(View.GONE);
            }

        } else {
            holder.iv_icon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return serverParamsBean.getLevlist() == null ? 0 : serverParamsBean.getLevlist().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
