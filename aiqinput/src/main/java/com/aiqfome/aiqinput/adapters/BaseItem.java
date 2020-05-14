package com.aiqfome.aiqinput.adapters;

public class BaseItem<Type> {

    private Type object;

    private String title;
    private String subTitle;

    public BaseItem(Type object, String title, String subTitle) {
        this.object = object;
        this.title = title;
        this.subTitle = subTitle;
    }

    public BaseItem(Type object, String title) {
        this.object = object;
        this.title = title;
    }

    public Type getObject() {
        return object;
    }

    public String getTitle() {
        return title;
    }


    public String getSubTitle() {
        return subTitle != null ? subTitle : "";
    }
}
