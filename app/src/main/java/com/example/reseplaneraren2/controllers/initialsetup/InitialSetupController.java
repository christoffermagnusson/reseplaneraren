package com.example.reseplaneraren2.controllers.initialsetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;

import java.util.ArrayList;


/**
 * Created by christoffer on 2017-05-09.
 */

public class InitialSetupController extends Fragment{



    private ViewPager initialSetupViewPager;

    private InitialSetupAdapter initialSetupAdapter;

    private Button selectButton;

    private ArrayList<ImageView> indicatorIcons = new ArrayList<ImageView>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.initial_setup,container,false);

        initialSetupAdapter = new InitialSetupAdapter(getContext(),getActivity().getSupportFragmentManager());
        initialSetupViewPager = (ViewPager)view.findViewById(R.id.initialVP);
        initialSetupViewPager.setAdapter(initialSetupAdapter);

        initIndicatorIcons(view);

        initialSetupViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position){
                changeIndicatorColor(position);
                Log.d("Page changed","Position "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        selectButton = (Button)view.findViewById(R.id.selectButton);

        ((MainActivity)getActivity()).hideToolbar();

        selectButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                UISetupFragment currentFragment = (UISetupFragment) initialSetupAdapter.getItem(initialSetupViewPager.getCurrentItem());
                MainActivity activity = (MainActivity) getActivity();
                activity.saveStartScreen(currentFragment.getLayoutResource());
                Toast.makeText(getActivity(),"Startsida vald",Toast.LENGTH_LONG).show();
                activity.inflateStartScreen();
                ((MainActivity)getActivity()).showToolbar();
            }
        });

        return view;
    }


    private void initIndicatorIcons(View view){

            indicatorIcons.add((ImageView) view.findViewById(R.id.indicator_dot_1));
            indicatorIcons.add((ImageView) view.findViewById(R.id.indicator_dot_2));
            indicatorIcons.add((ImageView) view.findViewById(R.id.indicator_dot_3));
            indicatorIcons.add((ImageView) view.findViewById(R.id.indicator_dot_4));
        for(ImageView icon : indicatorIcons){
            icon.setImageResource(R.drawable.ic_indicator_dot);
        }

    }

    private void changeIndicatorColor(int position){
        for(ImageView icon : indicatorIcons){
            if(indicatorIcons.indexOf(icon) == position){
                icon.setImageResource(R.drawable.ic_indicator_dot_selected);
            } else{
                icon.setImageResource(R.drawable.ic_indicator_dot);
            }
        }
    }


}
