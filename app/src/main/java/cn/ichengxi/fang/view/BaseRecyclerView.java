package cn.ichengxi.fang.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by quan on 16/11/7.
 */

public class BaseRecyclerView extends RecyclerView {

    private static final String TAG = "BaseRecyclerView";

    private int d;

    private OnScrollYChangeCallBack callBack;

    public BaseRecyclerView(Context context) {
        super(context);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

//        Log.d(TAG, "onScrollChanged() called with: l = [" + l + "], t = [" + t + "], oldl = [" + oldl + "], getScrollY() = [" + getScrollY() + "]");
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
    }


    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
//        Log.d(TAG, "onOverScrolled() called with: scrollX = [" + scrollX + "], scrollY = [" + scrollY + "], clampedX = [" + clampedX + "], clampedY = [" + clampedY + "]");
    }

    @Override
    public void onScrolled(int dx, int dy) {
        d += dy;
        if(callBack != null){
            callBack.onScrollYChange(d);
        }
//        Log.d(TAG, "onScrolled() called with: d = [" + d + "], dy = [" + dy + "]");
    }

    public void setCallBack(OnScrollYChangeCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnScrollYChangeCallBack {
        void onScrollYChange(int d);
    }
}
