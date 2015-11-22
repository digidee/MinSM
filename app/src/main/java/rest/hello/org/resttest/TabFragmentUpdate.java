package rest.hello.org.resttest;

/**
 * Created by digi on 16.11.2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by digi on 14.11.2015.
 */
public class TabFragmentUpdate extends Fragment {


    //Initializating for  Preferences Data
    private SharedPreferences SP;
    String strUserName;
    String strPassword;
    String strServer;
    String strPort;
    boolean demo;
    String url;
    String im;
    Spinner spinner;

    String JSONTestobject;
    EditText updateText;
    Object_Incident incident;
    Object_ActivityTypes activityTypes;
    Object_Activities acties;

    ListView lv;
    ListAdapterActivity adapter;

    private String[] textType, textOperator, textDescription, textDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       // inflater.inflate(R.layout.tab_fragment_updates, container, false);
        View view = inflater.inflate(R.layout.tab_fragment_updates, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        im = bundle.getString("im", "");

        updateText = (EditText) getView().findViewById(R.id.update_text);
        spinner = (Spinner)getView().findViewById(R.id.update_spinner);

        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        strUserName = SP.getString("username", "falcon");
        strPassword = SP.getString("password", "");
        strServer = SP.getString("server", "http://192.168.0.26");
        strPort = SP.getString("port", "13080");
        demo = SP.getBoolean("demo", false);


        Button updateButton = (Button) getView().findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Incident updated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/


                try {
                    JSONObject json = new JSONObject();
                    JSONObject ActJson = new JSONObject();
                    ActJson.put("DateStamp", "2015-12-15T21:08:19+00:00");
                    ActJson.put("Description", updateText.getText().toString());
                    ActJson.put("Operator", strUserName);
                    ActJson.put("number", im);
                    ActJson.put("Type", spinner.getSelectedItem().toString());
                    json.put("Activity", ActJson);
                    JSONTestobject = json.toString();
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());

                }

                if (!demo) new HttpRequestTask().execute();
                else {

                    Snackbar snack = Snackbar.make(view, "Incident updated", Snackbar.LENGTH_LONG);
                    View snackView = snack.getView();
                    TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextAlignment(snackView.TEXT_ALIGNMENT_CENTER);
                    snack.show();

                }


            }

        });

        if (!demo) new HttpRequestTask2().execute();
        if (!demo) new HttpRequestTask3().execute();

    }

    private class HttpRequestTask3 extends AsyncTask<Void, Void, Object_Activities> {


        @Override
        protected Object_Activities doInBackground(Void... params) {
            try {

                url = strServer + ":" + strPort + "/SM/9/rest/Activity?number="+im+"&view=expand";
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
                Log.e("TabFragmentUpdate-type",response.getBody().getContent().get(1).getActivity().getType().toString());
                Log.e("TabFragmentUpdate", response.toString());
                acties = response.getBody();
                return acties;

            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage(), e);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Object_Activities activities) {
            super.onPostExecute(activities);

            createList(activities);

        }
    }

    private class HttpRequestTask2 extends AsyncTask<Void, Void, Object_ActivityTypes> {


        @Override
        protected Object_ActivityTypes doInBackground(Void... params) {
            try {

                url = strServer + ":" + strPort + "/SM/9/rest/globallists/Incident Activity Types";
                Log.e("MainActivity", "url: " + url);


                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object_ActivityTypes> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Object_ActivityTypes.class);

                Log.e("TabFragmentUpdateHTTP2", response.toString());
                activityTypes = response.getBody();
                return activityTypes;

            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage(), e);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Object_ActivityTypes activityTypes) {
            super.onPostExecute(activityTypes);


            String actTypes = activityTypes.getGlobalList().getValueList();
            actTypes = actTypes.replaceAll("[\"\\{\\}]","");
            String[] values = actTypes.split(",");
            // Application of the Array to the Spinner
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),   android.R.layout.simple_spinner_item, values);
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
            spinner.setAdapter(spinnerArrayAdapter);

        }
    }

    public void createList(final Object_Activities activities) {
        textType = new String[activities.getCount()];
        textOperator = new String[activities.getCount()];
        textDescription = new String[activities.getCount()];
        textDate = new String[activities.getCount()];

        for (int i = 0; i < activities.getCount(); i++) {
            textType[i] = activities.getContent().get(i).getActivity().getType().toString();
            textOperator[i] = activities.getContent().get(i).getActivity().getOperator().toString();
            textDescription[i] = activities.getContent().get(i).getActivity().getDescription().toString();
            textDate[i] = activities.getContent().get(i).getActivity().getDateStamp().toString();
            Log.e("TabFragmentUpdateloop", "iteration"+i);
            Log.e("Type", textType[i]);
            Log.e("Operator", textOperator[i]);
            Log.e("Description", textDescription[i]);
            Log.e("Date", textDate[i]);
            textType[i] = "test";
            textOperator[i] =  "test";
            textDescription[i] =  "test";
            textDate[i] = "test";
        }


        adapter = new ListAdapterActivity(getContext(), textDate, textOperator, textDescription, textDate);
        lv = (ListView) getView().findViewById(R.id.listview_activities);
        lv.setAdapter(adapter);
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Object_Incident> {


        @Override
        protected Object_Incident doInBackground(Void... params) {
            try {
                // The connection URL - Building a parameterized URL
                url = strServer + ":" + strPort + "/SM/9/rest/activity?number="+im;

                RestTemplate restTemplate = new RestTemplate();

                Log.e("MainActivity", "url: " + url);


                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                ResponseEntity<Object_Incident> response;
                HttpEntity<String> entity = new HttpEntity<String>(JSONTestobject, requestHeaders);
                response = restTemplate.exchange(url, HttpMethod.POST, entity, Object_Incident.class);

                Log.e("MainActivity", response.toString());
                incident = response.getBody();
                return incident;

            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage(), e);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Object_Incident incident) {
            super.onPostExecute(incident);

            Snackbar snack = Snackbar.make(getView().findViewById(R.id.view_incident_update_id),incident.getMessages().toString(), Snackbar.LENGTH_LONG);
            View snackView = snack.getView();
            TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextAlignment(snackView.TEXT_ALIGNMENT_CENTER);
            snack.show();


        }
    }


}
