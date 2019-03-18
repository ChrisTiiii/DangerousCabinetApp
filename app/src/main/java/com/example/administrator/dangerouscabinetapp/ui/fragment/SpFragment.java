package com.example.administrator.dangerouscabinetapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.dangerouscabinetapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 16:26
 * Description:
 */
public class SpFragment extends Fragment {
    public volatile static SpFragment instance;
    private View rootView;
    Unbinder unbinder;

    public synchronized static SpFragment getInstance() {
        if (instance == null) {
            synchronized (SpFragment.class) {
                instance = new SpFragment();
            }
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != rootView) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            unbinder = ButterKnife.bind(this, rootView);
            if (null != parent) {
                parent.removeView(rootView);
            }
        } else {
            rootView = inflater.inflate(R.layout.sp_fragment, null);
            unbinder = ButterKnife.bind(this, rootView);
        }
        return rootView;
    }
}
