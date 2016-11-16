package cn.ichengxi.fang.business.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/16/2016 11:23
 * email：409962004@qq.com
 * TODO: 设置页面
 */
public class SettingActivity extends BaseFrameActivity {

    @Bind(R.id.setting_message_layout)
    LinearLayout mMessageLayout;

    @Bind(R.id.setting_cache_layout)
    LinearLayout mCacheLayout;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("设置");
        setFunctionView(getLeft(), "", R.mipmap.ico_back);

        mMessageLayout.setOnClickListener(this);
        mCacheLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.actionBar_left_txt:
                finish();
                break;

            case R.id.setting_message_layout:
                break;

            case R.id.setting_cache_layout:
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
