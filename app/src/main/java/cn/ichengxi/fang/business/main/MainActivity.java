package cn.ichengxi.fang.business.main;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.main.Message.MessageFragment;
import cn.ichengxi.fang.business.main.home.HomeFragment;
import cn.ichengxi.fang.business.main.personal.PersonalFragment;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * Created by quan on 16/11/4.
 */

public class MainActivity extends BaseFrameActivity {

    @Bind(android.R.id.content)
    ViewGroup decorView;

    private static final int[] ICO = {
            R.mipmap.ico_home, R.mipmap.ico_message, R.mipmap.ico_personal
    };
    private static final int[] ICO_FOCUS = {
            R.mipmap.ico_home_focus, R.mipmap.ico_message_focus, R.mipmap.ico_personal_focus
    };

    private View view;
    private TextView[] mTabs;
    private Fragment[] mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | params.flags);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setFrameId(R.id.main_frame_contain);
        view = new View(this);
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayout mTabContain = findViewByIdToView(R.id.main_tab_contain);
        int tabCount = mTabContain.getChildCount();
        mTabs = new TextView[tabCount];
        for (int i = 0; i < tabCount; i++) {
            mTabs[i] = (TextView) mTabContain.getChildAt(i);
            mTabs[i].setTag(i);
            mTabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int j = 0; j < mTabs.length; j++) {
                        TextView tab = mTabs[j];
                        if (tab == view) {
                            TextViewCompat.setTextAppearance(tab, R.style.MainTabFocus);
                            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(tab, 0, ICO_FOCUS[j], 0, 0);
                            int tag = (int) tab.getTag();
                            toFragment(mFragment[tag]);
                            switchState(mFragment[tag]);
                        } else {
                            TextViewCompat.setTextAppearance(tab, R.style.MainTab);
                            TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(tab, 0, ICO[j], 0, 0);
                        }
                    }
                }
            });

            System.out.println();
        }

        mFragment = new Fragment[]{
                new HomeFragment(),
                new MessageFragment(),
                new PersonalFragment()
        };
    }

    @Override
    public void initLoad() {
        super.initLoad();
        setCurrFragment(mFragment[0]);
        toFragment(mFragment[0]);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    private void switchState(Fragment fragment) {
        if (fragment instanceof HomeFragment) {
            setStatusBarColor(android.R.color.transparent);
        } else if (fragment instanceof MessageFragment) {
            setStatusBarColor(R.color.green1);
        } else if (fragment instanceof PersonalFragment) {
            setStatusBarColor(android.R.color.transparent);
        }
    }

    private void setStatusBarColor(int res) {
        //状态栏着色
        decorView.removeView(view);
        view.setBackgroundColor(getResources().getColor(res));
        decorView.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
    }

    public int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}
