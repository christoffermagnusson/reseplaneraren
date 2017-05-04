package com.example.reseplaneraren2.controllers.departuredisplay;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        this.mContext = context;
        this.mViewId = viewId;
        this.mDepartureList = departureList;

    }

    public Departure getItem(int position){

        return mDepartureList.get(position)
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup){
        try{
            if(convertView == null){
                LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
                convertView = inflater.inflate(mViewId,viewGroup,false);

                /*TextView departureName = (TextView)                 // nameField
                TextView departureDestination = (TextView)          // destinationField
                TextView departureTime = (TextView) */                // departuresField
            }
        }
    }

}
