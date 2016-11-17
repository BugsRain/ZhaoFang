package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.ichengxi.fang.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by quan on 16/11/10.
 */

public class MyRefreshHeader extends RelativeLayout implements PtrUIHandler {

    private static final String TAG = "MyRefreshHeader";

    private static final int[] IMG = {
            R.mipmap.ico_refresh1,
            R.mipmap.ico_refresh2,
            R.mipmap.ico_refresh3,
            R.mipmap.ico_refresh4,
            R.mipmap.ico_refresh5
    };

    private TextView mMessageView;

    private ImageView mImgView;

    private AnimationDrawable mAnimationDrawable;

    public MyRefreshHeader(Context context) {
        this(context, null);
    }

    public MyRefreshHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mAnimationDrawable = (AnimationDrawable) ContextCompat.getDrawable(context, R.drawable.refresh);

        View view = View.inflate(getContext(), R.layout.header_refresh, this);
        mImgView = (ImageView) view.findViewById(R.id.refresh_img);
        mMessageView = (TextView) view.findViewById(R.id.refresh_message);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
            mAnimationDrawable = null;
        }
        super.onDetachedFromWindow();
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Log.d(TAG, "onUIReset() called");
        mAnimationDrawable.stop();
        mImgView.setImageDrawable(null);

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Log.d(TAG, "onUIRefreshPrepare() called");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Log.d(TAG, "onUIRefreshBegin() called");
        mMessageView.setText("正在刷新...");
        mImgView.setImageDrawable(mAnimationDrawable);
        mAnimationDrawable.start();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        Log.d(TAG, "onUIRefreshComplete() called with");
    }

    //    final static byte PTR_STATUS_INIT = 1;
//    final static byte PTR_STATUS_PREPARE = 2;
//    final static byte PTR_STATUS_LOADING = 3;
//    final static byte PTR_STATUS_COMPLETE = 4;
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();


        final int offset = 35;
        final int ratio = offset / IMG.length;
        int index = currentPos % offset / ratio;


        if (isUnderTouch && status != PtrFrameLayout.PTR_STATUS_LOADING) {


            mImgView.setImageResource(IMG[index]);

            if (status == PtrFrameLayout.PTR_STATUS_COMPLETE) {
                mMessageView.setText("刷新完成");
            } else {

                if (currentPos >= mOffsetToRefresh) {
                    mMessageView.setText("释放刷新");
                } else {
                    mMessageView.setText("下拉刷新");

                }

            }
        }

//        MyLog.d(TAG, "onUIPositionChange() called with: isUnderTouch = [" + isUnderTouch + "], status = [" + status
//                + "], mOffsetToRefresh = " + mOffsetToRefresh + ", currentPos = " + currentPos + ", lastPos = " + lastPos
//                + ", height = " + getMeasuredHeight() + ", index = " + index);
        }
    }
