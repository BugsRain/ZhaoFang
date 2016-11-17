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
        INTERSECT,
        LEFT,
        RIGHT,
        NONE
    }

    private Bitmap mThumbBitmap;

    private Rect mBarLeft, mBarRight, mBarCenter, mThumbLeft, mThumbRight;

    private int mProgressHeight, mHeight, mThumbSize, mBarWidth;

    private int mMax, mMin;

    private int mDx;

    private int mProgressBgColor, mProgressColor;

    private Paint mPaint;

    private MoveThumb mMoveThumb = MoveThumb.NONE;

    private OnComputeProgressListener mOnComputeProgressListener;

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
        mThumbSize = mProgressHeight * 6;

        mPaint = new Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);

        mMin = 0;
        mMax = 12000;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);

        updatePosition(0, width);
        mBarWidth = mBarCenter.width();
        setMeasuredDimension(width, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mProgressColor);
        canvas.drawRect(mBarCenter, mPaint);

        mPaint.setColor(mProgressBgColor);
        canvas.drawRect(mBarLeft, mPaint);
        canvas.drawRect(mBarRight, mPaint);
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


                if (mThumbLeft.equals(mThumbRight) && mThumbRight.contains(x, y)) {
                    mMoveThumb = MoveThumb.INTERSECT;
                } else {

                    if (mThumbRight.contains(x, y)) {
                        mMoveThumb = MoveThumb.RIGHT;

                    } else if (mThumbLeft.contains(x, y)) {
                        mMoveThumb = MoveThumb.LEFT;
                    }
                }


                mDx = x;
                Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_DOWN ], mDx = " + mDx + ", x = " + x);
                break;

            case MotionEvent.ACTION_MOVE:
                int tempLeftDx = 0;
                int tempRightDx = 0;
                int dx = (int) (event.getX() - mDx);
                Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_MOVE ], mDx = " + mDx + ", dx = " + dx);

                if (mMoveThumb == MoveThumb.INTERSECT) {
                    if (dx > 0) {
                        mMoveThumb = MoveThumb.RIGHT;
                        System.out.println("RIGHT!!!!!");
                    } else if (dx < 0) {
                        mMoveThumb = MoveThumb.LEFT;
                        System.out.println("LEFT!!!!!");
                    }
                }

                switch (mMoveThumb) {
                    case LEFT:
                        tempLeftDx = mThumbLeft.left + dx;
                        if (tempLeftDx < 0) {
                            dx = dx + Math.abs(tempLeftDx);
                        }

                        tempRightDx = mThumbLeft.right + dx;
                        if (tempRightDx > mThumbRight.right) {
                            dx = dx - (tempRightDx - mThumbRight.right);
                            mMoveThumb = MoveThumb.RIGHT;
                        }
                        mThumbLeft.offset(dx, 0);
                        updateBarLeft();
                        break;

                    case RIGHT:
                        tempLeftDx = mThumbRight.left + dx;
                        if (tempLeftDx < mThumbLeft.left) {
                            dx = dx + Math.abs(mThumbLeft.left - tempLeftDx);
                            mMoveThumb = MoveThumb.LEFT;
                        }

                        tempRightDx = mThumbRight.right + dx;
                        if (tempRightDx > getMeasuredWidth()) {
                            dx = dx - (tempRightDx - getMeasuredWidth());
                        }
                        mThumbRight.offset(dx, 0);
                        updateBarRight();

                        break;
                }
                updateBarCenter();
                invalidate();
                mDx = x;

                if (mOnComputeProgressListener != null) {
                    mOnComputeProgressListener.onComputeProgress(computeMin(), computeMax());
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (action == MotionEvent.ACTION_CANCEL) {
                    Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_CANCEL ]");
                } else {
                    Log.d(TAG, "onTouchEvent() called with: event = [ MotionEvent.ACTION_UP ]");
                }
                mDx = 0;
                mMoveThumb = MoveThumb.NONE;
                invalidate();
                if (mOnComputeProgressListener != null) {
                    mOnComputeProgressListener.onComputeProgress(computeMin(), computeMax());
                }
                break;
        }

        return true;
    }


    private void updatePosition(int start, int end) {
        updateLeft(start);
        updateRight(end);
        updateBarCenter();
        updateBarLeft();
        updateBarRight();
    }

    private void updateLeft(int start) {

        if (mThumbLeft == null) mThumbLeft = new Rect();

        mThumbLeft.left = start;
        mThumbLeft.top = (mHeight - mThumbSize) / 2;
        mThumbLeft.right = mThumbLeft.left + mThumbSize;
        mThumbLeft.bottom = mThumbLeft.top + mThumbSize;
    }

    private void updateRight(int end) {

        if (mThumbRight == null) mThumbRight = new Rect();

        mThumbRight.left = end - mThumbSize;
        mThumbRight.top = (mHeight - mThumbSize) / 2;
        mThumbRight.right = mThumbRight.left + mThumbSize;
        mThumbRight.bottom = mThumbRight.top + mThumbSize;
    }

    private void updateBarCenter() {

        if (mBarCenter == null) mBarCenter = new Rect();

        mBarCenter.left = mThumbLeft.centerX();
        mBarCenter.top = (mHeight - mProgressHeight) / 2;
        mBarCenter.right = mThumbRight.centerX();
        mBarCenter.bottom = mBarCenter.top + mProgressHeight;
    }


    private void updateBarLeft() {
        if (mBarLeft == null) mBarLeft = new Rect();

        mBarLeft.left = mThumbSize / 2;
        mBarLeft.top = (mHeight - mProgressHeight) / 2;
        mBarLeft.right = mBarLeft.left + mThumbLeft.left;
        mBarLeft.bottom = mBarLeft.top + mProgressHeight;
    }

    private void updateBarRight() {
        if (mBarRight == null) mBarRight = new Rect();

        mBarRight.left = mThumbRight.centerX();
        mBarRight.top = (mHeight - mProgressHeight) / 2;
        mBarRight.right = getMeasuredWidth() - mThumbSize / 2;
        mBarRight.bottom = mBarRight.top + mProgressHeight;
    }

    private int computeMin() {
        return (int) (mMin + 1.0f * mThumbLeft.left / mBarWidth * (mMax - mMin));
    }

    private int computeMax() {
        return (int) (mMin + 1.0f * (mThumbRight.right - mThumbSize) / mBarWidth * (mMax - mMin));
    }

    public void setOnComputeProgressListener(OnComputeProgressListener listener) {
        mOnComputeProgressListener = listener;
    }

    public interface OnComputeProgressListener {
        void onComputeProgress(int min, int max);
    }

    public void setProgress(int mMin, int mMax) {
        this.mMin = mMin;
        this.mMax = mMax;
    }

}
