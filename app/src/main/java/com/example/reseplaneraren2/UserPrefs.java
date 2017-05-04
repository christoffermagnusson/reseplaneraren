package com.example.reseplaneraren2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by christoffer on 2017-05-04.
 */

public class UserPrefs {

    static Screen startScreen;


    /*public void setStartScreen(int startScreenPos){
        switch(startScreenPos){
            case R.id.navigation_search_trip:
                startScreen=Screen.SEARCH_TRIP;
            case R.id.navigation_next_trip:
                startScreen=Screen.NEXT_TRIP;
            case R.id.navigation_favorites:
                startScreen=Screen.FAVORITES;
            case R.id.navigation_ticket:
                startScreen=Screen.TICKET;
            default:
                startScreen=Screen.DEFAULT;
        }

    }*/

    public static void setStartScreen(int startScreenPos){
        switch(startScreenPos){
            case R.id.navigation_search_trip:
                Log.d("Message: ","Setting start screen to Search Trip");
                startScreen=Screen.SEARCH_TRIP;
            case R.id.navigation_next_trip:
                Log.d("Message: ","Setting start screen to Next Trip");
                startScreen=Screen.NEXT_TRIP;
            case R.id.navigation_favorites:
                Log.d("Message: ","Setting start screen to Favorites");
                startScreen=Screen.FAVORITES;
            case R.id.navigation_ticket:
                Log.d("Message: ","Setting start screen to Ticket");
                startScreen=Screen.TICKET;
            default:
                Log.d("Message: ","Setting start screen to Default");
                startScreen=Screen.DEFAULT;
        }

    }





}
