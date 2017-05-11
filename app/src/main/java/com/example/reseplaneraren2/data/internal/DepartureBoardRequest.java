package com.example.reseplaneraren2.data.internal;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.reseplaneraren2.data.interfaces.UIDepartureBoardHandler;
import com.example.reseplaneraren2.model.Departure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartureBoardRequest {

    private String baseUrl = "https://api.vasttrafik.se/bin/rest.exe/v2/departureBoard";
    private String format = "json";

    private String timeSpan = "30";
    private String maxDepartures = "4";

    void send(final UIDepartureBoardHandler handler, final String id, final String date, final String time, final RequestQueue queue, final String accessToken) {
        baseUrl += "?id=" + id + "&date=" + date + "&time=" + time + "&timeSpan=" + timeSpan + "&maxDeparturesPerLine=" + maxDepartures + "&format=" + format;
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

    private void sendToCaller(String message, UIDepartureBoardHandler handler) {
        try {
            Log.d("dunkDEBUG", "Response: " + message);
            JSONObject root = new JSONObject(message);
            JSONObject depList = (JSONObject) root.get("DepartureBoard");

            ArrayList<Departure> departureArrayList = new ArrayList<Departure>();
            if (depList.has("Departure")) { // If no departure is found, it won't contain this JSONArray. We should return an empty ArrayList then.
                Object depObj = depList.get("Departure");
                if (depObj instanceof JSONArray) {
                    JSONArray depArray = (JSONArray) depObj;
                    for (int i = 0; i < depArray.length(); i++) {
                        JSONObject depJson = (JSONObject) depArray.getJSONObject(i);
                        parseDeparture(depJson, departureArrayList);
                    }
                } else if (depObj instanceof JSONObject) {
                    JSONObject depJson = (JSONObject) depObj;
                    parseDeparture(depJson, departureArrayList);
                } else {
                    Log.d(getClass().toString(), "Unexpected Departure-response");
                }
            }
            handler.receiveDeparture(departureArrayList);
            Log.d(getClass().toString(), "Successfully fetched " + departureArrayList.size() + " Departure-objects.");
        } catch(JSONException je) {
            je.printStackTrace();
        }
    }

    private void parseDeparture(JSONObject depJson, List<Departure> listToStore) {
        try {
            Departure departure = new Departure(
                    (String) depJson.get("sname"),
                    (String) depJson.get("direction"),
                    (String) depJson.get("type"),
                    (String) depJson.get("time"),
                    (String) depJson.get("bgColor"),
                    (String) depJson.get("fgColor"));

            if (listToStore.contains(departure)) { /* Existing departure */
                int index = listToStore.indexOf(departure);
                Departure depToUpdate = listToStore.get(index);
                depToUpdate.addDepartureTime(departure.getDepartureTimes().get(0));
            } else { /* New departure */ /* Optional fields */
                if (depJson.has("track")) {
                    departure.setTrack((String) depJson.get("track"));
                }
                if (depJson.has("rtTime")) {
                    departure.setRealTime((String) depJson.get("rtTime"));
                }
                if (depJson.has("accessibility")) {
                    departure.setAccessibility((String) depJson.get("accessibility"));
                }
                listToStore.add(departure);
            }
        } catch (JSONException jsonE) {
            jsonE.printStackTrace();
        }
    }
}
