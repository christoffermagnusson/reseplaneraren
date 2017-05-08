package com.example.reseplaneraren2.data.internal;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;
import com.example.reseplaneraren2.data.interfaces.UIDepartureBoardHandler;
import com.example.reseplaneraren2.data.interfaces.UIStopLocationHandler;
import com.example.reseplaneraren2.data.internal.util.DateHelper;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class APIJourneyPlanner implements IJourneyPlannerData {

    // Do NOT forget to check about closing requests. Should tag them and cancel all at some point...
    private static APIJourneyPlanner instance;
    private RequestQueue queue;
    private String accessToken; // Always check that this is not null before performing any GET requests...If possible maybe also check that it's valid, or revoke it on onPause

    private APIJourneyPlanner(Context context, String identifier) {
        this.queue = Volley.newRequestQueue(context); // Should be setup with application context, not activity context (otherwise will be destroyed when activity is destroyed)
        authenticate(identifier);
    }

    public static APIJourneyPlanner getInstance(Context context, String identifier) {
        if (context == null || identifier == null) throw new NullPointerException("Context context cannot be null");
        if (identifier.trim().isEmpty()) throw new IllegalArgumentException("String identifier must be longer than 0");

        if (instance == null ) {
            instance = new APIJourneyPlanner(context, identifier);
        }
        return instance;
    }

    public void getStopLocationByName(UIStopLocationHandler handler, String input) {
        if (handler == null) throw new NullPointerException("UIStopLocationHandler handler cannot be null");
        if (input == null) throw new NullPointerException("Argument input cannot be null");
        if (input.trim().isEmpty()) throw new IllegalArgumentException("Argument input must be longer than 0");

        if (accessToken != null) { // This is quite bad...some kind of state for the "client" of this method to check if ready?
            new StopLocationByNameRequest().send(handler, input, queue, accessToken);
        }
    }

    public void getDepartureBoard(UIDepartureBoardHandler handler, Calendar time, StopLocation stop) {
        if (handler == null) throw new NullPointerException("UIDepartureBoardHandler handler cannot be null");
        if (time == null) throw new NullPointerException("Argument time cannot be null");
        if (stop == null) throw new NullPointerException("Argument stop cannot be null");
        if ((stop.getStopId().trim().isEmpty()) || stop.getStopId() == null) throw new IllegalArgumentException("Invalid id for StopLocation: " + stop.toString());

        String d = DateHelper.getDate(time);
        String t = DateHelper.getTime(time);

        if (accessToken != null) {
            new DepartureBoardRequest().send(handler, stop.getStopId(), d, t, queue, accessToken);
        }
    }

    private void authenticate(final String identifier) {
        StringRequest authReq = new StringRequest(Request.Method.POST, "https://api.vasttrafik.se/token", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("dunkDEBUG", response);
                String[] parts = response.toString().split("\"access_token\":\"");
                accessToken = parts[1].substring(0, parts[1].length()-2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR","error => "+error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  headers = new HashMap<String, String>();
                headers.put("User-Agent", "Mozilla/5.0");
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                headers.put("Authorization", "Basic Y0ZtTENRS2lwMGVRUElJMTdSdkVkR1NhenBRYTpZTU5PdXhNRlg0OUpWaEhQTkpCV0xZQVU3Zllh");
                return headers;
            }
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("grant_type", "client_credentials");
                params.put("scope", identifier);
                return params;
            }
        };
        queue.add(authReq);
    }
}
