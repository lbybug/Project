package com.example.lee.project.listener;

import android.view.View;

public interface RegisterContact {

    interface RegisterPresenter extends ImplementDefault {

        void register(String account, String password,String confirmPass, String email, String qq, String address);

    }

    interface RegisterView extends ViewDefault {

    }

}
