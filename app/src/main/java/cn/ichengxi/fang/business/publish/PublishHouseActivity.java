package cn.ichengxi.fang.business.publish;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.HouseTagAdapter;
import cn.ichengxi.fang.entity.HouseTag;
import cn.ichengxi.fang.frame.base.BaseFrameActivity;
import cn.ichengxi.fang.view.flow.TagFlowLayout;
import cn.ichengxi.fang.view.popup.HousePopup;
import upload.utils.UploadPicHelper;
import upload.view.PictureUploadView;

/**
 * author：created by Snail.江
 * time: 11/21/2016 11:24
 * email：409962004@qq.com
 * TODO: 我要卖房
 */
public class PublishHouseActivity extends BaseFrameActivity implements PictureUploadView.UploadCallBack{

    @Bind(R.id.flowLayout)
    TagFlowLayout mFlowLayout;

    @Bind(R.id.pictureUploadView)
    PictureUploadView mPictureUploadView;

    @Bind(R.id.house_type_layout)
    LinearLayout mHouseTypeLayout;

    @Bind(R.id.house_type_txt)
    TextView mHouseTypeTxt;

    private Map<String, String> mUploadMap, mLocalMap;
    private HousePopup mHousePopup;

    @Override
    protected void onViewCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_house);
    }

    @Override
    public void initData() {
        super.initData();
        mUploadMap = new HashMap<>();
        mLocalMap = new HashMap<>();
        mHousePopup = new HousePopup(this);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle("我要卖房");
        setFunctionView(getLeft(), null, R.mipmap.ico_back);

        String[] mKeys = new String[]{"学位", "地铁口", "电梯", "满二", "免个税", "精装修"};
        String[] mColor = new String[]{"#FF6A51", "#B8857B", "#1A70D0", "#51CA76", "#B945E9", "#FF4040"};
        List<HouseTag> mData = new ArrayList<>();
        for (int i = 0; i < mKeys.length; i++) {
            mData.add(new HouseTag(mKeys[i], mColor[i]));
        }

        HouseTagAdapter mAdapter = new HouseTagAdapter(this, mData);
        mFlowLayout.setAdapter(mAdapter);

        mHouseTypeLayout.setOnClickListener(this);
        mPictureUploadView = (PictureUploadView) findViewById(R.id.pictureUploadView);
        mPictureUploadView.init(this, UploadPicHelper.UPLOAD, 3, false);
        mPictureUploadView.setShowMethod(PictureUploadView.POPUPWINDOW);
        mPictureUploadView.setUploadCallBack(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.actionBar_left_txt:
                finish();
                break;

            case R.id.house_type_layout:
                mHousePopup.showAtLocation(v,Gravity.BOTTOM,0,0);
//                mHouseTypeTxt.setText();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mPictureUploadView.setResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onAddCallback(String tag, String path) {
        mLocalMap.put(tag, path);
    }

    @Override
    public void onRemoveCallback(String tag) {
        mUploadMap.remove(tag);
        mLocalMap.remove(tag);
    }
}
