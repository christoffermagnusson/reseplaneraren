package com.example.reseplaneraren2.controllers.initialsetup;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.reseplaneraren2.Screen;

import java.util.ArrayList;

/**
 * Created by christoffer on 2017-05-08.
 */

public class InitialSetupAdapter extends ArrayAdapter {

    private Context context;
    private int viewId;
    private ArrayList<Integer> screens;

    public InitialSetupAdapter(Context context, int viewId, ArrayList<Integer> screens){
        super(context,viewId,screens);
        this.context=context;
        this.viewId=viewId;
        this.screens=screens;
    }

    public Integer getItem(int position){ return screens.get(position);}


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup ){
        try{
            if(convertView==null){
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                convertView = inflater.inflate(viewId, viewGroup, false);
            }
        } catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        return convertView;
    }


}
