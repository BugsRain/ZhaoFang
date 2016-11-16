package cn.ichengxi.fang.business.feedback;

import android.os.Bundle;
import android.view.View;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/16/2016 11:15
 * email：409962004@qq.com
 * TODO: 意见反馈页面
 */
public class FeedbackActivity extends BaseFrameActivity {
    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("意见反馈");
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
