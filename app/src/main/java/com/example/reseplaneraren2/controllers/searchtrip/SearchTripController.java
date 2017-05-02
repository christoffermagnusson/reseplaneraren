package com.example.reseplaneraren2.controllers.searchtrip;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.reseplaneraren2.R;

public class SearchTripController extends Fragment {

    private AutoCompleteTextView searchStopField;
    private ListView nearbyStopsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_trip_layout, container, false);

        searchStopField = (AutoCompleteTextView) view.findViewById(R.id.searchStopField);
        nearbyStopsList = (ListView) view.findViewById(R.id.nearbyStopsList);

        return view;
    }

}
