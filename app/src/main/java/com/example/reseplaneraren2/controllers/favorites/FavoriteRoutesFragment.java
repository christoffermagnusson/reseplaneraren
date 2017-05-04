package com.example.reseplaneraren2.controllers.favorites;

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

public class FavoriteRoutesFragment extends Fragment {

    ListView routeListView;
    List<String> favoriteRoutes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.favorites_routes, container, false);
        routeListView = (ListView) view.findViewById(R.id.favoriteRouteList);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        favoriteRoutes = new ArrayList<String>();
        favoriteRoutes.addAll(Arrays.asList(new String[]{"Lindholmen -> Wieselgrensplatsen", "Wieselgrensplatsen -> Järntorget", "Friskväderstorget -> Wieselgrensplatsen"}));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getContext(), R.layout.simple_list_item, favoriteRoutes);
        routeListView.setAdapter(adapter);
    }
}
