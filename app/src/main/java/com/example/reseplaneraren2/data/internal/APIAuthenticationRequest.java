package com.example.reseplaneraren2.data.internal;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class APIAuthenticationRequest {

    private String baseUrl = "https://api.vasttrafik.se/token";

    public interface APIAuthenticationListener {
        void receiveAccessToken(String token, Calendar expiryTime);
        void receiveError(String error);
    }

    void send(final String identifier, final RequestQueue queue, final APIAuthenticationListener handler) {
        StringRequest authReq = new StringRequest(Request.Method.POST, baseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Scanner scanner = new Scanner(response);
                String expiry = scanner.findInLine("expires_in\":[0-9]+").split(":")[1];
                String accessToken = scanner.findInLine("access_token\":\"[a-zA-Z0-9]+\"").split(":")[1].replace("\"", "");

                Calendar expiryTime = Calendar.getInstance();
                expiryTime.add(Calendar.SECOND, Integer.parseInt(expiry));

                handler.receiveAccessToken(accessToken, expiryTime);
                Log.d(getClass().toString(), "Authorized successfully with " + baseUrl + ". Access token: " + accessToken + ". Estimated expiration: " + expiryTime.getTime().toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handler.receiveError(error.toString());
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
