package com.sound.server.request;

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
    
    public JSONObject getJSONObject() {
        JSONObject parameter = new JSONObject();
        try {
            parameter.put("artist", this.artist == null ? "GOD" : URLEncoder.encode(this.artist, "utf-8"));
            parameter.put("title", this.title == null ? "" : URLEncoder.encode(this.title, "utf-8"));
            parameter.put("start", this.start == null ? 0 : this.start);
            parameter.put("count", this.count == null? 10 : this.count);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return parameter;
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
