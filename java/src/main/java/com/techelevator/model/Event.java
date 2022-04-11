package com.techelevator.model;

public class Event {

    private Long eventId;
    private String eventName;
    private String information;


    public Long getEventId() {return eventId;}
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {return eventName;}
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getInformation() {return information;}
    public void setInformation(String information) {
        this.information = information;
    }



    //todo also represent the photo at some point


}
