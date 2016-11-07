package cn.ichengxi.fang.business.house_detail;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.BaseRecyclerView;

/**
 * Created by quan on 16/11/7.
 */

public class HouseDetailActivity extends BaseFrameActivity {

    private HouseAdapter mAdapter;
    private BaseRecyclerView mContentView;

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

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        mAdapter = new HouseAdapter(this, new Object(), list);

        mContentView = findViewByIdToView(R.id.house_content);

        mContentView.setLayoutManager(new LinearLayoutManager(this));

        mContentView.setAdapter(mAdapter);

    }

    @Override
    public void initListener() {
        super.initListener();
        mContentView.setCallBack(new BaseRecyclerView.OnScrollYChangeCallBack() {
            @Override
            public void onScrollYChange(int d) {

            }
        });
//        mContentView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Log.d("TAG", "onScrolled() called with: recyclerView = [" + recyclerView + "], dx = [" + dx + "], dy = [" + dy + "]");
//            }
//        });
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }
}
