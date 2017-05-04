
package com.example.reseplaneraren2.controllers.departuredisplay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.data.interfaces.IDepartureHandler;
import com.example.reseplaneraren2.data.interfaces.IStopLocationHandler;
import com.example.reseplaneraren2.data.internal.JourneyPlannerFactory;
import com.example.reseplaneraren2.model.Departure;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DepartureDisplayController  extends Fragment implements IDepartureHandler{

    private StopLocation stopLocation = null;

    private IDepartureHandler depHandler;

    private DepartureAdapter depAdapter;
    private ArrayList<Departure> mDepList = new ArrayList<Departure>();
    private ListView depListView;

    public DepartureDisplayController() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_departure_display_controller, container, false);

        depListView = (ListView)view.findViewById(R.id.departureListView);

        depHandler = this;



        //setupListClickListener();
        depAdapter = new DepartureAdapter(getContext(),R.layout.departure_display_listitem, mDepList);

        depListView.setAdapter(depAdapter);
        return view;
    }

    @Override
    public void receiveDeparture(ArrayList<Departure> departureList) {
        mDepList.clear();
        mDepList.addAll(departureList);

    }

    @Override
    public void receiveDepartureError(String message) {

    }

    public void setStopLocation(StopLocation stopLocation){
        this.stopLocation=stopLocation;
        JourneyPlannerFactory.getJourneyPlanner().getDepartureBoard(this,stopLocation, Calendar.getInstance());
        Log.d("DepartureDisplay!", stopLocation.toString());
    }

}
