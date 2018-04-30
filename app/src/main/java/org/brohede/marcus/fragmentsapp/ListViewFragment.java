package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ListViewFragment extends Fragment {

    ListView myListView;
    ArrayAdapter adapter;


    public ListViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        List mountainList = activity.getMountainList();

        adapter = new ArrayAdapter<Mountain>(getActivity(), android.R.layout.simple_list_item_1, mountainList);
        Log.d("hiho", "Added data");
        Log.d("hiho", mountainList.toString());



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);;


        myListView = (ListView) view.findViewById(R.id.listView);
        myListView.setAdapter(adapter);


        return view;
    }


}
