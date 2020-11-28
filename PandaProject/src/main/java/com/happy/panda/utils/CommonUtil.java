package com.happy.panda.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 公共工具类    无法归类的
 */
public class CommonUtil {

    /**
     * 动态设置shape背景
     *
     * @param mTvType
     */
    public static void setShapeBackground(Context context, TextView mTvType, String mStrokeColor, String mContentColor) {
        GradientDrawable drawable = new GradientDrawable();
        //设置圆角大小
        drawable.setCornerRadius(dip2px(context,10));
        //设置边缘线的宽以及颜色
        drawable.setStroke(dip2px(context, 2), Color.parseColor(mStrokeColor));
        //设置shape背景色
        drawable.setColor(Color.parseColor(mContentColor));
        //动态设置宽高
        drawable.setSize(ViewGroup.LayoutParams.WRAP_CONTENT, dip2px(context, 30));
        //设置到TextView中
        mTvType.setBackground(drawable);
        mTvType.setTextColor(Color.RED);
        mTvType.setPaddingRelative(dip2px(context, 4), 0, dip2px(context, 4), 0);
    }


    //dp转px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //px转dp
    public static int px2dip(Context context, int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()));
    }

}
