package com.example.reseplaneraren2.controllers.searchtrip;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;

public class SearchTripController extends Fragment {

    private AutoCompleteTextView searchStopField;
    private ListView nearbyStopsList;
    private Spinner changeTimeSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_trip_layout, container, false);

        searchStopField = (AutoCompleteTextView) view.findViewById(R.id.searchStopField);
        nearbyStopsList = (ListView) view.findViewById(R.id.nearbyStopsList);
        changeTimeSpinner = (Spinner) view.findViewById(R.id.changeTimeSpinner);
        fillChangeTimeSpinner();
        ((MainActivity)getActivity()).changeTitle("Sök resa");
        return view;
    }

    //Fyler changeTimeSpinner med siffror
    private void fillChangeTimeSpinner(){
        Integer[] changeTimes = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_spinner_item, changeTimes);
        changeTimeSpinner.setAdapter(adapter);
    }

}
