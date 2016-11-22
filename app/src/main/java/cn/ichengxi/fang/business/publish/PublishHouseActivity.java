package cn.ichengxi.fang.business.publish;

import android.os.Bundle;
import android.view.View;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/21/2016 11:24
 * email：409962004@qq.com
 * TODO: 我要卖房
 */
public class PublishHouseActivity extends BaseFrameActivity {

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_house);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我要卖房");
        setFunctionView(getLeft(),null,R.mipmap.ico_back);
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
