package com.example.reseplaneraren2.controllers.nexttrip;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reseplaneraren2.R;

/**
 * Created by christoffer on 2017-05-02.
 */

public class NextTripController extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.next_trip_layout, container, false);

    }
}
