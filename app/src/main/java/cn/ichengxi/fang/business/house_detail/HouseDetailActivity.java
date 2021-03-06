package cn.ichengxi.fang.business.house_detail;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import chengxinet.chengxilibs.utils.MyLog;
import cn.ichengxi.fang.MyApplication;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.HouseAdapter;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.BackgroundView;
import cn.ichengxi.fang.view.BaseRecyclerView;
import cn.ichengxi.fang.view.popup.BasePopup;
import cn.ichengxi.fang.view.popup.UserPopup;

/**
 * Created by quan on 16/11/7.
 */

public class HouseDetailActivity extends BaseFrameActivity implements BasePopup.OnPopupWindowListener{

    @Bind(R.id.house_online)
    TextView mHouseOnline;

    @Bind(R.id.house_content)
    BaseRecyclerView mContentView;

    @Bind(R.id.bg_contain)
    BackgroundView mBackgroundView;

    private View mStateBar;

    private UserPopup mUserPopup;

    private HouseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (MyApplication.isMoreKitkat()) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {

        if (MyApplication.isMoreKitkat()) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_house_detail, null);
            mStateBar = new View(this);
            mStateBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyApplication.getStatusBarHeight()));
            mStateBar.setBackgroundColor(ContextCompat.getColor(this, R.color.green1));
            ViewCompat.setAlpha(mStateBar, 0);
            viewGroup.addView(mStateBar);
            View actionBar = viewGroup.findViewById(R.id.action_bar_layout);
            FrameLayout.LayoutParams p = (FrameLayout.LayoutParams) actionBar.getLayoutParams();
            p.setMargins(0, MyApplication.getStatusBarHeight(), 0, 0);
            actionBar.setLayoutParams(p);
            setContentView(viewGroup);
        } else {
            setContentView(R.layout.activity_house_detail);
        }
    }

    @Override
    public void initView() {
        super.initView();
        setFunctionView(getLeft(), null, R.mipmap.ico_back);
        setFunctionView(getRight(), null, R.mipmap.ico_share);
        ViewCompat.setAlpha(getActionBarBg(), 0);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        mUserPopup = new UserPopup(this);

        mAdapter = new HouseAdapter(this, new Object(), list.subList(0, 3), new Object(), list);

        mContentView.setLayoutManager(new LinearLayoutManager(this));

        mContentView.setAdapter(mAdapter);

        mBackgroundView.loading();

        mBackgroundView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBackgroundView.success("加载...");
            }
        }, 1000);

    }

    @Override
    public void initListener() {
        super.initListener();

        mHouseOnline.setOnClickListener(this);
        mUserPopup.setListener(this);
        mContentView.setCallBack(new BaseRecyclerView.OnScrollYChangeCallBack() {
            @Override
            public void onScrollYChange(int d) {
                MyLog.d("TAG", "onScrollYChange() called with:  mContentView.getChildAt(0).getMeasuredHeight() = ["
                        + mContentView.getChildAt(0).getMeasuredHeight() + "]");


                float p = 1.0f * d / (mContentView.getChildAt(0).getMeasuredHeight() / 2);
                p = Math.max(Math.min(p, 1), 0);

                MyLog.d("TAG", "onScrollYChange() called with:  p = [" + p + "]" + ", d = " + d +
                        ", mContentView.getChildAt(0).getMeasuredHeight() / 10 = " +
                        (mContentView.getChildAt(0).getMeasuredHeight() / 10));
                ViewCompat.setAlpha(getActionBarBg(), p);
                if (mStateBar != null) ViewCompat.setAlpha(mStateBar, p);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.actionBar_left_txt:
                finish();
                break;

            case R.id.house_online:
                mUserPopup.showAtLocation(v, Gravity.CENTER, 0, 0);
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void show(PopupWindow window) {
        mBackgroundView.post(new Runnable() {
            @Override
            public void run() {
                mBackgroundView.setBackgroundColor(Color.BLACK);
            }
        });
        mBackgroundView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.setAlpha(mContentView, 0.8f);
            }
        }, 50);
    }

    @Override
    public void dismiss(PopupWindow window) {
        mBackgroundView.setBackgroundColor(Color.WHITE);
        ViewCompat.setAlpha(mContentView, 1f);
    }
}
