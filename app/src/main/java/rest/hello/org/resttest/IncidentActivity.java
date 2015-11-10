package rest.hello.org.resttest;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
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
    String im;

    String JSONTestobject;

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

        // TextView imView = (TextView) findViewById(R.id.im_value);
        TextView titleView = (TextView) findViewById(R.id.title_value);
        //TextView statusView = (TextView) findViewById(R.id.status_value);
        TextView descView = (TextView) findViewById(R.id.description_value);
        TextView serviceView = (TextView) findViewById(R.id.service_value);
        TextView openByView = (TextView) findViewById(R.id.openby_value);

        //imView.setText(im);
        titleView.setText(title);
        //statusView.setText(status);
        descView.setText(description);
        serviceView.setText(service);
        openByView.setText(openby);

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


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

                showInputDialog(true);


            }

        });


        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Resolving incident", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                showInputDialog(false);

            }

        });


        /*PUT /<incidents>/<id> Updates an specific Incident
          POST /<incidents>/<id>/action/<action> Invokesn a customiz ed action on a specific Incident*/


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
            if (!demo) new HttpRequestTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {
            try {


                RestTemplate restTemplate = new RestTemplate();

                Log.e("MainActivity", "url: " + url);


                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                ResponseEntity<String> response;
                HttpEntity<String> entity = new HttpEntity<String>(JSONTestobject, requestHeaders);
                response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

                // response = restTemplate.postForObject(url, new HttpEntity<String>(JSONTestobject,requestHeaders), String.class, JSONTestobject);


                Log.e("MainActivity", response.toString());


            } catch (Exception e) {

                Log.e("MainActivity", e.getMessage(), e);

            }

            return null;
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


    public String JSONBUILDER2(String n) {
        try {
            JSONObject json = new JSONObject();
            JSONObject incJson = new JSONObject();
            incJson.put("resolution", n);
            incJson.put("resolution.code", "Solved by Workaround");
            json.put("Incident", incJson);
            return json.toString();
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
            return null;
        }
    }


    protected void showInputDialog(final boolean t) {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(IncidentActivity.this);
        View promptView = layoutInflater.inflate(R.layout.update_incident, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(IncidentActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (t) {
                            url = strServer + ":" + strPort + "/SM/9/rest/incidents/" + im + "/action/update";
                            JSONTestobject = JSONBUILDER(editText.getText().toString());
                            Log.e("MainActivity", JSONTestobject);
                            if (!demo) new HttpRequestTask().execute();
                        } else if (!t) {
                            url = strServer + ":" + strPort + "/SM/9/rest/incidents/" + im + "/action/close";
                            JSONTestobject = JSONBUILDER2(editText.getText().toString());
                            Log.e("MainActivity", JSONTestobject);
                            if (!demo) new HttpRequestTask().execute();
                        }


                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


}