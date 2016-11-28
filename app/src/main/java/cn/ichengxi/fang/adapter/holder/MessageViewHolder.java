package cn.ichengxi.fang.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import chengxinet.chengxilibs.widget.MySimpleDraweeView;
import cn.ichengxi.fang.R;

/**
 * author：created by Snail.江
 * time: 11/24/2016 11:35
 * email：409962004@qq.com
 * TODO:
 */
public class MessageViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout layout;
    public MySimpleDraweeView avatar;
    public ImageView delete;
    public TextView name;
    public TextView content;
    public TextView time;

    public MessageViewHolder(View itemView) {
        super(itemView);

        layout = (RelativeLayout) itemView.findViewById(R.id.item_message_layout);
        avatar = (MySimpleDraweeView) itemView.findViewById(R.id.item_message_avatar);
        delete = (ImageView) itemView.findViewById(R.id.item_message_delete);
        name = (TextView) itemView.findViewById(R.id.item_message_name);
        content = (TextView) itemView.findViewById(R.id.item_message_content);
        time = (TextView) itemView.findViewById(R.id.item_message_time);
    }
}
