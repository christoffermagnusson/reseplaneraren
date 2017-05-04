package com.example.reseplaneraren2.controllers.departuredisplay;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.reseplaneraren2.model.Departure;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-04.
 */



public class DepartureAdapter extends ArrayAdapter<Departure> {

    private ArrayList<Departure> mDepartureList;
    private int mViewId;
    private Context mContext;

    public DepartureAdapter(Context context, int viewId, ArrayList<Departure> departureList){
        super(context, viewId, departureList);


    }

}
