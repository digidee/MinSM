package rest.hello.org.resttest;

/**
 * Created by digi on 16.11.2015.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

/**
 * Created by digi on 14.11.2015.
 */
public class TabFragmentInfo extends Fragment {


    //Initializating for  Preferences Data
    private SharedPreferences SP;
    String strUserName, strPassword,strServer, strPort,strIncidentCount,strSortOrder, strOffline;
    boolean demo;
    String url;
    String im, journal;

    Object_Incident incident;

    TextView titleView;
    TextView descView;
    TextView serviceView;
    TextView openByView;
    TextView journalView;
    TextView aciView;

    //**************************
    // TEST OBJECT JSON
    //*************************
    ObjectMapper mapper;
    Object_TestJSON JSONObject;
    //**************************
    // TEST OBJECT JSON
    //*************************

    String title = "";
    String status = "";
    String description = "";
    String service = "";
    String openby = "";
    String aci = "";

    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_info, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        demo = SP.getBoolean("demo", false);
        JSONObject = new Object_TestJSON();
        strOffline = SP.getString("offline", JSONObject.getJSONObject());


        titleView = (TextView) getView().findViewById(R.id.title_value);
        descView = (TextView) getView().findViewById(R.id.description_value);
        serviceView = (TextView) getView().findViewById(R.id.service_value);
        openByView = (TextView) getView().findViewById(R.id.openby_value);
        journalView = (TextView) getView().findViewById(R.id.journal_value);
        aciView = (TextView) getView().findViewById(R.id.aci_value);

        Bundle bundle = getArguments();
        im = bundle.getString("im", "");


        if (!demo) new HttpRequestTask().execute();
        else {
            JSONObjectList();
            titleView.setText(title);
            descView.setText(description);
            serviceView.setText(service);
            openByView.setText(openby);
            journalView.setText(journal);
            aciView.setText(aci);
        }

        Button activityButton = (Button) getView().findViewById(R.id.activity_button);
        activityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!demo) {
                    Intent intent = new Intent(getContext(), ListActivityActivity.class);
                    intent.putExtra("im", im);
                    startActivity(intent);
                }


            }

        });

    }


    public void JSONObjectList() {
        try {
            //**************************
            // TEST OBJECT JSON
            //*************************
            //using JSONObject as test
            //JSON from String to Object
            mapper = new ObjectMapper();
            incident = mapper.readValue(strOffline, Object_Incident.class);

            createList(incident);

        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);

        }


    }

    public void createList(final Object_Incident incident) {
        for (int i = 0; i < incident.getCount(); i++) {
            if (incident.getContent().get(i).getIncident().getIncidentID().toString().equals(im)) {
                title = incident.getContent().get(i).getIncident().getTitle().toString();
                status = incident.getContent().get(i).getIncident().getStatus().toString();
                description = incident.getContent().get(i).getIncident().getDescription().toString();
                openby = incident.getContent().get(i).getIncident().getOpenedBy().toString();
                service = incident.getContent().get(i).getIncident().getService().toString();
                journal = incident.getContent().get(i).getIncident().getJournalUpdates().toString();
                aci = incident.getContent().get(i).getIncident().getAffectedCI().toString();
                ;
            }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Object_Incident> {

        @Override
        protected Object_Incident doInBackground(Void... params) {
            try {

                strUserName = SP.getString("username", "falcon");
                strPassword = SP.getString("password", "");
                strServer = SP.getString("server", "http://192.168.0.26");
                strPort = SP.getString("port", "13080");

                // The connection URL - Building a parameterized URL
                String url = strServer + ":" + strPort + "/SM/9/rest/incidents/";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("view", "expand")
                        .queryParam("number", im);
                String paramURL = builder.build().encode().toUri().toString();

                //Logging username, password and url
                Log.e("tab", "Username: " + strUserName + " - Password: " + strPassword);
                Log.e("tab", "url: " + paramURL);


                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object_Incident> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(paramURL, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Object_Incident.class);
                Log.e("tab", url);
                Log.e("tab", response.getBody().getCount().toString());
                Log.e("tab", response.getBody().getTotalcount().toString());
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
//            mSwipeRefreshLayout.setRefreshing(false);
            if (incident != null) {
                titleView.setText(incident.getContent().get(0).getIncident().getTitle());
                descView.setText(incident.getContent().get(0).getIncident().getDescription().toString());
                serviceView.setText(incident.getContent().get(0).getIncident().getService());
                openByView.setText(incident.getContent().get(0).getIncident().getOpenedBy());
                journalView.setText(incident.getContent().get(0).getIncident().getJournalUpdates().toString());
//                aciView.setText(incident.getContent().get(0).getIncident().getAffectedCI().toString());
            }
        }
    }


}
