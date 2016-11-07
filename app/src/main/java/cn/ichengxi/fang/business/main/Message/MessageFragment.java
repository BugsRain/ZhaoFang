package cn.ichengxi.fang.business.main.Message;

import android.os.Bundle;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class MessageFragment extends BaseFrameFragment {
    @Override
    public void onSetView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_message);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("消息");
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
