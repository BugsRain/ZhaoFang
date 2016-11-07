package cn.ichengxi.fang.business.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.main.Message.MessageFragment;
import cn.ichengxi.fang.business.main.home.HomeFragment;
import cn.ichengxi.fang.business.main.personal.PersonalFragment;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * Created by quan on 16/11/4.
 */

public class MainActivity extends BaseFrameActivity {

    private static final int[] ICO = {
            R.mipmap.ico_home, R.mipmap.ico_message, R.mipmap.ico_personal
    };
    private static final int[] ICO_FOCUS = {
            R.mipmap.ico_home_focus, R.mipmap.ico_message_focus, R.mipmap.ico_personal_focus
    };
    private TextView[] mTabs;
    private Fragment[] mFragment;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setFrameId(R.id.main_frame_contain);
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
}
