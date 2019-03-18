package com.example.administrator.dangerouscabinetapp.item;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 16:56
 * Description:
 */
public class GoodsItem {
    private String name;
    private int mip;

    public GoodsItem(String name, int mip) {
        this.name = name;
        this.mip = mip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMip() {
        return mip;
    }

    public void setMip(int mip) {
        this.mip = mip;
    }
}

