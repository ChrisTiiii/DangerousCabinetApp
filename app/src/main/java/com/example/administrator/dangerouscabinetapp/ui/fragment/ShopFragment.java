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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.adpter.shop.GoodsAdapter;
import com.example.administrator.dangerouscabinetapp.item.GoodsItem;
import com.example.administrator.dangerouscabinetapp.ui.activity.ShopDetailActivity;
import com.example.administrator.dangerouscabinetapp.weight.search.MaterialSearchView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 16:21
 * Description:
 */
public class ShopFragment extends Fragment {
    public volatile static ShopFragment instance;
    private View rootView;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.show_all)
    TextView showAll;
    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;
    GoodsAdapter adapter;
    List<String> listName;
    List<GoodsItem> listData;
    Unbinder unbinder;

    public synchronized static ShopFragment getInstance() {
        if (instance == null) {
            synchronized (ShopFragment.class) {
                instance = new ShopFragment();
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
            rootView = inflater.inflate(R.layout.shop_fragment, null);
            unbinder = ButterKnife.bind(this, rootView);
            initData();
            initEvent();
        }
        return rootView;
    }


    /**
     * 初始化数据
     */
    private void initData() {
        listData = new ArrayList<>();
        listName = new ArrayList<>();
        setData();
        adapter = new GoodsAdapter(getContext(), R.layout.goods_item, listData);
        recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerview.setAdapter(adapter);
        searchView.setVoiceSearch(false); //or true,是否支持声音的
        searchView.setSubmitOnClick(true);  //设置为true后，单击ListView的条目，searchView隐藏。实现数据的搜索
        searchView.setEllipsize(true);   //搜索框的ListView中的Item条目是否是单显示
        //设置空布局
        adapter.bindToRecyclerView(recyclerview);
        adapter.setEmptyView(R.layout.empty_layout);
        for (GoodsItem goods : listData) {
            listName.add(goods.getName());
        }
        String[] array = listName.toArray(new String[listName.size()]);
        searchView.setSuggestions(array);
    }


    @OnClick({R.id.img_search, R.id.show_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_search:
                if (searchView.isSearchOpen()) {
                    searchView.closeSearch();//隐藏搜索框
                } else {
                    searchView.showSearch(true);//显示搜索框
                }
                break;
            case R.id.show_all:
                listData.clear();
//                setData();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 设置回调事件
     */
    private void initEvent() {
        adapter.openLoadAnimation();//打开加载动画
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getContext(), ShopDetailActivity.class));
                //                Toast.makeText(getContext(), "点击的是：" + listData.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        //数据的监听（在自定义类中已经做了些处理）
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            //数据提交时
            //1.点击ListView的Item条目会回调这个方法
            //2.点击系统键盘的搜索/回车后回调这个方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getActivity(), "你要搜索的是：" + query, Toast.LENGTH_SHORT).show();
                listData.clear();
                for (String temp : listName) {
                    if (temp.equals(query)) {
                        listData.add(new GoodsItem(temp, R.drawable.timg, "1902个"));
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }

            //文本内容发生改变时
            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                return false;
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                listData.clear();
                setData();
                adapter.notifyDataSetChanged();
                mRefreshLayout.finishRefresh();
            }
        });

        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                for (int i = 0; i < 10; i++) {
                    GoodsItem goodsItem = new GoodsItem("化学剂" + i, R.drawable.timg, i * 15 + "个");
                    listData.add(goodsItem);
                }
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });


    }


    /**
     * 设置假数据
     */
    public void setData() {
        listData.add(new GoodsItem("环己烷", R.drawable.timg, 67 + ""));
        listData.add(new GoodsItem("甲酸", R.drawable.timg, 97 + ""));
        listData.add(new GoodsItem("碳酸钙", R.drawable.timg, 345 + ""));
        listData.add(new GoodsItem("六水合硝酸钴", R.drawable.timg, 66 + ""));
        listData.add(new GoodsItem("硫酸锰", R.drawable.timg, 86 + ""));
        listData.add(new GoodsItem("氢氧化钠", R.drawable.timg, 89 + ""));
        listData.add(new GoodsItem("氟化钠", R.drawable.timg, 8443 + ""));
        listData.add(new GoodsItem("碳酸氢钠", R.drawable.timg, 293 + ""));
        listData.add(new GoodsItem("硫酸镁", R.drawable.timg, 1 + ""));
        listData.add(new GoodsItem("聚氯乙烯", R.drawable.timg, 39 + ""));
    }

}
