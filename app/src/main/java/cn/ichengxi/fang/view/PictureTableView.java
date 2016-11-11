package cn.ichengxi.fang.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/11.
 */

public class PictureTableView extends ViewGroup {

    private List<Integer> mData;

    private int mSingleItemSize;

    private int mColumnCount;

    private int mSpec;

    public PictureTableView(Context context) {
        this(context, null);
    }

    public PictureTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PictureTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PictureTableView);

        mColumnCount = ta.getInt(R.styleable.PictureTableView_column, 3);
        mSpec = (int) ta.getDimension(R.styleable.PictureTableView_spec, 20);

        ta.recycle();

        mData = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            mData.add(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        }

        for (int i = 0; i < mData.size(); i++) {

            ImageView picView = new ImageView(context);
            picView.setImageDrawable(new ColorDrawable(mData.get(i)));
            addView(picView);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightSize = 0;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);


        if (mColumnCount != 0) {


            mSingleItemSize = (widthSize - mSpec * (mColumnCount + 1)) / mColumnCount;
            for (int i = 0; i < mData.size(); i++) {
                if (i % mColumnCount == 0) {
                    heightSize += mSpec + mSingleItemSize;
                }
            }
            heightSize += mSpec;


            setMeasuredDimension(widthSize, heightSize);

        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            if (mColumnCount != 0) {
                int columnOffset = 0;
                int rowOffset = 0;
                int column = 0;
                int row = 0;

                for (int i = 0; i < getChildCount(); i++) {
                    ImageView picView = (ImageView) getChildAt(i);

                    if (i % mColumnCount == 0) {
                        columnOffset = mSpec;
                        column = 1;
                        row++;
                        rowOffset = mSpec * row + (row - 1) * mSingleItemSize;
                    } else {
                        column++;
                        columnOffset = mSpec * column + mSingleItemSize * (column - 1);
                    }

                    picView.layout(columnOffset, rowOffset, columnOffset + mSingleItemSize, rowOffset + mSingleItemSize);
                }
            }
        }
    }
}
