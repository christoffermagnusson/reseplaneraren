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
import java.util.List;

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
            Departure departure = getItem(postion);
            TextView departureName = (TextView)convertView.findViewById(R.id.nameField);                 // nameField
            TextView departureDestination = (TextView)convertView.findViewById(R.id.destinationField);          // destinationField
            TextView departureTrack = (TextView)convertView.findViewById(R.id.trackField);

            List<TextView> timeFields = new ArrayList<TextView>();
            timeFields.add((TextView)convertView.findViewById(R.id.depField1));                 // departuresField 1
            timeFields.add((TextView)convertView.findViewById(R.id.depField2));                 // departuresField 1
            timeFields.add((TextView)convertView.findViewById(R.id.depField3));                 // departuresField 1
            timeFields.add((TextView)convertView.findViewById(R.id.depField4));                  // depaField 4

            departureName.setText(departure.getName());
            departureDestination.setText(departure.getDestination());
            departureTrack.setText("Läge " + departure.getTrack());
            departureName.setBackgroundColor(Color.parseColor(departure.getFgColor()));
            departureName.setTextColor(Color.parseColor(departure.getBgColor()));

            List<String> timeValues = departure.getDepartureTimes(); // Departure-object is limited to max 4 departure times
            for (int i = 0; i < timeValues.size(); i++) {
                timeFields.get(i).setText(timeValues.get(i));
            }
        } catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return convertView;
    }

}
