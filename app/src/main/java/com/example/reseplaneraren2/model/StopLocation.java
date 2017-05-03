package com.example.reseplaneraren2.model;

/**
 * Created by christoffer on 2017-05-02.
 */

public class StopLocation {

    public String name;
    private int stopId;


    public StopLocation(String name,int stopId){
            this.name = name;
            this.stopId = stopId;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
