package com.example.reseplaneraren2.controllers.searchtrip;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
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

    private EditText dateField;
    private Calendar selectedDate;
    private EditText timeField;
    private Calendar selectedTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_trip_layout, container, false);

        dateField = (EditText) view.findViewById(R.id.dateField);
        timeField = (EditText) view.findViewById(R.id.timeField);
        setupDefaults();
        setupListeners();

        ((MainActivity)getActivity()).changeTitle("Sök resa");
        return view;
    }

    private void setupDefaults() {
        Calendar c = Calendar.getInstance();
        changeDate(c);
        changeTime(c);
    }

    private void setupListeners() {
        dateField.setInputType(InputType.TYPE_NULL);
        dateField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog();
                }
            }
        });
        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        timeField.setInputType(InputType.TYPE_NULL);
        timeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showTimePickerDialog();
                }
            }
        });
        timeField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });
    }

    void changeTime(Calendar time) {
        this.selectedTime = time;
        timeField.setText(String.format("%02d:%02d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE)));
    }

    void changeDate(Calendar date) {
        this.selectedDate = date;
        dateField.setText(String.format("%d-%02d-%02d", date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, date.get(Calendar.DAY_OF_MONTH)));
    }


    public void showTimePickerDialog() {
        TimePickerFragment newFragment = TimePickerFragment.newInstance(selectedTime);
        newFragment.setParent(this);
        newFragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(selectedDate);
        newFragment.setParent(this);
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private SearchTripController parent;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int hour, minute = -1;
            Bundle b = getArguments();

            if (b.containsKey("Hour") && b.containsKey("Minute")) {
                hour = b.getInt("Hour"); minute = b.getInt("Minute");
            } else {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY); minute = c.get(Calendar.MINUTE);
            }

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public static TimePickerFragment newInstance(Calendar c) {
            TimePickerFragment tpf = new TimePickerFragment();
            if (c != null) {
                Bundle args = new Bundle();
                args.putInt("Hour", c.get(Calendar.HOUR_OF_DAY));
                args.putInt("Minute", c.get(Calendar.MINUTE));
                tpf.setArguments(args);
            }
            return tpf;
        }

        public void setParent(SearchTripController fragment) {
            this.parent = fragment;
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar time = Calendar.getInstance();
            time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            time.set(Calendar.MINUTE, minute);
            parent.changeTime(time);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private SearchTripController parent;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int year, month, day = -1;
            Bundle b = getArguments();

            if (b.containsKey("Year") && b.containsKey("Month") && b.containsKey("Day")) {
                year = b.getInt("Year"); month = b.getInt("Month"); day = b.getInt("Day");
            } else {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR); month = c.get(Calendar.MONTH); day = c.get(Calendar.DAY_OF_MONTH);
            }
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public static DatePickerFragment newInstance(Calendar c) {
            DatePickerFragment dpf = new DatePickerFragment();
            if (c != null) {
                Bundle args = new Bundle();
                args.putInt("Year", c.get(Calendar.YEAR));
                args.putInt("Month", c.get(Calendar.MONTH));
                args.putInt("Day", c.get(Calendar.DAY_OF_MONTH));
                dpf.setArguments(args);
            }
            return dpf;
        }

        public void setParent(SearchTripController fragment) { this.parent = fragment; }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar date = Calendar.getInstance();
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month);
            date.set(Calendar.DAY_OF_MONTH, day);
            parent.changeDate(date);
        }
    }
}
