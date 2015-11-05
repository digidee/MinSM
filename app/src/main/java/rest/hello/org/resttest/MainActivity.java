package rest.hello.org.resttest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    //*****************
    //Listview
    //****************
    private String[] textIM, textTitle;
    private Integer[] image_id;

    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronizing with Service Manager", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                new HttpRequestTask().execute();
            }
        });


        act = this;


    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Object_Incident> {


        @Override
        protected Object_Incident doInBackground(Void... params) {
            try {


                //Get Preferences Data
                SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String strUserName = SP.getString("username", "falcon");
                String strPassword = SP.getString("password", "");
                String strServer = SP.getString("server", "http://192.168.0.26");
                String strPort = SP.getString("port", "13080");
                String strIncidentCount = SP.getString("incidentCount", "10");
                boolean bAppUpdates = SP.getBoolean("notifyNew", false);


                // The connection URL - Building a parameterized URL
                String url = strServer + ":" + strPort + "/SM/9/rest/incidents";

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("view", "expand")
                        .queryParam("count", strIncidentCount)
                        .queryParam("assignee.name", strUserName);
                String paramURL = builder.build().encode().toUri().toString();

                //Logging username, password and url
                Log.e("MainActivity", "Username: " + strUserName + " - Password: " + strPassword);
                Log.e("MainActivity", "url: " + paramURL);


                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object_Incident> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(paramURL, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Object_Incident.class);

                Log.e("MainActivity", response.getBody().getCount().toString());

                return response.getBody();

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object_Incident incident) {
            TextView incidentView = (TextView) findViewById(R.id.totalview_value);
            TextView incidentCount = (TextView) findViewById(R.id.totalcount_value);
            TextView incidentName = (TextView) findViewById(R.id.resourcename_value);
            incidentView.setText(incident.getCount().toString());
            incidentCount.setText(incident.getTotalcount().toString());
            incidentName.setText(incident.getResourceName());


            Log.e("MainActivity", "Count: " + incident.getCount());
            Log.e("MainActivity", "Total Count: " + incident.getTotalcount());


            //*****************
            //Listview
            //****************
            textIM = new String[incident.getCount()];
            textTitle = new String[incident.getCount()];

            image_id = new Integer[incident.getCount()];
            for (int i = 0; i < incident.getCount(); i++) {
                textIM[i] = incident.getContent().get(i).getIncident().getIncidentID().toString();
                textTitle[i] = incident.getContent().get(i).getIncident().getTitle().toString();
                image_id[i] = R.mipmap.ic_launcher;
            }

            CustomListAdapter adapter = new CustomListAdapter(act, image_id, textIM, textTitle);
            ListView lv = (ListView) findViewById(R.id.listView);
            lv.setAdapter(adapter);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(this, PreferencesActivity.class);
            startActivity(i);
            return true;
        }
        if (id == R.id.action_refresh) {
            new HttpRequestTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
