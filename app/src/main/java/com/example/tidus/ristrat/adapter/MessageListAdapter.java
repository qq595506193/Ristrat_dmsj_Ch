package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.MessageBean;
import com.example.tidus.ristrat.utils.TimeChangeUtil;
import com.example.tidus.ristrat.weight.MyRecyclerViewItem;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageListAdapter extends XRecyclerView.Adapter<MessageListAdapter.ViewHolder> {
    private Context context;
    private List<MessageBean.ServerParamsBean.ListBean> listBeans;

    public MessageListAdapter(Context context) {
        listBeans = new ArrayList<>();
        this.context = context;
    }

    public void setListBeans(List<MessageBean.ServerParamsBean.ListBean> listBeans) {
        if (listBeans != null) {
            this.listBeans = listBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.im_test_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageListAdapter.ViewHolder holder, final int position) {
        final MessageBean.ServerParamsBean.ListBean listBean = listBeans.get(position);
        Date date = TimeChangeUtil.strToDateLong(listBean.getSEND_TIME());

        //恢复状态
        holder.recyclerViewItem.apply();
        if (listBean.getMESSAGE_STATUS() == 0) {
            holder.iv_dian.setImageResource(R.mipmap.red_dian);
            holder.tv_message_time.setText(date.toString());
            holder.tv_system_message.setTextColor(Color.BLACK);
            holder.tv_message_content.setText(listBean.getMESSAGE_CONTENT());
        } else {
            holder.tv_system_message.setTextColor(Color.GRAY);
            holder.iv_dian.setImageResource(R.mipmap.gray_dian);
            holder.tv_message_time.setText(date.toString());
            holder.tv_message_content.setText(listBean.getMESSAGE_CONTENT());
        }
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBeans.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_message_content;
        private final TextView tv_message_time;
        private final ImageView iv_dian;
        private final TextView tv_system_message;
        private final TextView click;
        private final MyRecyclerViewItem recyclerViewItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_message_content = itemView.findViewById(R.id.tv_message_content);
            tv_message_time = itemView.findViewById(R.id.tv_message_time);
            iv_dian = itemView.findViewById(R.id.iv_dian);
            tv_system_message = itemView.findViewById(R.id.tv_system_message);
            recyclerViewItem = itemView.findViewById(R.id.scroll_item);
            click = itemView.findViewById(R.id.click);
        }
    }
}
