package cn.ichengxi.fang.business.login;

import android.content.Context;

import java.util.List;

import chengxinet.chengxilibs.entity.Entity;
import cn.ichengxi.fang.entity.UserInfo;
import rx.Observable;

/**
 * Created by quan on 16/11/4.
 */

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<Entity<List<UserInfo>>> login(Context context, String userName, String password) {
        return null;
    }
}

