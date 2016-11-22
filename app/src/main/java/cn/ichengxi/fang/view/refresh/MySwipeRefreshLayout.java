package cn.ichengxi.fang.view.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 刷新控件
 * Created by Jun on 2016/3/9.
 * <p>
 * PtrFrameLayout: 使用自定义头布局或者尾布局
 * PtrClassicFrameLayout：使用默认样式
 */
public class MySwipeRefreshLayout extends PtrFrameLayout {

    private float mPrevX;

    public MySwipeRefreshLayout(Context context) {
        this(context, null);

    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        ;
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.initConfig();
//        this.initHeader(context);
//        this.initFooter(context);

        MyRefreshHeader header = new MyRefreshHeader(context);
        setHeaderView(header);
        addPtrUIHandler(header);

        MyRefreshFooter footer = new MyRefreshFooter(context);
        setFooterView(footer);
        addPtrUIHandler(footer);
    }

//    private void initFooter(Context context) {
//
//        MaterialHeader footer = new MaterialHeader(context);
//        footer.setColorSchemeColors(colors);
//        footer.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        footer.setPadding(0, 20, 0, 25);
//        footer.setPtrFrameLayout(this);
//
//        this.addPtrUIHandler(footer);
//        this.setFooterView(footer);
//    }

//    private void initHeader(Context context) {
//        MaterialHeader header = new MaterialHeader(context);
//        header.setColorSchemeColors(colors);
//        header.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//        header.setPadding(0, 25, 0, 20);
//        header.setPtrFrameLayout(this);
//
//        this.addPtrUIHandler(header);
//        this.setHeaderView(header);
//    }


//    @Override
//    public void autoRefresh() {
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                MySwipeRefreshLayout.super.autoRefresh();
//            }
//        }, 100);
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        return super.dispatchTouchEvent(e);
    }

    private void initConfig() {

        /**
         * 拦截水平滑动监听
         * 默认为false，mPagingTouchSlop获得系统最少滑动距离原来为 系统参数*2
         * 为了配合侧滑item,系统最少滑动距离改为 系统参数/2
         * */
        disableWhenHorizontalMove(true);
        /**
         * 阻尼系数
         * 默认: 1.7f，越大，感觉下拉时越吃力
         */
        setResistance(1.5f);

        /**
         * 触发刷新时移动的位置比例
         * 默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作
         */
        setRatioOfHeaderHeightToRefresh(1.2f);

        /**
         * 回弹延时
         * 默认 200ms，回弹到刷新高度所用时间
         */
        setDurationToClose(750);

        /**
         * 头部回弹时间
         * 默认1000ms
         */
        setDurationToCloseHeader(1000);

        /**
         * 刷新是保持头部
         * 默认值 true
         */
        setKeepHeaderWhenRefresh(true);

        /**
         * 下拉刷新 / 释放刷新
         * 默认为释放刷新 false
         */
        setPullToRefresh(false);

        /**
         * 刷新时，保持内容不动，仅头部下移, 使用 Material Design 风格才好看一点
         * 默认 false
         */
        setPinContent(false);

        /**
         * 刷新模式
         * 默认 TOP：只支持下拉
         * Bottom：只支持上拉
         * BOTH：两种同时支持
         */
        setMode(Mode.BOTH);

        /**
         * 显示距离上次刷新时间
         * 默认不显示
         */
//        setLastUpdateTimeRelateObject(this);
    }

}
