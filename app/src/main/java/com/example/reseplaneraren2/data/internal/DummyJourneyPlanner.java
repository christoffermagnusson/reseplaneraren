package com.example.reseplaneraren2.data.internal;

import com.example.reseplaneraren2.data.interfaces.IDepartureHandler;
import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;
import com.example.reseplaneraren2.data.interfaces.IStopLocationHandler;
import com.example.reseplaneraren2.model.Departure;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by christoffer on 2017-05-03.
 */

public class DummyJourneyPlanner implements IJourneyPlannerData {

    ArrayList<StopLocation> stopList = new ArrayList<StopLocation>();
    ArrayList<Departure> depList = new ArrayList<Departure>();

    public DummyJourneyPlanner(){
        stopList.add(new StopLocation("Redbergsplatsen",1));
        stopList.add(new StopLocation("Hisingen",2));
        stopList.add(new StopLocation("Centralstationen",3));

        depList.add(new Departure("60","Masthugget"));
        depList.add(new Departure("11","Saltholmen"));
        depList.add(new Departure("6","Länsmansgården"));
    }

    @Override
    public void getStopLocationByName(IStopLocationHandler handler, String request) {

    }

    @Override
    public void getDepartureBoard(IDepartureHandler handler, StopLocation location, Calendar calendar) {

    }
}
