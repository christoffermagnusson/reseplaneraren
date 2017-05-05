package com.example.reseplaneraren2.controllers.nexttrip;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;
import com.example.reseplaneraren2.Util.Utils;
import com.example.reseplaneraren2.data.interfaces.IStopLocationHandler;
import com.example.reseplaneraren2.data.internal.JourneyPlannerFactory;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-02.
 */


public class NextTripController extends Fragment implements IStopLocationHandler {

    private ArrayList<StopLocation> mStopLocationsBySearch;
    AutoCompleteTextView autoCompleteTextView;
    private StopLocationAdapter searchAdapter;

    private ArrayList<StopLocation> mStopLocationsNearby;
    private StopLocationAdapter nearbyAdapter;
    private ListView nearbyList;

    private IStopLocationHandler locHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.next_trip_layout, container, false);


        autoCompleteTextView = (AutoCompleteTextView)v.findViewById(R.id.searchStopField);
        mStopLocationsBySearch = new ArrayList<StopLocation>();

        nearbyList = (ListView) v.findViewById(R.id.nearbyStopsList);
        mStopLocationsNearby = new ArrayList<StopLocation>();

        locHandler = this;

        setupTextListener();
        setupSearchClickListener();
        setupListClickListener();

        // testing coordinate function
        JourneyPlannerFactory.getJourneyPlanner().getStopLocationByCoordinate(locHandler,null);

        searchAdapter = new StopLocationAdapter(getContext(), R.layout.simple_list_item, mStopLocationsBySearch);
        autoCompleteTextView.setAdapter(searchAdapter);

        nearbyAdapter = new StopLocationAdapter(getContext(),R.layout.simple_list_item, mStopLocationsNearby);
        nearbyList.setAdapter(nearbyAdapter);
        ((MainActivity)getActivity()).changeTitle("Nästa tur");
        return v;
    }

    @Override
    public void receiveStopLocationBySearch(ArrayList<StopLocation> stopLocList) {
        Log.d("dunkDEBUG", "Received stuff");
        mStopLocationsBySearch.clear();
        mStopLocationsBySearch.addAll(stopLocList);
    }

    @Override
    public void receiveStopLocationByCoordinate(ArrayList<StopLocation> stopLocList){
        mStopLocationsNearby.clear();
        mStopLocationsNearby.addAll(stopLocList);
    }

    @Override
    public void receiveStopLocationError(String message) {

    }



    private void proceedToDepartureDisplay(StopLocation selectedLocation){
        MainActivity activity = (MainActivity) this.getActivity();

        activity.inflateDepartureDisplay(Screen.DEPARTURE_DISPLAY, selectedLocation);
    }

    private void setupTextListener() {
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 3) {
                    JourneyPlannerFactory.getJourneyPlanner().getStopLocationByName(locHandler, s.toString());
                    // Could implement something here to wait 0,75s~ between requests to reduce amount of requests
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupSearchClickListener() {
        final MainActivity activity = (MainActivity) this.getActivity();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                StopLocation stop = (StopLocation) parent.getItemAtPosition(pos);
                Utils.hideKeyboard(activity);
                proceedToDepartureDisplay(stop);
            }
        });
    }

    private void setupListClickListener() {

        nearbyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                StopLocation stop = (StopLocation) parent.getItemAtPosition(pos);
                proceedToDepartureDisplay(stop);
            }
        });
    }




}
