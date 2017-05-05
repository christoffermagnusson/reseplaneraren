package com.example.reseplaneraren2.Util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by samuel on 2017-05-05.
 */

public class Utils {

    public static void hideKeyboard(@NonNull Activity activity){
        View v = activity.getCurrentFocus();
        if (v != null){
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
