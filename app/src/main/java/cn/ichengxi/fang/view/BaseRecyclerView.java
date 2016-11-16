package cn.ichengxi.fang.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by quan on 16/11/7.
 */

public class BaseRecyclerView extends RecyclerView {

    private static final String TAG = "BaseRecyclerView";

    private AdapterDataObserver observer;

    private int d, x;

    private OnScrollYChangeCallBack callBack;

    private int touchSlop;

    public BaseRecyclerView(Context context) {
        this(context, null);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
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

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean b = false;
        int action = e.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                x = (int) e.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = (int) Math.abs(x - e.getX());
                if(dx > touchSlop/2){
                    return false;
                }
                break;

            case MotionEvent.ACTION_UP:
                x = 0;
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        return super.onTouchEvent(e);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
