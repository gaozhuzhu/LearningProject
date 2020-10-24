package com.happy.panda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.happy.panda.R;
import com.happy.panda.bean.TabNameBean;

import java.util.List;

public class VerticalTabAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private List<TabNameBean> mTabNameList;
    private int mCurrentPosition = 0;
    private OnItemClickListener mOItemClickListener;


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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_name_item, parent, false);
        return new TabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final TabViewHolder mHolder = (TabViewHolder) holder;
        mHolder.title.setText(mTabNameList.get(position).getmTabName());

        mHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentPosition = position;
                if (mOItemClickListener != null) {
                    mOItemClickListener.onItemClick(position);
                }
                notifyDataSetChanged();
            }
        });

        if (position == mCurrentPosition) {
            mHolder.rlLayout.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
        } else if (position == mCurrentPosition - 1) {
            mHolder.rlLayout.setBackgroundResource(R.drawable.shape_bottom_right_corner);
        } else if (position == mCurrentPosition + 1) {
            mHolder.rlLayout.setBackgroundResource(R.drawable.shape_top_right_corner);
        } else {
            mHolder.rlLayout.setBackgroundResource(R.drawable.gray_drawable);
        }
    }

    @Override
    public int getItemCount() {
        return mTabNameList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rlLayout;
        public TextView title;

        public TabViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tv_tab_name);
            rlLayout = v.findViewById(R.id.rl_root_layout);
        }
    }
}
