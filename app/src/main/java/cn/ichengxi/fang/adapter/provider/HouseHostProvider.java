package cn.ichengxi.fang.adapter.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ichengxi.fang.R;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;

/**
 * Created by quan on 16/11/7.
 */

public class HouseHostProvider extends ItemViewProvider<HouseHostProvider.ViewHolder, Object> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(layoutInflater.inflate(R.layout.common_host, viewGroup, false), i);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, Section<Object> section) {

    }

    protected class ViewHolder extends BaseViewHolder {

        ViewHolder(View itemView, int type) {
            super(itemView, type);

        }
    }
}
