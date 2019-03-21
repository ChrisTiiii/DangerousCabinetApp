package com.example.administrator.dangerouscabinetapp.utils.menu;


import com.example.administrator.dangerouscabinetapp.entity.SortModel;

import java.util.Comparator;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/21 0021 10:38
 * Description:
 */
public class PinyinComparator implements Comparator<SortModel> {

    public int compare(SortModel o1, SortModel o2) {
        if (o1.sortLetters.equals("@") || o2.sortLetters.equals("#")) {
            return -1;
        } else if (o1.sortLetters.equals("#") || o2.sortLetters.equals("@")) {
            return 1;
        } else {
            return o1.sortLetters.compareTo(o2.sortLetters);
        }
    }

}

