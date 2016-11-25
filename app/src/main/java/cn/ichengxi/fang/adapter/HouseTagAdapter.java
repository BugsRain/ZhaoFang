package cn.ichengxi.fang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cn.ichengxi.fang.R;
import cn.ichengxi.fang.entity.HouseTag;
import cn.ichengxi.fang.view.flow.TagAdapter;

/**
 * author：created by Snail.江
 * time: 11/22/2016 16:19
 * email：409962004@qq.com
 * TODO:
 */
public class HouseTagAdapter extends TagAdapter<HouseTag> {

    private List<HouseTag> mData;
    private Context context;

    public HouseTagAdapter(Context context, List<HouseTag> mData) {
        super(mData);
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(View convertView, int position, final HouseTag houseTag) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_flow_text, null);

        textView.setText(houseTag.getName());
        textView.setTextColor(Color.parseColor(houseTag.getColor()));

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(5);
        drawable.setStroke(1, Color.parseColor(houseTag.getColor()));
        textView.setBackgroundDrawable(drawable);
//        textView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    GradientDrawable drawable = new GradientDrawable();
//                    drawable.setCornerRadius(5);
//                    drawable.setColor(Color.parseColor(houseTag.getColor()));
//                    v.setBackgroundDrawable(drawable);
//                    ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
//                }
//                return false;
//            }
//        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable drawable = new GradientDrawable();
                if (houseTag.isSelect()) {
                    houseTag.setSelect(false);
                    drawable.setCornerRadius(5);
                    drawable.setStroke(1, Color.parseColor(houseTag.getColor()));
                    v.setBackgroundDrawable(drawable);
                    ((TextView) v).setTextColor(Color.parseColor(houseTag.getColor()));
                } else {
                    houseTag.setSelect(true);
                    drawable.setCornerRadius(5);
                    drawable.setColor(Color.parseColor(houseTag.getColor()));
                    v.setBackgroundDrawable(drawable);
                    ((TextView) v).setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        return textView;
    }

}
