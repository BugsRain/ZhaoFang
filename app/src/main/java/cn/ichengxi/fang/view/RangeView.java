package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/15.
 */

public class RangeView extends View {

    private Bitmap mSliderBitmap;

    private Rect mProgressBgRect, mProgressRect;

    private int mProgressHeight, mHeight;

    private int mProgressBgColor, mProgressColor;

    private Paint mPaint;

    public RangeView(Context context) {
        this(context, null);
    }

    public RangeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        mSliderBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ico_thumb, options);

        mProgressBgColor = ContextCompat.getColor(context, R.color.grey2);
        mProgressColor = ContextCompat.getColor(context, R.color.red1);

        mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        mProgressHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());


        mProgressBgRect = new Rect();
        mProgressRect = new Rect();

        mPaint = new Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        mProgressBgRect.left = 0;
        mProgressBgRect.top = (mHeight - mProgressHeight) / 2;
        mProgressBgRect.right = width;
        mProgressBgRect.bottom = mProgressBgRect.top + mProgressHeight;

        setMeasuredDimension(width, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(mProgressBgColor);
        canvas.drawRect(mProgressBgRect, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
