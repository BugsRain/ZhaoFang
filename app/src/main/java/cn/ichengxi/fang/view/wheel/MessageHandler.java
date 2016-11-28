// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package cn.ichengxi.fang.view.wheel;

import android.os.Handler;
import android.os.Message;

/**
 * author：created by Snail.江
 * time: 11/7/2016 12:23
 * email：409962004@qq.com
 * TODO:
 */
final class MessageHandler extends Handler {
    public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    public static final int WHAT_SMOOTH_SCROLL = 2000;
    public static final int WHAT_ITEM_SELECTED = 3000;

    final LoopView loopview;

    MessageHandler(LoopView loopview) {
        this.loopview = loopview;
    }

    @Override
    public final void handleMessage(Message msg) {
        switch (msg.what) {
            case WHAT_INVALIDATE_LOOP_VIEW:
                loopview.invalidate();
                break;

            case WHAT_SMOOTH_SCROLL:
                loopview.smoothScroll(LoopView.ACTION.FLING);
                break;

            case WHAT_ITEM_SELECTED:
                loopview.onItemSelected();
                break;
        }
    }

}
