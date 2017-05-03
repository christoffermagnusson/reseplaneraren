package com.example.reseplaneraren2.data.interfaces;

import com.example.reseplaneraren2.model.Departure;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-03.
 */

public interface IDepartureHandler {

        public void receiveDeparture(ArrayList<Departure> departureList);

        public void receiveDepartureError(String message);
}
