package com.example.reseplaneraren2.data.interfaces;

import com.example.reseplaneraren2.model.StopLocation;

import java.util.Calendar;

/**
 * Created by christoffer on 2017-05-03.
 */

public interface IJourneyPlannerData {

    public void getStopLocationByName(IStopLocationHandler handler, String request);

    public void getDepartureBoard(IDepartureHandler handler, StopLocation location, Calendar calendar);
}
