package com.sound.app.dto;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Francis on 2015-06-06.
 */
public class Recommend {
    
    private String trackId;
    
    private Integer count;

    public Recommend() {
    }

    public Recommend(Integer count, String trackId) {
        this.count = count;
        this.trackId = trackId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "trackId='" + trackId + '\'' +
                ", count=" + count +
                '}';
    }
}
