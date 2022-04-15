package com.techelevator.model;

public class Event {

    private Long id;
    private Long userId;
    private Long hostId;
    private String name;
    private String information;


    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {return information;}
    public void setInformation(String information) {
        this.information = information;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHostId() {
        return hostId;
    }
    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    //todo also represent the photo at some point


}
