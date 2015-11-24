package rest.hello.org.resttest;

/**
 * Created by digi on 16.11.2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

public class ListActivityActivity extends AppCompatActivity {


    boolean demo;
    String url;
    String im;
    private String[] textType, textOperator, textDescription;



    //*****************
    //Listview
    //****************
    private String[] textIM, textTitle, textStatus, textCrit, textDate;
    Object_Incident incident;
    ListView lv;
    ListAdapterIncident adapter;

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
        toolbar.setTitle("Activity");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //Getting intent data from MainActivity
        Intent intent = getIntent();
        im = intent.getStringExtra("im");
        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        strUserName = SP.getString("username", "falcon");
        strPassword = SP.getString("password", "");
        strServer = SP.getString("server", "http://192.168.0.26");
        strPort = SP.getString("port", "13080");
        demo = SP.getBoolean("demo", false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!demo) new HttpRequestTask3().execute();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!demo) new HttpRequestTask3().execute();
    }


    private class HttpRequestTask3 extends AsyncTask<Void, Void, Object_Activities> {


        @Override
        protected Object_Activities doInBackground(Void... params) {
            try {

                url = strServer + ":" + strPort + "/SM/9/rest/Activity?number=" + im + "&view=expand";
                Log.e("MainActivity", "url: " + url);


                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object_Activities> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Object_Activities.class);

                Log.e("TabFragmentUpdate", response.getBody().getCount().toString());
                Log.e("TabFragmentUpdate", response.getBody().getTotalcount().toString());
                Log.e("TabFragmentUpdate-type", response.getBody().getContent().get(0).getActivity().getType().toString());
                Log.e("TabFragmentUpdate", response.toString());
                return response.getBody();

            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage(), e);

            }

            return null;
        }

        @Override
        protected void onPostExecute(final Object_Activities activities) {
            mSwipeRefreshLayout.setRefreshing(false);
            createList(activities);

        }
    }


    public void createList(final Object_Activities activities) {
        textType = new String[activities.getCount()];
        textOperator = new String[activities.getCount()];
        textDescription = new String[activities.getCount()];
        textDate = new String[activities.getCount()];
        String[] textFiller = new String[activities.getCount()];

        for (int i = 0; i < activities.getCount(); i++) {
            textType[i] = activities.getContent().get(i).getActivity().getType().toString();
            textOperator[i] = activities.getContent().get(i).getActivity().getOperator().toString();
            textDescription[i] = activities.getContent().get(i).getActivity().getDescription().toString();
            textDate[i] = activities.getContent().get(i).getActivity().getDateStamp().toString();
            textFiller[i]="";
            Log.e("TabFragmentUpdateloop", "iteration" + i);
            Log.e("Type", textType[i]);
            Log.e("Operator", textOperator[i]);
            Log.e("Description", textDescription[i]);
            Log.e("Date", textDate[i]);
        }


        adapter = new ListAdapterIncident(this, textType, textOperator, textDescription,textFiller,textDate);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
    }

}

