package com.example.administrator.dangerouscabinetapp.ui.adpter.cabinet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.item.GoodsItem;

import java.util.List;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/19 0019 11:27
 * Description:
 */
public class CabinetManagerApdater extends BaseQuickAdapter<GoodsItem, BaseViewHolder> {
    private Context context;

    public CabinetManagerApdater(Context context, int layoutResId, @Nullable List<GoodsItem> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsItem item) {
        helper.setText(R.id.tv_cabinet, item.getName());
        Glide.with(context).load(item.getMip()).into((ImageView) helper.getView(R.id.img_cabinet));

    }
}
