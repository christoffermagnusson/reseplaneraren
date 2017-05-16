package com.example.reseplaneraren2.controllers.nexttrip;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;
import com.example.reseplaneraren2.Util.LocationHelperFragment;
import com.example.reseplaneraren2.Util.Utils;
import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;
import com.example.reseplaneraren2.data.interfaces.UIStopLocationHandler;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-02.
 */


public class NextTripController extends Fragment implements UIStopLocationHandler {

    private ArrayList<StopLocation> mStopLocationsBySearch;
    private AutoCompleteTextView autoCompleteTextView;
    private StopLocationAdapter searchAdapter;

    private ArrayList<StopLocation> mStopLocationsNearby;
    private StopLocationAdapter nearbyAdapter;
    private ListView nearbyList;

    private UIStopLocationHandler locHandler;

    private MainActivity mParent;
    private IJourneyPlannerData journeyPlanner;
    private long lastSearchTime;

    private boolean nearbyFetched = false;
    private SwipeRefreshLayout nearbySwipeRefresh;
    private double currentLng;
    private double currentLat;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mParent = (MainActivity) context;
            locHandler = this;
        } catch (final ClassCastException e) {
            e.printStackTrace();
        }
        journeyPlanner = mParent.getJourneyPlanner();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.next_trip_layout, container, false);
        initSearchField(v);
        initNearbyList(v);
        initNearbyRefresh(v);
        setHasOptionsMenu(true);
        mParent.changeTitle("Nästa tur");
        return v;
    }

    /* JourneyPlanner hadn't gotten any access token when doing this in onCreate (I got next trip as start page). Trying here instead. Maybe good to get new location
    everytime onStart is called anyway. */
    @Override
    public void onStart() {
        super.onStart();
        startLocationService();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopLocationService();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            requestStopLocationsByCoordinates(currentLat, currentLng);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.refresh).setVisible(true);
    }

    private void initSearchField(View v) {
        autoCompleteTextView = (AutoCompleteTextView)v.findViewById(R.id.searchStopField);
        mStopLocationsBySearch = new ArrayList<StopLocation>();
        setupTextListener();
        setupSearchClickListener();
        searchAdapter = new StopLocationAdapter(getContext(), R.layout.simple_list_item, mStopLocationsBySearch);
        autoCompleteTextView.setAdapter(searchAdapter);
    }

    private void initNearbyList(View v) {
        nearbyList = (ListView) v.findViewById(R.id.nearbyStopsList);
        mStopLocationsNearby = new ArrayList<StopLocation>();
        setupListClickListener();
        nearbyAdapter = new StopLocationAdapter(getContext(),R.layout.simple_list_item, mStopLocationsNearby);
        nearbyList.setAdapter(nearbyAdapter);
    }

    private void initNearbyRefresh(View v) {
        nearbySwipeRefresh = (SwipeRefreshLayout) v.findViewById(R.id.nearbySwipeRefresh);
        nearbySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestStopLocationsByCoordinates(currentLat, currentLng);
            }
        });
    }

    private void startLocationService() {
        LocationHelperFragment lhf = new LocationHelperFragment();
        lhf.setListener(new LocationHelperFragment.CoordinateListener() {
            @Override
            public void receiveLocation(double lat, double lng) {
                if (!nearbyFetched) {
                    requestStopLocationsByCoordinates(lat, lng);
                    nearbyFetched = true;
                }
                currentLat = lat;
                currentLng = lng;
                Log.d(getClass().toString(), "Updated coordinates: " + lat + ", " + lng);
            }
            @Override
            public void receiveLocationError(LocationHelperFragment.LocationError error) {
                switch (error) {
                    case NO_LOCATION_FOUND:
                        displayErrorMessage("Inga närliggande hållplatser hittade");
                        break;
                    case CONNECTION_FAILED:
                        displayErrorMessage("Kunde ej hämta platsdata");
                        break;
                    case PERMISSION_NOT_GRANTED:
                        displayErrorMessage("Ingen åtkomst till platsdata");
                        break;
                }
            }
        });
        getChildFragmentManager().beginTransaction().add(lhf, "location_fragment").commit();
    }

    private void stopLocationService() {
        Fragment locationService = getChildFragmentManager().findFragmentByTag("location_fragment");
        if (locationService != null) {
            getChildFragmentManager().beginTransaction().remove(locationService).commitAllowingStateLoss();
            nearbyFetched = false;
        }
    }

    @Override
    public void receiveStopLocationByName(ArrayList<StopLocation> stopLocList) {
        mStopLocationsBySearch.clear();
        mStopLocationsBySearch.addAll(stopLocList);
        searchAdapter.notifyDataSetChanged();
    }

    @Override
    public void receiveStopLocationByCoordinate(ArrayList<StopLocation> stopLocList){
        mStopLocationsNearby.clear();
        mStopLocationsNearby.addAll(stopLocList);
        nearbyAdapter.notifyDataSetChanged();
        nearbySwipeRefresh.setRefreshing(false);
    }

    private void requestStopLocationsByCoordinates(double lat, double lng) {
        journeyPlanner.getStopLocationByCoordinate(this, lat, lng);
        nearbySwipeRefresh.setRefreshing(true);
    }

    @Override
    public void receiveStopLocationError(String message) {
        displayErrorMessage("Connection failed to Västtrafik server");
    }

    private void proceedToDepartureDisplay(StopLocation selectedLocation){
        MainActivity activity = (MainActivity) this.getActivity();
        autoCompleteTextView.setText("");
        activity.inflateDepartureDisplay(Screen.DEPARTURE_DISPLAY, selectedLocation);
    }

    private void setupTextListener() {
        final MainActivity activity = (MainActivity) this.getActivity();
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 3) {
                    long currentTime = System.nanoTime();
                    long elapsed = currentTime - lastSearchTime;
                    // Right now, an extra search is made when user clicks an autocomplete-entry, since that changes content in the text-field, but I don't think we can prevent it?
                    if (elapsed >= 750000000) {
                        journeyPlanner.getStopLocationByName(locHandler, s.toString());
                        lastSearchTime = System.nanoTime();
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    Utils.hideKeyboard(activity);
                    return true;
                }
                return false;
            }
        });

    }

    private void setupSearchClickListener() {
        final MainActivity activity = (MainActivity) this.getActivity();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                StopLocation stop = (StopLocation) parent.getItemAtPosition(pos);
                Utils.hideKeyboard(activity);
                proceedToDepartureDisplay(stop);
            }
        });
    }

    private void setupListClickListener() {

        nearbyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos, long id) {
                StopLocation stop = (StopLocation) parent.getItemAtPosition(pos);
                proceedToDepartureDisplay(stop);
            }
        });
    }

    private void displayErrorMessage(String errorMsg) {
        Toast.makeText(mParent, errorMsg, Toast.LENGTH_LONG).show();
    }
}
