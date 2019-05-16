package com.example.lee.project.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lee.project.R;
import com.example.lee.project.implement.RegisterImplement;
import com.example.lee.project.listener.RegisterContact;
import com.example.lee.project.view.LoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterContact.RegisterView {

    @BindView(R.id.includeBack)
    TextView includeBack;
    @BindView(R.id.includeTitle)
    TextView includeTitle;
    @BindView(R.id.registerMobil)
    EditText registerMobil;
    @BindView(R.id.registerVerificationCode)
    EditText registerVerificationCode;
    @BindView(R.id.getVerificationCode)
    Button getVerificationCode;
    @BindView(R.id.registerPassword)
    EditText registerPassword;
    @BindView(R.id.registerConfirmPassword)
    EditText registerConfirmPassword;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.registerEmail)
    EditText registerEmail;
    @BindView(R.id.registerQQ)
    EditText registerQQ;
    @BindView(R.id.registerAddress)
    EditText registerAddress;

    private RegisterImplement registerImplement;
    public LoadingView loadingView;

    @Override
    public int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        includeTitle.setText("注册");
    }

    @Override
    public void initData() {
        if (registerImplement == null) {
            registerImplement = RegisterImplement.getInstance();
            registerImplement.setRegisterView(this);
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
        if (registerImplement != null) {
            registerImplement.release();
            registerImplement = null;
        }
    }

    @OnClick({R.id.includeBack, R.id.getVerificationCode, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.includeBack:
                this.finish();
                break;
            case R.id.getVerificationCode:
                break;
            case R.id.register:
                String mobile = registerMobil.getText().toString().trim();
                String pass = registerPassword.getText().toString().trim();
                String confirmPass = registerConfirmPassword.getText().toString().trim();
                String email = registerEmail.getText().toString().trim();
                String qq = registerQQ.getText().toString().trim();
                String address = registerAddress.getText().toString().trim();
                registerImplement.register(mobile, pass, confirmPass, email, qq, address);
                break;
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
}
