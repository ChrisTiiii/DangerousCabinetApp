package com.example.administrator.dangerouscabinetapp.item;

import java.io.Serializable;
import java.util.Map;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/21 0021 09:50
 * Description:
 */
public class MenuListBean implements Serializable {

    private Map<String, Object> map;
    private boolean isSelect;

    public MenuListBean(Map<String, Object> map, boolean isSelect) {
        this.map = map;
        this.isSelect = isSelect;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }


    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}