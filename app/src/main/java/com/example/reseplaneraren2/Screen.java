package com.example.reseplaneraren2;

import android.support.v4.app.Fragment;

import com.example.reseplaneraren2.controllers.departuredisplay.DepartureDisplayController;
import com.example.reseplaneraren2.controllers.favorites.FavoritesController;
import com.example.reseplaneraren2.controllers.nexttrip.NextTripController;
import com.example.reseplaneraren2.controllers.searchtrip.SearchTripController;
import com.example.reseplaneraren2.controllers.ticket.MyTicketFragment;
import com.example.reseplaneraren2.controllers.ticket.TicketController;

public enum Screen {

    SEARCH_TRIP(new SearchTripController(),1),
    NEXT_TRIP(new NextTripController(),2),
    FAVORITES(new FavoritesController(),3),
    TICKET(new TicketController(),4),

    DEPARTURE_DISPLAY (new DepartureDisplayController(),5),
    MY_TICKET_DISPLAY (new MyTicketFragment(),6),

    DEFAULT(new Fragment(),0); // borde vara den initiala screen



    private Fragment fragment;

    private int id;

    Screen(Fragment fragment,int id) {
        this.fragment = fragment;
        this.id =id;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public int getId(){ return id; }
}
