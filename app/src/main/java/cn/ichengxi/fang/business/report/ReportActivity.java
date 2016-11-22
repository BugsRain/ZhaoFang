package cn.ichengxi.fang.business.report;

import android.os.Bundle;
import android.view.View;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/22/2016 16:56
 * email：409962004@qq.com
 * TODO:
 */
public class ReportActivity extends BaseFrameActivity {

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_report);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("举报房源");
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
