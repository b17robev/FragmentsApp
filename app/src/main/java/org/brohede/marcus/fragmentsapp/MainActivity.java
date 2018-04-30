package org.brohede.marcus.fragmentsapp;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class MainActivity extends AppCompatActivity {
    private static final String[] mountainNames = {"Matterhorn","Mont Blanc","Denali"};
    private static final String[] mountainLocations = {"Alps","Alps","Alaska"};
    private static final int[] mountainHeights ={4478,4808,6190};
    public static final List<Mountain> MOUNTAINS = new ArrayList<Mountain>();

    ArrayList<Mountain> mountainList = new ArrayList<>();
    ListView myListView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchData getJson = new FetchData();
        getJson.execute();



        /*
        TODO: You should create an app that uses fragments and orientation

        TODO: 1 - Create a fragment (list) to hold a ListView of Mountains
        See: https://developer.android.com/training/basics/fragments/fragment-ui.html

        TODO: 2 - Create a fragment (blank) to hold a details view of Mountain
        See: https://developer.android.com/training/basics/fragments/fragment-ui.html

        TODO: 3 - Create a separate layout for landscape orientation
        See: https://developer.android.com/training/multiscreen/screensizes.html
        See: https://developer.android.com/training/multiscreen/screensizes.html#alternative-layouts

        TODO: 4 - The layout in portrait mode should only display the list fragment and when
                  pressing a list item the details fragment should replace the list fragment
                  and show all the Mountain data.

        TODO: 5 - The layout in landscape mode should display both the list fragment and the
                  details fragment. The details fragment should display details of the currently
                  selected list item.

        */
    }

    public List getMountainList(){
        return mountainList;
    }

    private class FetchData extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            // Implement a parsing code that loops through the entire JSON and creates objects
            // of our newly created Mountain class
            try
            {
                JSONArray mountains = new JSONArray(o);

                for(int i = 0; i < mountains.length(); i++){
                    JSONObject mountain = mountains.getJSONObject(i);

                    String name = mountain.getString("name");
                    String location = mountain.getString("location");
                    int height = mountain.getInt("size");

                    String auxData = mountain.getString("auxdata");
                    JSONObject aux = new JSONObject(auxData);
                    String imageUrl = aux.getString("img");
                    String wikipediaUrl = aux.getString("url");

                    Mountain m = new Mountain(name, height, location, imageUrl, wikipediaUrl);
                    mountainList.add(m);
                }
            }
            catch( JSONException e) {
                e.printStackTrace();
            }

            /*
            FragmentManager fragmentManager = getSupportFragmentManager();
            ListViewFragment listView = (ListViewFragment)getSupportFragmentManager().findFragmentById(R.id.list);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.list, listView);
            fragmentTransaction.commit();
            */



        }
    }
}
