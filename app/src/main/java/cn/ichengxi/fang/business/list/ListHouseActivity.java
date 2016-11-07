/*
 * Copyright 2016 Freelander
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ichengxi.fang.business.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.ListHouseAdapter;
import cn.ichengxi.fang.adapter.decoration.ItemLine;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.widget.MySwipeRefreshLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Jun on 2016/11/7.
 */
public class ListHouseActivity extends BaseFrameActivity<ListHousePresenter, ListHouseModel> {
    @Bind(R.id.list_house_rv)
    RecyclerView mListHouseRv;
    @Bind(R.id.swipe_layout)
    MySwipeRefreshLayout mSwipeLayout;

    private ListHouseAdapter mAdapter;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_house);
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter = new ListHouseAdapter(this);
    }

    @Override
    public void initView() {
        super.initView();
        mListHouseRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mListHouseRv.addItemDecoration(new ItemLine(this, R.drawable.item_line));
        mListHouseRv.setAdapter(mAdapter);
    }

    @Override
    public void initLoad() {
        super.initLoad();
    }

    @Override
    public void initListener() {
        super.initListener();
        findViewByIdToView(R.id.iv_back).setOnClickListener(this);

        mSwipeLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                mSwipeLayout.refreshComplete();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mSwipeLayout.refreshComplete();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_back:
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
