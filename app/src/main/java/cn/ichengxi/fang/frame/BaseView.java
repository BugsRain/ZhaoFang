package cn.ichengxi.fang.frame;

/**
 * Created by baixiaokang on 16/4/22.
 */
public interface  BaseView {
    void onRequestStart();
    void onRequestError(String msg);
    void onRequestEnd();
    void onInternetError();

}
