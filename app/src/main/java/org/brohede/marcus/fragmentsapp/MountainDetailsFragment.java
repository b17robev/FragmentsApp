package org.brohede.marcus.fragmentsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;


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

        String url = getArguments().getString("mountain_image");

        ImageView image = view.findViewById(R.id.mountainImage);

        class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
            ImageView mountainImage;

            public DownloadImageTask(ImageView mountainImage) {
                this.mountainImage = mountainImage;
            }

            protected Bitmap doInBackground(String... urls) {
                String urldisplay = urls[0];
                Bitmap mountain = null;
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    mountain = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return mountain;
            }

            protected void onPostExecute(Bitmap result) {
                mountainImage.setImageBitmap(result);
            }
        }
        new DownloadImageTask(image).execute(url);

        return view;
    }


}
