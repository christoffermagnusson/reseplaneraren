package com.example.reseplaneraren2.controllers.nexttrip;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.data.interfaces.IStopLocationHandler;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-02.
 */

public class NextTripController extends Fragment implements IStopLocationHandler {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.next_trip_layout, container, false);

    }

    @Override
    public void receiveStopLocation(ArrayList<StopLocation> stopLocList) {

    }

    @Override
    public void receiveStopLocationError(String message) {

    }


}
