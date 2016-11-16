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

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.ListHouseAdapter;
import cn.ichengxi.fang.adapter.decoration.ItemLine;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.BackgroundView;
import cn.ichengxi.fang.view.MySwipeRefreshLayout;
import cn.ichengxi.fang.view.popup.BasePopup;
import cn.ichengxi.fang.view.popup.SearchRangePopup;
import cn.ichengxi.fang.view.popup.SearchTypePopup;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Jun on 2016/11/7.
 */
public class ListHouseActivity extends BaseFrameActivity<ListHousePresenter, ListHouseModel> implements  BasePopup.OnPopupWindowListener {
    @Bind(R.id.list_house_rv)
    RecyclerView mListHouseRv;
    @Bind(R.id.swipe_layout)
    MySwipeRefreshLayout mSwipeLayout;
    @Bind(R.id.bg_contain)
    BackgroundView mBackgroundView;
    @Bind(R.id.search_range)
    LinearLayout mSearchRange;
    @Bind(R.id.search_price)
    LinearLayout mSearchPrice;
    @Bind(R.id.search_type)
    LinearLayout mSearchType;
    @Bind(R.id.search_more)
    LinearLayout mSearchMore;


    private ListHouseAdapter mAdapter;

    private SearchTypePopup mSearchTypePopup;

    private SearchRangePopup mSearchRangePopup;

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
        mBackgroundView = findViewByIdToView(R.id.bg_contain);

        mSearchTypePopup = new SearchTypePopup(this);
        mSearchRangePopup = new SearchRangePopup(this);
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
            public void onRefreshBegin(final PtrFrameLayout frame) {

                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);

            }
        });

        mSearchRange.setOnClickListener(this);
        mSearchPrice.setOnClickListener(this);
        mSearchType.setOnClickListener(this);
        mSearchMore.setOnClickListener(this);

        mBackgroundView.loading();

        mBackgroundView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mBackgroundView != null)
                    mBackgroundView.success();
            }
        }, 1000);

        mSearchTypePopup.setListener(this);
        mSearchRangePopup.setListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();

                break;

            case R.id.search_more:
                break;

            case R.id.search_price:
                if(!mBackgroundView.isLoading()) {
                    int offset = (int) getResources().getDimension(R.dimen.line_height);
                    mSearchRangePopup.showAsDropDown(v, 0, offset);
                }
                break;

            case R.id.search_range:

                break;

            case R.id.search_type:
//                mSearchTypePopup.showAtLocation(v, Gravity.BOTTOM, 0,0);
                if(!mBackgroundView.isLoading()) {
                    int offset = (int) getResources().getDimension(R.dimen.line_height);
                    mSearchTypePopup.showAsDropDown(v, 0, offset);
                }
                break;
        }

        super.onClick(v);

    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void show(PopupWindow window) {
        mBackgroundView.setBackgroundColor(Color.BLACK);
        ViewCompat.setAlpha(mListHouseRv, 0.5f);
    }

    @Override
    public void dismiss(PopupWindow window) {
        mBackgroundView.setBackgroundResource(R.color.bg);
        ViewCompat.setAlpha(mListHouseRv, 1f);
    }
}
