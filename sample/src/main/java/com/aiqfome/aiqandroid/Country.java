package com.aiqfome.aiqandroid;

import android.graphics.drawable.Drawable;

public class Country {

    private Drawable icon;
    private String idd;
    private String name;

    public Country(Drawable icon, String idd, String name) {
        this.icon = icon;
        this.idd = idd;
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getIdd() {
        return idd;
    }

    public String getName() {
        return name;
    }
}
