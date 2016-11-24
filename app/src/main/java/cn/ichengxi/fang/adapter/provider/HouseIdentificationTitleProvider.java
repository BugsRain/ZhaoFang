package cn.ichengxi.fang.adapter.provider;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.business.report.ReportActivity;
import me.bugsrain.library.adapter.base.Section;
import me.bugsrain.library.adapter.holder.BaseViewHolder;
import me.bugsrain.library.adapter.provider.base.ItemViewProvider;

/**
 * Created by quan on 16/11/7.
 */

public class HouseIdentificationTitleProvider extends ItemViewProvider<HouseIdentificationTitleProvider.ViewHolder, Object> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(layoutInflater.inflate(R.layout.common_house_identification_title, viewGroup, false), i);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, Section<Object> section) {
    }

    protected class ViewHolder extends BaseViewHolder {

        ViewHolder(View itemView, int type) {
            super(itemView, type);
            TextView view = (TextView) itemView.findViewById(R.id.house_identification_report_txt);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //举报房源
//                    ReportActivity.class
                    v.getContext().startActivity(new Intent(v.getContext(), ReportActivity.class));
                }
            });
        }
    }
}
