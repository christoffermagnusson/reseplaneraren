package com.example.reseplaneraren2.data.internal;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.reseplaneraren2.data.interfaces.UIStopLocationHandler;
import com.example.reseplaneraren2.model.StopLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopLocationByCoordinateRequest {

    private String baseUrl = "https://api.vasttrafik.se/bin/rest.exe/v2/location.nearbystops";
    private String format = "json";
    private String maxDist = "1500";

    void send(final UIStopLocationHandler handler, final double lat, final double lng, final RequestQueue queue, final String accessToken) {
        baseUrl += "?originCoordLat=" + lat + "&originCoordLong=" + lng + "&maxDist=" + maxDist + "&maxNo" + 20 + "&format=" + format;
        StringRequest stopReq = new StringRequest(Request.Method.GET, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                sendToCaller(response, handler);
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
                headers.put("Authorization", "Bearer " + accessToken);
                return headers;
            }
        };
        queue.add(stopReq);
    }

    private void sendToCaller(String message, UIStopLocationHandler handler) {
        try {
            JSONObject root = new JSONObject(message);
            JSONObject locationList = (JSONObject) root.get("LocationList");

            ArrayList<StopLocation> stopArrayList = new ArrayList<StopLocation>();
            if (locationList.has("StopLocation")) {
                Object stopLocObj = locationList.get("StopLocation");
                if (stopLocObj instanceof JSONArray) {
                    JSONArray stopLocArray = (JSONArray) stopLocObj;
                    for (int i = 0; i < stopLocArray.length(); i++) {
                        JSONObject stopJson = stopLocArray.getJSONObject(i);
                        parseStopLocation(stopJson, stopArrayList);
                    }
                } else if (stopLocObj instanceof JSONObject) {
                    JSONObject stopJson = (JSONObject) stopLocObj;
                    parseStopLocation(stopJson, stopArrayList);
                } else {
                    Log.d(getClass().toString(), "Unexpected StopLocation-response");
                }
            }
            handler.receiveStopLocationByCoordinate(stopArrayList);
            Log.d(getClass().toString(), "Successfully fetched " + stopArrayList.size() + " StopLocation-objects.");
        } catch(JSONException je) {
            je.printStackTrace();
        }
    }

    private void parseStopLocation(JSONObject stopJson, List<StopLocation> listToStore) {
        try {
            if (!stopJson.has("track")) { // This service returns one StopLocation for each track. We only want one.
                StopLocation stop = new StopLocation(
                        (String)stopJson.get("name"),
                        (String)stopJson.get("id"));
                listToStore.add(stop);
            }
        } catch (JSONException jsonE) {
            jsonE.printStackTrace();
        }
    }
}
