
package com.example.reseplaneraren2.controllers.departuredisplay;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.data.interfaces.IDepartureHandler;
import com.example.reseplaneraren2.data.interfaces.IStopLocationHandler;
import com.example.reseplaneraren2.model.Departure;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

public class DepartureDisplayController  extends Fragment implements IDepartureHandler{

    private StopLocation stopLocation = null;

    public DepartureDisplayController() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_departure_display_controller, container, false);
    }

    @Override
    public void receiveDeparture(ArrayList<Departure> departureList) {

    }

    @Override
    public void receiveDepartureError(String message) {

    }

    public void setStopLocation(StopLocation stopLocation){
        this.stopLocation=stopLocation;
        Log.d("DepartureDisplay!", stopLocation.toString());
    }

}
