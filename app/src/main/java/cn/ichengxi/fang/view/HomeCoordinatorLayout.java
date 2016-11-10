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
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import chengxinet.chengxilibs.utils.MyLog;
import cn.ichengxi.fang.MyApplication;
import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/5.
 */

public class HomeCoordinatorLayout extends LinearLayout implements NestedScrollingParent {

    enum ScrollStatus {
        UP, DOWN, NONE
    }

    private ScrollStatus mScrollStatus = ScrollStatus.NONE;

    private static final String TAG = "HomeCoordinatorLayout";

    private View mHeaderView;

    private View mLocationView;

    private View mCenterView;

    private View mSearchView;

    private View mActionBarView;

    private NestedScrollView mContentView;

    private int mHeaderHeight, mActionBarHeight, mActionBarWidth, mLocationHeight, mLocationWidth;

    private int mLocationMargin, mSearchHeight, mSearchWidth, mSearchMarginBottom, mSearchMarginWidth;

    private int mTouchSlop;


    private OverScroller mScroller;

    private boolean isMoreKitkat;

    private int mStatusBarHeight;

    private OnScrollListener mOnScrollListener;

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

        mScroller = new OverScroller(getContext(), new DecelerateInterpolator());

        isMoreKitkat = MyApplication.isMoreKitkat();

        mStatusBarHeight = MyApplication.getStatusBarHeight();

        setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderView = findViewById(R.id.home_header);
        mContentView = (NestedScrollView) findViewById(R.id.home_content);
        mActionBarView = mHeaderView.findViewById(R.id.home_actionBar);
        mLocationView = mHeaderView.findViewById(R.id.home_location);
        mSearchView = mHeaderView.findViewById(R.id.home_search);
        ImageView mBackGroundView = (ImageView) mHeaderView.findViewById(R.id.home_bg);
        Uri bg = Uri.parse("res:///" + R.mipmap.home_bg);
        mBackGroundView.setImageURI(bg);

        if (isMoreKitkat) {
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mActionBarView.getLayoutParams();
            params.height += mStatusBarHeight;
            mActionBarView.setLayoutParams(params);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int height = mHeaderView.getMeasuredHeight() + mContentView.getMeasuredHeight();
//        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, heightMode));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        ViewGroup.LayoutParams params = mContentView.getLayoutParams();
        params.height = getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), mHeaderView.getMeasuredHeight() + mContentView.getMeasuredHeight());


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (isMoreKitkat) {
            mLocationView.layout(
                    mLocationMargin,
                    getScrollY() + mStatusBarHeight + mLocationMargin,
                    mLocationMargin + mLocationWidth,
                    getScrollY() + mStatusBarHeight + mLocationHeight + mLocationMargin);
        }

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

    }

    int startScrollY;

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        MyLog.d(TAG, "onStartNestedScroll() called with: nestedScrollAxes = [" + nestedScrollAxes + "]");
        startScrollY = getScrollY();
        mScroller.abortAnimation();
        return true;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        if (mOnScrollListener != null) mOnScrollListener.onStart();
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

        if (dy > 0) {
            mScrollStatus = ScrollStatus.UP;
        } else if (dy < 0) {
            mScrollStatus = ScrollStatus.DOWN;
        } else {
            mScrollStatus = ScrollStatus.NONE;
        }

        boolean hiddenHeader = dy > 0 && getScrollY() < mHeaderHeight - mActionBarHeight;
        //用来判断view在竖直方向上能不能向上或者向下滑动
        //direction 方向    负数代表向上滑动 ，正数则反之
        boolean showHeader = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenHeader || showHeader) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }

//        MyLog.e(TAG, "onNestedPreScroll: dy = " + dy + ", mScroller.getCurrY() = " + mScroller.getCurrY());

