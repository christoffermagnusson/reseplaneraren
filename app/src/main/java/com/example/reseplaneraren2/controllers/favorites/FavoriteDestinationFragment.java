package com.example.reseplaneraren2.controllers.favorites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.data.internal.JourneyPlannerFactory;
import com.example.reseplaneraren2.model.StopLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavoriteDestinationFragment extends Fragment {

    ListView destinationListView;
    List<String> favoriteDestinations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.favorites_destinations, container, false);
        destinationListView = (ListView) view.findViewById(R.id.favoriteDestinationList);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        favoriteDestinations = new ArrayList<String>();
        favoriteDestinations.addAll(Arrays.asList(new String[]{"Lindholmen", "Wieselgrensplatsen", "Järntorget"}));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), R.layout.simple_list_item, favoriteDestinations);
        destinationListView.setAdapter(adapter);
    }
}
