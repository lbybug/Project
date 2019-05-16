package com.example.lee.project.listener;

/**
 * Created by Lee on 2019/5/15.
 */

public interface MainContact {

    interface MainPresenter extends ImplementDefault{

        void login(String account,String pass);

    }

    interface MainView extends ViewDefault{

    }

}
