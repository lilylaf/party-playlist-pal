package com.techelevator.model;

public class Song {

    private Long songId;
    private Long artistId;
    private String songName;
    //missing featured artist column from table

    public Long getSongId(){
        return songId;
    }
    public void setSongId(Long songId){
        this.songId = songId;
    }

    public Long getArtistId(){
        return artistId;
    }
    public void setArtistId(Long artistId){
        this.artistId = artistId;
    }

    public String getSongName;
    public void setSongName(String songName) {
        this.songName = songName;
    }

    //featured artist getter
    //featured artist setter

    //todo -> my table is missing featured_artist column
}