//        scrollAnimation();
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
//        if (getScrollY() > mHeaderHeight - mActionBarHeight - mStatusBarHeight) return false;
//        fling((int) (velocityY));
        MyLog.e(TAG, "onNestedPreFling: velocityY = " + velocityY);

        inertia();

        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public void onStopNestedScroll(View child) {

        if (getScrollY() == startScrollY) return;

        if (mOnScrollListener != null) mOnScrollListener.onStop();

        MyLog.d(TAG, "onStopNestedScroll() called with: startScrollY = [" + (startScrollY) + "] , getScrollY() = [" + getScrollY() + "]");

        inertia();
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mHeaderHeight - mActionBarHeight);
    }

    @Override
    public void scrollTo(int x, int y) {


        y = Math.max(Math.min(y, mHeaderHeight - mActionBarHeight), 0);

        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            MyLog.d(TAG, "computeScroll() called");
            postInvalidate();
            ViewCompat.postInvalidateOnAnimation(this);
        }

        scrollAnimation();
    }

    private void inertia(){
        int offset = mHeaderHeight - mActionBarHeight - mStatusBarHeight;
        MyLog.d(TAG, "inertia() called with: getScrollY() = [" + getScrollY() + "]");
        if(!mScroller.computeScrollOffset()) {
            switch (mScrollStatus) {
                case UP:
                    System.out.println("UP!!!!startScroll!!!!");
                    mScroller.startScroll(0, getScrollY(), 0, offset, 300);
                    break;

                case DOWN:
                    System.out.println("DOWN!!!!startScroll!!!!");
                    mScroller.startScroll(0, getScrollY(), 0, -offset, 300);
                    break;
            }

            invalidate();
        }
    }

    private void scrollAnimation() {
        float p = 1.0f * getScrollY() / (mHeaderHeight - mActionBarHeight);

        ViewCompat.setAlpha(mActionBarView, p);

        if (isMoreKitkat) {
            mLocationView.layout(
                    mLocationMargin,
                    getScrollY() + mStatusBarHeight + mLocationMargin,
                    mLocationMargin + mLocationWidth,
                    getScrollY() + mStatusBarHeight + mLocationHeight + mLocationMargin);
        } else {
            mLocationView.layout(
                    mLocationMargin,
                    getScrollY() + mLocationMargin,
                    mLocationMargin + mLocationWidth,
                    getScrollY() + mLocationHeight + mLocationMargin);
        }

        mActionBarView.layout(
                0,
                getScrollY(),
                mActionBarWidth,
                getScrollY() + mActionBarHeight);

        mLocationView.getBackground().setColorFilter(
                evaluate(Math.max(Math.min(p, 1), 0), Color.parseColor("#FF2A2D2F"), ContextCompat.getColor(getContext(), R.color.green1)),
                PorterDuff.Mode.SRC_OVER);

        int offset = (int) (p * (mLocationView.getRight() - mLocationMargin));
        int searchTop = mHeaderHeight - mSearchHeight - mSearchMarginBottom;
        int searchBottom = searchTop + mSearchHeight;
        int searchLeft = offset + mSearchMarginWidth;
        int searchRight = (int) (mSearchWidth + mSearchMarginWidth + (mSearchMarginWidth / 2 * p));
        int padding = (mActionBarHeight - mSearchHeight) / 2;

        if (isMoreKitkat) {
            if (getScrollY() > searchTop - padding - mStatusBarHeight / 2) {
                searchTop = getScrollY() + padding + mStatusBarHeight / 2;
                searchBottom = searchTop + mSearchHeight;
            }
        } else {
            if (getScrollY() > searchTop - padding) {
                searchTop = getScrollY() + padding;
                searchBottom = searchTop + mSearchHeight;
            }
        }

        mSearchView.layout(searchLeft, searchTop, searchRight, searchBottom);
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

    public interface OnScrollListener {
        void onStart();

        void onStop();
    }

    public void setOnScrollListener(HomeCoordinatorLayout.OnScrollListener mOnScrollListener) {
        this.mOnScrollListener = mOnScrollListener;
    }

}
