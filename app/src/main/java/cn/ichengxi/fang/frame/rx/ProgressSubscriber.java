package cn.ichengxi.fang.frame.rx;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import cn.ichengxi.fang.MyApplication;
import rx.Subscriber;

/**
 * Created by quan on 16/9/18.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {

    private static final int SHOW_PROGRESS_DIALOG = 1;

    private static final int DISMISS_PROGRESS_DIALOG = 2;

    private final static String TAG = "tag";

    private ProgressDialog mDialog;

    private Handler mHandler;


    public ProgressSubscriber(Context context) {

        MyApplication application = (MyApplication) context.getApplicationContext();
        Class<? extends ProgressDialog> cls = application.getProgressDialogClass();
        if(cls != null) {
            try {
                mDialog = cls.getConstructor(Context.class).newInstance(context);

                mDialog.setMessage("加载中...");

                mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        onCancelProgress();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            mHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message message) {
                    switch (message.what) {
                        case SHOW_PROGRESS_DIALOG:
                            if (mDialog != null && !mDialog.isShowing()) {
                                mDialog.show();
                            }
                            break;

                        case DISMISS_PROGRESS_DIALOG:
                            if (mDialog != null && mDialog.isShowing()) {
                                mDialog.dismiss();
                            }
                            mDialog = null;
                            mHandler = null;

                            break;
                    }
                    return true;
                }
            });
        }
    }


    @Override
    public void onStart() {
        if (mHandler != null)
            mHandler.obtainMessage(SHOW_PROGRESS_DIALOG).sendToTarget();
    }

    @Override
    public void onCompleted() {
        if (mHandler != null)
            mHandler.obtainMessage(DISMISS_PROGRESS_DIALOG).sendToTarget();
    }

    @Override
    public void onError(Throwable e) {
        if (mHandler != null)
            mHandler.obtainMessage(DISMISS_PROGRESS_DIALOG).sendToTarget();
    }



    @Override
    public void onNext(T t) {

    }


    private void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
            mHandler = null;
            mDialog = null;
        }
    }
}
