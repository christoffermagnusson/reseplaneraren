package com.example.reseplaneraren2.controllers.nexttrip;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;
import com.example.reseplaneraren2.Util.LocationHelper;
import com.example.reseplaneraren2.Util.Utils;
import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;
import com.example.reseplaneraren2.data.interfaces.UIStopLocationHandler;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-02.
 */


public class NextTripController extends Fragment implements UIStopLocationHandler {

    private ArrayList<StopLocation> mStopLocationsBySearch;
    private AutoCompleteTextView autoCompleteTextView;
    private StopLocationAdapter searchAdapter;

    private ArrayList<StopLocation> mStopLocationsNearby;
    private StopLocationAdapter nearbyAdapter;
    private ListView nearbyList;

    private UIStopLocationHandler locHandler;

    private MainActivity mParent;
    private IJourneyPlannerData journeyPlanner;
    private long lastSearchTime;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mParent = (MainActivity) context;
        } catch (final ClassCastException e) {
            e.printStackTrace();
        }

        journeyPlanner = mParent.getJourneyPlanner();
    }

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
        //JourneyPlannerFactory.getJourneyPlanner().getStopLocationByCoordinate(locHandler,null);

        searchAdapter = new StopLocationAdapter(getContext(), R.layout.simple_list_item, mStopLocationsBySearch);
        autoCompleteTextView.setAdapter(searchAdapter);

        nearbyAdapter = new StopLocationAdapter(getContext(),R.layout.simple_list_item, mStopLocationsNearby);
        nearbyList.setAdapter(nearbyAdapter);
        ((MainActivity)getActivity()).changeTitle("Nästa tur");
        LocationHelper lHelp = new LocationHelper(mParent);
        return v;
    }

    @Override
    public void receiveStopLocationByName(ArrayList<StopLocation> stopLocList) {
        mStopLocationsBySearch.clear();
        mStopLocationsBySearch.addAll(stopLocList);
        searchAdapter.notifyDataSetChanged();
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
        autoCompleteTextView.setText("");
        activity.inflateDepartureDisplay(Screen.DEPARTURE_DISPLAY, selectedLocation);
    }

    private void setupTextListener() {
        final MainActivity activity = (MainActivity) this.getActivity();
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 3) {
                    long currentTime = System.nanoTime();
                    long elapsed = currentTime - lastSearchTime;

                    if (elapsed >= 750000000) {
                        journeyPlanner.getStopLocationByName(locHandler, s.toString());
                        lastSearchTime = System.nanoTime();
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    Utils.hideKeyboard(activity);
                    return true;
                }
                return false;
            }
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
