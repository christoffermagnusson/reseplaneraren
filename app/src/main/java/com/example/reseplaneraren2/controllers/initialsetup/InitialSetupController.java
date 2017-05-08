package com.example.reseplaneraren2.controllers.initialsetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-08.
 */

public class InitialSetupController extends Fragment {

    ArrayList<Integer> possibleScreens = new ArrayList<Integer>();
    ListView possibleScreensListView;
    InitialSetupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.initial_setup, container, false);

        fillListWithScreens();
        possibleScreensListView = (ListView) view.findViewById(R.id.initialSetupList);

        adapter = new InitialSetupAdapter(getContext(),R.layout.simple_list_item,possibleScreens);
        possibleScreensListView.setAdapter(adapter);


        return view;

    }

    private void fillListWithScreens(){
        possibleScreens.add(R.string.search_trip);
        possibleScreens.add(R.string.next_trip);
        possibleScreens.add(R.string.favorites);
        possibleScreens.add(R.string.ticket);
    }
}
