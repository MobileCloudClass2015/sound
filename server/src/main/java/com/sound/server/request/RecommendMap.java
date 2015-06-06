package com.sound.server.request;

import com.sound.server.request.Search;
import com.sound.server.request.Track;

import java.util.List;

/**
 * Created by Francis on 2015-06-06.
 */
public class RecommendMap {
    
    private Track track;
    
    private List<Track> tracks;

    public RecommendMap() {
    }

    public RecommendMap(List<Track> tracks, Track track) {
        this.tracks = tracks;
        this.track = track;
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
        return "RecommendMap{" +
                "track=" + track +
                ", tracks=" + tracks +
                '}';
    }
}
