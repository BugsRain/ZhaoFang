package cn.ichengxi.fang.business.main.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;

/**
 * Created by quan on 16/11/4.
 */

public class PersonalFragment extends BaseFrameFragment {

    @Bind(R.id.personal_collect_layout)
    LinearLayout mCollectLayout;

    @Bind(R.id.personal_house_layout)
    LinearLayout mHouseLayout;

    @Bind(R.id.personal_about_layout)
    LinearLayout mAboutLayout;

    @Bind(R.id.personal_feedback_layout)
    LinearLayout mFeedbackLayout;

    @Bind(R.id.personal_setting_layout)
    LinearLayout mSettingLayout;

    @Override
    public void onSetView(Bundle savedInstanceState) {
        setContentView(R.layout.fragement_personal);
    }

    @Override
    public void initView() {
        super.initView();
        mCollectLayout.setOnClickListener(this);
        mHouseLayout.setOnClickListener(this);
        mAboutLayout.setOnClickListener(this);
        mFeedbackLayout.setOnClickListener(this);
        mSettingLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.personal_collect_layout:
                break;

            case R.id.personal_house_layout:
                break;

            case R.id.personal_about_layout:
                break;

            case R.id.personal_feedback_layout:
                break;

            case R.id.personal_setting_layout:
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
