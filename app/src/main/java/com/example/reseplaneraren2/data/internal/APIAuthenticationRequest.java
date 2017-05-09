package com.example.reseplaneraren2.data.internal;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class APIAuthenticationRequest {

    private String baseUrl = "https://api.vasttrafik.se/token";

    public interface APIAuthenticationListener {
        void receiveAccessToken(String token);
    }

    void send(final String identifier, final RequestQueue queue, final APIAuthenticationListener handler) {
        StringRequest authReq = new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] parts = response.toString().split("\"access_token\":\"");
                String accessToken = parts[1].substring(0, parts[1].length()-2);
                handler.receiveAccessToken(accessToken);
                Log.d(getClass().toString(), "Authorized successfully with " + baseUrl + ". Access token: " + accessToken);
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
                params.put("scope", "device_" + identifier);
                return params;
            }
        };
        queue.add(authReq);
    }
}
