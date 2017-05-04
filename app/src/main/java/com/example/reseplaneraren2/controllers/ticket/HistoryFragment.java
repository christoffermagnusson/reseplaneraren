package com.example.reseplaneraren2.controllers.ticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reseplaneraren2.R;

/**
 * Created by William on 2017-05-04.
 */

public class HistoryFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        return inflater.inflate(R.layout.ticket_history, container, false);
    }
}
