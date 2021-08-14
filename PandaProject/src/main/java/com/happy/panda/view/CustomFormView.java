package com.happy.panda.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 自定义直方统计图
 */
public class CustomFormView extends View {
    private Paint mPaint;

    private RectF rectF;

    private Path path;

    public CustomFormView(Context context) {
        super(context);
        initPaint();
    }

    public CustomFormView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CustomFormView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        // 矩形
        rectF = new RectF(100, 450, 160, 500);
        // 矩形path
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画一个标题
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        canvas.drawText("2010-2021年统计数据", 20, 30, mPaint);

        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        // 横坐标 x轴起点100，终点500，y轴500
        canvas.drawLine(100, 500, 600, 500, mPaint);
        // 纵坐标
        canvas.drawLine(100, 150, 100, 500, mPaint);

        // 绘制纵坐标刻度
        int[] total = {0, 50, 100, 150, 200, 250, 300, 350};
        // 单位：亿万
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(20);
        canvas.drawText("单位：百万亿元", 30, 90, mPaint);

        mPaint.setColor(Color.GREEN);
        for (int i = 0; i < total.length; i++) {
            // 绘制刻度
            mPaint.setColor(Color.RED);
            if (i != 0) {
                canvas.drawLine(100, 500 - total[i], 105, 500 - total[i], mPaint);
            }
            // 绘制文字
            mPaint.setColor(Color.BLUE);
            mPaint.setTextAlign(Paint.Align.RIGHT);
            // 纵坐标刻度
            canvas.drawText(total[i] + "", 90, 500 - total[i] + 5, mPaint);
        }

        float[] radiusArray = {8f, 8f, 8f, 8f, 0f, 0f, 0f, 0f};
        // 柱状图四个
        for (int i = 0; i < 4; i++) {
            // 绘制柱状图 宽度30，高度依次递增50，间距30
            float startMarginLeft = 150 + i * 60;
            rectF.set(startMarginLeft, 450 - i * 50, startMarginLeft + 30, 500);
            path.addRoundRect(rectF, radiusArray, Path.Direction.CCW);
            canvas.drawPath(path, mPaint);
            // 绘制横坐标文字
            String[] textYear = {"2021", "2022", "2023", "2024", "2025"};
            mPaint.setTextSize(15);
            float textWidth = mPaint.measureText(textYear[i]);
            float endMargin = startMarginLeft + 30;
            float xCenter = (endMargin + startMarginLeft + textWidth) / 2;
            canvas.drawText(textYear[i], xCenter, 520, mPaint);
        }
    }
}
