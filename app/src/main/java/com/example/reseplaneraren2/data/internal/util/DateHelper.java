package com.example.reseplaneraren2.data.internal.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {

    public static String getDate(Calendar date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        sdf.setCalendar(date);
        String d = sdf.format(date.getTime());
        return d;
    }

    public static String getTime(Calendar time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setCalendar(time);
        String t = sdf.format(time.getTime());
        return t;
    }
}
