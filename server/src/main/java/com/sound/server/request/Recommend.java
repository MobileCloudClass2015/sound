package com.sound.server.request;

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

    public JSONObject getJSONObject() {
        JSONObject parameter = new JSONObject();
        try {
            // Default GOD - 바람 Youtube https://www.youtube.com/watch?v=QZvV313a3xc
            parameter.put("track_id", this.trackId == null ? "msNbcnXS38fP4HYI" : this.trackId);
            parameter.put("count", this.count == null? 10 : this.count);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parameter;
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
