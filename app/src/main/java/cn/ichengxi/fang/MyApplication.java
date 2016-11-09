package cn.ichengxi.fang;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import chengxinet.chengxilibs.global.ChengXiApplication;

/**
 * Created by quan on 16/8/25.
 */

public class MyApplication extends ChengXiApplication {

    private static MyApplication mMyApplication;

    private static int mStatusBarHeight;

    @Override
    public void init() {
        mMyApplication = this;;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        mStatusBarHeight = getResources().getDimensionPixelSize(resourceId);
    }

    @Override
    public String getCacheDirPath() {
        return null;
    }

    @Override
    public String getApiUrl() {
        return null;
    }

    @Override
    public Object getAipImpl() {
        return null;
    }

    @Override
    public Class<? extends ProgressDialog> getProgressDialogClass() {
        return ProgressDialog.class;
    }


    @Override
    public int getActionBarBgColor() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }

    public static Context getAppContext() {
        return mMyApplication;
    }


    public static int getStatusBarHeight() {

        return mStatusBarHeight;
    }

    public static boolean isMoreKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
