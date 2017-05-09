package com.example.reseplaneraren2.controllers.initialsetup;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import com.example.reseplaneraren2.R;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by christoffer on 2017-05-09.
 */

public class InitialSetupAdapter extends FragmentPagerAdapter {

    private Context context;

    private static final int NUM_ITEMS = 4;

    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    public InitialSetupAdapter(Context context, FragmentManager manager){
        super(manager);
        this.context = context;

        setupFragmentsList();
    }

    private void setupFragmentsList(){
        InitialSetupFragment searchTripFragment = new InitialSetupFragment();
        //searchTripFragment.setLabel(R.string.search_trip_setup);
        // in med icon
        fragments.add(searchTripFragment);

        InitialSetupFragment nextTripFragment = new InitialSetupFragment();
        //nextTripFragment.setLabel(R.string.next_trip_setup);
        // in med icon
        fragments.add(nextTripFragment);

        InitialSetupFragment favoritesFragment = new InitialSetupFragment();
        //favoritesFragment.setLabel(R.string.favorites_setup);
        // in med icon
        fragments.add(favoritesFragment);

        InitialSetupFragment ticketFragment = new InitialSetupFragment();
        //ticketFragment.setLabel(R.string.ticket_setup);
        // in med icon
        fragments.add(ticketFragment);

    }

    @Override
    public Fragment getItem(int position) {

                return fragments.get(position);

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }



}
