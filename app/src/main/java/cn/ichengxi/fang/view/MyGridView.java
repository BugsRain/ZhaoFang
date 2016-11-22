package cn.ichengxi.fang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/21.
 */

public class MyGridView extends ViewGroup {

    private int NONE_STYLE = -1;

    private int mTextSize;

    private int mItemWidth, mItemHeight;

    private int mMarginWidth, mMarginHeight;

    private int mDefaultColor;

    private HashMap<View, Integer> mTextColorMap;

    private MyGridViewAdapter mAdapter;

    private LayoutParams mLayoutParams;

    public MyGridView(Context context) {
        this(context, null);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mItemHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());
        mTextSize = 11;
        mDefaultColor = ContextCompat.getColor(context, R.color.grey1);

        mMarginWidth = (int) getResources().getDimension(R.dimen.partition_normal2);
        mMarginHeight = (int) getResources().getDimension(R.dimen.partition_normal);

        mTextColorMap = new HashMap<>();

        mLayoutParams = new LayoutParams(mItemWidth, mItemHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            if (i % 4 == 0) {
                heightSize += mMarginHeight + mItemHeight;
            }

        }

        mItemWidth = (widthSize - 3 * mMarginWidth) / 4;

        setMeasuredDimension(widthSize, heightSize);
//        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(heightSize, heightMode));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int row = 0;
        int column = -1;
        for (int i = 0; i < getChildCount(); i++) {
            TextView view = (TextView) getChildAt(i);

            if (i != 0 && i % 4 == 0) {
                row++;
                column = 0;
            } else {
                column++;
            }
            int left = column * (mItemWidth + mMarginWidth);
            int top = row * (mItemHeight + mMarginHeight);
            view.layout(left, top, left + mItemWidth, top + mItemHeight);
            view.setGravity(Gravity.CENTER);
            mLayoutParams.width = mItemWidth;
            mLayoutParams.height = mItemHeight;
            view.setLayoutParams(mLayoutParams);

        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    private TextView getItem(String string) {
        TextView view = new TextView(getContext());


        if (TextUtils.isEmpty(string)) {
            view.setText("item");
        } else {
            view.setText(string);
        }
        view.setTextSize(mTextSize);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView view = (TextView) v;
                view.setSelected(!v.isSelected());
                int color = mTextColorMap.get(v);
                view.setTextColor(v.isSelected() ? Color.WHITE : color);

            }
        });
        view.setTextColor(mDefaultColor);
//        view.setBackgroundResource(R.drawable.btn_item_selector);
        return view;
    }


    public void setAdapter(MyGridViewAdapter adapter) {
        mAdapter = adapter;
        if (mAdapter != null) {

            mTextColorMap.clear();

            int count = mAdapter.getCount();
            removeAllViews();
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    TextView view = getItem(mAdapter.getContent(i));
                    if (mAdapter.getBackground(i) != 0) {
                        view.setBackgroundResource(mAdapter.getBackground(i));
                    }

                    if(mAdapter.getTag(i) != null){
                        view.setTag(mAdapter.getTag(i));
                    }

                    if(mAdapter.getTextColor(i) != 0){
                        mTextColorMap.put(view, mAdapter.getTextColor(i));
                        view.setTextColor(mAdapter.getTextColor(i));
                    } else {
                        mTextColorMap.put(view, mDefaultColor);
                        view.setTextColor(mDefaultColor);
                    }

                    addView(view);
                }

            }


        }
    }
}
