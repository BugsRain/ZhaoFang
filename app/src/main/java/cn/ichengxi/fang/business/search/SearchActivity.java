package cn.ichengxi.fang.business.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.SearchRecordAdapter;
import cn.ichengxi.fang.adapter.decoration.ItemLine2;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.BaseRecyclerView;

/**
 * Created by quan on 16/11/22.
 */

public class SearchActivity extends BaseFrameActivity {


    @Bind(R.id.search_content)
    EditText mSearchContent;
    @Bind(R.id.search_record)
    BaseRecyclerView mSearchRecord;
    @Bind(R.id.search_button)
    TextView mSearchButton;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
    }

    @Override
    public void initView() {
        super.initView();
        mSearchRecord.addItemDecoration(new ItemLine2(this, R.drawable.item_line_location));
        mSearchRecord.setLayoutManager(new LinearLayoutManager(this));
        mSearchRecord.setAdapter(new SearchRecordAdapter(this));

    }

    @Override
    public void initListener() {
        super.initListener();
        mSearchRecord.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

}
