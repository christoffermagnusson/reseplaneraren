package com.example.reseplaneraren2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.reseplaneraren2.controllers.departuredisplay.DepartureDisplayController;
import com.example.reseplaneraren2.model.StopLocation;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;

    public static int currentScreen;  // to use when setting the start page


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search_trip:
                    inflate(Screen.SEARCH_TRIP);
                    currentScreen=R.id.navigation_search_trip;
                    return true;
                case R.id.navigation_next_trip:
                    inflate(Screen.NEXT_TRIP);
                    currentScreen=R.id.navigation_next_trip;
                    return true;
                case R.id.navigation_favorites:
                    inflate(Screen.FAVORITES);
                    currentScreen=R.id.navigation_favorites;
                    return true;
                case R.id.navigation_ticket:
                    inflate(Screen.TICKET);
                    currentScreen=R.id.navigation_ticket;
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



        // Hämtar in den lagrade startsidan i appen
        navigationView.setSelectedItemId(getStartScreen());
    }

    // denna metod ska kopplas till någon form av menyval , inställningar eller liknande som ställer in ny startsida
    private void saveStartScreen(int screen){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.start_screen), screen);
        editor.commit();
    }

    private int getStartScreen(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        int screen = sharedPref.getInt(getString(R.string.start_screen),0);

        return screen;
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings: {
                Log.d("Sammitest", "Kom hit iaf");
                break;

            }

        }
        return false;
    }
}
