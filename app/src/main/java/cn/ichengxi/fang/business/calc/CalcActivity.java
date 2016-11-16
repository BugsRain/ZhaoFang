package cn.ichengxi.fang.business.calc;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.CalcFragmentAdapter;
import cn.ichengxi.fang.business.calc.accumulation.AccumulationAFragment;
import cn.ichengxi.fang.business.calc.combination.CombinationFragment;
import cn.ichengxi.fang.business.calc.commercial.CommercialFragment;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/16/2016 13:30
 * email：409962004@qq.com
 * TODO: 房产计算器页面
 */
public class CalcActivity extends BaseFrameActivity {

    @Bind(R.id.calc_tabLayout)
    TabLayout mTabLayout;

    @Bind(R.id.calc_viewPager)
    ViewPager mViewPager;

    private List<Fragment> mFragments;
    private List<String> mTitles;
    private CalcFragmentAdapter mAdapter;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_calc);
    }

    @Override
    public void initData() {
        super.initData();
        mFragments = new ArrayList<>();
        mTitles = new ArrayList<>();

        AccumulationAFragment accumulationAFragment = new AccumulationAFragment();
        CombinationFragment combinationFragment = new CombinationFragment();
        CommercialFragment commercialFragment = new CommercialFragment();
        mFragments.add(accumulationAFragment);
        mFragments.add(combinationFragment);
        mFragments.add(commercialFragment);
        mTitles.add("商业贷款");
        mTitles.add("公积金贷款");
        mTitles.add("组合贷款");
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("房产计算器");
        setFunctionView(getLeft(),"",R.mipmap.ico_back);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(2)));

        mAdapter = new CalcFragmentAdapter(getSupportFragmentManager(),mFragments,mTitles);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
