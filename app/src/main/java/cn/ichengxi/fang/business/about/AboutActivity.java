package cn.ichengxi.fang.business.about;

import android.os.Bundle;
import android.view.View;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/16/2016 10:33
 * email：409962004@qq.com
 * TODO: 关于我们页面
 */
public class AboutActivity extends BaseFrameActivity {


    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("关于我们");
        setFunctionView(getLeft(),"",R.mipmap.ico_back);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.actionBar_left_txt:
                finish();
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
