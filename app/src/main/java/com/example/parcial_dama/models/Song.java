package com.example.parcial_dama.models;

public class Song {
    private String name;
    private String artist;
    private String album;
    private int imageResource;
    private int audioResource;

    public Song(String name, String artist, String album, int imageResource, int audioResource) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.imageResource = imageResource;
        this.audioResource = audioResource;
    }

    public String getAlbum() {
        return album;
    }
    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getAudioResource() {
        return audioResource;
    }
}
