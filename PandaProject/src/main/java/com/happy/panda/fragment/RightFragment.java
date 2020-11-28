package com.happy.panda.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.happy.panda.R;

public class RightFragment extends Fragment {

    private String name;
    private TextView tvFragmentName;

    public static RightFragment newInstance(String name) {
        RightFragment newFragment = new RightFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        newFragment.setArguments(bundle);
        return newFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            name = (String) args.getString("name");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_right_layout, null);
        tvFragmentName = inflate.findViewById(R.id.tv_fragment);
        tvFragmentName.setText(name);
        return inflate;
    }
}
