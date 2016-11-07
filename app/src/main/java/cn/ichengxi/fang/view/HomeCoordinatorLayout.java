package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/5.
 */

public class HomeCoordinatorLayout extends LinearLayout implements NestedScrollingParent {

    private static final String TAG = "HomeCoordinatorLayout";

    private View mHeaderView;

    private ImageView mBackGroundView;

    private View mLocationView;

    private View mCenterView;

    private View mSearchView;

    private View mActionBarView;

    private NestedScrollView mContentView;

    private int mHeaderHeight, mActionBarHeight, mActionBarWidth, mLocationHeight, mLocationWidth;

    private int mLocationMargin, mSearchHeight, mSearchWidth, mSearchMarginBottom, mSearchMarginWidth;

    private int mTouchSlop;

    private OverScroller mScroller;


    public HomeCoordinatorLayout(Context context) {
        this(context, null);
    }

    public HomeCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLocationMargin = (int) getResources().getDimension(R.dimen.partition_normal);
        mSearchMarginBottom = (int) getResources().getDimension(R.dimen.partition_big);
        mSearchMarginWidth = (int) getResources().getDimension(R.dimen.partition_very_large);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new OverScroller(getContext());
        setOrientation(LinearLayout.VERTICAL);


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = findViewById(R.id.home_header);
        mContentView = (NestedScrollView) findViewById(R.id.home_content);
        mBackGroundView = (ImageView) mHeaderView.findViewById(R.id.home_bg);
        mActionBarView = mHeaderView.findViewById(R.id.home_actionBar);
        mLocationView = mHeaderView.findViewById(R.id.home_location);
        mSearchView = mHeaderView.findViewById(R.id.home_search);
        Uri bg = Uri.parse("res:///" + R.mipmap.home_bg);
        mBackGroundView.setImageURI(bg);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int height = mHeaderView.getMeasuredHeight() + mContentView.getMeasuredHeight();
//        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, heightMode));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams params = mContentView.getLayoutParams();
        params.height = getMeasuredHeight() + 100;
        setMeasuredDimension(getMeasuredWidth(), mHeaderView.getMeasuredHeight() + mContentView.getMeasuredHeight());


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeaderHeight = mHeaderView.getMeasuredHeight();
        mActionBarHeight = mActionBarView.getMeasuredHeight();
        mActionBarWidth = mActionBarView.getMeasuredWidth();
        mLocationHeight = mLocationView.getMeasuredHeight();
        mLocationWidth = mLocationView.getMeasuredWidth();
        mSearchHeight = mSearchView.getMeasuredHeight();
        mSearchWidth = mSearchView.getMeasuredWidth();

//        mScroller.startScroll(0, getScrollY(),0 ,mHeaderHeight - mActionBarHeight, 3000);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

        boolean hiddenHeader = dy > 0 && getScrollY() < mHeaderHeight - mActionBarHeight;
        //用来判断view在竖直方向上能不能向上或者向下滑动
        //direction 方向    负数代表向上滑动 ，正数则反之
        boolean showHeader = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        float p = (float) (1.0 * getScrollY() / (mHeaderHeight - mActionBarHeight));

        Log.e(TAG, "onNestedPreScroll: dy = " + dy + ", p = " + (p) + ", getScrollY() = " + getScrollY());



        if (hiddenHeader || showHeader) {


            scrollBy(0, dy);
            consumed[1] = dy;
        }

        ViewCompat.setAlpha(mActionBarView, p);


        mActionBarView.layout(0, getScrollY(), mActionBarWidth, getScrollY() + mActionBarHeight);
        mLocationView.layout(mLocationMargin, getScrollY() + mLocationMargin, mLocationMargin + mLocationWidth, getScrollY() + mLocationHeight + mLocationMargin);
        mLocationView.getBackground().setColorFilter(
                evaluate(Math.max(Math.min(p, 1), 0), Color.parseColor("#FF2A2D2F"), ContextCompat.getColor(getContext(), R.color.green1)),
                PorterDuff.Mode.SRC_OVER);


        int offset = (int) (p * (mLocationView.getRight() - mLocationMargin));
        int searchTop = mHeaderHeight - mSearchHeight - offset - mSearchMarginBottom;
        int searchBottom = searchTop + mSearchHeight;
        int searchLeft = offset + mSearchMarginWidth;
        int searchRight = (int) (mSearchWidth + mSearchMarginWidth + (mSearchMarginWidth / 2 * p));
        int padding = (mActionBarHeight - mSearchHeight) / 2;

        if (getScrollY() > searchTop - padding) {
            searchTop = getScrollY() + padding;
            searchBottom = searchTop + mSearchHeight;
        }


        mSearchView.layout(searchLeft, searchTop, searchRight, searchBottom);

    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (getScrollY() >= mHeaderHeight - mActionBarHeight) return false;
        fling((int) velocityY);
//        Log.e(TAG, "onNestedPreFling: velocityY = " + velocityY);

        return true;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return true;
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);



    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mHeaderHeight - mActionBarHeight);
    }

    @Override
    public void scrollTo(int x, int y) {
//        Log.e(TAG, "scrollTo: y = " + y);

        y = Math.max(Math.min(y, mHeaderHeight - mActionBarHeight), 0);

        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
           postInvalidate();
        }
    }

    private int evaluate(float fraction, int startValue, int endValue) {
        int startA = (startValue >> 24) & 0xff;
        int startR = (startValue >> 16) & 0xff;
        int startG = (startValue >> 8) & 0xff;
        int startB = startValue & 0xff;

        int endA = (endValue >> 24) & 0xff;
        int endR = (endValue >> 16) & 0xff;
        int endG = (endValue >> 8) & 0xff;
        int endB = endValue & 0xff;

        return (startA + (int) (fraction * (endA - startA))) << 24
                | (startR + (int) (fraction * (endR - startR))) << 16
                | (startG + (int) (fraction * (endG - startG))) << 8
                | (startB + (int) (fraction * (endB - startB)));

    }
}
