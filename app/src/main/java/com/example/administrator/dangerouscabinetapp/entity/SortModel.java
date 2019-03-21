package com.example.administrator.dangerouscabinetapp.entity;

import com.example.administrator.dangerouscabinetapp.utils.menu.Contact;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/21 0021 10:38
 * Description:
 */

public class SortModel extends Contact {

    public SortModel(String name, String number, String sortKey) {
        super(name, number, sortKey);
    }

    public String sortLetters; //显示数据拼音的首字母

    public SortToken sortToken=new SortToken();
}
