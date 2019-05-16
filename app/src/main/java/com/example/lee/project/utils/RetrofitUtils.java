package com.example.lee.project.utils;

import com.example.lee.project.listener.RequestListener;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lee on 2019/5/12.
 */

public class RetrofitUtils {

    private static final String TAG = "RetrofitUtils";

    public static final String BASE_URL = "http://192.168.3.97/"; //http://172.20.10.12/

    private volatile static RetrofitUtils retrofitUtils;

    private Retrofit retrofit;

    private RequestListener requestListener;

    public RequestListener getRequestListener() {
        return requestListener;
    }

    private RetrofitUtils() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        if (requestListener == null) {
            requestListener = retrofit.create(RequestListener.class);
        }
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void release() {
        if (retrofitUtils != null) {
            retrofitUtils = null;
        }
        if (retrofit != null){
            retrofit = null;
        }
        if (requestListener != null) {
            requestListener = null;
        }
    }

}
