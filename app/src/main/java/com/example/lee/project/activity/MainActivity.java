package com.example.lee.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lee.project.R;
import com.example.lee.project.implement.MainImplement;
import com.example.lee.project.listener.MainContact;
import com.example.lee.project.utils.RetrofitUtils;
import com.example.lee.project.view.LoadingView;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.button.CountDownButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContact.MainView, View.OnClickListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.bt_countdown4)
    CountDownButton btCountdown4;

    private MainImplement mainImplement;
    public LoadingView loadingView;

    private CountDownButtonHelper countDownButtonHelper;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        countDownButtonHelper = new CountDownButtonHelper(btCountdown4,60);
    }

    @Override
    public void initData() {
        if (mainImplement == null) {
            mainImplement = MainImplement.getInstance();
            mainImplement.setMainView(this);
        }
    }

    @Override
    public void initBroadcast() {

    }

    @Override
    public void requestData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RetrofitUtils.getInstance().release();
        if (mainImplement != null) {
            mainImplement.release();
            mainImplement = null;
        }
    }

    @Override
    public void showLoad(String msg) {
        if (loadingView == null) {
            loadingView = new LoadingView(this, msg);
        }
        loadingView.show();
    }

    @Override
    public void tip(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoad() {
        if (loadingView != null) {
            loadingView.dismiss();
            loadingView.release();
            loadingView = null;
        }
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.bt_countdown4)
    public void onViewClicked() {

        try {
            Thread.sleep(2000);
            countDownButtonHelper.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
