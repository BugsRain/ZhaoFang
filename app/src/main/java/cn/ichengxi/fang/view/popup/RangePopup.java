package cn.ichengxi.fang.view.popup;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.decoration.ItemLine2;
import cn.ichengxi.fang.view.popup.provider.PriceRangeFooterProvider;
import cn.ichengxi.fang.view.popup.provider.PriceProvider;
import me.bugsrain.library.adapter.base.BaseRecyclerAdapter;
import me.bugsrain.library.adapter.base.Section;

/**
 * Created by quan on 16/11/15.
 */

public class RangePopup extends BasePopup {
    private List<String> data;

    public RangePopup(BaseImplCompat compat) {
        super(compat);
        data = new ArrayList<>();
        data.add("不限");
        data.add("1000以下");
        data.add("1000 - 2000元");
        data.add("2000 - 3000元");
        data.add("3000 - 4000元");
        data.add("4000 - 5000元");
        data.add("5000元以上");

        Context context = compat.getContext();
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_type, null, false);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.list);
        recyclerView.addItemDecoration(new ItemLine2(recyclerView.getContext(), R.drawable.item_line_location));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyAdapter(context, data));

        setContentView(contentView);
    }

    private class MyAdapter extends BaseRecyclerAdapter {

        MyAdapter(Context mContext, Object... objects) {
            super(mContext);
            init(objects);
        }

        @Override
        protected void sectionInit(Section section, Object data) {
            switch (section.getType()) {
                case 0:
                    section.setItemViewProvider(PriceProvider.class);
                    break;

                case 1:
                    section.setDefaultSingle(true);
                    section.setItemViewProvider(PriceRangeFooterProvider.class);
                    break;
            }
        }

        @Override
        public String initSectionFooterLeftContent(Section section) {
            if(section.getType() == 0){
                section.setFooterProvider(PriceRangeFooterProvider.class);
                return "footer";
            }
            return super.initSectionHeaderLeftContent(section);
        }
    }



}
