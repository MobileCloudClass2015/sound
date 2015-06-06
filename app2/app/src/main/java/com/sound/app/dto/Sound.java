package com.sound.app.dto;

/**
 * Created by Francis on 2015-06-04.
 */
public class Sound {
    
    private Integer pn;
    
    private String id;
    
    private String title;
    
    private String artist;
    
    private String timeStamp;

    public Sound() {
    }

    public Sound(String id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
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

    @Override
    public String toString() {
        return "Sound{" +
                "pn=" + pn +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
