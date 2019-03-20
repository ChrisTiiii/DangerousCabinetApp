package com.example.administrator.dangerouscabinetapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.weight.thermometer.HumidityView;
import com.example.administrator.dangerouscabinetapp.weight.thermometer.ThermometerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/20 0020 09:47
 * Description:
 */
public class TempControlActivity extends AppCompatActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_temp_random)
    Button btnTempRandom;
    @BindView(R.id.btn_temp_control)
    Button btnTempControl;
    @BindView(R.id.et_temp_control)
    EditText etTempControl;
    @BindView(R.id.tv_temp)
    ThermometerView tvTemp;
    @BindView(R.id.btn_humidity_random)
    Button btnHumidityRandom;
    @BindView(R.id.btn_humidity_control)
    Button btnHumidityControl;
    @BindView(R.id.et_humidity_control)
    EditText etHumidityControl;
    @BindView(R.id.tv_humidity)
    HumidityView tvHumidity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tempcontrol_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.btn_temp_random, R.id.btn_temp_control, R.id.btn_humidity_random, R.id.btn_humidity_control})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_temp_random:
                tvTemp.setValueAndStartAnim(getRandomValue());
                break;
            case R.id.btn_temp_control:
                Log.d("HUM", String.valueOf(etTempControl.getText()));
                if (!etTempControl.getText().equals("") && etTempControl.getText() != null)
                    tvTemp.setValueAndStartAnim(Float.valueOf(etTempControl.getText().toString()));
                break;
            case R.id.btn_humidity_random:
                tvHumidity.setValueAndStartAnim(getRandomValue());
                break;
            case R.id.btn_humidity_control:
                Log.d("HUM", String.valueOf(etHumidityControl.getText()));
                if (!etHumidityControl.getText().equals("") && etHumidityControl.getText() != null)
                    tvHumidity.setValueAndStartAnim(Float.valueOf(etHumidityControl.getText().toString()));
                break;
        }
    }

    private float getRandomValue() {
        float value = (int) (40 + Math.random() * (60 - 40 + 1));
        Log.i("tempControl", "current value: " + value);
        return value;
    }
}
