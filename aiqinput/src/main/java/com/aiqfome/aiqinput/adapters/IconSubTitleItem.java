package com.aiqfome.aiqinput.adapters;

import android.graphics.drawable.Drawable;

public class IconSubTitleItem<Type> {

    private Type object;

    private String title;
    private Drawable icon;
    private String subTitle;

    public IconSubTitleItem(Type object, String title, Drawable icon, String subTitle) {
        this.object = object;
        this.title = title;
        this.icon = icon;
        this.subTitle = subTitle;
    }

    public IconSubTitleItem(Type object, String title, Drawable icon) {
        this.object = object;
        this.title = title;
        this.icon = icon;
    }

    public Type getObject() {
        return object;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getSubTitle() {
        return subTitle != null ? subTitle : "";
    }
}
