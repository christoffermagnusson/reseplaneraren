package com.example.reseplaneraren2;

import android.support.v4.app.Fragment;

import com.example.reseplaneraren2.controllers.departuredisplay.DepartureBoardDisplayController;
import com.example.reseplaneraren2.controllers.favorites.FavoritesController;
import com.example.reseplaneraren2.controllers.nexttrip.NextTripController;
import com.example.reseplaneraren2.controllers.searchtrip.SearchTripController;
import com.example.reseplaneraren2.controllers.ticket.MyTicketFragment;
import com.example.reseplaneraren2.controllers.ticket.TicketController;

public enum Screen {

    //INITIAL_SETUP(new InitialSetupController()),

    SEARCH_TRIP(new SearchTripController()),
    NEXT_TRIP(new NextTripController()),
    FAVORITES(new FavoritesController()),
    TICKET(new TicketController()),

    DEPARTURE_DISPLAY (new DepartureBoardDisplayController()),
    MY_TICKET_DISPLAY (new MyTicketFragment()),

    DEFAULT(new Fragment()); // borde vara den initiala screen



    private Fragment fragment;



    Screen(Fragment fragment) {
        this.fragment = fragment;
    }

    public Fragment getFragment() {
        return fragment;
    }


}
