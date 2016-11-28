package cn.ichengxi.fang.view.popup;

import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.view.wheel.LoopView;

/**
 * author：created by Snail.江
 * time: 11/28/2016 16:32
 * email：409962004@qq.com
 * TODO:
 */
public class HousePopup extends BasePopup {

    public HousePopup(final BaseImplCompat compat) {
        super(compat);

        View contentView = LayoutInflater.from(compat.getContext()).inflate(R.layout.popup_house, null, false);


        LoopView loopView1 = (LoopView) contentView.findViewById(R.id.loopView1);
        LoopView loopView2 = (LoopView) contentView.findViewById(R.id.loopView2);
        LoopView loopView3 = (LoopView) contentView.findViewById(R.id.loopView3);
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(String.valueOf(i));
        }
        //设置是否循环播放
        //loopView.setNotLoop();
        //滚动监听
//        loopView.setListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                Toast.makeText(compat.getContext(), list.get(index), Toast.LENGTH_SHORT).show();
//            }
//        });
        //设置原始数据
        loopView1.setItems(list);
        loopView2.setItems(list);
        loopView3.setItems(list);
        //设置初始位置
        loopView1.setInitPosition(2);
        loopView2.setInitPosition(2);
        loopView3.setInitPosition(2);
        //设置字体大小
        loopView1.setTextSize(18);
        loopView2.setTextSize(18);
        loopView3.setTextSize(18);

        setContentView(contentView);
    }
}
