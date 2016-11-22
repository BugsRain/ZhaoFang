package cn.ichengxi.fang.view.popup;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;

/**
 * Created by quan on 16/11/15.
 */

public class TypePopup extends BasePopup {

    private List<String> data;
    private Context context;
    public TypePopup(BaseImplCompat compat) {
        super(compat);
        context = compat.getContext();

        data = new ArrayList<>();
        data.add("不限");
        data.add("一房");
        data.add("二房");
        data.add("三房");
        data.add("四房");
        data.add("五房及以上");


        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_type, null, false);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_popup, parent, false));
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.txt.setText(data.get(position));
            }

            @Override
            public int getItemCount() {
                return data.size();
            }
        });

        setContentView(contentView);
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt;
        public ViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.popup_item);
        }
    }
}
