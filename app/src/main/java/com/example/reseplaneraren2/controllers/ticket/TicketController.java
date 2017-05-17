package com.example.reseplaneraren2.controllers.ticket;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;


public class TicketController extends Fragment {

    private FragmentTabHost tabHost;

    private MainActivity mParent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mParent = (MainActivity) context;
        } catch (final ClassCastException e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void onStart() {
        super.onStart();
        mParent.setCurrentScreen(Screen.TICKET);
    }



}

