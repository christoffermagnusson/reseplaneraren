package com.example.reseplaneraren2.controllers.ticket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.reseplaneraren2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by William on 2017-05-04.
 */

public class HistoryFragment extends Fragment {

    ListView hList;
    List<String> history;
    String historyDummy1, historyDummy2, historyDummy3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View v = inflater.inflate(R.layout.ticket_history, container, false);
        hList = (ListView) v.findViewById(R.id.historyList);
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();

        historyDummy1 = "Göteborg \nVUXEN\n14:15-17:14 10 jun               29kr";
        historyDummy2 = "Göteborg \nVUXEN\n11:00-22:14 8 maj                49kr";
        historyDummy3 = "Göteborg \nVUXEN\n08:55-09:55 29 apr               29kr";

        history = new ArrayList<String>();
        history.addAll(Arrays.asList(new String[]{historyDummy1, historyDummy2, historyDummy3}));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), R.layout.simple_list_item, history);
        hList.setAdapter(adapter);

    }
}
