package cn.ichengxi.fang.business.house_detail;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * Created by quan on 16/11/7.
 */

public class HouseDetailActivity extends BaseFrameActivity {



    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_house_detail);
    }

    @Override
    public void initView() {
        super.initView();
        setFunctionView(getLeft(), null, R.mipmap.ico_back);
        setFunctionView(getRight(), null, R.mipmap.ico_share);
        ViewCompat.setAlpha(getActionBarBg(), 0);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
