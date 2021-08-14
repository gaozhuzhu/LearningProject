package com.happy.panda.adapter;

import java.util.List;

import com.happy.panda.R;
import com.happy.panda.bean.TabNameBean;

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

public class VerticalTabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_FOOT = 1000;
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

    public VerticalTabAdapter(Context context, List<TabNameBean> tabNameList) {
        this.mContext = context;
        this.mTabNameList = tabNameList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_name_item, parent, false);
        if (viewType == TYPE_FOOT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_name_item, parent, false);
            return new FootViewHolder(view);
        }
        return new TabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,
        @SuppressLint("RecyclerView") final int position) {
        if (holder instanceof TabViewHolder) {
            TabViewHolder mHolder = (TabViewHolder)holder;
            mHolder.title.setText(mTabNameList.get(position).getTabName());
            if (mCurrentPosition == position) {
                mHolder.title.setTextColor(Color.parseColor("#ff00ff"));
            } else {
                mHolder.title.setTextColor(Color.BLACK);
            }
        } else {
            FootViewHolder mHolder = (FootViewHolder)holder;
            mHolder.mFootTitle.setText("");
            mHolder.itemView.setEnabled(false);
        }

        // 更新选中背景
        updateSelectBackground(holder, position);

        holder.itemView.setOnClickListener(v -> {
            mCurrentPosition = position;
            if (mOItemClickListener != null) {
                mOItemClickListener.onItemClick(position);
            }
            notifyDataSetChanged();
        });
    }

    private void updateSelectBackground(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == mCurrentPosition) {
            holder.itemView.setBackgroundResource(R.color.white);
        } else if (position == mCurrentPosition - 1) {
            holder.itemView.setBackgroundResource(R.drawable.layer_bottom_right_corner);
        } else if (position == mCurrentPosition + 1) {
            holder.itemView.setBackgroundResource(R.drawable.layer_top_right_corner);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.gray_drawable);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mTabNameList.size()) {
            return TYPE_FOOT;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mTabNameList == null ? 0 : mTabNameList.size() + 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public TabViewHolder(View v) {
            super(v);
            title = (TextView)v.findViewById(R.id.tv_tab_name);
        }
    }


    private static class FootViewHolder extends RecyclerView.ViewHolder {
        private TextView mFootTitle;
        private LinearLayout linearLayout;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            mFootTitle = (TextView)itemView.findViewById(R.id.tv_tab_name);
            linearLayout = itemView.findViewById(R.id.ll_root_layout);
        }
    }
}
