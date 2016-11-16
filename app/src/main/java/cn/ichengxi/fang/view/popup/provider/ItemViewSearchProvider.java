package cn.ichengxi.fang.view.popup.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.ichengxi.fang.R;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;

/**
 * Created by quan on 16/11/15.
 */

public class ItemViewSearchProvider extends ItemViewProvider<ItemViewSearchProvider.ViewHolder, List<String>> {

    @NonNull
    @Override
    public ItemViewSearchProvider.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new ItemViewSearchProvider.ViewHolder(inflater.inflate(R.layout.item_popup, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, Section<List<String>> section) {
        holder.txt.setText(section.getData().get(position));

    }


    public class ViewHolder extends BaseViewHolder {
        TextView txt;

        protected ViewHolder(View itemView, int type) {
            super(itemView, type);
            txt = (TextView) itemView.findViewById(R.id.search_item);
        }
    }

}
