package cn.ichengxi.fang.frame.base;

import android.os.Bundle;

import chengxinet.chengxilibs.fragment.BaseFragment;
import chengxinet.chengxilibs.utils.TUtil;
import cn.ichengxi.fang.frame.BaseModel;
import cn.ichengxi.fang.frame.BasePresenter;
import cn.ichengxi.fang.frame.BaseView;


/**
 * Created by quan on 16/9/20.
 */

public abstract class BaseFrameFragment<P extends BasePresenter, M extends BaseModel> extends BaseFragment implements BaseView {

    public P mPresenter;

    public M mModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null && mModel != null)
            mPresenter.setVM(this, mModel);

    }

    @Override
    public void onDestroy() {
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

}
