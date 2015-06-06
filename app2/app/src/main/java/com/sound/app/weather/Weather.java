package com.sound.app.weather;

/**
 * Created by Francis on 2015-06-07.
 */
public class Weather {

    private String main;

    private String description;

    private String icon;

    public Weather() {
    }

    public Weather(String main, String description, String icon) {
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
