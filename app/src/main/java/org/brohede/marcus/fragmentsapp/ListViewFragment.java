package org.brohede.marcus.fragmentsapp;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class ListViewFragment extends android.app.Fragment {

    ListView myListView;
    ArrayAdapter adapter;


    public ListViewFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        MainActivity activity = (MainActivity) getActivity();
        List mountainList = activity.getMountainList();

        final MountainAdapter adapter = new MountainAdapter(getActivity(), (ArrayList<Mountain>) mountainList);
        myListView = (ListView) view.findViewById(R.id.listView);
        myListView.setAdapter(adapter);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MountainDetailsFragment fragment = new MountainDetailsFragment();

                Bundle extras = new Bundle();
                Mountain mountain = adapter.getItem(i);

                extras.putString("mountain_name", mountain.getName());
                extras.putString("mountain_location", mountain.getLocation());
                extras.putString("mountain_height", Integer.toString(mountain.getHeight()));
                extras.putString("mountain_image", mountain.getImage());

                fragment.setArguments(extras);

                android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();


                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    transaction.replace(R.id.details_fragment_container, fragment);
                } else {
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                }

                transaction.commit();


            }
        });

        return view;
    }


}
