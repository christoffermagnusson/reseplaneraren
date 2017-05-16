
package com.example.reseplaneraren2.controllers.departuredisplay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
    private SwipeRefreshLayout departureSwipeRefreshLayout;

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

        /* Init list stuff */
        depListView = (ListView)view.findViewById(R.id.departureListView);
        depHandler = this;
        depAdapter = new DepartureAdapter(getContext(),R.layout.departure_display_listitem, mDepList);
        depListView.setAdapter(depAdapter);

        mParent.changeTitle(stopLocation.getName());

        /* Init swipe refresh stuff */
        departureSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.departureSwipeRefresh);
        setupSwipeListener(departureSwipeRefreshLayout);

        depAdapter.clear(); // If list has been loaded before (e.g. doing a search, navigating back, searching again) > List should be cleared.
        requestDeparture();

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            requestDeparture();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.refresh).setVisible(true);
    }

    private void setupSwipeListener(SwipeRefreshLayout srl) {
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestDeparture();
            }
        });
    }

    private void requestDeparture() {
        journeyPlanner.getDepartureBoard(this, Calendar.getInstance(), stopLocation);
        departureSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void receiveDeparture(ArrayList<Departure> departureList) {
        depAdapter.clear();
        depAdapter.addAll(departureList);
        depAdapter.notifyDataSetChanged();
        departureSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void receiveDepartureError(String message) {
        displayErrorMessage(message);
    }

    public void setStopLocation(StopLocation stopLocation){
        this.stopLocation=stopLocation;
    }

    private void displayErrorMessage(String errorMsg) {
        Toast.makeText(mParent, errorMsg, Toast.LENGTH_LONG).show();
    }
}
