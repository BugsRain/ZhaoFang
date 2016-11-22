package cn.ichengxi.fang.entity;

/**
 * author：created by Snail.江
 * time: 11/22/2016 16:17
 * email：409962004@qq.com
 * TODO:
 */
public class HouseTag {

    private String name;
    private String color;
    private boolean isSelect = false;

    public HouseTag(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
