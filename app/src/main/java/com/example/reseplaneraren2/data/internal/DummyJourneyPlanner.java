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

    ArrayList<StopLocation> stopListBySearch = new ArrayList<StopLocation>();
    ArrayList<StopLocation> stopListByCoordinate = new ArrayList<StopLocation>();
    ArrayList<Departure> depList = new ArrayList<Departure>();

    public DummyJourneyPlanner(){
        stopListBySearch.add(new StopLocation("Redbergsplatsen",1));
        stopListBySearch.add(new StopLocation("Hisingen",2));
        stopListBySearch.add(new StopLocation("Centralstationen",3));

        stopListByCoordinate.add(new StopLocation("Lindholmen",4));
        stopListByCoordinate.add(new StopLocation("Lindholmspiren",5));
        stopListByCoordinate.add(new StopLocation("Teknikgatan",6));


        depList.add(new Departure("60","Masthugget"));
        depList.add(new Departure("11","Saltholmen"));
        depList.add(new Departure("6","Länsmansgården"));
    }

    @Override
    public void getStopLocationByName(IStopLocationHandler handler, String request) {
        handler.receiveStopLocationBySearch(stopListBySearch);
    }

    @Override
    public void getStopLocationByCoordinate(IStopLocationHandler handler, String request){
        handler.receiveStopLocationByCoordinate(stopListByCoordinate);
    }

    @Override
    public void getDepartureBoard(IDepartureHandler handler, StopLocation location, Calendar calendar) {
        handler.receiveDeparture(depList);
    }
}
