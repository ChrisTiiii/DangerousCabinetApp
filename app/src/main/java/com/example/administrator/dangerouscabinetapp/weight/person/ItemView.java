package com.example.administrator.dangerouscabinetapp.weight.person;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.dangerouscabinetapp.R;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/19 0019 10:18
 * Description:
 */
public class ItemView extends LinearLayout {
    private ImageView imageView;//item的图标
    private TextView textView;//item的文字
    private ImageView bottomview;
    private boolean isbootom = true;//是否显示底部的下划线

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.person_item, this);
       /* LayoutInflater mInflater = LayoutInflater.from(context);
        View myView = mInflater.inflate(R.layout.person_item, null);
        addView(myView);*/
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.item_view);
        isbootom = ta.getBoolean(R.styleable.item_view_show_bottomline, true);
        bottomview = findViewById(R.id.item_bottom);
        imageView = findViewById(R.id.item_img);
        textView = findViewById(R.id.item_text);

        textView.setText(ta.getString(R.styleable.item_view_show_text));
        imageView.setBackgroundResource(ta.getResourceId(R.styleable.item_view_show_leftimg, R.drawable.setting));

        ta.recycle();
        initview();
    }

    private void initview() {
        if (isbootom) {
            bottomview.setVisibility(View.VISIBLE);
        } else {
            bottomview.setVisibility(View.GONE);
        }
    }


}

