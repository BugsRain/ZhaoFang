package cn.ichengxi.fang.view.popup.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.view.RangeView;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;

/**
 * Created by quan on 16/11/15.
 */

public class PriceRangeFooterProvider extends ItemViewProvider<PriceRangeFooterProvider.ViewHolder, Object> {

    @NonNull
    @Override
    public PriceRangeFooterProvider.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent, int type) {
        return new PriceRangeFooterProvider.ViewHolder(inflater.inflate(R.layout.item_popup_footer, parent, false), type);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position, Section<Object> section) {
        holder.rangeView.setProgress(2000, 12000);
        holder.textView.setText(2000 + " - " + 12000);
        holder.rangeView.setOnComputeProgressListener(new RangeView.OnComputeProgressListener() {
            @Override
            public void onComputeProgress(int min, int max) {
                holder.textView.setText(min + " - " + max);
            }
        });
    }


    protected class ViewHolder extends BaseViewHolder {
        RangeView rangeView;
        TextView textView;

        protected ViewHolder(View itemView, int type) {
            super(itemView, type);
            rangeView = (RangeView) itemView.findViewById(R.id.rangeView);
            textView = (TextView) itemView.findViewById(R.id.textView2);
        }
    }

}
