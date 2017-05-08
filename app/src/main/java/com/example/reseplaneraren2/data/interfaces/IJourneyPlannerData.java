package com.example.reseplaneraren2.data.interfaces;

import com.example.reseplaneraren2.model.StopLocation;

import java.util.Calendar;

/**
 * Created by christoffer on 2017-05-03.
 */

public interface IJourneyPlannerData {

    public void getStopLocationByName(UIStopLocationHandler handler, String request);

    public void getDepartureBoard(UIDepartureBoardHandler handler, Calendar calendar, StopLocation location);
}
