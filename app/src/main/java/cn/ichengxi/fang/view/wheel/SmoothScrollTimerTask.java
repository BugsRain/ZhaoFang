// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package cn.ichengxi.fang.view.wheel;

import java.util.TimerTask;

/**
 * author：created by Snail.江
 * time: 11/7/2016 12:23
 * email：409962004@qq.com
 * TODO:
 */
final class SmoothScrollTimerTask extends TimerTask {

    int realTotalOffset;
    int realOffset;
    int offset;
    final LoopView loopView;

    SmoothScrollTimerTask(LoopView loopview, int offset) {
        this.loopView = loopview;
        this.offset = offset;
        realTotalOffset = Integer.MAX_VALUE;
        realOffset = 0;
    }

    @Override
    public final void run() {
        if (realTotalOffset == Integer.MAX_VALUE) {
            realTotalOffset = offset;
        }
        realOffset = (int) ((float) realTotalOffset * 0.1F);

        if (realOffset == 0) {
            if (realTotalOffset < 0) {
                realOffset = -1;
            } else {
                realOffset = 1;
            }
        }
        if (Math.abs(realTotalOffset) <= 0) {
            loopView.cancelFuture();
            loopView.handler.sendEmptyMessage(MessageHandler.WHAT_ITEM_SELECTED);
        } else {
            loopView.totalScrollY = loopView.totalScrollY + realOffset;
            loopView.handler.sendEmptyMessage(MessageHandler.WHAT_INVALIDATE_LOOP_VIEW);
            realTotalOffset = realTotalOffset - realOffset;
        }
    }
}
