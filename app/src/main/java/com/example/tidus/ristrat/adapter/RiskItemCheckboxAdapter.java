package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.RiskAssessmentBean;
import com.example.tidus.ristrat.utils.CommonPopupWindow;

import java.util.List;

/**
 * Created by TriumphalSun
 * on 2019/7/17 0017
 */
public class RiskItemCheckboxAdapter extends RecyclerView.Adapter<RiskItemCheckboxAdapter.ViewHolder> {

    private Context context;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans;
    private List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans_checked;
    private CommonPopupWindow commonPopupWindow;
    private boolean isCommit = false;
    private RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean;
    private int group_id;


    public RiskItemCheckboxAdapter(Context context) {
        this.context = context;
    }

    public void setSublistBeans(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans) {
        if (sublistBeans != null) {
            this.sublistBeans = sublistBeans;
        }
        notifyDataSetChanged();
    }

    public void setCommit(boolean commit) {
        this.isCommit = commit;
        notifyDataSetChanged();
    }

    public void setWenjuannameBean(RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean wenjuannameBean) {
        if (wenjuannameBean != null) {
            this.wenjuannameBean = wenjuannameBean;
        }
        notifyDataSetChanged();
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

//    public void setSublistBeans_checked(List<RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean> sublistBeans_checked) {
//        if (sublistBeans_checked != null) {
//            this.sublistBeans_checked = sublistBeans_checked;
//        }
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public RiskItemCheckboxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.risk_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RiskItemCheckboxAdapter.ViewHolder holder, final int position) {
        final RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean = sublistBeans.get(position);
        holder.ck_checked.setText(sublistBean.getRISK_FACTOR_NAME());
        // 默认勾选
        if (sublistBean.getIsslect().equals("1")) {
            holder.ck_checked.setChecked(true);
            holder.iv_wenhao.setVisibility(View.VISIBLE);
            setGradeListener.onGradeListener(holder.ck_checked.isChecked(), sublistBean);
        } else {
            holder.ck_checked.setChecked(false);
            holder.iv_wenhao.setVisibility(View.INVISIBLE);
        }





//        // 控制联动
//        if (sublistBeans_checked != null) {
//            for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean bean : sublistBeans_checked) {
//                if (bean.getRISK_FACTOR_ID() == sublistBean.getRISK_FACTOR_ID()) {
//                    holder.ck_checked.setChecked(true);
//                } else {
//                    holder.ck_checked.setChecked(false);
//                }
//            }
//        }

        // checkbox监听







        holder.ck_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 算分回调+题ID
                setGradeListener.onGradeListener(holder.ck_checked.isChecked(), sublistBean);
                // 判断选中了其他，显示输入框
                if (holder.ck_checked.isChecked()) {
                    if (sublistBean.getSCORE_SHOW_TYPE() == 30) {
                        holder.et_shuru.setVisibility(View.VISIBLE);
                        String trim = holder.et_shuru.getText().toString().trim();
                    }
                } else {
                    //setCheckItem.onSetCheckItem(isChecked, position, sublistBean);
                    if (sublistBean.getSCORE_SHOW_TYPE() == 30) {
                        holder.et_shuru.setVisibility(View.GONE);
                    }
                }


                if(holder.ck_checked.isChecked()&&sublistBeans.get(position).getMUTEX_GROUP()==1){

                    if (getGroupIdListener!=null){
                        getGroupIdListener.getGroupId(group_id,position);
                    }

                }

            }
        });
        // 互斥单选
//        for (RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean bean : sublistBeans) {
//            if (group_id == bean.getFACTOR_GROUP_SEQ()) {
//                if (bean.getMUTEX_GROUP() == 1) {
//                    holder.ck_checked.setChecked(true);
//                }
//            } else {
//                if (bean.getMUTEX_GROUP() == 1) {
//                    holder.ck_checked.setChecked(false);
//                }
//            }
//        }


        // 判断提交后置为不能选择

        if (isCommit) {
            holder.ck_checked.setEnabled(false);
        } else {
            holder.ck_checked.setEnabled(true);
        }


        // 问号pop弹窗
        holder.iv_wenhao.setOnClickListener(new View.OnClickListener() {

            private TextView tv_wenhao_content;

            @Override
            public void onClick(View v) {

                commonPopupWindow = new CommonPopupWindow.Builder(context)
                        //设置PopupWindow布局
                        .setView(R.layout.item_wenhao_pop)
                        //设置宽高
                        .setWidthAndHeight(1000,
                                ViewGroup.LayoutParams.WRAP_CONTENT)
                        //设置动画
                        //.setAnimationStyle(R.style.PopupWindow)
                        //设置背景颜色，取值范围0.0f-1.0f 值越小越暗 1.0f为透明
                        //.setBackGroundLevel(0.5f)
                        //设置PopupWindow里的子View及点击事件
                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                            @Override
                            public void getChildView(View view, int layoutResId) {
                                TextView tv_wenhao_content = view.findViewById(R.id.tv_wenhao_content);
                                tv_wenhao_content.setText(sublistBean.getANALYSIS_SOURCE_STR());
                            }
                        })//设置外部是否可点击 默认是true
                        .setOutsideTouchable(true)
                        //开始构建
                        .create();

                commonPopupWindow.showAsDropDown(v, 0, 0);


            }
        });




        holder.ck_checked.setChecked(sublistBean.isChecked());

    }

    @Override
    public int getItemCount() {
        return sublistBeans == null ? 0 : sublistBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox ck_checked;
        private final ImageView iv_wenhao;
        private final EditText et_shuru;

        public ViewHolder(View itemView) {
            super(itemView);
            ck_checked = itemView.findViewById(R.id.cb_checked);
            iv_wenhao = itemView.findViewById(R.id.iv_wenhao);
            et_shuru = itemView.findViewById(R.id.et_shuru);
        }
    }

    // 算分回调
    private SetGradeListener setGradeListener;

    public interface SetGradeListener {
        void onGradeListener(boolean isChecked, RiskAssessmentBean.ServerParamsBean.WENJUANNAMEBean.XUANXIANGBean.WENJUANBean.SublistBean sublistBean);
    }

    public void setSetGradeListener(SetGradeListener setGradeListener) {
        this.setGradeListener = setGradeListener;
    }

    private getGroupIdListener getGroupIdListener;

    public void setGroupIdListener(getGroupIdListener getGroupIdListener){

        this.getGroupIdListener = getGroupIdListener;

    }

    public interface getGroupIdListener{

        void getGroupId(int groupId,int cheId);

    }


}
