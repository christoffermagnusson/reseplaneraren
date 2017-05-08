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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
                TextView timeField = timeFields.get(i);
                timeField.setText(timeValues.get(i));
                if (i == 0 && departure.getRealTime() != null) {
                    long diff = getTimeDifference(timeValues.get(i), departure.getRealTime());
                    if (diff != 0) {
                        if (diff < 0)
                            timeField.append(" " + diff);
                        else {
                            timeField.append(" +" + diff);
                        }
                    }
                }
            }
        } catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return convertView;
    }

    private long getTimeDifference(String origin, String realTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d1 = null; Date d2 = null;
        try {
            d1 = sdf.parse(origin);
            d2 = sdf.parse(realTime);
        } catch (ParseException pe) {
            Log.d(getClass().toString(), "Could not parse date");
        }
        return TimeUnit.MILLISECONDS.toMinutes((d2.getTime() - d1.getTime()));
    }
}
