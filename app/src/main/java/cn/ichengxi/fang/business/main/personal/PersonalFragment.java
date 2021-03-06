package cn.ichengxi.fang.business.main.personal;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.NestedScrollingParent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import chengxinet.chengxilibs.widget.MySimpleDraweeView;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.about.AboutActivity;
import cn.ichengxi.fang.business.calc.CalcActivity;
import cn.ichengxi.fang.business.collect.CollectionActivity;
import cn.ichengxi.fang.business.feedback.FeedbackActivity;
import cn.ichengxi.fang.business.login.LoginActivity;
import cn.ichengxi.fang.business.setting.SettingActivity;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class PersonalFragment extends BaseFrameFragment{

    @Bind(R.id.personal_background)
    MySimpleDraweeView mBackGroundView;

    @Bind(R.id.personal_collect_txt)
    TextView mCollectLayout;

    @Bind(R.id.personal_house_txt)
    TextView mHouseLayout;

    @Bind(R.id.personal_about_txt)
    TextView mAboutLayout;

    @Bind(R.id.personal_feedback_txt)
    TextView mFeedbackLayout;

    @Bind(R.id.personal_setting_txt)
    TextView mSettingLayout;

    @Bind(R.id.personal_username)
    TextView mUsernameTxt;


    @Override
    public void onSetView(Bundle savedInstanceState) {
        setContentView(R.layout.fragement_personal);
    }

    @Override
    public void initView() {
        super.initView();
        Uri bg = Uri.parse("res:///" + R.mipmap.home_bg);
        mBackGroundView.setImageURI(bg);
        mCollectLayout.setOnClickListener(this);
        mHouseLayout.setOnClickListener(this);
        mAboutLayout.setOnClickListener(this);
        mFeedbackLayout.setOnClickListener(this);
        mSettingLayout.setOnClickListener(this);
        mUsernameTxt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.personal_collect_txt:
                openActivity(CollectionActivity.class);
                break;

            case R.id.personal_house_txt:
                break;

            case R.id.personal_about_txt:
                openActivity(AboutActivity.class);
                break;

            case R.id.personal_feedback_txt:
                openActivity(FeedbackActivity.class);
                break;

            case R.id.personal_setting_txt:
                openActivity(SettingActivity.class);
                break;

            case R.id.personal_username:
                openActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

}
