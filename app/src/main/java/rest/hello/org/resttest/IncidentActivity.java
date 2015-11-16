package rest.hello.org.resttest;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

/**
 * Created by digi on 06.11.2015.
 */
public class IncidentActivity extends AppCompatActivity {


    //Initializating for  Preferences Data
    private SharedPreferences SP;
    String strUserName;
    String strPassword;
    String strServer;
    String strPort;
    boolean demo;
    String url;
    String im, journal;

    Object_Incident incident;

    TextView titleView;
    TextView descView;
    TextView serviceView;
    TextView openByView;
    TextView journalView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_main);

        //Getting intent data from MainActivity
        Intent intent = getIntent();
        im = intent.getStringExtra("im");
        String title = intent.getStringExtra("title");
        String status = intent.getStringExtra("status");
        String description = intent.getStringExtra("description");
        String service = intent.getStringExtra("service");
        String openby = intent.getStringExtra("openby");
        journal = intent.getStringExtra("journal");

        titleView = (TextView) findViewById(R.id.title_value);
        descView = (TextView) findViewById(R.id.description_value);
        serviceView = (TextView) findViewById(R.id.service_value);
        openByView = (TextView) findViewById(R.id.openby_value);
        journalView = (TextView) findViewById(R.id.journal_value);

        titleView.setText(title);
        descView.setText(description);
        serviceView.setText(service);
        openByView.setText(openby);
        journalView.setText(journal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.incident_label) + " " + im);
        toolbar.setSubtitle(status);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        strUserName = SP.getString("username", "falcon");
        strPassword = SP.getString("password", "");
        strServer = SP.getString("server", "http://192.168.0.26");
        strPort = SP.getString("port", "13080");
        demo = SP.getBoolean("demo", false);
        // The connection URL - Building a parameterized URL
        url = strServer + ":" + strPort + "/SM/9/rest/incidents/" + im + "/action/";




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Update"));
        tabLayout.addTab(tabLayout.newTab().setText("Resolve"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(),im);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronizing with Service Manager", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }

        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Updating incident", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), IncidentUpdate.class);
                intent.putExtra("im", im);
                intent.putExtra("journal", journal);
                startActivity(intent);


            }

        });


        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Resolving incident", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), IncidentResolve.class);
                intent.putExtra("im", im);
                startActivity(intent);

            }

        });*/

        if (!demo) new HttpRequestTask().execute();


    }



    private class HttpRequestTask extends AsyncTask<Void, Void, Object_Incident> {


        @Override
        protected Object_Incident doInBackground(Void... params) {
            try {

                // The connection URL - Building a parameterized URL
                String url = strServer + ":" + strPort + "/SM/9/rest/incidents/"+im;
                Log.e("MainActivity", url);
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
                String paramURL = builder.build().encode().toUri().toString();

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object_Incident> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(paramURL, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Object_Incident.class);
                incident = response.getBody();
                Log.e("response", response.toString());
                Log.e("body", response.getBody().toString());
                Log.e("Header", response.getHeaders().toString());

                return incident;


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
//                notifyMe(e.getMessage());

            }

            return null;
        }

        @Override
        protected void onPostExecute(final Object_Incident incident) {
/*            titleView.setText(incident.getTitle());
            descView.setText(incident.toString());
            serviceView.setText(incident.getService());
            openByView.setText(incident.getOpenedBy());
            journalView.setText(incident.getJournalUpdates().toString());*/

        }
    }











}