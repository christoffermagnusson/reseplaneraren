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
import java.util.Map;

public class StopLocationByNameRequest {

    private String baseUrl = "https://api.vasttrafik.se/bin/rest.exe/v2/location.name";
    private String format = "json";

    void send(final UIStopLocationHandler handler, final String input, final RequestQueue queue, final String accessToken) {
        baseUrl += "?input=" + input + "&format=" + format;
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
            JSONArray stopLocArray = locationList.getJSONArray("StopLocation");

            ArrayList<StopLocation> stopArrayList = new ArrayList<StopLocation>();
            for (int i = 0; i < stopLocArray.length(); i++) {
                JSONObject stopJson = (JSONObject) stopLocArray.getJSONObject(i);

                StopLocation stop = new StopLocation(
                        (String)stopJson.get("name"),
                        (String)stopJson.get("id"));
                        //Double.parseDouble((String)stopJson.get("lat")),
                        //Double.parseDouble((String)stopJson.get("lon")),
                        //(String)stopJson.get("idx"));
                stopArrayList.add(stop);
            }
            handler.receiveStopLocationByName(stopArrayList);

        } catch(JSONException je) {
            je.printStackTrace();
        }
    }
}
