package com.example.reseplaneraren2.controllers.initialsetup;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by christoffer on 2017-05-10.
 */

public interface UISetupFragment {

    // Tag

    void setLabel(int resourceLabel);

    int getLabel();

    void setIconReference(int resourceIcon);

    int getIconReference();

    int getLayoutResource();

}
