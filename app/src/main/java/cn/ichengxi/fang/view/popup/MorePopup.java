package cn.ichengxi.fang.view.popup;

import android.view.LayoutInflater;
import android.view.View;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.view.MyGridView;
import cn.ichengxi.fang.view.MyGridViewAdapter;

/**
 * Created by quan on 16/11/21.
 */

public class MorePopup extends BasePopup {

    private final static String[] Area =
            {"50m²以下", "50m²-70m²","70m²-90m²","90m²-110m²","110m²-130m²","130m²-150m²","150m²以上"};

    private final static String[] Direction =
            {"东", "南","西","北","东北","东南","西南","西北", "东西通透", "南北通透"};

    private final static String[] PropertyType =
            {"住宅", "商用"};

    private final static String[] HouseType =
            {"公寓", "别墅"};

    private final static String[] Decoration =
            {"毛坯", "简装","中装","精装","豪装"};

    private final static String[] Age =
            {"2年以下", "2-5年","5-10年","10年以上"};



    public MorePopup(BaseImplCompat compat) {
        super(compat);

        View contentView = LayoutInflater.from(compat.getContext()).inflate(R.layout.popup_more, null, false);
        MyGridView area = (MyGridView) contentView.findViewById(R.id.popup_area);
        MyGridView direction = (MyGridView) contentView.findViewById(R.id.popup_direction);
        MyGridView propertyType = (MyGridView) contentView.findViewById(R.id.popup_property_type);
        MyGridView houseType = (MyGridView) contentView.findViewById(R.id.popup_house_type);
        MyGridView decoration = (MyGridView) contentView.findViewById(R.id.popup_decoration);
        MyGridView age = (MyGridView) contentView.findViewById(R.id.popup_house_age);

        area.setAdapter(new MyGridViewAdapter() {
            @Override
            public int getCount() {
                return Area.length;
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.btn_item_selector;
            }

            @Override
            public String getContent(int position) {
                return Area[position];
            }


        });

        direction.setAdapter(new MyGridViewAdapter() {
            @Override
            public int getCount() {
                return Direction.length;
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.btn_item_selector;
            }

            @Override
            public String getContent(int position) {
                return Direction[position];
            }

        });

        propertyType.setAdapter(new MyGridViewAdapter() {
            @Override
            public int getCount() {
                return PropertyType.length;
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.btn_item_selector;
            }


            @Override
            public String getContent(int position) {
                return PropertyType[position];
            }

        });

        houseType.setAdapter(new MyGridViewAdapter() {
            @Override
            public int getCount() {
                return HouseType.length;
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.btn_item_selector;
            }

            @Override
            public String getContent(int position) {
                return HouseType[position];
            }

        });

        decoration.setAdapter(new MyGridViewAdapter() {
            @Override
            public int getCount() {
                return Decoration.length;
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.btn_item_selector;
            }

            @Override
            public String getContent(int position) {
                return Decoration[position];
            }
        });

        age.setAdapter(new MyGridViewAdapter() {
            @Override
            public int getCount() {
                return Age.length;
            }

            @Override
            public int getBackground(int position) {
                return R.drawable.btn_item_selector;
            }

            @Override
            public String getContent(int position) {
                return Age[position];
            }

        });

        setContentView(contentView);
    }
}
