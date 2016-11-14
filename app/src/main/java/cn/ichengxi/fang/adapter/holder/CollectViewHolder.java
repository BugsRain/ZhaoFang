/*
 * Copyright 2016 Freelander
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ichengxi.fang.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import chengxinet.chengxilibs.widget.MySimpleDraweeView;
import cn.ichengxi.fang.R;

/**
 * Created by Jun on 2016/11/7.
 */
public class CollectViewHolder extends RecyclerView.ViewHolder {

    public MySimpleDraweeView image;
    public TextView title, describe, price;

    public CollectViewHolder(View itemView) {
        super(itemView);
        image = (MySimpleDraweeView) itemView.findViewById(R.id.item_image);
        title = (TextView) itemView.findViewById(R.id.item_title);
        describe = (TextView) itemView.findViewById(R.id.item_describe);
        price = (TextView) itemView.findViewById(R.id.item_price);
    }
}
