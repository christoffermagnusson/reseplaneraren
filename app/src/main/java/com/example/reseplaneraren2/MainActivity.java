package com.example.reseplaneraren2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.reseplaneraren2.controllers.departuredisplay.DepartureDisplayController;
import com.example.reseplaneraren2.model.StopLocation;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search_trip:
                    inflate(Screen.SEARCH_TRIP);
                    return true;
                case R.id.navigation_next_trip:
                    inflate(Screen.NEXT_TRIP);
                    return true;
                case R.id.navigation_favorites:
                    inflate(Screen.FAVORITES);
                    return true;
                case R.id.navigation_ticket:
                    inflate(Screen.TICKET);
                    return true;
            }
            return false;
        }
    };

    public void changeTitle(String newTitle){
    Toolbar mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        TextView mTitle = (TextView) mToolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(newTitle);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        System.out.println("Testing_new_commit");
    }

    public void inflate(Screen screenToDisplay){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, screenToDisplay.getFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void inflateDepartureDisplay(Screen screenToDisplay, StopLocation stopLocation){
        inflate(screenToDisplay);
        DepartureDisplayController controller = (DepartureDisplayController) screenToDisplay.getFragment();
        controller.setStopLocation(stopLocation);

    }
}
