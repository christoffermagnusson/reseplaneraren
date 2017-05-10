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

public class InitialSetupFragmentTicket extends Fragment implements UISetupFragment {

    private int label;
    private int iconReference;

    private TextView labelTextView;
    private ImageView iconImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.initial_setup_listitem,container,false);

        labelTextView = (TextView)view.findViewById(R.id.screenDescription);
        iconImageView = (ImageView)view.findViewById(R.id.screenLabelImage);

        labelTextView.setText(getString(R.string.ticket_setup));
        iconImageView.setImageResource(R.drawable.ic_ticket_24dp);

        return view;
    }




    @Override
    public void setLabel(int resourceLabel) {
        String labelText = getString(label);
        labelTextView.setText(labelText);
        this.label = label;
    }

    @Override
    public int getLabel() {
        return label;
    }

    @Override
    public void setIconReference(int resourceIcon) {
        iconImageView.setBackgroundResource(iconReference);
        this.iconReference = iconReference;
    }

    @Override
    public int getIconReference() {
        return iconReference;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.ticket_layout;
    }
}
