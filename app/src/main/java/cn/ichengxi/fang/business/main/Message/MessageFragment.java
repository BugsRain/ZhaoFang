package cn.ichengxi.fang.business.main.Message;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.Bind;
import cn.ichengxi.fang.MyApplication;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.MessageAdapter;
import cn.ichengxi.fang.adapter.decoration.ItemLine2;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class MessageFragment extends BaseFrameFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MessageAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

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

        mAdapter = new MessageAdapter(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.addItemDecoration(new ItemLine2(getActivity(), R.drawable.item_line_location));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
