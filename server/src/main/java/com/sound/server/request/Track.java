package com.sound.server.request;

/**
 * Created by Francis on 2015-06-06.
 */
public class Track {

    private String trackId;
    
    private String artist;
    
    private String title;
    
    private String url;
    
    private Double score;

    public Track() {
    }

    public Track(String trackId, String artist, String title, String url) {
        this.trackId = trackId;
        this.artist = artist;
        this.title = title;
        this.url = url;
    }

    public Track(String trackId, String artist, String title, String url, Double score) {
        this.trackId = trackId;
        this.artist = artist;
        this.title = title;
        this.url = url;
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId='" + trackId + '\'' +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", score=" + score +
                '}';
    }
}
