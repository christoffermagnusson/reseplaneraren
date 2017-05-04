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
    private String bgColor;
    private String fgColor;
    private boolean accessibility;

    public Departure(String name, String destination, String type, String track, String[] departureTimes, String bgColor, String fgColor, boolean accessibility){
        this.name=name;
        this.type=type;
        this.track = track;
        this.departureTimes = departureTimes;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.accessibility = accessibility;
    }



}
