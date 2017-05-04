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


        depList.add(new Departure("60","Masthugget", "BUS", "C", new String[]{"11:18", "11:28", "11:38", "11:48", "11:58", "12:08"}, "#ffffff", "#00b6f1", true));
        depList.add(new Departure("62","Skår", "BUS", "E", new String[]{"11:21", "11:51", "12:21", "12:51", "13:21", "13:51"}, "#ffffff", "#00b6f1", true));
        depList.add(new Departure("11","Saltholmen", "TRAM", "B", new String[]{"11:16", "11:21", "11:26", "11:31", "11:36", "11:41"}, "#ffffff", "#000000", false));
        depList.add(new Departure("6","Länsmansgården", "TRAM", "C", new String[]{"11:15", "11:20", "11:25", "11:30", "11:35", "11:40"}, "#00394d", "#fa8719", false));
        depList.add(new Departure("6","Kortedala", "TRAM", "E", new String[]{"11:17", "11:22", "11:27", "11:32", "11:37", "11:42"}, "#00394d", "#fa8719", false));
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
