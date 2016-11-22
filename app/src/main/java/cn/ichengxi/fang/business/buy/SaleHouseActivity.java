package cn.ichengxi.fang.business.buy;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.publish.PublishHouseActivity;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/18/2016 17:18
 * email：409962004@qq.com
 * TODO: 我要卖房
 */
public class SaleHouseActivity extends BaseFrameActivity {

    @Bind(R.id.house_publish_layout)
    RelativeLayout mPublishLayout;

    @Bind(R.id.house_entrust_layout)
    RelativeLayout mEntrustLayout;

    @Bind(R.id.house_layout)
    LinearLayout mLayout;

    @Bind(R.id.house_call_txt)
    TextView mCallTxt;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_buyhouse);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我要卖房");
        setFunctionView(getLeft(),null,R.mipmap.ico_back);

        mPublishLayout.setOnClickListener(this);
        mEntrustLayout.setOnClickListener(this);
        mLayout.setOnClickListener(this);
        mCallTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.actionBar_left_txt:
                finish();
                break;

            case R.id.house_publish_layout:
                openActivity(PublishHouseActivity.class);
                break;

            case R.id.house_entrust_layout:
                break;

            case R.id.house_layout:
                break;

            case R.id.house_call_txt:
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
