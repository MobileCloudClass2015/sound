package com.sound.app.util;

/**
 * Created by Francis on 2015-06-10.
 */
public class Content {

    private int icon;

    private String text;

    public Content(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
