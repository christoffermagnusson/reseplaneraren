package com.example.reseplaneraren2.controllers.initialsetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reseplaneraren2.R;

/**
 * Created by christoffer on 2017-05-09.
 */

public class InitialSetupController extends Fragment{

    private ViewPager initialSetupViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.initial_setup,container,false);

        //initialSetupViewPager = (ViewPager)

        return view;
    }

}
