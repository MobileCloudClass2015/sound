package com.sound.app.dto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Francis on 2015-06-06.
 */
public class Search {
    
    private String artist;
    
    private String title;
    
    private Integer start;
    
    private Integer count;

    public Search() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Search{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", count=" + count +
                '}';
    }
}
