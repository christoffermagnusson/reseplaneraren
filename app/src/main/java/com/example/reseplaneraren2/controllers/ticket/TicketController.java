package com.example.reseplaneraren2.controllers.ticket;

/**
 * Created by christoffer on 2017-05-02.
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;


public class TicketController extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.ticket_layout, container, false);

        switchTicketView(new MyTicketFragment());

        Button myTicketButton = (Button) view.findViewById(R.id.myTicketButton);
        Button buyTicketButton = (Button) view.findViewById(R.id.buyTicketButton);
        Button historyButton = (Button) view.findViewById(R.id.historyButton);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.myTicketButton:
                        switchTicketView(new MyTicketFragment());
                        break;
                    case R.id.buyTicketButton:
                        switchTicketView(new BuyTicketFragment());
                        break;
                    case R.id.historyButton:
                        switchTicketView(new HistoryFragment());
                        break;
                }
            }
        };
        myTicketButton.setOnClickListener(listener);
        buyTicketButton.setOnClickListener(listener);
        historyButton.setOnClickListener(listener);


       // return inflater.inflate(R.layout. ticket_layout, container, false);
        return view;
    }

    private void switchTicketView(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.ticketFragmentHolder, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

