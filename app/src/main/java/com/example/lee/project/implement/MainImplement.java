package com.example.lee.project.implement;

import android.text.TextUtils;

import com.example.lee.project.listener.MainContact;
import com.example.lee.project.listener.RequestListener;
import com.example.lee.project.model.UserBean;
import com.example.lee.project.utils.LoggerUtils;
import com.example.lee.project.utils.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lee on 2019/5/12.
 */

public class MainImplement implements MainContact.MainPresenter {

    private static final String TAG = "MainImplement";

    private volatile static MainImplement mainImplement;

    private RequestListener listener;

    private MainContact.MainView mainView;

    public void setMainView(MainContact.MainView mainView) {
        this.mainView = mainView;
    }

    private MainImplement() {
        if (listener == null) {
            listener = RetrofitUtils.getInstance().getRequestListener();
        }
    }

    public static MainImplement getInstance() {
        if (mainImplement == null) {
            mainImplement = new MainImplement();
            synchronized (MainImplement.class) {
                if (mainImplement == null) {
                    mainImplement = new MainImplement();
                }
            }
        }
        return mainImplement;
    }

    @Override
    public void release() {
        if (mainImplement != null) {
            mainImplement = null;
        }
    }

    @Override
    public void login(String account, String pass) {
        if (TextUtils.isEmpty(account)) {
            mainView.tip("请输入账号");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            mainView.tip("请输入密码");
            return;
        }
        mainView.showLoad("登陆中");
        listener.login(account, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(UserBean value) {
                        LoggerUtils.d(TAG, "onNext: " + value.toString());
                        if (value.status.equals("0")) {
                            mainView.tip(value.error);
                        } else {
                            mainView.tip(value.error);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.tip(e.getMessage());
                        mainView.dismissLoad();
                    }

                    @Override
                    public void onComplete() {
                        mainView.dismissLoad();
                    }
                });

    }
}
