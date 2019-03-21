package com.example.administrator.dangerouscabinetapp.item;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/20 0020 17:17
 * Description:
 */
public class SqViewType implements MultiItemEntity {
    //申领
    public final static int SL_TYPE_HEAD = 0;//用途
    public final static int SL_TYPE_DETAIL = 1;//明细
    public final static int SL_TYPE_ADD = 2;//添加
    public final static int SL_TYPE_EXPLAIN = 3;//详情
    public final static int SL_TYPE_LEADER = 4;//审批人

    private List<GoodsItem> list;
    private int position;//记录当前位置


    private int type;//Item类型类别

    public SqViewType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public List<GoodsItem> getList() {
        return list;
    }

    public void setList(List<GoodsItem> list) {
        this.list = list;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void removeListData(int position) {
        list.remove(position);
    }
}
