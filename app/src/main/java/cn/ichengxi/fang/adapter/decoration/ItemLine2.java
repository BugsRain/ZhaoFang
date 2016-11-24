package cn.ichengxi.fang.adapter.decoration;

import android.content.Context;

import cn.ichengxi.fang.R;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ItemLine2 extends ItemLine {

    public ItemLine2(Context context, int resId) {
        super(context, resId);
        padding = (int) context.getResources().getDimension(R.dimen.partition_normal);
    }

}
