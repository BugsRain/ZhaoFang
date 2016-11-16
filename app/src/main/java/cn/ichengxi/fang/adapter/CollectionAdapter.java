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
        if (position % 2 == 0) {
            holder.image.setImageURI(Uri.parse("http://img3.cache.netease.com/photo/0007/2014-08-29/A4QDMDQ15LOC0007.jpg"));
        } else if (position % 3 == 0) {
            holder.image.setImageURI(Uri.parse("http://www.zjgzf.cn/vip/center/uploads/20140313140941011.jpg"));
        } else {
            holder.image.setImageURI(Uri.parse("http://pic.ihk.cn/first/ihk/2015/07/17/172432442.jpg"));
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

}
