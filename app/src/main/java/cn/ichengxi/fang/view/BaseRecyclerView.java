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

    private AdapterDataObserver observer;

    private int d;

    private OnScrollYChangeCallBack callBack;

    public BaseRecyclerView(Context context) {
        this(context, null);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
    }


    @Override
    public void onScrolled(int dx, int dy) {
        d += dy;
        if (callBack != null) {
            callBack.onScrollYChange(d);
        }
    }

    public void setCallBack(OnScrollYChangeCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnScrollYChangeCallBack {
        void onScrollYChange(int d);
    }




}
