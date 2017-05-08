
package com.example.reseplaneraren2.controllers.departuredisplay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;
import com.example.reseplaneraren2.data.interfaces.UIDepartureBoardHandler;
import com.example.reseplaneraren2.data.internal.APIJourneyPlanner;
import com.example.reseplaneraren2.data.internal.JourneyPlannerFactory;
import com.example.reseplaneraren2.model.Departure;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class DepartureBoardDisplayController extends Fragment implements UIDepartureBoardHandler {

    private StopLocation stopLocation = null;

    private IJourneyPlannerData journeyPlanner;

    private UIDepartureBoardHandler depHandler;

    private DepartureAdapter depAdapter;
    private ArrayList<Departure> mDepList = new ArrayList<Departure>();
    private ListView depListView;

    private MainActivity mParent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mParent = (MainActivity) context;
        } catch (final ClassCastException e) {
            e.printStackTrace();
        }

        journeyPlanner = mParent.getJourneyPlanner();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_departure_display_controller, container, false);

        depListView = (ListView)view.findViewById(R.id.departureListView);
        depHandler = this;
        depAdapter = new DepartureAdapter(getContext(),R.layout.departure_display_listitem, mDepList);
        depListView.setAdapter(depAdapter);

        journeyPlanner.getDepartureBoard(this, Calendar.getInstance(), stopLocation);

        return view;
    }

    @Override
    public void receiveDeparture(ArrayList<Departure> departureList) {
        mDepList.clear();
        mDepList.addAll(departureList);
        depAdapter.notifyDataSetChanged();
    }

    @Override
    public void receiveDepartureError(String message) {

    }

    public void setStopLocation(StopLocation stopLocation){
        this.stopLocation=stopLocation;
    }

}
