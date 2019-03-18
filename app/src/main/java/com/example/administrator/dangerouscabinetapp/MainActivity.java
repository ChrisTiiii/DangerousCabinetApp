package com.example.administrator.dangerouscabinetapp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.administrator.dangerouscabinetapp.ui.fragment.CabinetFragment;
import com.example.administrator.dangerouscabinetapp.ui.fragment.PersonFragment;
import com.example.administrator.dangerouscabinetapp.ui.fragment.ShopFragment;
import com.example.administrator.dangerouscabinetapp.ui.fragment.SpFragment;
import com.example.administrator.dangerouscabinetapp.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    List<Fragment> listFragment;
    private int lastfragment;//用于记录上个选择的Fragmenet
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        initView();
        initNavigation();

    }

    private void initView() {
        listFragment = new ArrayList<>();
        listFragment.add(ShopFragment.getInstance());
        listFragment.add(SpFragment.getInstance());
        listFragment.add(CabinetFragment.getInstance());
        listFragment.add(PersonFragment.getInstance());

    }

    private void initNavigation() {
        lastfragment = 0;
        switchFragment(lastfragment, 0);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.shop:
                        if (lastfragment != 0) {
                            switchFragment(lastfragment, 0);
                            lastfragment = 0;
                        }
                        return true;
                    case R.id.sp:
                        if (lastfragment != 1) {
                            switchFragment(lastfragment, 1);
                            lastfragment = 1;
                        }
                        return true;
                    case R.id.cabinet:
                        if (lastfragment != 2) {
                            switchFragment(lastfragment, 2);
                            lastfragment = 2;
                        }
                        return true;
                    case R.id.person:
                        if (lastfragment != 3) {
                            switchFragment(lastfragment, 3);
                            lastfragment = 3;
                        }
                        return true;
                }
                return false;
            }
        });
    }

    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(listFragment.get(lastfragment));
        if (listFragment.get(index).isAdded() == false)
            transaction.add(R.id.content, listFragment.get(index));
        transaction.show(listFragment.get(index)).commitAllowingStateLoss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
