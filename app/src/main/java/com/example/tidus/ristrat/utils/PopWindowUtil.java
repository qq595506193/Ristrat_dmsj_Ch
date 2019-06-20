package com.example.tidus.ristrat.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.tidus.ristrat.R;
import com.example.tidus.ristrat.bean.CaseControlBean;

import java.util.List;

public class PopWindowUtil implements View.OnClickListener {

    private static PopWindowUtil instance;// 私有化构造方法，变成单例模式
    private PopupWindow mPopupWindow;
    private View customView;


    private PopWindowUtil() {
    }

    // 对外提供一个该类的实例，考虑多线程问题，进行同步操作
    public static PopWindowUtil getInstance() {
        if (instance == null) {
            synchronized (PopWindowUtil.class) {
                if (instance == null) {
                    instance = new PopWindowUtil();
                }
            }
        }
        return instance;
    }

    /**
     * @param cx    activity
     * @param view  传入需要显示在什么控件下
     * @param view1 传入内容的view
     * @return
     */
    public PopWindowUtil makePopupWindow(Context cx, View view, View view1, int color) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wmManager = (WindowManager) cx.getSystemService(Context.WINDOW_SERVICE);
        wmManager.getDefaultDisplay().getMetrics(dm);
        int Hight = dm.heightPixels;

        mPopupWindow = new PopupWindow(cx);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(color));
        view1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        // 设置PopupWindow的大小（宽度和高度）
        mPopupWindow.setWidth(view.getWidth());
        mPopupWindow.setHeight((Hight - view.getBottom()) * 2 / 3);
        // 设置PopupWindow的内容view
        mPopupWindow.setContentView(view1);
        mPopupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
        mPopupWindow.setTouchable(true); // 设置PopupWindow可触摸
        mPopupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸

        return instance;
    }

    /**
     * @param cx                上下文
     * @param serverParamsBeans
     * @param activity          activity
     * @param view              在哪个控件下显示
     * @param view1             传入显示的布局
     * @param xOff              x坐标
     * @param yOff              y坐标
     * @param anim              显示及消失动画
     */
    public void showAssessPopupWindow(final Context cx, List<CaseControlBean.ServerParamsBean> serverParamsBeans, Activity activity, View view, View view1,
                                      int xOff, int yOff, int anim) {

/**
 * pop弹窗
 */

        if (mPopupWindow == null) {

// // 获取自定义布局文件pop.xml的视图
            customView = view1;
            // 创建PopupWindow实例,200,150分别是宽度和高度
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
        TextView tv_now_assess = customView.findViewById(R.id.tv_now_assess);
        TextView tv_no_assess = customView.findViewById(R.id.tv_no_assess);
        TextView tv_history_assess = customView.findViewById(R.id.tv_history_assess);


        ////////////////////////////////////
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(view1, Gravity.CENTER, xOff, yOff);
        }

    }

    public void showSelectAssessTablePopupWindow(final Context cx, Activity activity, View view, View view1,
                                                 int xOff, int yOff, int anim) {

/**
 * pop弹窗
 */

        if (mPopupWindow == null) {

// // 获取自定义布局文件pop.xml的视图
            customView = view1;
            // 创建PopupWindow实例,200,150分别是宽度和高度
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


        /////////////////////////////////////

        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(view1, Gravity.CENTER, xOff, yOff);
        }

    }

    public void disPopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {

            mPopupWindow.dismiss();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_now_assess:
                break;
            case R.id.tv_no_assess:
                break;
            case R.id.tv_history_assess:
                break;
        }
    }
}
