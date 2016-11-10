package cn.ichengxi.fang.frame.base;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import chengxinet.chengxilibs.activity.BaseActivity;
import chengxinet.chengxilibs.utils.TUtil;
import cn.ichengxi.fang.frame.BaseModel;
import cn.ichengxi.fang.frame.BasePresenter;
import cn.ichengxi.fang.frame.BaseView;


/**
 * Created by quan on 16/9/1.
 */

public abstract class BaseFrameActivity<P extends BasePresenter, M extends BaseModel> extends BaseActivity implements BaseView {

    public P mPresenter;

    public M mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);

        if (mPresenter != null && mModel != null)
            mPresenter.setVM(this, mModel);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) mPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onInternetError() {
        showShortToast("网络异常");
    }

    @Override
    public void onRequestError(String msg) {
        showShortToast(msg);
    }

    //根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
    public boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null) {// && (v instanceof EditText)
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
