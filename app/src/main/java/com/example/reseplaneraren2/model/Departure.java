package com.example.reseplaneraren2.model;

import android.graphics.Color;

import java.sql.Time;

/**
 * Created by christoffer on 2017-05-02.
 */

public class Departure {

    private String name;
    private String type;
    private Time departureTime;
    private Color backgroundColor;
    private Color foregroundColor;
    // private boolean handicappedAccessability;

    public Departure(String name, String type){
        this.name=name;
        this.type=type;
    }



}
