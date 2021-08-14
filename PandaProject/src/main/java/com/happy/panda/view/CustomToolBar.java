package com.happy.panda.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happy.panda.R;


/**
 * Created by Danny 姜.
 */
public class CustomToolBar extends RelativeLayout {

    private ImageView leftImg, rightImg;
    private TextView titleTextView;

    public interface ImgClickListener {
        void leftImgClick();

        void rightImgClick();
    }

    //2.创建一个接口变量
    private ImgClickListener imgClickListener;

    //3.为接口变量声明一个set方法，
    public void setImgClickListener(ImgClickListener imgClickListener) {
        this.imgClickListener = imgClickListener;
    }

    public CustomToolBar(Context context) {
        this(context, null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolBar(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        titleTextView = new TextView(context);

        leftImg = new ImageView(context);
        leftImg.setPadding(12, 12, 12, 12);

        rightImg = new ImageView(context);
        rightImg.setPadding(12, 12, 12, 12);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomToolBar);
        String titleText = ta.getString(R.styleable.CustomToolBar_titleText);
        //第二个参数表示默认颜色
        int titleTextColor = ta.getColor(R.styleable.CustomToolBar_myTitleTextColor, Color.BLACK);
        //已经由sp转为px
        float titleTextSize = ta.getDimension(R.styleable.CustomToolBar_titleTextSize, 16);

        //读取图片
        Drawable leftDrawable = ta.getDrawable(R.styleable.CustomToolBar_leftImageSrc);
        Drawable rightDrawable = ta.getDrawable(R.styleable.CustomToolBar_rightImageSrc);

        //回收TypedArray
        ta.recycle();


        leftImg.setImageDrawable(leftDrawable);
        rightImg.setImageDrawable(rightDrawable);
        titleTextView.setText(titleText);
        titleTextView.setTextSize(titleTextSize);
        titleTextView.setTextColor(titleTextColor);

        //初始化布局参数
        initLayoutParams();
        //初始化事件监听
        initListener();
    }

    private void initLayoutParams() {
        LayoutParams leftParams = new LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics()));
        //表示该控件和父容器的左边对齐
        leftParams.addRule(CENTER_VERTICAL | ALIGN_PARENT_LEFT, TRUE);
        addView(leftImg, leftParams);

        LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        titleParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(titleTextView, titleParams);

        LayoutParams rightParamsHA = new LayoutParams((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics()),
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, getResources().getDisplayMetrics()));
        rightParamsHA.addRule(CENTER_VERTICAL, TRUE);
        rightParamsHA.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(rightImg, rightParamsHA);
    }

    private void initListener() {
        //4.点击ImageView时调用接口中的方法
        leftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgClickListener != null) {
                    imgClickListener.leftImgClick();
                }
            }
        });

        rightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgClickListener != null) {
                    imgClickListener.rightImgClick();
                }
            }
        });
    }
}
