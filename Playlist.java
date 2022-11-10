package jukebox1;

public class Playlist {
    private int playlistId;
    private String playlistName;
    private String trackType;
    private String trackName;
    private int Songid;
    private int podcastId;

    public int getPlaylistId() {
        return playlistId;
    }
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public String getTrackType() {
        return trackType;
    }
    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }
    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public int getSongid() {
        return Songid;
    }
    public void setSongid(int songid) {
        Songid = songid;
    }
    public int getPodcastId() {
        return podcastId;
    }
    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

}