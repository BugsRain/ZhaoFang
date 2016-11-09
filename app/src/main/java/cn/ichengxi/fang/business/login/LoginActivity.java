package cn.ichengxi.fang.business.login;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import chengxinet.chengxilibs.widget.MySimpleDraweeView;
import cn.ichengxi.fang.MyApplication;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * Created by quan on 16/11/4.
 */

public class LoginActivity extends BaseFrameActivity<LoginPresenter, LoginModel> {

    @Bind(R.id.login_background)
    MySimpleDraweeView mBackGroundView;

    @Bind(R.id.login_back)
    ImageView mBackImg;

    @Bind(R.id.login_phone_edt)
    EditText mPhoneEdt;

    @Bind(R.id.login_code_edt)
    EditText mCodeEdt;

    @Bind(R.id.login_code_txt)
    TextView mCodeTxt;

    @Bind(R.id.login_agreement)
    TextView mAgreementTxt;

    @Bind(R.id.login_btn)
    Button mLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (MyApplication.isMoreKitkat()) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        super.initView();
        Uri bg = Uri.parse("res:///" + R.mipmap.home_bg);
        mBackGroundView.setImageURI(bg);
        mBackImg.setOnClickListener(this);
        mCodeTxt.setOnClickListener(this);
        mAgreementTxt.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;

            case R.id.login_code_txt:
                showShortToast("获取验证码");
                break;

            case R.id.login_btn:
                break;

            case R.id.login_agreement:
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View v = getCurrentFocus();
            hideKeyboard(v);
            if (!isShouldHideKeyboard(mPhoneEdt, ev) || !isShouldHideKeyboard(mCodeEdt, ev)) {
                mPhoneEdt.clearFocus();
                mCodeEdt.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
}
