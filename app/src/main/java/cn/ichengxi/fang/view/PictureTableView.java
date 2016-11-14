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

    private static final int MaxColumnCount = 3;


    private enum LayoutType {
        NORMAL,
        FLOW,
        NONE
    }

    private LayoutType mLayoutType = LayoutType.FLOW;

    private List<Integer> mData;

    private int mSingleItemSize;


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

        mSpec = (int) ta.getDimension(R.styleable.PictureTableView_spec, 5);

        ta.recycle();

        mData = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            mData.add(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        }

        int dataSize = Math.max(Math.min(mData.size(), 7), 0);


        for (int i = 0; i < dataSize; i++) {

            ImageView picView = new ImageView(context);
            picView.setImageDrawable(new ColorDrawable(mData.get(i)));
            addView(picView);
        }

        setBackgroundColor(Color.YELLOW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int heightSize = 0;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);


        switch (mLayoutType) {
            case FLOW:
                switch (getChildCount()) {
                    case 1:
                    case 2:
                    case 4:
                    case 5:
                        heightSize = getHeightWithFLowWidth(widthSize, getChildCount());
                        break;

                    default:
                        heightSize = getHeightWithNormalWidth(widthSize);
                        break;
                }
                break;

            case NORMAL:
                heightSize = getHeightWithNormalWidth(widthSize);
                break;
        }


        setMeasuredDimension(widthSize, heightSize);

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            switch (mLayoutType) {
                case FLOW:

                    switch (getChildCount()) {
                        case 1:
                            layoutPictureViewWithCount(1);
                            break;
                        case 2:
                        case 4:
                            layoutPictureViewWithCount(2);
                            break;

                        case 5:
                            layoutPictureViewWith5();
                            break;

                        default:
                            layoutPictureViewWithCount(MaxColumnCount);
                    }

                    break;

                case NORMAL:
                    layoutPictureViewWithCount(MaxColumnCount);
                    break;
            }
        }
    }

    public void setLayoutType(LayoutType mLayoutType) {
        this.mLayoutType = mLayoutType;
    }


    private int getHeightWithFLowWidth(int widthSize, int columnCount) {
        if (columnCount == 5) {
            return measurePictureView(widthSize, 2, 2) + measurePictureView(widthSize, 3, 3);
        } else {
            return measurePictureView(widthSize, columnCount, getChildCount());
        }
    }


    private int getHeightWithNormalWidth(int widthSize) {
        return measurePictureView(widthSize, MaxColumnCount, getChildCount());
    }

    private int measurePictureView(int widthSize, int columnCount, int childCount) {

        int heightSize = 0;

        mSingleItemSize = getItemSize(widthSize, columnCount);
        for (int i = 0; i < childCount; i++) {
            if (i % columnCount == 0) {
                heightSize += mSpec + mSingleItemSize;
            }
        }
        heightSize += mSpec;

        return heightSize;
    }

    private void layoutPictureViewWithCount(int columnCount) {
        int columnOffset = 0;
        int rowOffset = 0;
        int column = 0;
        int row = 0;

        for (int i = 0; i < getChildCount(); i++) {
            ImageView picView = (ImageView) getChildAt(i);

            if (i % columnCount == 0) {
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

    private void layoutPictureViewWith5() {
        int columnOffset = 0;
        int rowOffset = 0;
        int column = 0;
        int row = 0;

        ImageView lastRowView = null;
        for (int i = 0; i < getChildCount(); i++) {
            ImageView picView = (ImageView) getChildAt(i);

            if (i < 2) {
                int itemSize = getItemSize(getMeasuredWidth(), 2);
                columnOffset = (i + 1) * mSpec + i * itemSize;
                picView.layout(columnOffset, mSpec, columnOffset + itemSize, mSpec + itemSize);
                lastRowView = picView;
            } else {
                int newI = i % MaxColumnCount;
                columnOffset = (newI + 1) * mSpec + newI * mSingleItemSize;
                rowOffset = lastRowView.getBottom() + mSpec;
                picView.layout(columnOffset, rowOffset, columnOffset + mSingleItemSize, rowOffset + mSingleItemSize);
            }


        }
    }

    private int getItemSize(int widthSize, int columnCount) {
        return (widthSize - mSpec * (columnCount + 1)) / columnCount;
    }
}
