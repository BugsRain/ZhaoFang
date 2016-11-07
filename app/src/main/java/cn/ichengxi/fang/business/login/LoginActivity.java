package cn.ichengxi.fang.business.login;

import android.os.Bundle;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * Created by quan on 16/11/4.
 */

public class LoginActivity extends BaseFrameActivity<LoginPresenter, LoginModel> {

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
