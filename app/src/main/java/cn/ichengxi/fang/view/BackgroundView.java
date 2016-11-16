package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/10.
 */

public class BackgroundView extends FrameLayout {

    private List<View> mContentView;

    private ImageView mBgImgView;

    private TextView mMessageView;

    private AnimationDrawable mLoadingDrawable;

    private LinearLayout mBgContain;

    public BackgroundView(Context context) {
        this(context, null);
    }

    public BackgroundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mLoadingDrawable = (AnimationDrawable) ContextCompat.getDrawable(getContext(), R.drawable.loading);

        mContentView = new ArrayList<>();

    }

    @Override
    protected void onFinishInflate() {

        int count = getChildCount();
        if (count == 0) {
            throw new RuntimeException("没有子控件");
        }

        for (int i = 0; i < count; i++) {
            View v = getChildAt(i);
            if (v.getTag() != null && v.getTag() instanceof String) {
                String tag = (String) v.getTag();
                if (tag.equals("actionBar")) continue;
            }

            mContentView.add(v);
        }

        mBgContain = new LinearLayout(getContext());
        mBgImgView = new ImageView(getContext());
        mMessageView = new TextView(getContext());

        mBgContain.setOrientation(LinearLayout.VERTICAL);
        mBgContain.setGravity(Gravity.CENTER);

        LayoutParams containLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containLayoutParams.gravity = Gravity.CENTER;
        mBgContain.setLayoutParams(containLayoutParams);

        LinearLayout.LayoutParams subviewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mBgImgView.setLayoutParams(subviewLayoutParams);
        mMessageView.setLayoutParams(subviewLayoutParams);
        mMessageView.setTextColor(ContextCompat.getColor(getContext(), R.color.grey2));

        mMessageView.setPadding(10, 10, 10, 10);

        mBgContain.addView(mBgImgView);
        mBgContain.addView(mMessageView);

        addView(mBgContain, 0);
    }


    @Override
    protected void onDetachedFromWindow() {
//        mLoadingDrawable = null;

        if (mContentView != null) {
            for (View v : mContentView) {
                v.clearAnimation();
            }
//            mContentView.clear();
        }


//        mContentView = null;
        super.onDetachedFromWindow();
    }

    public void loading() {
        loading(null);
    }

    public void loading(String msg) {

        if (mMessageView != null) {
            mMessageView.setVisibility(VISIBLE);
            if (!TextUtils.isEmpty(msg)) {
                mMessageView.setText(msg);
            } else {
                mMessageView.setText("正在加载...");
            }
        }
        if (mBgImgView != null)
            mBgImgView.setImageDrawable(mLoadingDrawable);
        if (mLoadingDrawable != null)
            mLoadingDrawable.start();
        hideContentView();
        showBg();
    }

    public void empty() {
        empty(null);
    }

    public void empty(String msg) {
        checkMsg(msg);
        hideContentView();
        showBg();
    }

    public void success() {
        success(null);
    }

    public void success(String msg) {

        checkMsg(msg);

        if (mLoadingDrawable != null)
            mLoadingDrawable.stop();
        if (mBgImgView != null) {
            mBgImgView.setImageDrawable(null);
        }
        showContentView();
        hideBg();
    }

    public void error() {
        error(null);
    }

    public void error(String msg) {
        checkMsg(msg);
        hideContentView();
        showBg();
    }

    private void hideContentView() {
        if (mContentView != null) {
            for (View v : mContentView) {
                v.setVisibility(GONE);
            }
        }

    }

    private void showContentView() {
        if (mContentView != null) {

            Animation animation = new AlphaAnimation(0, 1);
            animation.setDuration(300);
            animation.setInterpolator(new DecelerateInterpolator());

            for (View v : mContentView) {
                v.setVisibility(VISIBLE);
                v.startAnimation(animation);
            }
        }
    }


    private void hideBg() {
        if (mBgContain != null) {
            mBgContain.setVisibility(GONE);
        }
    }

    private void showBg() {
        if (mBgContain != null) {
            mBgContain.setVisibility(VISIBLE);
        }
    }

    private void checkMsg(String msg) {
        if (mMessageView != null)
            if (!TextUtils.isEmpty(msg)) {
                mMessageView.setText(msg);
                mMessageView.setVisibility(VISIBLE);
            } else {
                mMessageView.setVisibility(GONE);
            }
    }

    public boolean isLoading(){
        if (mBgContain != null){
            if(mBgContain.getVisibility() == VISIBLE){
                return true;
            }
        }

        return false;
    }
}
