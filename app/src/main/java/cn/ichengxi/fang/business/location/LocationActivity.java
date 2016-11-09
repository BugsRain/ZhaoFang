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
package cn.ichengxi.fang.business.location;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;

import butterknife.Bind;
import cn.ichengxi.fang.MyApplication;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.ListLocationAdapter;
import cn.ichengxi.fang.adapter.decoration.ItemLine;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * Created by Jun on 2016/11/7.
 */
public class LocationActivity extends BaseFrameActivity<LocationPresenter, LocationModel> {

    @Bind(R.id.list_location_rv)
    RecyclerView mListLocationRv;

    private ListLocationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_location);
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter = new ListLocationAdapter(this);
    }

    @Override
    public void initView() {
        super.initView();
        setFunctionView(getLeft(), null, R.mipmap.ico_close);
        setTitle("开通城市");

        mListLocationRv.setLayoutManager(new LinearLayoutManager(this));
        mListLocationRv.addItemDecoration(new ItemLine(this, R.drawable.item_line_location));
        mListLocationRv.setAdapter(mAdapter);
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
