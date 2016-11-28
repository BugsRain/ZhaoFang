package cn.ichengxi.fang.view.popup;

import android.view.LayoutInflater;
import android.view.View;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/23.
 */

public class UserPopup extends BasePopup {

    public UserPopup(BaseImplCompat compat) {
        super(compat);

        View contentView = LayoutInflater.from(compat.getContext()).inflate(R.layout.popup_user, null, false);

        setContentView(contentView);
    }
}
