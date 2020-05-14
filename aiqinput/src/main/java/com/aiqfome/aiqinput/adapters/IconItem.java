package com.aiqfome.aiqinput.adapters;

import android.graphics.drawable.Drawable;

public class IconItem<Type> extends BaseItem<Type>{

    private Drawable icon;

    public IconItem(Type object, String title, String subTitle, Drawable icon) {
        super(object, title, subTitle);
        this.icon = icon;
    }

    public IconItem(Type object, String title, Drawable icon) {
        super(object, title);
        this.icon = icon;
    }

    public Drawable getIcon() {
        return icon;
    }

}
