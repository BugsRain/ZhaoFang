package cn.ichengxi.fang.business.login;

import android.content.Context;

import java.util.List;

import chengxinet.chengxilibs.entity.Entity;
import cn.ichengxi.fang.entity.UserInfo;
import cn.ichengxi.fang.frame.BaseModel;
import cn.ichengxi.fang.frame.BasePresenter;
import cn.ichengxi.fang.frame.BaseView;
import rx.Observable;

/**
 * Created by quan on 16/11/4.
 */

public class LoginContract {
    interface Model extends BaseModel {
        Observable<Entity<List<UserInfo>>> login(Context context, String userName, String password);
    }

    interface View extends BaseView {
        void loginSuccess(UserInfo userInfo);
    }

    static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void login(Context context, String userName, String password);
    }
}
