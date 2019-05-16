package com.example.lee.project.listener;

import android.support.annotation.NonNull;

import com.example.lee.project.model.RegisterBean;
import com.example.lee.project.model.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lee on 2019/5/13.
 */

public interface RequestListener {

    @POST("project/school/user/login.php")
    @FormUrlEncoded
    Observable<UserBean> login(@Field("account") @NonNull String account,
                               @Field("pass") @NonNull String pass);

    @POST("project/school/user/register.php")
    @FormUrlEncoded
    Observable<RegisterBean> register(@Field("account") @NonNull String account,
                                      @Field("pass") @NonNull String pass,
                                      @Field("email") @NonNull String email,
                                      @Field("qq") @NonNull String qq,
                                      @Field("address") @NonNull String address);

}
