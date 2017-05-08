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
import java.util.Map;

public class DepartureBoardRequest {

    private String baseUrl = "https://api.vasttrafik.se/bin/rest.exe/v2/departureBoard";
    private String format = "json";

    private String timeSpan = "90";
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
            JSONObject root = new JSONObject(message);
            JSONObject locationList = (JSONObject) root.get("DepartureBoard");
            JSONArray stopLocArray = locationList.getJSONArray("Departure");

            ArrayList<Departure> departureArrayList = new ArrayList<Departure>();
            for (int i = 0; i < stopLocArray.length(); i++) {
                JSONObject depJson = (JSONObject) stopLocArray.getJSONObject(i);
                Departure departure = new Departure(
                        (String) depJson.get("sname"),
                        (String) depJson.get("direction"),
                        (String) depJson.get("type"),
                        (String) depJson.get("time"),
                        (String) depJson.get("bgColor"),
                        (String) depJson.get("fgColor"));

                /* New departure, or should we only add time to existing one */
                if (departureArrayList.contains(departure)) {
                    int index = departureArrayList.indexOf(departure);
                    Departure depToUpdate = departureArrayList.get(index);
                    depToUpdate.addDepartureTime(departure.getDepartureTimes().get(0));
                } else {
                    /* Optional fields */
                    if (depJson.has("track")) {
                        departure.setTrack((String) depJson.get("track"));
                    }
                    if (depJson.has("rtTime")) {
                        departure.setRealTime((String) depJson.get("rtTime"));
                    }
                    if (depJson.has("accessibility")) {
                        departure.setAccessibility((String) depJson.get("accessibility"));
                    }
                    departureArrayList.add(departure);
                }
                handler.receiveDeparture(departureArrayList);
            }
        } catch(JSONException je) {
            je.printStackTrace();
        }
    }
}
