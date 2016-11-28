package cn.ichengxi.fang.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chengxinet.chengxilibs.global.BaseImplCompat;
import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.holder.MessageViewHolder;

/**
 * author：created by Snail.江
 * time: 11/24/2016 11:32
 * email：409962004@qq.com
 * TODO:
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private BaseImplCompat mCompat;

    public MessageAdapter(BaseImplCompat compat) {
        this.mCompat = compat;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(LayoutInflater.from(mCompat.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        String avatar = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1479960963&di=e09eb40241fed4e067fda1062359ba96&src=http://p2.gexing.com/G1/M00/60/C2/rBACFFIF5tPCgzq3AADKe214SC4688.jpg";
        holder.avatar.setImageUrl(avatar);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mCompat.openActivity(HouseDetailActivity.class);
            }
        });
//        holder.name.setText();
//        holder.content.setText();
//        holder.time.setText();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
