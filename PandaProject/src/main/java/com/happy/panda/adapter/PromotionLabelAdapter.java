package com.happy.panda.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happy.panda.R;
import com.happy.panda.bean.TabNameBean;

import java.util.List;

public class PromotionLabelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private List<TabNameBean> mTabNameList;
    private int mCurrentPosition = 0;
    private OnItemClickListener mOItemClickListener;
    private View view;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOItemClickListener = onItemClickListener;
    }

    public PromotionLabelAdapter(Context context, List<TabNameBean> tabNameList) {
        this.mContext = context;
        this.mTabNameList = tabNameList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promotion_label_view,
                parent, false);
        return new TabViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mTabNameList == null ? 0 : mTabNameList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder {

        public TabViewHolder(View v) {
            super(v);
        }
    }

}
