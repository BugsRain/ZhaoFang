package cn.ichengxi.fang.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.ichengxi.fang.adapter.provider.SearchRecordClearFooterProvider;
import cn.ichengxi.fang.adapter.provider.SearchRecordProvider;
import cn.ichengxi.fang.adapter.provider.SearchTitleProvider;
import me.bugsrain.library.adapter.base.BaseRecyclerAdapter;
import me.bugsrain.library.adapter.base.Section;

/**
 * Created by quan on 16/11/22.
 */

public class SearchRecordAdapter extends BaseRecyclerAdapter {

    public SearchRecordAdapter(Context mContext) {
        super(mContext);
        List<String> list = new ArrayList<>();
        list.add("1111111111111111111");
        list.add("2222222222222222222222");
        list.add("333333333333333333333333333");
        list.add("333333333333333333333333333");
        list.add("333333333333333333333333333");
        init(new Object[]{new Object(), list, new Object()});
    }

    @Override
    protected void sectionInit(Section section, Object data) {
        switch (section.getType()) {
            case 0:
                section.setDefaultSingle(true);
                section.setItemViewProvider(SearchTitleProvider.class);
                break;

            case 1:
                section.setItemViewProvider(SearchRecordProvider.class);
                break;

            case 2:
                section.setDefaultSingle(true);;
                section.setItemViewProvider(SearchRecordClearFooterProvider.class);
                break;
        }
    }


}
