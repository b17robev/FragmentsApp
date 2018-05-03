package org.brohede.marcus.fragmentsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
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


        Log.d("hiho", "Added data");
        Log.d("hiho", mountainList.toString());

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.list_item_textview, R.id.mountain_name, mountainList);
        myListView = (ListView) view.findViewById(R.id.listView);
        myListView.setAdapter(adapter);

        Log.d("hiho", "return view");

        return view;
    }


}
