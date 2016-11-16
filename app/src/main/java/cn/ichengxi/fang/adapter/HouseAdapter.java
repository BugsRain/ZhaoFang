package cn.ichengxi.fang.adapter;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.adapter.provider.ItemViewHouseHeaderProvider;
import cn.ichengxi.fang.adapter.provider.ItemViewHouseHostProvider;
import cn.ichengxi.fang.adapter.provider.ItemViewHouseIdentificationTitleProvider;
import cn.ichengxi.fang.adapter.provider.ItemViewHouseMapProvider;
import cn.ichengxi.fang.adapter.provider.ItemViewHouseRecommendProvider;
import cn.ichengxi.fang.adapter.provider.ItemViewHouseTitleIProvider;
import me.bugsrain.library.adapter.base.BaseRecyclerAdapter;
import me.bugsrain.library.adapter.base.Section;


/**
 * Created by quan on 16/11/7.
 */

public class HouseAdapter extends BaseRecyclerAdapter {

    public HouseAdapter(BaseImplCompat compat, Object... data) {
        super(compat.getContext());
        init(data);
    }

    @Override
    public String initSectionHeaderLeftContent(Section section) {
        if (section.getType() == 3) {
            section.setHeaderProvider(ItemViewHouseTitleIProvider.class);
            return "更多推荐";
        } else if (section.getType() == 1) {
            section.setHeaderProvider(ItemViewHouseIdentificationTitleProvider.class);
            return "房源已认证";
        }
        return super.initSectionHeaderLeftContent(section);
    }

    @Override
    protected void sectionInit(Section section, Object o) {
        switch (section.getType()) {
            case 0:
                section.setData(o, true);
                section.setItemViewProvider(ItemViewHouseHeaderProvider.class);
                break;

            case 1:
                section.setData(o, false);
                section.setItemViewProvider(ItemViewHouseHostProvider.class);
                break;
            case 2:
                section.setData(o, true);
                section.setItemViewProvider(ItemViewHouseMapProvider.class);
                break;

            case 3:
                section.setData(o, false);
                section.setItemViewProvider(ItemViewHouseRecommendProvider.class);
                break;

        }
    }

}
