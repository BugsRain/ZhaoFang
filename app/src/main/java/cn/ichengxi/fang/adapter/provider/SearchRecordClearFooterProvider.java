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

public class SearchRecordClearFooterProvider extends ItemViewProvider<SearchRecordClearFooterProvider.ViewHolder, Object> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ViewHolder(inflater.inflate(R.layout.common_search_clear, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, Section<Object> section) {

    }

    protected class ViewHolder extends BaseViewHolder{
        View clear;
        protected ViewHolder(View itemView, int type) {
            super(itemView, type);
        }
    }
}
