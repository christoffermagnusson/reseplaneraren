package com.example.reseplaneraren2.controllers.initialsetup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reseplaneraren2.R;

/**
 * Created by christoffer on 2017-05-10.
 */

public class InitialSetupFragmentFavorites extends Fragment implements UISetupFragment {

    private int label;
    private int iconReference;

    private TextView labelTextView;
    private ImageView iconImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.initial_setup_listitem,container,false);

        labelTextView = (TextView)view.findViewById(R.id.screenDescription);
        iconImageView = (ImageView)view.findViewById(R.id.screenLabelImage);

        labelTextView.setText(getString(R.string.favorites_setup));
        iconImageView.setImageResource(R.drawable.ic_favorites_24dp);

        return view;
    }



    public void setLabel(int label) { // reference till en resurs, byter beroende på skärm som ska visas!
        String labelText = getString(label);
        labelTextView.setText(labelText);
        this.label = label;

    }

    public void setIconReference(int iconReference) { // reference till en drawable
        iconImageView.setBackgroundResource(iconReference);
        this.iconReference = iconReference;
    }

    public int getLabel() {
        return label;
    }

    public int getIconReference() {
        return iconReference;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.favorites_layout;
    }
}
