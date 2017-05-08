package com.example.reseplaneraren2.model;

import android.graphics.Color;

/**
 * Created by christoffer on 2017-05-02.
 */

public class Departure {

    private String name; // 5, eller ROSA
    private String destination;
    private String type;
    private String track;
    private String[] departureTimes;
    private String realTime;
    private String bgColor;
    private String fgColor;
    private String accessibility;

    public Departure(String name, String destination, String type, String[] departureTimes, String bgColor, String fgColor){
        this.name=name;
        this.destination = destination;
        this.type=type;
        this.departureTimes = departureTimes;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public void setRealTime(String realTime) {
        this.realTime = realTime;
    }

    public String getName(){
        return this.name;
    }

    public String getDestination(){
        return this.destination;
    }

    public String getType(){
        return this.type;
    }

    public String getTrack(){
        return this.track;
    }

    public String[] getDepartureTimes(){
        return this.departureTimes;
    }

    public String getBgColor(){
        return this.bgColor;
    }

    public String getFgColor(){
        return this.fgColor;
    }



}
