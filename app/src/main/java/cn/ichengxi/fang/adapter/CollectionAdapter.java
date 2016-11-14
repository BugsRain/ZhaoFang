package cn.ichengxi.fang.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.holder.CollectViewHolder;

/**
 * author：created by Snail.江
 * time: 11/14/2016 13:34
 * email：409962004@qq.com
 * TODO:
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectViewHolder> {

    private BaseImplCompat mCompat;

    public CollectionAdapter(BaseImplCompat compat) {
        this.mCompat = compat;
    }

    @Override
    public CollectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CollectViewHolder(LayoutInflater.from(mCompat.getContext()).inflate(R.layout.item_collect, parent, false));
    }

    @Override
    public void onBindViewHolder(CollectViewHolder holder, int position) {
        holder.image.setImageURI(Uri.parse("res:///" + R.mipmap.home_bg));
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
