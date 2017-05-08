package com.example.reseplaneraren2.data.internal;

import android.content.Context;

import com.example.reseplaneraren2.data.interfaces.IJourneyPlannerData;

/**
 * Created by christoffer on 2017-05-03.
 */

public class JourneyPlannerFactory {

    public static IJourneyPlannerData getJourneyPlanner(Context context, String identifier){
            return APIJourneyPlanner.getInstance(context, identifier);
    }
}
