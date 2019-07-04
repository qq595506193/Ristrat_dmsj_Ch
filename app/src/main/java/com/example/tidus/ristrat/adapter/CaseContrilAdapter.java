package com.example.tidus.ristrat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.application.App;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.QueryHMBean;
import com.example.tidus.ristrat.mvp.view.CaseControlActivity;
import com.example.tidus.ristrat.utils.LogUtils;
import com.example.tidus.ristrat.utils.PopWindowUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class CaseContrilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CaseControlBean.ServerParamsBean> serverParamsBeans;
    private CaseControlActivity caseControlActivity;
    private View view_pop;
    private PopWindowUtil popWindowUtil = new PopWindowUtil();
    private QueryHMBean.ServerParamsBean queryHMBean;
    private final int YOU = 0;
    private final int WU = 1;

    public CaseContrilAdapter(Context context, CaseControlActivity caseControlActivity) {
        this.context = context;
        serverParamsBeans = new ArrayList<>();
        queryHMBean = new QueryHMBean.ServerParamsBean();
        this.caseControlActivity = caseControlActivity;
    }


    public void setQueryHMBean(QueryHMBean.ServerParamsBean queryHMBean) {
        if (queryHMBean != null) {
            this.queryHMBean = queryHMBean;

            for (CaseControlBean.ServerParamsBean serverParamsBean : serverParamsBeans) {
                for (QueryHMBean.ServerParamsBean.LISTBean listBean : queryHMBean.getLIST()) {
                    if (serverParamsBean.getVISIT_SQ_NO() != null) {
                        if (serverParamsBean.getVISIT_SQ_NO().equals(listBean.getVISIT_SQ_NO())) {
                            serverParamsBean.setType(true);
                            serverParamsBean.setColor(listBean.getREMINDE_COLOR());
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setCaseControlBean(List<CaseControlBean.ServerParamsBean> serverParamsBeans) {
        if (serverParamsBeans != null) {
            this.serverParamsBeans = serverParamsBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == YOU) {
            view = LayoutInflater.from(context).inflate(R.layout.item_patient_list, parent, false);
            return new ViewHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_patient_wu_list, parent, false);
            return new WuViewHolder(view);
        }

    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view_pop = (View) LayoutInflater.from(context).inflate(R.layout.pop_child, null);
        CaseControlBean.ServerParamsBean serverParamsBean = serverParamsBeans.get(position);
        if (getItemViewType(position) == YOU) {

            ViewHolder viewHolder = (ViewHolder) holder;


            if (serverParamsBean.getPATIENT_NAME() != null) {
                viewHolder.tv_icon_name.setText(serverParamsBean.getPATIENT_NAME());
            }
            if (serverParamsBean.getBIRTHDAY() != null) {
                viewHolder.tv_age.setText(serverParamsBean.getBIRTHDAY() + "岁");
            }
            if (serverParamsBean.getBED_NUMBER() != null) {
                viewHolder.tv_bed_id.setText(serverParamsBean.getBED_NUMBER() + "床");
            }

            if (serverParamsBean.getVISIT_SQ_NO() != null) {
                viewHolder.tv_accommodation_id.setText(serverParamsBean.getVISIT_SQ_NO());
            }

            if (serverParamsBean.getIN_DEPT_NAME() != null) {
                viewHolder.tv_section.setText(serverParamsBean.getIN_DEPT_NAME());
            }

            if (serverParamsBean.getDOCTOR_NAME() != null) {
                viewHolder.tv_doctor_name.setText(serverParamsBean.getDOCTOR_NAME());
            }

            // 判断提示框颜色
            if (serverParamsBean.isType()) {
                viewHolder.card_view.setBackgroundColor(Color.parseColor(serverParamsBean.getColor()));
            } else {
                viewHolder.card_view.setBackgroundColor(Color.WHITE);
            }

            if (serverParamsBean.getCARE_UNIT_NAME() != null) {
                viewHolder.tv_danyuan.setText(serverParamsBean.getCARE_UNIT_NAME());
            }


            if (serverParamsBean.getPATIENT_SEX() != null) {
                String patient_sex = serverParamsBean.getPATIENT_SEX();
                if (patient_sex.equals("M")) {
                    viewHolder.iv_icon.setImageResource(R.mipmap.m_01);
                } else {
                    viewHolder.iv_icon.setImageResource(R.mipmap.w_02);
                }
            }


            // 就诊信息
            if (serverParamsBean.getJibinlist().size() != 0 && serverParamsBean != null) {
                viewHolder.tv_content.setText(serverParamsBean.getJibinlist().get(0).getDIAGNOSIS_DISEASE_NAME());
            } else {
                viewHolder.tv_content.setText("暂无就诊信息");
            }

            IconAdapter iconAdapter = new IconAdapter(context, serverParamsBean);
            ((ViewHolder) holder).rv_icon.setLayoutManager(new LinearLayoutManager(App.getContext(), LinearLayoutManager.HORIZONTAL, false));
            ((ViewHolder) holder).rv_icon.setAdapter(iconAdapter);




            initListener(viewHolder, view_pop, serverParamsBean, position);// 事件监听
        } else if (getItemViewType(position) == WU) {
            WuViewHolder viewHolder = (WuViewHolder) holder;
            viewHolder.tv_wu_bed_id.setText(serverParamsBean.getBED_NUMBER() + "床");


        }


    }

    private void initListener(ViewHolder holder, final View view_pop, final CaseControlBean.ServerParamsBean serverParamsBean, final int position) {
        CaseControlBean.ServerParamsBean serverParamsBean1 = serverParamsBeans.get(position);
        EventBus.getDefault().postSticky(serverParamsBean1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ////////////popupWindow
                popWindowUtil.setSetOnIntentActivityHistoryPop(new PopWindowUtil.SetOnIntentActivityHistoryPop() {
                    @Override
                    public void onIntentActivityPop() {
                        setOnIntentActivityHistory.onStratActivity(serverParamsBeans, queryHMBean, position);// 接口回调
                    }
                });

                popWindowUtil.setSetOnIntentActivityPop(new PopWindowUtil.SetOnIntentActivityPop() {
                    @Override
                    public void onIntentActivityPop(View view, int position) {
                        setOnIntentActivity.onStartActivity(serverParamsBeans, queryHMBean, view, position);// 接口回调
                    }
                });

                popWindowUtil.setSetOnIntentActivityCancelPop(new PopWindowUtil.SetOnIntentActivityCancelPop() {
                    @Override
                    public void onIntentActivityPop() {
                        setOnIntentActivityCancel.onStartActivity(queryHMBean, position);// 接口回调
                    }
                });

                popWindowUtil.setSetOnTanChuang(new PopWindowUtil.SetOnTanChuang() {
                    @Override
                    public void OnTanChuang() {
                        setOnTanChuangDiaLog.OnTanChuangDiaLog(serverParamsBean, queryHMBean, position);
                    }
                });

                ////////////////////
                LogUtils.e("坐标===================" + position);
                if (queryHMBean != null) {
                    for (QueryHMBean.ServerParamsBean.LISTBean listBean : queryHMBean.getLIST()) {
                        if (listBean.getVISIT_SQ_NO().equals(serverParamsBean.getVISIT_SQ_NO())) {
                            popWindowUtil.showAssessPopupWindow(context, queryHMBean, serverParamsBean, v, position, view_pop, 180, -260, R.style.PopupWindow);
                            return;
                        }
                    }
                }


                setOnIntentStartActivity.onStartActivity(serverParamsBeans, queryHMBean, position);


            }
        });


    }

    @Override
    public int getItemViewType(int position) {
        CaseControlBean.ServerParamsBean serverParamsBean = serverParamsBeans.get(position);
        if (serverParamsBean != null) {
            if (TextUtils.isEmpty(serverParamsBean.getVISIT_SQ_NO())) {
                return WU;
            } else {
                return YOU;
            }
        }
        return YOU;
    }

    @Override
    public int getItemCount() {
        return serverParamsBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_icon;
        private final TextView tv_icon_name;
        private final TextView tv_age;
        private final TextView tv_bed_id;
        private final TextView tv_accommodation_id;
        private final TextView tv_section;
        private final TextView tv_doctor_name;
        private final TextView tv_content;
        private final CardView card_view;
        private TextView tv_danyuan;
        private final RecyclerView rv_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_icon_name = itemView.findViewById(R.id.tv_icon_name);
            tv_age = itemView.findViewById(R.id.tv_age);
            tv_bed_id = itemView.findViewById(R.id.tv_bed_id);
            tv_accommodation_id = itemView.findViewById(R.id.tv_accommodation_id);
            tv_section = itemView.findViewById(R.id.tv_section);
            tv_doctor_name = itemView.findViewById(R.id.tv_doctor_name);
            tv_content = itemView.findViewById(R.id.tv_content);
            card_view = itemView.findViewById(R.id.card_view);
            tv_danyuan = itemView.findViewById(R.id.tv_danyuan);
            rv_icon = itemView.findViewById(R.id.rv_icon);

        }
    }

    public static class WuViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_wu_bed;
        private TextView tv_wu_bed_id;


        public WuViewHolder(View itemView) {
            super(itemView);

            iv_wu_bed = itemView.findViewById(R.id.iv_wu_bed);
            tv_wu_bed_id = itemView.findViewById(R.id.tv_wu_bed_id);

        }
    }


    private SetOnIntentActivity setOnIntentActivity;

    public interface SetOnIntentActivity {
        void onStartActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, QueryHMBean.ServerParamsBean queryHMBean, View view, int position);
    }

    public void setSetOnIntentActivity(SetOnIntentActivity setOnIntentActivity) {
        this.setOnIntentActivity = setOnIntentActivity;
    }

    private SetOnIntentActivityCancel setOnIntentActivityCancel;

    public interface SetOnIntentActivityCancel {
        void onStartActivity(QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnIntentActivityCancel(SetOnIntentActivityCancel setOnIntentActivityCancel) {
        this.setOnIntentActivityCancel = setOnIntentActivityCancel;
    }

    private SetOnIntentActivityHistory setOnIntentActivityHistory;

    public interface SetOnIntentActivityHistory {
        void onStratActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnIntentActivityHistory(SetOnIntentActivityHistory setOnIntentActivityHistory) {
        this.setOnIntentActivityHistory = setOnIntentActivityHistory;
    }

    private SetOnIntentStartActivity setOnIntentStartActivity;

    public interface SetOnIntentStartActivity {
        void onStartActivity(List<CaseControlBean.ServerParamsBean> serverParamsBeans, QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnIntentStartActivity(SetOnIntentStartActivity setOnIntentStartActivity) {
        this.setOnIntentStartActivity = setOnIntentStartActivity;
    }

    private SetOnTanChuangDiaLog setOnTanChuangDiaLog;

    public interface SetOnTanChuangDiaLog {
        void OnTanChuangDiaLog(CaseControlBean.ServerParamsBean serverParamsBean, QueryHMBean.ServerParamsBean queryHMBean, int position);
    }

    public void setSetOnTanChuangDiaLog(SetOnTanChuangDiaLog setOnTanChuangDiaLog) {
        this.setOnTanChuangDiaLog = setOnTanChuangDiaLog;
    }
}
