package com.example.reseplaneraren2.controllers.nexttrip;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;
import com.example.reseplaneraren2.data.interfaces.IStopLocationHandler;
import com.example.reseplaneraren2.data.internal.JourneyPlannerFactory;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-02.
 */


public class NextTripController extends Fragment implements IStopLocationHandler {

    private ArrayList<StopLocation> mStopLocations;
    AutoCompleteTextView autoCompleteTextView;
    private StopLocationAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.next_trip_layout, container, false);



        autoCompleteTextView = (AutoCompleteTextView)v.findViewById(R.id.searchStopField);
        mStopLocations = new ArrayList<StopLocation>();

        JourneyPlannerFactory.getJourneyPlanner().getStopLocationByName(this, "abc");
        return v;
    }

    @Override
    public void receiveStopLocation(ArrayList<StopLocation> stopLocList) {
        mStopLocations.addAll(stopLocList);
        adapter = new StopLocationAdapter(getContext(), R.layout.next_trip_autocomplete, mStopLocations);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    public void receiveStopLocationError(String message) {

    }

    private void proceedToDepartureDisplay(StopLocation selectedLocation){
        MainActivity activity = (MainActivity) this.getActivity();

        activity.inflateDepartureDisplay(Screen.DEPARTURE_DISPLAY, selectedLocation);
    }


}
