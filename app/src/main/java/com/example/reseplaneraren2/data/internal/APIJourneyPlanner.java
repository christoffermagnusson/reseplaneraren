package com.example.reseplaneraren2.data.internal;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;
import com.example.reseplaneraren2.data.interfaces.UIDepartureBoardHandler;
import com.example.reseplaneraren2.data.interfaces.UIStopLocationHandler;
import com.example.reseplaneraren2.data.internal.util.DateHelper;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.Calendar;

public class APIJourneyPlanner implements IJourneyPlannerData {

    // Do NOT forget to check about closing requests. Should tag them and cancel all at some point...
    private static APIJourneyPlanner instance;
    private RequestQueue queue;
    private String identifier;

    private String accessToken;
    private Calendar expirationTime;

    private APIJourneyPlanner(Context context, String identifier) {
        this.queue = Volley.newRequestQueue(context); // Should be setup with application context, not activity context (otherwise will be destroyed when activity is destroyed)
        this.identifier = identifier;
        authenticate();
    }

    private void authenticate() {
        APIAuthenticationRequest authReq = new APIAuthenticationRequest();
        authReq.send(identifier, queue, new APIAuthenticationRequest.APIAuthenticationListener() {
            @Override
            public void receiveAccessToken(String token, Calendar expiryTime) {
                accessToken = token;
                expirationTime = expiryTime;
            }
            @Override
            public void receiveError(String error) {
                // Inform user interface?
            }
        });
    }

    public static APIJourneyPlanner getInstance(Context context, String identifier) {
        if (context == null || identifier == null) throw new NullPointerException("Context context cannot be null");
        if (identifier.trim().isEmpty()) throw new IllegalArgumentException("String identifier must be longer than 0");

        if (instance == null ) {
            instance = new APIJourneyPlanner(context, identifier);
        }
        return instance;
    }

    public void getStopLocationByName(final UIStopLocationHandler handler, final String input) {
        if (handler == null) throw new NullPointerException("UIStopLocationHandler handler cannot be null");
        if (input == null) throw new NullPointerException("Argument input cannot be null");
        if (input.trim().isEmpty()) throw new IllegalArgumentException("Argument input must be longer than 0");

        if (accessToken != null && expirationTime != null &&
                (Calendar.getInstance().compareTo(expirationTime) < 0)) {
            new StopLocationByNameRequest().send(handler, input, queue, accessToken);
            Log.d(getClass().toString(), "Requesting StopLocations by name: " + input);
        } else {
            authenticate();
            Log.d(getClass().toString(), "Missing or expired access token. Re-authenticating.");
            new Handler().postDelayed(new Runnable() { // I think this is really bad, but will probably work for this prototype. Same goes for other get-methods in this class.
                @Override
                public void run() {
                    getStopLocationByName(handler, input);
                }
            }, 2000);
        }
    }

    public void getStopLocationByCoordinate(final UIStopLocationHandler handler, final double lat, final double lng) {
        if (handler == null) throw new NullPointerException("UIStopLocationHandler handler cannot be null");

        if (accessToken != null && expirationTime != null &&
                (Calendar.getInstance().compareTo(expirationTime) < 0)) {
            new StopLocationByCoordinateRequest().send(handler, lat, lng, queue, accessToken);
        } else {
            authenticate();
            Log.d(getClass().toString(), "Missing or expired access token. Re-authenticating.");
            new Handler().postDelayed(new Runnable() { // I think this is really bad, but will probably work for this prototype. Same goes for other get-methods in this class.
                @Override
                public void run() {
                    getStopLocationByCoordinate(handler, lat, lng);
                }
            }, 2000);
        }
    }

    public void getDepartureBoard(final UIDepartureBoardHandler handler, final Calendar time, final StopLocation stop) {
        if (handler == null) throw new NullPointerException("UIDepartureBoardHandler handler cannot be null");
        if (time == null) throw new NullPointerException("Argument time cannot be null");
        if (stop == null) throw new NullPointerException("Argument stop cannot be null");
        if ((stop.getStopId().trim().isEmpty()) || stop.getStopId() == null) throw new IllegalArgumentException("Invalid id for StopLocation: " + stop.toString());

        String d = DateHelper.getDate(time);
        String t = DateHelper.getTime(time);

        if (accessToken != null && expirationTime != null &&
                (Calendar.getInstance().compareTo(expirationTime) < 0)) {
            new DepartureBoardRequest().send(handler, stop.getStopId(), d, t, queue, accessToken);
        } else {
            authenticate();
            Log.d(getClass().toString(), "Missing or expired access token. Re-authenticating.");
            new Handler().postDelayed(new Runnable() { // I think this is really bad, but will probably work for this prototype. Same goes for other get-methods in this class.
                @Override
                public void run() {
                    getDepartureBoard(handler, time, stop);
                }
            }, 2000);
        }
    }
}
