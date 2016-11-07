package cn.ichengxi.fang.business.main.home;

import android.os.Bundle;
import android.view.View;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.house_detail.HouseDetailActivity;
import cn.ichengxi.fang.business.list.ListHouseActivity;
import cn.ichengxi.fang.business.location.LocationActivity;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class HomeFragment extends BaseFrameFragment {

    @Override
    public void onSetView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
    }

    @Override
    public void initView() {
        super.initView();
        findViewByIdToView(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(HouseDetailActivity.class);
            }
        });
        findViewByIdToView(R.id.home_search).setOnClickListener(this);
        findViewByIdToView(R.id.home_location).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.home_search:
                openActivity(ListHouseActivity.class);
                break;
            case R.id.home_location:
                openActivity(LocationActivity.class);
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
