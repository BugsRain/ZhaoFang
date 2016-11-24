package cn.ichengxi.fang.adapter;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.adapter.provider.HouseHeaderProvider;
import cn.ichengxi.fang.adapter.provider.HouseHostProvider;
import cn.ichengxi.fang.adapter.provider.HouseIdentificationTitleProvider;
import cn.ichengxi.fang.adapter.provider.HouseMapProvider;
import cn.ichengxi.fang.adapter.provider.HouseRecommendProvider;
import cn.ichengxi.fang.adapter.provider.HouseTitleIProvider;
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
            section.setHeaderProvider(HouseTitleIProvider.class);
            return "更多推荐";
        } else if (section.getType() == 1) {
            section.setHeaderProvider(HouseIdentificationTitleProvider.class);
            return "房源已认证";
        }
        return super.initSectionHeaderLeftContent(section);
    }

    @Override
    protected void sectionInit(Section section, Object o) {
        switch (section.getType()) {
            case 0:
                section.setDefaultSingle(true);
                section.setItemViewProvider(HouseHeaderProvider.class);
                break;

            case 1:
                section.setItemViewProvider(HouseHostProvider.class);
                break;
            case 2:
                section.setDefaultSingle(true);
                section.setItemViewProvider(HouseMapProvider.class);
                break;

            case 3:
                section.setItemViewProvider(HouseRecommendProvider.class);
                break;

        }
    }

}
