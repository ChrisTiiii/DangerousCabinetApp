package com.example.administrator.dangerouscabinetapp.utils;

import com.example.administrator.dangerouscabinetapp.item.GoodsItem;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/21 0021 14:42
 * Description:
 */
public class MessageEvent {
    private int TAG;
    private String msg;
    private GoodsItem goodsItem;

    public GoodsItem getGoodsItem() {
        return goodsItem;
    }

    public void setGoodsItem(GoodsItem goodsItem) {
        this.goodsItem = goodsItem;
    }

    public MessageEvent(int TAG) {
        this.TAG = TAG;
    }

    public int getTAG() {
        return TAG;
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
