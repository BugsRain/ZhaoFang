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
package cn.ichengxi.fang.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.adapter.holder.ListHouseViewHolder;

/**
 * Created by Jun on 2016/11/7.
 */
public class ListHouseAdapter extends RecyclerView.Adapter<ListHouseViewHolder> {

    private Context mContext;

    public ListHouseAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ListHouseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListHouseViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_list_house, parent, false));
    }

    @Override
    public void onBindViewHolder(ListHouseViewHolder holder, int position) {
        holder.mHouseImg.setImageURI(Uri.parse("res://cn.ichengxi.fang/" + R.mipmap.home_bg));
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
