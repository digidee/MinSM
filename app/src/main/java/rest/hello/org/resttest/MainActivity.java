package rest.hello.org.resttest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //**************************
    // TEST OBJECT JSON
    //*************************
    ObjectMapper mapper;
    Object_TestJSON JSONObject;
    //**************************
    // TEST OBJECT JSON
    //*************************


    //Progress bar
    ProgressDialog pDialog;


    //*****************
    //Listview
    //****************
    private String[] textIM, textTitle, textStatus;
    private Integer[] image_id;
    private String crash;
    Object_Incident incident;
    private Activity act;
    ListView lv;
    CustomListAdapter adapter;

    //*****************
    //Preference Data Declaration
    //****************
    SharedPreferences SP;
    String strUserName;
    String strPassword;
    String strServer;
    String strPort;
    String strIncidentCount;
    boolean bAppUpdates;
    boolean demoMode;

    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Get Preferences Data
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        demoMode = SP.getBoolean("demo", false);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting Incidents ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        act = this;

        final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                if (!demoMode) new HttpRequestTask().execute();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        demoMode = SP.getBoolean("demo", false);
        if (demoMode) JSONObjectList();
        else new HttpRequestTask().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        demoMode = SP.getBoolean("demo", false);
        if (demoMode) JSONObjectList();
        else new HttpRequestTask().execute();
    }

    public void JSONObjectList() {
        try {
            //**************************
            // TEST OBJECT JSON
            //*************************
            //using JSONObject as test
            //JSON from String to Object
            mapper = new ObjectMapper();
            JSONObject = new Object_TestJSON();
            incident = mapper.readValue(JSONObject.getJSONObject(), Object_Incident.class);

            createList(incident);

        } catch (Exception e) {
            crash = e.getMessage();
            Log.e("MainActivity", e.getMessage(), e);

        }

    }

    public void createList(final Object_Incident incident) {
        textIM = new String[incident.getCount()];
        textStatus = new String[incident.getCount()];
        textTitle = new String[incident.getCount()];
        image_id = new Integer[incident.getCount()];
        for (int i = 0; i < incident.getCount(); i++) {
            textIM[i] = incident.getContent().get(i).getIncident().getIncidentID().toString();
            textTitle[i] = incident.getContent().get(i).getIncident().getTitle().toString();
            textStatus[i] = incident.getContent().get(i).getIncident().getStatus().toString();
            //image_id[i] = R.mipmap.ic_launcher;
        }
        adapter = new CustomListAdapter(act, textIM, textStatus, textTitle);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        Intent intent = new Intent(getApplicationContext(), IncidentActivity.class);
                        intent.putExtra("im", incident.getContent().get(lv.getPositionForView(view)).getIncident().getIncidentID().toString());
                        intent.putExtra("title", incident.getContent().get(lv.getPositionForView(view)).getIncident().getTitle().toString());
                        intent.putExtra("status", incident.getContent().get(lv.getPositionForView(view)).getIncident().getStatus().toString());
                        intent.putExtra("description", incident.getContent().get(lv.getPositionForView(view)).getIncident().getDescription().toString());
                        intent.putExtra("service", incident.getContent().get(lv.getPositionForView(view)).getIncident().getService().toString());
                        startActivity(intent);
                    }
                }
        );
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Object_Incident> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected Object_Incident doInBackground(Void... params) {
            try {

                strUserName = SP.getString("username", "falcon");
                strPassword = SP.getString("password", "");
                strServer = SP.getString("server", "http://192.168.0.26");
                strPort = SP.getString("port", "13080");
                strIncidentCount = SP.getString("incidentCount", "10");
                bAppUpdates = SP.getBoolean("notifyNew", false);

                // The connection URL - Building a parameterized URL
                String url = strServer + ":" + strPort + "/SM/9/rest/incidents";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("view", "expand")
                        .queryParam("count", strIncidentCount)
                        .queryParam("assignee.name", strUserName)
                        .queryParam("sort", "number:descending")
                        .queryParam("problem.status~", "Closed");
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
                Log.e("MainActivity", response.getBody().getTotalcount().toString());
                incident = response.getBody();
                return incident;


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
//                notifyMe(e.getMessage());

            }

            return null;
        }

        @Override
        protected void onPostExecute(final Object_Incident incident) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pDialog.dismiss();
                }
            }, 1000L);
            if (incident != null) {
                createList(incident);
            }

        }
    }


    public void notifyMe(String m) {
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, m, duration);
        toast.show();
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
            Intent i = new Intent(this, SettingsPreferencesActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_refresh) {
            if (!demoMode) new HttpRequestTask().execute();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    //TESTING MENU
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.user_settings) {
            Intent i = new Intent(this, SettingsUserActivity.class);
            startActivity(i);
        } else if (id == R.id.view_settings) {
            Intent i = new Intent(this, SettingsViewActivity.class);
            startActivity(i);

        } else if (id == R.id.notifications) {
            Intent i = new Intent(this, SettingsNotificationActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
