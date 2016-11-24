package cn.ichengxi.fang.business.publish;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.HouseTagAdapter;
import cn.ichengxi.fang.entity.HouseTag;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.flow.TagFlowLayout;

/**
 * author：created by Snail.江
 * time: 11/21/2016 11:24
 * email：409962004@qq.com
 * TODO: 我要卖房
 */
public class PublishHouseActivity extends BaseFrameActivity {

    @Bind(R.id.flowLayout)
    TagFlowLayout mFlowLayout;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_house);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我要卖房");
        setFunctionView(getLeft(), null, R.mipmap.ico_back);

        String[] mVals = new String[]
                {"学位", "地铁口", "电梯", "满二", "免个税", "精装修",
                        "Android", "Weclome", "Button ImageView"};
        final String[] mColor = new String[]
                {"#FF6A51", "#B8857B", "#1A70D0", "#51CA76", "#B945E9", "#FF4040",
                        "#224040", "#CC226E", "#A68833"};
        List<HouseTag> mData = new ArrayList<>();
        for (int i = 0; i < mVals.length; i++) {
            mData.add(new HouseTag(mVals[i], mColor[i]));
        }
        HouseTagAdapter mAdapter = new HouseTagAdapter(this, mData);
        mFlowLayout.setAdapter(mAdapter);
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
