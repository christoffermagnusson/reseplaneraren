package com.example.reseplaneraren2.data.interfaces;

import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-03.
 */

public interface IStopLocationHandler {

    public void receiveStopLocation(ArrayList<StopLocation> stopLocList);

    public void receiveStopLocationError(String message);


}
