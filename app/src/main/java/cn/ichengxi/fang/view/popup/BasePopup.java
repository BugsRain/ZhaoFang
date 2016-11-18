package cn.ichengxi.fang.view.popup;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;


/**
 * Created by quan on 16/11/15.
 */

public class BasePopup extends PopupWindow {

    private BaseImplCompat compat;
    private OnPopupWindowListener listener;

    public BasePopup(BaseImplCompat compat){
        super(compat.getContext());
        this.compat = compat;

        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setOutsideTouchable(true);
        setFocusable(true);
        setAnimationStyle(R.style.popupWindow_anim_style);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        update();
    }


    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
        if(listener != null){
            listener.show(this);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if(listener != null){
            listener.dismiss(this);
        }
    }


    public interface OnPopupWindowListener{
        void show(PopupWindow window);
        void dismiss(PopupWindow window);
    }

    public void setListener(OnPopupWindowListener listener) {
        this.listener = listener;
    }
}
