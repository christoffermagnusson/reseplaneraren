package com.example.reseplaneraren2.controllers.initialsetup;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-09.
 */

public class InitialSetupAdapter extends FragmentPagerAdapter {

    private Context context;

    private static final int NUM_ITEMS = 4;

    private ArrayList<UISetupFragment> fragments = new ArrayList<UISetupFragment>();

    public InitialSetupAdapter(Context context, FragmentManager manager){
        super(manager);
        this.context = context;

        setupFragmentsList();


    }

    private void setupFragmentsList(){
        InitialSetupFragmentSearchTrip searchTripFragment = new InitialSetupFragmentSearchTrip();

        fragments.add(searchTripFragment);

        InitialSetupFragmentNextTrip nextTripFragment = new InitialSetupFragmentNextTrip();

        fragments.add(nextTripFragment);

        InitialSetupFragmentFavorites favoritesFragment = new InitialSetupFragmentFavorites();

        fragments.add(favoritesFragment);

        InitialSetupFragmentTicket ticketFragment = new InitialSetupFragmentTicket();

        fragments.add(ticketFragment);

    }



    @Override
    public Fragment getItem(int position) {

                return (Fragment) fragments.get(position);

    }



    @Override
    public int getCount() {
        return NUM_ITEMS;
    }



}
