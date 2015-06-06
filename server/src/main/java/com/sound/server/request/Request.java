package com.sound.server.request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Francis on 2015-06-06.
 */
public class Request {
    
    private String artist;
    
    private String title;
    
    private Integer start;
    
    private Integer count;

    public Request() {
    }
    
    public JSONObject getJSONObject() {
        JSONObject parameter = new JSONObject();
        try {
            parameter.put("artist", this.artist == null ? "GOD" : this.artist);
            parameter.put("title", this.title == null ? "" : this.title);
            parameter.put("start", this.start == null ? 0 : this.start);
            parameter.put("count", this.count == null? 10 : this.count);
        } catch (JSONException e) {
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
        return "Request{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", count=" + count +
                '}';
    }
}
