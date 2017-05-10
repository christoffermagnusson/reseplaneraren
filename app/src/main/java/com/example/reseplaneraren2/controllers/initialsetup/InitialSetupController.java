package com.example.reseplaneraren2.controllers.initialsetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;

/**
 * Created by christoffer on 2017-05-09.
 */

public class InitialSetupController extends Fragment{

    private ViewPager initialSetupViewPager;

    private InitialSetupAdapter initialSetupAdapter;

    private Button selectButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.initial_setup,container,false);

        initialSetupAdapter = new InitialSetupAdapter(getContext(),getActivity().getSupportFragmentManager());
        initialSetupViewPager = (ViewPager)view.findViewById(R.id.initialVP);
        initialSetupViewPager.setAdapter(initialSetupAdapter);

        selectButton = (Button)view.findViewById(R.id.selectButton);


        selectButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                UISetupFragment currentFragment = (UISetupFragment) initialSetupAdapter.getItem(initialSetupViewPager.getCurrentItem());
                MainActivity activity = (MainActivity) getActivity();
                activity.saveStartScreen(currentFragment.getLayoutResource());
                Toast.makeText(getActivity(),"Startsida vald",Toast.LENGTH_LONG).show();
                activity.inflateStartScreen();

            }
        });

        return view;
    }



}
