package cn.ichengxi.fang.business.main.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;
import java.util.List;

import chengxinet.chengxilibs.widget.MySimpleDraweeView;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.list.ListHouseActivity;
import cn.ichengxi.fang.business.location.LocationActivity;
import cn.ichengxi.fang.frame.base.BaseFrameFragment;
import cn.ichengxi.fang.view.HomeCoordinatorLayout;

/**
 * Created by quan on 16/11/4.
 */

public class HomeFragment extends BaseFrameFragment {

    private ConvenientBanner<String> mBanner;

    private HomeCoordinatorLayout mHomeCoordinatorLayout;

    @Override
    public void onSetView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
    }

    @Override
    public void initView() {
        super.initView();

        findViewByIdToView(R.id.home_search).setOnClickListener(this);
        findViewByIdToView(R.id.home_location).setOnClickListener(this);



        mBanner = findViewByIdToView(R.id.home_banner);

        mHomeCoordinatorLayout = findViewByIdToView(R.id.home_container);
    }

    @Override
    public void initListener() {
        super.initListener();

        mHomeCoordinatorLayout.setOnScrollListener(new HomeCoordinatorLayout.OnScrollListener() {
            @Override
            public void onStart() {
                mBanner.stopTurning();
            }

            @Override
            public void onStop() {
                mBanner.startTurning(8000);
            }
        });
    }

    @Override
    public void initLoad() {
        super.initLoad();

        List<String> colors = new ArrayList<>();
        colors.add("http://pic.90sjimg.com/design/00/23/04/59/561768662e08b.jpg");
        colors.add("http://pic.90sjimg.com/design/00/23/04/59/561768662e08b.jpg");
        colors.add("http://pic.90sjimg.com/design/00/23/04/59/561768662e08b.jpg");
        colors.add("http://pic.90sjimg.com/design/00/23/04/59/561768662e08b.jpg");
        colors.add("http://pic.90sjimg.com/design/00/23/04/59/561768662e08b.jpg");
        colors.add("http://pic.90sjimg.com/design/00/23/04/59/561768662e08b.jpg");

        mBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, colors)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.banner_dot, R.drawable.banner_dot_focus});

        mBanner.setScrollDuration(1000);
        mBanner.startTurning(8000);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.home_search:
                openActivity(ListHouseActivity.class);
                break;
            case R.id.home_location:
                openActivity(LocationActivity.class);
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    public class LocalImageHolderView implements Holder<String> {
        private View v;
        @Override
        public View createView(Context context) {

            v = View.inflate(context, R.layout.common_banner, null);
            return v;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            MySimpleDraweeView imageView = (MySimpleDraweeView) v.findViewWithTag("img");
            imageView.setImageURI(Uri.parse(data));
        }
    }



}
