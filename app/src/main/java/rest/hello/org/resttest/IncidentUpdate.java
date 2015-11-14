package rest.hello.org.resttest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by digi on 14.11.2015.
 */
public class IncidentUpdate extends AppCompatActivity {


    //Initializating for  Preferences Data
    private SharedPreferences SP;
    String strUserName;
    String strPassword;
    String strServer;
    String strPort;
    boolean demo;
    String url;
    String im, journal;

    String JSONTestobject;
    EditText updateText;
    Object_Incident incident;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_update);

        Intent intent = getIntent();
        im = intent.getStringExtra("im");
        journal = intent.getStringExtra("journal");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.incident_update_title) + " " + im);
        toolbar.showOverflowMenu();
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        updateText = (EditText) findViewById(R.id.update_text);

        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(this);
        strUserName = SP.getString("username", "falcon");
        strPassword = SP.getString("password", "");
        strServer = SP.getString("server", "http://192.168.0.26");
        strPort = SP.getString("port", "13080");
        demo = SP.getBoolean("demo", false);
        // The connection URL - Building a parameterized URL
        url = strServer + ":" + strPort + "/SM/9/rest/incidents/" + im + "/action/update";

        Button updateButton = (Button) findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Incident updated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                JSONTestobject = JSONBUILDER(updateText.getText().toString());
                if (!demo) new HttpRequestTask().execute();
                else{

                    Snackbar snack = Snackbar.make(view, "Incident updated", Snackbar.LENGTH_LONG);
                    View snackView = snack.getView();
                    TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextAlignment(snackView.TEXT_ALIGNMENT_CENTER);
                    snack.show();

                }


            }

        });

    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Object_Incident> {


        @Override
        protected Object_Incident doInBackground(Void... params) {
            try {


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

            Snackbar snack = Snackbar.make(getWindow().findViewById(R.id.view_incident_update_id),incident.getMessages().toString(), Snackbar.LENGTH_LONG);
            View snackView = snack.getView();
            TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextAlignment(snackView.TEXT_ALIGNMENT_CENTER);
            snack.show();


        }
    }

    public String JSONBUILDER(String n) {
        try {
            JSONObject json = new JSONObject();
            JSONObject incJson = new JSONObject();
            incJson.put("JournalUpdates", n);
            json.put("Incident", incJson);
            return json.toString();
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
            return null;
        }
    }


}
