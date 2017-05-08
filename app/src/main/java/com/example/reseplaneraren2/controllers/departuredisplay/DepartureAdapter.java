package com.example.reseplaneraren2.controllers.departuredisplay;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.reseplaneraren2.R;
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

        return mDepartureList.get(position);
    }

    @Override
    public View getView(int postion, View convertView, ViewGroup viewGroup){
        try{
            if(convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mViewId, viewGroup, false);
            }
            Log.d("dunkDEBUG", "I am creating stuff");
                Departure departure = getItem(postion);
                TextView departureName = (TextView)convertView.findViewById(R.id.nameField);                 // nameField
                TextView departureDestination = (TextView)convertView.findViewById(R.id.destinationField);          // destinationField
                TextView departureTime_1 = (TextView)convertView.findViewById(R.id.depField1);                 // departuresField 1
                TextView departureTime_2 = (TextView)convertView.findViewById(R.id.depField2);                 // departuresField 1
                TextView departureTime_3 = (TextView)convertView.findViewById(R.id.depField3);                 // departuresField 1
                TextView departureTime_4 = (TextView)convertView.findViewById(R.id.depField4);                  // depaField 4
                TextView departureTrack = (TextView)convertView.findViewById(R.id.trackField);

                departureName.setText(departure.getName());
                departureDestination.setText(departure.getDestination());
                departureTime_1.setText(departure.getDepartureTimes()[0]);
                /*departureTime_2.setText(departure.getDepartureTimes()[1]);
                departureTime_3.setText(departure.getDepartureTimes()[2]);
                departureTime_4.setText(departure.getDepartureTimes()[3]); SHOULD LOOP THROUGH DEPARTURE TIMES INSTEAD*/
                departureTrack.setText("Läge " + departure.getTrack());

                departureName.setBackgroundColor(Color.parseColor(departure.getFgColor()));
                departureName.setTextColor(Color.parseColor(departure.getBgColor()));
        } catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return convertView;
    }

}
