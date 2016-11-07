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
public class ListHouseViewHolder extends RecyclerView.ViewHolder {
    public MySimpleDraweeView mHouseImg;
    public TextView mHouseDescTv, mHouseAddressTv, mHousePriceTv, mHouseAreaTv;

    public ListHouseViewHolder(View itemView) {
        super(itemView);
        mHouseImg = (MySimpleDraweeView) itemView.findViewById(R.id.house_img);
        mHouseDescTv = (TextView) itemView.findViewById(R.id.tv_house_desc);
        mHouseAddressTv = (TextView) itemView.findViewById(R.id.tv_house_address);
        mHousePriceTv = (TextView) itemView.findViewById(R.id.tv_house_price);
        mHouseAreaTv = (TextView) itemView.findViewById(R.id.tv_house_area);
    }
}
