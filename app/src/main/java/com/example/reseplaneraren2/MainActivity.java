package com.example.reseplaneraren2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        System.out.println("Testing_new_commit");
    }

    private void inflate(Screen screenToDisplay){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, screenToDisplay.getFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
