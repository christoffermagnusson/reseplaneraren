package com.example.reseplaneraren2.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christoffer on 2017-05-02.
 */

public class Departure {

    private String name; // 5, eller ROSA
    private String destination;
    private String type;
    private String track;
    private List<String> departureTimes = new ArrayList<>();
    private String realTime;
    private String bgColor;
    private String fgColor;
    private String accessibility;

    public Departure(String name, String destination, String type, String departureTime, String bgColor, String fgColor){
        this.name=name;
        this.destination = destination;
        this.type=type;
        this.departureTimes.add(departureTime);
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

    public void addDepartureTime(String departureTime) {
        if (departureTimes.size() < 4) {
            this.departureTimes.add(departureTime);
        }
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

    public List<String> getDepartureTimes(){
        return this.departureTimes;
    }

    public String getRealTime() {
        return realTime;
    }

    public String getBgColor(){
        return this.bgColor;
    }

    public String getFgColor(){
        return this.fgColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departure departure = (Departure) o;

        if (!name.equals(departure.name)) return false;
        return destination.equals(departure.destination);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }
}
