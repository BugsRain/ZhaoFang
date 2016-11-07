package cn.ichengxi.fang.business.main.personal;

import android.os.Bundle;
import android.widget.LinearLayout;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class PersonalFragment extends BaseFrameFragment {

    private LinearLayout mTabContain;

    @Override
    public void onSetView(Bundle savedInstanceState) {
        setmContentView(R.layout.fragement_personal);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我的");
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
