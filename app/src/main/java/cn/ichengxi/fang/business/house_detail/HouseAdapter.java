package cn.ichengxi.fang.business.house_detail;

import chengxinet.chengxilibs.global.BaseImplCompat;
import me.bugsrain.library.adapter.base.BaseRecyclerAdapter;
import me.bugsrain.library.adapter.base.Section;


/**
 * Created by quan on 16/11/7.
 */

public class HouseAdapter extends BaseRecyclerAdapter {

    public HouseAdapter(BaseImplCompat compat, Object... data){
        super(compat.getContext());
        init(data);
    }

    @Override
    public String initSectionHeaderLeftContent(Section section) {
        if(section.getType() == 1){
            section.setHeaderProvider(HouseTitleIProvider.class);
            return "更多推荐";
        }
        return super.initSectionHeaderLeftContent(section);
    }

    @Override
    protected void sectionInit(Section section, Object o) {
        switch (section.getType()){
            case 0:
                section.setData(o, true);
                section.setItemViewProvider(ItemViewHouseHeaderProvider.class);
                break;
            case 1:
                section.setData(o, false);
                section.setItemViewProvider(ItemViewHouseRecommendProvider.class);
                break;
        }
    }

}