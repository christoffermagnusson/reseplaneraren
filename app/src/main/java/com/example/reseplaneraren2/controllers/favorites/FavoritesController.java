package com.example.reseplaneraren2.controllers.favorites;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;
import com.example.reseplaneraren2.Screen;
import com.example.reseplaneraren2.controllers.ticket.BuyTicketFragment;
import com.example.reseplaneraren2.controllers.ticket.HistoryFragment;
import com.example.reseplaneraren2.controllers.ticket.MyTicketFragment;

public class FavoritesController extends Fragment {

    private FragmentTabHost tabHost;

    private MainActivity mParent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mParent = (MainActivity) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        ((MainActivity)getActivity()).changeTitle("Favoriter");

        tabHost = new FragmentTabHost(getActivity());
        tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.favorites_layout);

        // För sista argumentet kan man stoppa in en Bundle ifall man behöver ge argument
        tabHost.addTab(tabHost.newTabSpec("Tab1").setIndicator("Destinationer"),
                FavoriteDestinationFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("Tab2").setIndicator("Rutter"),
                FavoriteRoutesFragment.class, null);

        return tabHost;
    }

    @Override
    public void onStart() {
        super.onStart();
        mParent.setCurrentScreen(Screen.FAVORITES);
    }

}
