package com.example.lee.project.implement;

import android.text.TextUtils;

import com.example.lee.project.listener.RegisterContact;
import com.example.lee.project.listener.RequestListener;
import com.example.lee.project.model.RegisterBean;
import com.example.lee.project.utils.DefaultUtils;
import com.example.lee.project.utils.MD5Utils;
import com.example.lee.project.utils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterImplement implements RegisterContact.RegisterPresenter {

    private volatile static RegisterImplement registerImplement;

    private RegisterContact.RegisterView registerView;
    private RequestListener listener;

    public void setRegisterView(RegisterContact.RegisterView registerView) {
        this.registerView = registerView;
    }

    public static RegisterImplement getInstance() {
        if (registerImplement == null) {
            registerImplement = new RegisterImplement();
            synchronized (RegisterImplement.class) {
                if (registerImplement == null) {
                    registerImplement = new RegisterImplement();
                }
            }
        }
        return registerImplement;
    }

    private RegisterImplement() {
        if (listener == null) {
            listener = RetrofitUtils.getInstance().getRequestListener();
        }
    }


    @Override
    public void register(String account, String password, String confirmPass, String email, String qq, String address) {
        if (TextUtils.isEmpty(account)
                || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(confirmPass)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(qq)
                || TextUtils.isEmpty(address)) {
            registerView.tip("请输入内容");
            return;
        }
        if (!password.equals(confirmPass)) {
            registerView.tip("两次密码不符");
            return;
        }
        if (!DefaultUtils.checkPhone(account)) {
            registerView.tip("请输入正确的手机号");
            return;
        }
        if (!DefaultUtils.checkEmail(email)) {
            registerView.tip("请输入正确的邮箱");
            return;
        }
        registerView.showLoad("正在注册");
        listener.register(account, password, email, qq, address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean value) {
                        registerView.tip(value.error);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerView.tip(e.getMessage());
                        registerView.dismissLoad();
                    }

                    @Override
                    public void onComplete() {
                        registerView.dismissLoad();
                    }
                });
    }

    @Override
    public void release() {

    }
}
