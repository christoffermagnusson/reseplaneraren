package com.example.reseplaneraren2.controllers.ticket;

/**
 * Created by christoffer on 2017-05-02.
 */


import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;


public class TicketController extends Fragment {

    private FragmentTabHost tabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ((MainActivity)getActivity()).changeTitle("Biljetter");

        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.ticket_layout);

        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("Din Biljett"), MyTicketFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("Köp Biljett"), BuyTicketFragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("Tab3").setIndicator("Historik"), HistoryFragment.class, null);

        return tabHost;

    }



}

