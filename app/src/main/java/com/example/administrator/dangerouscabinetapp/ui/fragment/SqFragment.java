package com.example.administrator.dangerouscabinetapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dangerouscabinetapp.MyApp;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.ui.adpter.apply.ApplyAdapter;
import com.example.administrator.dangerouscabinetapp.item.GoodsItem;
import com.example.administrator.dangerouscabinetapp.item.SqViewType;
import com.example.administrator.dangerouscabinetapp.ui.activity.MenuActivity;
import com.example.administrator.dangerouscabinetapp.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/20 0020 17:13
 * Description:申请页面
 */
public class SqFragment extends Fragment {
    public volatile static SqFragment instance;
    @BindView(R.id.shenpi)
    RecyclerView recyclerView;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    private View rootView;
    Unbinder unbinder;
    private List<SqViewType> uiList;
    private ApplyAdapter adapter;
    private static int _position = -1;
    private List<GoodsItem> dataList;

    public synchronized static SqFragment getInstance() {
        if (instance == null) {
            synchronized (SqFragment.class) {
                instance = new SqFragment();
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
            rootView = inflater.inflate(R.layout.cg_fragment, null);
            unbinder = ButterKnife.bind(this, rootView);
            if (!EventBus.getDefault().isRegistered(this))
                EventBus.getDefault().register(this);
            initData();
            initView();
            initEvent();
        }
        return rootView;
    }

    private void initEvent() {
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_add:
                        startActivity(new Intent(getContext(), MenuActivity.class));
                        _position = position;
                        break;
                    case R.id.btn_del:
                        if (position > 0) {
                            Toast.makeText(getContext(), "删除" + position, Toast.LENGTH_SHORT).show();
                            adapter.remove(position);
                            dataList.remove(position - 1);
                        } else dataList.clear();
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    private void initData() {
        uiList = new ArrayList<>();
        dataList = new ArrayList<>();
        initList();
    }

    private void initList() {
        uiList.add(new SqViewType(SqViewType.SL_TYPE_HEAD));
        uiList.add(new SqViewType(SqViewType.SL_TYPE_ADD));
        uiList.add(new SqViewType(SqViewType.SL_TYPE_EXPLAIN));
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ApplyAdapter(uiList);
        recyclerView.setAdapter(adapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(MessageEvent messageEvent) {
        switch (messageEvent.getTAG()) {
            case MyApp.CHOOSE_GOODS_NUMBER:
                if (messageEvent.getGoodsItem() != null) {
                    dataList.add(messageEvent.getGoodsItem());
                    SqViewType myType = new SqViewType(SqViewType.SL_TYPE_DETAIL);
                    myType.setPosition(_position);
                    myType.setList(dataList);
                    uiList.add(_position, myType);
                    adapter.notifyDataSetChanged();
                }
                _position = -1;
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {

    }
}
