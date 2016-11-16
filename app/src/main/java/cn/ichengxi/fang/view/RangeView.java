package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/15.
 */

public class RangeView extends View {

    private final static String TAG = "RangeView";

    private enum MoveThumb {
        LEFT,
        RIGHT,
        NONE
    }

    private Bitmap mThumbBitmap;

    private Rect mBarLeft, mBarRight, mBarCenter, mThumbLeft, mThumbRight;

    private int mProgressHeight, mHeight, mThumbSize;

    private int mMax, mMin, mProgress;

    private int mDx;

    private int mProgressBgColor, mProgressColor;

    private Paint mPaint;

    private MoveThumb mMoveThumb = MoveThumb.NONE;

    public RangeView(Context context) {
        this(context, null);
    }

    public RangeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        mThumbBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ico_thumb, options);


        mProgressBgColor = ContextCompat.getColor(context, R.color.grey2);
        mProgressColor = ContextCompat.getColor(context, R.color.red2);

        mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        mProgressHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        mThumbSize = mProgressHeight * 5;

        mBarCenter = new Rect();
        mBarLeft = new Rect();
        mBarRight = new Rect();
        mThumbLeft = new Rect();
        mThumbRight = new Rect();

        mPaint = new Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);

        mMin = 0;
        mMax = 100;
        mProgress = mMin;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);

        invalidatePosition(0, width);

        setMeasuredDimension(width, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mProgressColor);
        canvas.drawRect(mBarCenter, mPaint);

        canvas.drawBitmap(mThumbBitmap, null, mThumbLeft, mPaint);
        canvas.drawBitmap(mThumbBitmap, null, mThumbRight, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                if (mThumbRight.contains(x, y)) {
                    mDx = x;
                    mMoveThumb = MoveThumb.RIGHT;

                } else if (mThumbLeft.contains(x, y)) {
                    mDx = x;
                    mMoveThumb = MoveThumb.LEFT;
                }
                Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_DOWN ], mDx = " + mDx + ", x = " + x);
                break;

            case MotionEvent.ACTION_MOVE:

                int dx = (int) (event.getX() - mDx);
                Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_MOVE ], mDx = " + mDx + ", x = " + x);

                if (!isOutBorder(x)) {
                    switch (mMoveThumb) {
                        case LEFT:
                            mThumbLeft.offset(dx, 0);
                            break;

                        case RIGHT:
                            mThumbRight.offset(dx, 0);
                            break;
                    }
                    updateBarCenter();
                    invalidate();

                }

                mDx = x;

                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_CANCEL ]");
                mDx = 0;
                mMoveThumb = MoveThumb.NONE;
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_UP ]");
                mDx = 0;
                mMoveThumb = MoveThumb.NONE;
                break;
        }

        return true;
    }


    private void invalidatePosition(int start, int end) {
        updateLeft(start);
        updateRight(end);
        updateBarCenter();
    }

    private void updateLeft(int start) {
        mThumbLeft.left = start;
        mThumbLeft.top = (mHeight - mThumbSize) / 2;
        mThumbLeft.right = mThumbLeft.left + mThumbSize;
        mThumbLeft.bottom = mThumbLeft.top + mThumbSize;
    }

    private void updateRight(int end) {
        mThumbRight.left = end - mThumbSize;
        mThumbRight.top = (mHeight - mThumbSize) / 2;
        mThumbRight.right = mThumbRight.left + mThumbSize;
        mThumbRight.bottom = mThumbRight.top + mThumbSize;
    }

    private void updateBarCenter() {
        mBarCenter.left = mThumbLeft.centerX();
        mBarCenter.top = (mHeight - mProgressHeight) / 2;
        mBarCenter.right = mThumbRight.centerX();
        mBarCenter.bottom = mBarCenter.top + mProgressHeight;
    }



    private boolean isOutBorder(int x) {

        return x < mThumbSize / 2 || x > getMeasuredWidth() - mThumbSize / 2;

    }

}
