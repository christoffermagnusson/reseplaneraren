package com.example.reseplaneraren2;

import android.support.v4.app.Fragment;

import com.example.reseplaneraren2.controllers.departuredisplay.DepartureBoardDisplayController;
import com.example.reseplaneraren2.controllers.favorites.FavoritesController;
import com.example.reseplaneraren2.controllers.initialsetup.InitialSetupController;
import com.example.reseplaneraren2.controllers.nexttrip.NextTripController;
import com.example.reseplaneraren2.controllers.searchtrip.SearchTripController;
import com.example.reseplaneraren2.controllers.ticket.MyTicketFragment;
import com.example.reseplaneraren2.controllers.ticket.TicketController;

public enum Screen {

    INITIAL_SETUP(new InitialSetupController()),

    SEARCH_TRIP(new SearchTripController(),R.layout.search_trip_layout,R.string.search_trip, R.id.navigation_search_trip),
    NEXT_TRIP(new NextTripController(),R.layout.next_trip_layout,R.string.next_trip, R.id.navigation_next_trip),
    FAVORITES(new FavoritesController(),R.layout.favorites_layout,R.string.favorites, R.id.navigation_favorites),
    TICKET(new TicketController(),R.layout.ticket_layout,R.string.ticket, R.id.navigation_ticket),

    DEPARTURE_DISPLAY (new DepartureBoardDisplayController()),
    MY_TICKET_DISPLAY (new MyTicketFragment()),

    DEFAULT(new Fragment()); // borde vara den initiala screen



    private Fragment fragment;
    private int resourceID;
    private int navigationID;
    private int nameID;

    Screen(Fragment fragment, int resourceID, int nameID, int navigationID) {
        this.fragment = fragment;
        this.resourceID = resourceID;
        this.nameID = nameID;
        this.navigationID = navigationID;
    }

    Screen(Fragment fragment){
        this.fragment=fragment;

    }

    public Fragment getFragment() {
        return fragment;
    }

    public int getResourceID(){ return resourceID; }

    public int getNavigationID(){ return navigationID; }

    public int getNameID(){
        return nameID;
    }


}
