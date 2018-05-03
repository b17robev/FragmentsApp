package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MountainDetailsFragment extends android.app.Fragment {


    public MountainDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mountain_details, container, false);

        TextView nameText = view.findViewById(R.id.mtn_name);
        nameText.setText(getArguments().getString("mountain_name"));

        TextView LocationText = view.findViewById(R.id.mtn_location);
        LocationText.setText(getArguments().getString("mountain_location"));

        TextView HeightText = view.findViewById(R.id.mtn_height);
        HeightText.setText(getArguments().getString("mountain_height"));

        return view;
    }

}
