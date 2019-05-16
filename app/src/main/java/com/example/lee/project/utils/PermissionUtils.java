package com.example.lee.project.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

public class PermissionUtils {

    public static boolean checkPermission(Fragment fragment, String permission) {  //检查是否拥有权限  fragment
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(fragment.getActivity(), permission) == PackageManager.PERMISSION_GRANTED) {
            flag = true;
        }
        return flag;
    }

    public static void requestPermission(Fragment fragment, String[] permission, int requestCode) { //申请权限  fragment
//        Fragment.requestPermissions(fragment.getActivity(), permission, requestCode);
        fragment.requestPermissions(permission,requestCode);
    }

    public static boolean checkPermission(Activity activity, String permission) {  //检查是否拥有权限  activity
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            flag = true;
        }
        return flag;
    }

    public static void requestPermission(Activity activity, String[] permission, int requestCode) { //申请权限  activity
        ActivityCompat.requestPermissions(activity, permission, requestCode);
    }

}
