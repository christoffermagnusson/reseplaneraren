package com.example.reseplaneraren2.controllers.initialsetup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by christoffer on 2017-05-09.
 */

public class InitialSetupAdapter extends FragmentPagerAdapter {



    public InitialSetupAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
