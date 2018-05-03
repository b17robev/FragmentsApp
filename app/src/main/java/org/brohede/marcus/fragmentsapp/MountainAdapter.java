package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roberth on 5/2/2018.
 */

public class MountainAdapter extends ArrayAdapter<Mountain> {
    private Context context;
    private List<Mountain> mtnList = new ArrayList<>();

    public MountainAdapter(@NonNull Context c, ArrayList<Mountain> list) {
        super(c, 0, list);
        context = c;
        mtnList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.list_item_textview, parent, false);
        }

        Mountain currentMountain = mtnList.get(position);
        TextView name = listItem.findViewById(R.id.mountain_name);
        name.setText(currentMountain.getName());



        // Return the completed view to render on screen
        return listItem;
    }
}
