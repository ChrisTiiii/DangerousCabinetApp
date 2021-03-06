package com.example.administrator.dangerouscabinetapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.ui.adpter.cabinet.CabinetGoodsAdapter;
import com.example.administrator.dangerouscabinetapp.item.GoodsItem;
import com.example.administrator.dangerouscabinetapp.utils.DialogUtil;
import com.example.administrator.dangerouscabinetapp.weight.search.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/19 0019 11:56
 * Description:
 */
public class CabinetActivity extends AppCompatActivity {
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.show_all)
    TextView showAll;
    CabinetGoodsAdapter adapter;
    List<String> listName;
    List<GoodsItem> listData;
    @BindView(R.id.img_back)
    ImageView imgBack;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cabinet_activity);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }



    /**
     * 初始化数据
     */
    private void initData() {
        listData = new ArrayList<>();
        listName = new ArrayList<>();
        setData();
        adapter = new CabinetGoodsAdapter(CabinetActivity.this, R.layout.cabinet_manager_item, listData);
        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerview.setAdapter(adapter);
        searchView.setVoiceSearch(false); //or true,是否支持声音的
        searchView.setSubmitOnClick(true);  //设置为true后，单击ListView的条目，searchView隐藏。实现数据的搜索
        searchView.setEllipsize(true);   //搜索框的ListView中的Item条目是否是单显示
        for (GoodsItem goods : listData) {
            listName.add(goods.getName());
        }
        String[] array = listName.toArray(new String[listName.size()]);
        searchView.setSuggestions(array);
    }


    /**
     * 设置回调事件
     */
    private void initEvent() {
        adapter.openLoadAnimation();//打开加载动画
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DialogUtil.getInstance().setGoodsItem(new GoodsItem(listData.get(position).getName(), "SDSADHASJ", "856", "这是化学品"));
                DialogUtil.getInstance().showDialog(CabinetActivity.this, 1);
            }
        });
        //数据的监听（在自定义类中已经做了些处理）
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            //数据提交时
            //1.点击ListView的Item条目会回调这个方法
            //2.点击系统键盘的搜索/回车后回调这个方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(CabinetActivity.this, "你要搜索的是：" + query, Toast.LENGTH_SHORT).show();
                listData.clear();
                for (String temp : listName) {
                    if (temp.equals(query)) {
                        listData.add(new GoodsItem(temp, R.drawable.timg));
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

    }

    /**
     * 监听回退事件
     */
    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
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
                setData();
                adapter.notifyDataSetChanged();
                break;
        }
    }

    /**
     * 设置假数据
     */
    public void setData() {
        listData.add(new GoodsItem("环己烷", R.drawable.timg));
        listData.add(new GoodsItem("甲酸", R.drawable.timg));
        listData.add(new GoodsItem("碳酸钙", R.drawable.timg));
        listData.add(new GoodsItem("六水合硝酸钴(硝酸钴)", R.drawable.timg));
        listData.add(new GoodsItem("硫酸锰", R.drawable.timg));
        listData.add(new GoodsItem("氢氧化钠", R.drawable.timg));
        listData.add(new GoodsItem("氟化钠", R.drawable.timg));
        listData.add(new GoodsItem("碳酸氢钠", R.drawable.timg));
        listData.add(new GoodsItem("硫酸镁", R.drawable.timg));
        listData.add(new GoodsItem("聚氯乙烯", R.drawable.timg));
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
