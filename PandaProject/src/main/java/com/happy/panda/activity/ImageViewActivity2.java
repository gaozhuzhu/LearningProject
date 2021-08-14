package com.happy.panda.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.happy.panda.R;
import com.happy.panda.adapter.PromotionLabelAdapter;
import com.happy.panda.adapter.VerticalTabAdapter;
import com.happy.panda.bean.TabNameBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片缓存
 */
public class ImageViewActivity2 extends AppCompatActivity {

    private LruCache<String, Bitmap> tempCache;
    private LinearLayoutManager layoutManager;
    private RecyclerView rlvLabelLayout;
    private List<TabNameBean> mList;
    private PromotionLabelAdapter promotionLabelAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_layout);
        initView();
        initData();

        // 指定 LruCache 的最大空间为 20M，当超过 20M 时，LruCache 会根据内部缓存策略将多余 Bitmap 移除
        int cacheSize = 20 * 1024 * 1024;
        tempCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getAllocationByteCount();
            }
        };
    }

    private void initView() {
        rlvLabelLayout = findViewById(R.id.rlv_label_layout);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rlvLabelLayout.setItemAnimator(new DefaultItemAnimator());
        rlvLabelLayout.setLayoutManager(layoutManager);
        rlvLabelLayout.getItemAnimator().setChangeDuration(0);
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TabNameBean tabNameBean = new TabNameBean();
            tabNameBean.setTabName("教育" + i);
            mList.add(tabNameBean);
        }

        promotionLabelAdapter = new PromotionLabelAdapter(this, mList);
        promotionLabelAdapter.setHasStableIds(true);
        rlvLabelLayout.setAdapter(promotionLabelAdapter);
    }

    public void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) != null) {
            tempCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String key) {
        Bitmap bitmap = tempCache.get(key);
        return tempCache.get(key);
    }
}
