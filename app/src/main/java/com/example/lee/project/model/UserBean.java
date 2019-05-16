package com.example.lee.project.model;

/**
 * Created by Lee on 2019/5/13.
 */

public class UserBean {


    /**
     * name : Lee
     * account : 13192295124
     * email : 825132005@qq.com
     * qq : 825132005
     * token :
     * address : ???????????????
     * status : 0
     * error : 登陆成功
     */

    public String name;
    public String account;
    public String email;
    public String qq;
    public String token;
    public String address;
    public String status;
    public String error;

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", token='" + token + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
