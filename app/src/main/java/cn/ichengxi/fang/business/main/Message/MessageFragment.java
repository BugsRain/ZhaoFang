package cn.ichengxi.fang.business.main.Message;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.ichengxi.fang.MyApplication;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class MessageFragment extends BaseFrameFragment {


    @Override
    public void onSetView(Bundle savedInstanceState) {

        if (MyApplication.isMoreKitkat()) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.fragment_message, null);

            View statusBg = new View(getContext());
            statusBg.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyApplication.getStatusBarHeight()));
            statusBg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green1));
            viewGroup.addView(statusBg, 0);
            setContentView(viewGroup);
        }else {

            setContentView(R.layout.fragment_message);
        }
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("消息");
        mContainer = findViewByIdToView(R.id.message_contain);

    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
