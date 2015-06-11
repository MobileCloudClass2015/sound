package com.sound.server.request;

import com.sound.server.sound.Sound;

import java.util.List;

/**
 * Created by Francis on 2015-06-06.
 */
public class MyPlayMap {
    
    private Boolean result;
    
    private List<Sound> sounds;

    private Track track;

    private List<Track> tracks;

    public MyPlayMap() {
    }
    public MyPlayMap(List<Sound> sounds) {
        this.sounds = sounds;
    }
    
    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    public void setSounds(List<Sound> sounds) {
        this.sounds = sounds;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "MyPlayMap{" +
                "result=" + result +
                ", sounds=" + sounds +
                ", track=" + track +
                ", tracks=" + tracks +
                '}';
    }
}
