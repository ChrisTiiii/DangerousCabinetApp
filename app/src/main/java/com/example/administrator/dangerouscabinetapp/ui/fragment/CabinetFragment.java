package com.example.administrator.dangerouscabinetapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.adpter.cabinet.CabinetManagerApdater;
import com.example.administrator.dangerouscabinetapp.item.GoodsItem;
import com.example.administrator.dangerouscabinetapp.ui.activity.CabinetActivity;
import com.example.administrator.dangerouscabinetapp.ui.activity.TempControlActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 16:26
 * Description:
 */
public class CabinetFragment extends Fragment {
    public volatile static CabinetFragment instance;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private View rootView;
    Unbinder unbinder;
    List<GoodsItem> list;
    private CabinetManagerApdater adapter;

    public synchronized static CabinetFragment getInstance() {
        if (instance == null) {
            synchronized (CabinetFragment.class) {
                instance = new CabinetFragment();
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
            rootView = inflater.inflate(R.layout.cabinet_fragment, null);
            unbinder = ButterKnife.bind(this, rootView);
            initData();
            initEvent();
        }
        return rootView;
    }


    private void initData() {
        list = new ArrayList<>();
        setData();
        adapter = new CabinetManagerApdater(getContext(), R.layout.cabinet_manager_item, list);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerview.setAdapter(adapter);

    }

    private void initEvent() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(getContext(), CabinetActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), TempControlActivity.class));
                        break;
                }
            }
        });
    }

    private void setData() {
        list.add(new GoodsItem("视频监控", R.drawable.img1));
        list.add(new GoodsItem("柜内查询", R.drawable.state));
        list.add(new GoodsItem("柜体状态", R.drawable.img2));
        list.add(new GoodsItem("远程开门", R.drawable.img3));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
