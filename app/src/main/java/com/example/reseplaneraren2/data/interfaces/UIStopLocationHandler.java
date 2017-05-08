package com.example.reseplaneraren2.data.interfaces;

import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-03.
 */

public interface UIStopLocationHandler {

    public void receiveStopLocationByName(ArrayList<StopLocation> stopLocList);

    public void receiveStopLocationByCoordinate(ArrayList<StopLocation> stopLocList);

    public void receiveStopLocationError(String message);


}
