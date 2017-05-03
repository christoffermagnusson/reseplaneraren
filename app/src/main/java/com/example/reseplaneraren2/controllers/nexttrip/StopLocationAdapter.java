package com.example.reseplaneraren2.controllers.nexttrip;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by samuel on 2017-05-03.
 */

public class StopLocationAdapter extends ArrayAdapter<StopLocation> implements {
    private ArrayList<StopLocation> mStopLocations;
    private ArrayList<StopLocation> mStopLocations_All;
    private ArrayList<StopLocation> mStopLocations_Suggestion;
    private int mViewId;
    private Context mContext;

    public StopLocationAdapter(Context context, int viewId, ArrayList<StopLocation> stopLocations){
        super(context, view, stopLocations);
        this.mStopLocations = stopLocations;
        this.mViewId = viewId;
        this.mContext = context;
        this.mStopLocations_All = new ArrayList<>(stopLocations);
        this. mStopLocations_Suggestion = new ArrayList<>();


    }

    public StopLocation getItem(int position){
        return mStopLocations.get(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try {
            if (convertView ==null){
                LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
                convertView = inflater.inflate(mViewId, parent, false);
            }
            StopLocation stopLocation = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.textView);
            name.setText(stopLocation.name);
        } catch (Exception e){
            e.printStackTrace();
        }
     return convertView;
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
             @Override
            public String convertResultToString(Object resultValue){
                 return ((StopLocation) resultValue).name;
             }

             @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                 if (constraint != null) {
                mStopLocations_Suggestion.clear();
                     for (StopLocation stopLocation : mStopLocations_All){
                         if (stopLocation.name.toLowerCase().startsWith(constraint.toString().toLowerCase())){
                             mStopLocations_Suggestion.add(stopLocation);
                         }
                     }
                     FilterResults filterResults = new FilterResults();
                     filterResults.values = mStopLocations_Suggestion;
                     filterResults.count = mStopLocations_Suggestion.size();
                     return filterResults;
             } else {
                     return new FilterResults();
                 }
        }

        @Override
            protected  void publishResults (CharSequence constraint, FilterResults results) {
                mStopLocations.clear();
            if (results != null && results.count >0){
                ArrayList<?> result = (ArrayList<?>) results.values;
                for (Object object : result) {
                    if (object instanceof StopLocation)    {
                        mStopLocations.add((StopLocation) object);
                    }
                }
            }     else if (constraint == null)         {
                mStopLocations.addAll(mStopLocations_All);
            }
            notifyDataSetChanged();
        }
        };

    }
}
