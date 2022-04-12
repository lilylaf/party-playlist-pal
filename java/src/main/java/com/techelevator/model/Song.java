package com.techelevator.model;

public class Song {

    private Long id; //Long id
    private String artistName;
    private String name; //name
    private String featured;

    public Long getId(){
        return id;
    }
    public void setId(Long songId){
        this.id = id;
    }

    public String getArtistName(){
        return artistName;
    }
    public void setArtistName(Long artistId){
        this.artistName = artistName;
    }

    public String getName;
    public void setName(String songName) {
        this.name = name;
    }

    public String getFeatured(){
        return featured;
    }
    public void setFeatured(String  featured){
        this.featured = featured;
    }

}
