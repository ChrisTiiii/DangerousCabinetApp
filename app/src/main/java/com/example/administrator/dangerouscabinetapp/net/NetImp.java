package com.example.administrator.dangerouscabinetapp.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/18 0018 16:40
 * Description:
 */
public class NetImp {
    private Retrofit retrofit;
    private NetAPI netAPI;
    private static final String BASE_URL = "https://slyj.slicity.com";
    private OkHttpClient client;
    private String TAG = "NET WORK:";
    private volatile static NetImp instance;

    public static synchronized NetImp getInstance() {
        if (instance == null) {
            synchronized (NetImp.class) {
                instance = new NetImp();
            }
        }
        return instance;
    }

    /**
     * 初始化retrofit netApi
     *
     * @param
     */
    public NetImp() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        client = httpBuilder.readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS) //设置超时
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .client(client)
                .build();
        netAPI = retrofit.create(NetAPI.class);
    }

}
