package cn.ichengxi.fang.view;

/**
 * Created by quan on 16/11/21.
 */

public abstract class MyGridViewAdapter {
    public abstract int getCount();

    public int getBackground(int position){
        return 0;
    }

    public Object getTag(int position){
        return null;
    };

    public abstract String getContent(int position);

    public int getTextColor(int position){
        return 0;
    };
}
