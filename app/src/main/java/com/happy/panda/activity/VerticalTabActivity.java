package com.happy.panda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.happy.panda.R;
import com.happy.panda.fragment.RightFragment;
import com.happy.panda.bean.TabNameBean;
import com.happy.panda.adapter.VerticalTabAdapter;
import com.happy.panda.layoutmanager.CustomLinearLayoutManager;
import com.happy.panda.view.CustomToolBar;

import java.util.ArrayList;
import java.util.List;


public class VerticalTabActivity extends FragmentActivity {

    private CustomLinearLayoutManager layoutManager;
    private RecyclerView rlvVerticalView;
    private List<TabNameBean> mList;
    private VerticalTabAdapter verticalTabAdapter;
    private CustomToolBar customToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_tab_layout);
        initView();
        initData();
        initFragment();
        initListener();
    }

    private void initListener() {
        customToolBar.setImgClickListener(new CustomToolBar.ImgClickListener() {
            @Override
            public void leftImgClick() {
                Toast.makeText(getApplicationContext(), "左侧点击", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightImgClick() {
                Intent intent = new Intent();
                intent.setClass(VerticalTabActivity.this, CornerActivity.class);
                startActivity(intent);
            }
        });

        verticalTabAdapter.setOnItemClickListener(new VerticalTabAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                layoutManager.smoothScrollToPosition(rlvVerticalView, new RecyclerView.State(), position);
                showFragment(position);
            }
        });
    }

    private void initFragment() {
        verticalTabAdapter = new VerticalTabAdapter(this, mList);
        verticalTabAdapter.setHasStableIds(true);
        rlvVerticalView.setAdapter(verticalTabAdapter);
        showFragment(0);
    }

    private void initView() {
        customToolBar = findViewById(R.id.customToolBar);
        rlvVerticalView = findViewById(R.id.rlv_vertical_layout);
        layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rlvVerticalView.setItemAnimator(new DefaultItemAnimator());
        rlvVerticalView.setLayoutManager(layoutManager);
        rlvVerticalView.getItemAnimator().setChangeDuration(0);
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TabNameBean tabNameBean = new TabNameBean();
            tabNameBean.setmTabName("教育" + i);
            mList.add(tabNameBean);
        }
    }

    private void showFragment(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RightFragment rightFragment = RightFragment.newInstance("我是Fragment" + position);
        fragmentTransaction.replace(R.id.ll_container, rightFragment, null).commitAllowingStateLoss();
        fragmentTransaction.show(rightFragment);
    }
}
