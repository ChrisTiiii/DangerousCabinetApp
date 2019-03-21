package com.example.administrator.dangerouscabinetapp.item;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 16:56
 * Description:
 */
public class GoodsItem {
    private String name;
    private int mip;
    private String num;
    private String id;
    private String des;//描述

    public GoodsItem(String name, String id, String num, String des) {
        this.name = name;
        this.num = num;
        this.id = id;
        this.des = des;
    }

    public GoodsItem(String name, int mip, String num) {
        this.name = name;
        this.mip = mip;
        this.num = num;
    }

    public GoodsItem(String name, int mip) {
        this.name = name;
        this.mip = mip;
    }

    public GoodsItem(String id, String name, String num) {
        this.name = name;
        this.num = num;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

