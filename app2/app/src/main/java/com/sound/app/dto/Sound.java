package com.sound.app.dto;

/**
 * Created by Francis on 2015-06-04.
 */
public class Sound {
    
    private Integer pn;
    
    private String id;
    
    private String title;
    
    private String artist;
    
    private Double latitude;
    
    private Double longitude;
    
    private String weatherMain;
    
    private String weatherDescription;
    
    private String timeStamp;

    public Sound() {

    }

    public Sound(String id, String title, String artist, Double latitude, Double longitude, String weatherMain, String weatherDescription) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    @Override
    public String toString() {
        return "Sound{" +
                "pn=" + pn +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", weatherMain='" + weatherMain + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
