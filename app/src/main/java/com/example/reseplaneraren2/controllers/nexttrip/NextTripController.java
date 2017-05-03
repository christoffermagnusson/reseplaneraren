package com.example.reseplaneraren2.controllers.nexttrip;

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

    private IStopLocationHandler locHandler;

    private ListView nearbyList;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.next_trip_layout, container, false);

        nearbyList = (ListView) v.findViewById(R.id.nearbyStopsList);
        autoCompleteTextView = (AutoCompleteTextView)v.findViewById(R.id.searchStopField);
        mStopLocations = new ArrayList<StopLocation>();
        locHandler = this;

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

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                StopLocation selectedStop = (StopLocation) parent.getItemAtPosition(pos);
                proceedToDepartureDisplay(selectedStop);
            }
        });

        adapter = new StopLocationAdapter(getContext(), R.layout.next_trip_autocomplete, mStopLocations);
        autoCompleteTextView.setAdapter(adapter);
        nearbyList.setAdapter(adapter);

        return v;
    }

    @Override
    public void receiveStopLocation(ArrayList<StopLocation> stopLocList) {
        mStopLocations.clear();
        mStopLocations.addAll(stopLocList);
    }

    @Override
    public void receiveStopLocationError(String message) {

    }



    private void proceedToDepartureDisplay(StopLocation selectedLocation){
        MainActivity activity = (MainActivity) this.getActivity();

        /* Weird bug with autocomplete when going back to this screen. Clearing.*/
        autoCompleteTextView.setText("");
        activity.inflateDepartureDisplay(Screen.DEPARTURE_DISPLAY, selectedLocation);
    }


}
