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
 * Created by quan on 16/11/22.
 */

public class SearchTitleProvider extends ItemViewProvider {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ViewHolder(inflater.inflate(R.layout.common_search_title, parent, false),type);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, Section section) {

    }

    private class ViewHolder extends BaseViewHolder{

        protected ViewHolder(View itemView, int type) {
            super(itemView, type);
        }
    }
}
