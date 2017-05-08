package com.example.reseplaneraren2.controllers.searchtrip;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.reseplaneraren2.MainActivity;
import com.example.reseplaneraren2.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchTripController extends Fragment {

    private ListView timeListView;
    private List<String> timeEntry;
    private ArrayAdapter<String> timeAdapter;

    private ListView transferListView;
    private List<String> transferEntry;
    private ArrayAdapter<String> transferAdapter;

    // Borde skapa nytt fragment att visa i dialogruta, som inkluderar både datum och tid...Samt snygga till saker här.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_trip_layout, container, false);

        timeListView = (ListView) view.findViewById(R.id.timeList);
        transferListView = (ListView) view.findViewById(R.id.transferList);
        setupTimeDialog();
        setupListeners();

        ((MainActivity)getActivity()).changeTitle("Sök resa");
        return view;
    }

    private void setupTimeDialog() {
        timeEntry = new ArrayList<String>();
        timeEntry.add("5/5/17 - 10:48");
        timeAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.simple_list_item, timeEntry);
        timeListView.setAdapter(timeAdapter);
    }

    void changeTime(int hour, int minute) {
        timeEntry.clear();
        timeEntry.add(String.valueOf(hour) + ":" + String.valueOf(minute));
        timeAdapter.notifyDataSetChanged();
    }

    private void setupListeners() {
        timeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showTimePickerDialog();
            }
        });
    }

    public void showTimePickerDialog() {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setParent(this);
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private SearchTripController parent;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void setParent(SearchTripController fragment) {
            this.parent = fragment;
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            parent.changeTime(hourOfDay, minute);
        }
    }
}
