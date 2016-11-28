package cn.ichengxi.fang.business.report;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;

/**
 * author：created by Snail.江
 * time: 11/22/2016 16:56
 * email：409962004@qq.com
 * TODO: 举报房源页面
 */
public class ReportActivity extends BaseFrameActivity {

    @Bind(R.id.report_reason1)
    TextView mReportReason1;

    @Bind(R.id.report_reason2)
    TextView mReportReason2;

    @Bind(R.id.report_reason3)
    TextView mReportReason3;

    @Bind(R.id.report_reason4)
    TextView mReportReason4;

    @Bind(R.id.report_reason5)
    TextView mReportReason5;

    private boolean isReason1,isReason2,isReason3,isReason4,isReason5;
    private Drawable drawable;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_report);
    }

    @Override
    public void initData() {
        super.initData();
        drawable = getResources().getDrawable(R.mipmap.ico_hook);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("举报房源");
        setFunctionView(getLeft(),null,R.mipmap.ico_back);

        mReportReason1.setOnClickListener(this);
        mReportReason2.setOnClickListener(this);
        mReportReason3.setOnClickListener(this);
        mReportReason4.setOnClickListener(this);
        mReportReason5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.actionBar_left_txt:
                finish();
                break;

            case R.id.report_reason1:
                if (!isReason1) {
                    isReason1 = true;
                    mReportReason1.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mReportReason1.setCompoundDrawables(null,null,drawable,null);
                } else {
                    isReason1 = false;
                    mReportReason1.setTextColor(Color.parseColor("#333333"));
                    mReportReason1.setCompoundDrawables(null,null,null,null);
                }
                break;
            case R.id.report_reason2:
                if (!isReason2) {
                    isReason2 = true;
                    mReportReason2.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mReportReason2.setCompoundDrawables(null,null,drawable,null);
                } else {
                    isReason2 = false;
                    mReportReason2.setTextColor(Color.parseColor("#333333"));
                    mReportReason2.setCompoundDrawables(null,null,null,null);
                }
                break;
            case R.id.report_reason3:
                if (!isReason3) {
                    isReason3 = true;
                    mReportReason3.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mReportReason3.setCompoundDrawables(null,null,drawable,null);
                } else {
                    isReason3 = false;
                    mReportReason3.setTextColor(Color.parseColor("#333333"));
                    mReportReason3.setCompoundDrawables(null,null,null,null);
                }
                break;
            case R.id.report_reason4:
                if (!isReason4) {
                    isReason4 = true;
                    mReportReason4.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mReportReason4.setCompoundDrawables(null,null,drawable,null);
                } else {
                    isReason4 = false;
                    mReportReason4.setTextColor(Color.parseColor("#333333"));
                    mReportReason4.setCompoundDrawables(null,null,null,null);
                }
                break;
            case R.id.report_reason5:
                if (!isReason5) {
                    isReason5 = true;
                    mReportReason5.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mReportReason5.setCompoundDrawables(null,null,drawable,null);
                } else {
                    isReason5 = false;
                    mReportReason5.setTextColor(Color.parseColor("#333333"));
                    mReportReason5.setCompoundDrawables(null,null,null,null);
                }
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
