package cn.ichengxi.fang.view.popup;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ichengxi.fang.R;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;

/**
 * Created by quan on 16/11/15.
 */

public class ItemViewSearchFooterProvider extends ItemViewProvider<ItemViewSearchFooterProvider.ViewHolder, Object> {

    @NonNull
    @Override
    public ItemViewSearchFooterProvider.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ItemViewSearchFooterProvider.ViewHolder(inflater.inflate(R.layout.item_popup_footer, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, Section<Object> section) {

    }


    public class ViewHolder extends BaseViewHolder {

        protected ViewHolder(View itemView, int type) {
            super(itemView, type);
        }
    }

}
