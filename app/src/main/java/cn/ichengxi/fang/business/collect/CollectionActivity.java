package cn.ichengxi.fang.business.collect;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.CollectionAdapter;
import cn.ichengxi.fang.adapter.decoration.ItemLine;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.MySwipeRefreshLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * author：created by Snail.江
 * time: 11/14/2016 13:26
 * email：409962004@qq.com
 * TODO: 我的收藏
 */
public class CollectionActivity extends BaseFrameActivity {

    @Bind(R.id.collection_refreshLayout)
    MySwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.collection_recyclerView)
    RecyclerView mRecyclerView;

    private CollectionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_collection);
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter = new CollectionAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new ItemLine(this, R.drawable.item_line_location));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我的收藏");
        setFunctionView(getLeft(), null, R.mipmap.ico_back);
    }

    @Override
    public void initListener() {
        super.initListener();
        mSwipeRefreshLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                mSwipeRefreshLayout.refreshComplete();
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

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
}
