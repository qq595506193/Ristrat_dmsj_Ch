package com.example.tidus.ristrat.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;
import com.example.tidus.ristrat.bean.QueryHMBean;

public class PopWindowUtil {

    private PopupWindow mPopupWindow;
    private View customView;


    /**
     * @param cx               上下文
     * @param queryHMBean
     * @param serverParamsBean
     * @param view             在哪个控件下显示
     * @param position
     * @param view1            传入显示的布局
     * @param xOff             x坐标
     * @param yOff             y坐标
     * @param anim             显示及消失动画
     */
    public void showAssessPopupWindow(final Context cx, QueryHMBean.ServerParamsBean queryHMBean, CaseControlBean.ServerParamsBean serverParamsBean, final View view, final int position, View view1,
                                      int xOff, int yOff, int anim) {

        /**
         * pop弹窗
         */
        if (mPopupWindow == null) {

            // 获取自定义布局文件pop.xml的视图
            customView = view1;
            // 创建PopupWindow实例,宽度和高度
            mPopupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            // 自定义view添加触摸事件
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
            mPopupWindow.setTouchable(true);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setAnimationStyle(anim);

        } else {
            customView = mPopupWindow.getContentView();
        }


        /** 在这里可以实现自定义视图的功能 */
        TextView tv_now_assess = customView.findViewById(R.id.tv_now_assess);// 立即评估
        TextView tv_next_assess = customView.findViewById(R.id.tv_next_assess);// 下次评估
        TextView tv_no_assess = customView.findViewById(R.id.tv_no_assess);// 不再评估
        TextView tv_history_assess = customView.findViewById(R.id.tv_history_assess);// 查看详情
        // 透明度
        tv_now_assess.getBackground().setAlpha(200);
        tv_next_assess.getBackground().setAlpha(200);
        tv_no_assess.getBackground().setAlpha(200);
        tv_history_assess.getBackground().setAlpha(200);
        //
        tv_now_assess.setVisibility(View.VISIBLE);
        tv_next_assess.setVisibility(View.VISIBLE);
        tv_next_assess.setVisibility(View.VISIBLE);
        tv_next_assess.setVisibility(View.VISIBLE);


        ////////////////////////////////////
        mPopupWindow.showAsDropDown(view, xOff, yOff);

        tv_now_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setOnIntentActivityPop.onIntentActivityPop(view, position);
                disPopupWindow();
            }
        });

        tv_no_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnIntentActivityCancelPop.onIntentActivityPop();
                disPopupWindow();
            }
        });

        tv_history_assess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnIntentActivityHistoryPop.onIntentActivityPop();
                disPopupWindow();
            }
        });


    }


    public void disPopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {

            mPopupWindow.dismiss();
        }
    }

    private SetOnIntentActivityPop setOnIntentActivityPop;

    public interface SetOnIntentActivityPop {
        void onIntentActivityPop(View view, int position);
    }

    public void setSetOnIntentActivityPop(SetOnIntentActivityPop setOnIntentActivityPop) {
        this.setOnIntentActivityPop = setOnIntentActivityPop;
    }

    private SetOnIntentActivityCancelPop setOnIntentActivityCancelPop;

    public interface SetOnIntentActivityCancelPop {
        void onIntentActivityPop();
    }

    public void setSetOnIntentActivityCancelPop(SetOnIntentActivityCancelPop setOnIntentActivityCancelPop) {
        this.setOnIntentActivityCancelPop = setOnIntentActivityCancelPop;
    }

    private SetOnIntentActivityHistoryPop setOnIntentActivityHistoryPop;

    public interface SetOnIntentActivityHistoryPop {
        void onIntentActivityPop();
    }

    public void setSetOnIntentActivityHistoryPop(SetOnIntentActivityHistoryPop setOnIntentActivityHistoryPop) {
        this.setOnIntentActivityHistoryPop = setOnIntentActivityHistoryPop;
    }
}
