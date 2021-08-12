package com.happy.panda;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.happy.panda.activity.VerticalTabActivity;
import com.happy.panda.utils.CommonUtil;
import com.happy.panda.view.PieImageView;

public class FirstFragment extends Fragment {


    private TextView mShowTextView;
    private PieImageView pieImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        pieImageView = view.findViewById(R.id.pieImageView);
//        pieImageView.setProgress(90);
        mShowTextView = view.findViewById(R.id.tv_show);
        view.findViewById(R.id.bt_shape_dynamic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.setShapeBackground(getContext(), mShowTextView, "#ff0000", "#ffffff");
            }
        });

        view.findViewById(R.id.textview_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), VerticalTabActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
}