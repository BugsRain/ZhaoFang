package cn.ichengxi.fang.adapter.provider;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cn.ichengxi.fang.R;
import me.bugsrain.library.adapter.provider.base.ItemViewDefaultHeaderProvider;

/**
 * Created by quan on 16/11/7.
 */

public class ItemViewHouseTitleIProvider extends ItemViewDefaultHeaderProvider {

    @NonNull
    @Override
    public ItemViewDefaultHeaderProvider.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ViewHolder(inflater.inflate(R.layout.common_house_title, parent, false), type);
    }
}
