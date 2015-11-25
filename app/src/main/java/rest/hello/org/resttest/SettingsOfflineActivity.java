package rest.hello.org.resttest;

/**
 * Created by digi on 05.11.2015.
 */

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

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

public class SettingsOfflineActivity extends AppCompatActivity {


    static Object_Incident incident;
    //Progress bar
    static ProgressDialog pDialog;

    //*****************
    //Preference Data Declaration
    //****************
    static SharedPreferences SP;
    static String strUserName, strPassword,strServer, strPort,strIncidentCount,strSortOrder, strOffline;
    static boolean demo,assignmentGroup, decAsc;

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.content_frame, new MyPreferenceFragment()).commit();

        //Get Preferences Data
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        demo = SP.getBoolean("demo", false);
        assignmentGroup  = SP.getBoolean("assignment_group", false);
        strUserName = SP.getString("username", "falcon");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching offline data ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_offline);

            Preference button = (Preference) findPreference(getString(R.string.view_offline_now_title));
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    //code for what you want it to do
                    Log.e("Preference Activity", "fetching offline data");
                    if(!demo) new HttpRequestTask().execute();
                    return true;
                }
            });
        }
    }


    private static class HttpRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {

                strUserName = SP.getString("username", "falcon");
                strPassword = SP.getString("password", "");
                strServer = SP.getString("server", "http://192.168.0.26");
                strPort = SP.getString("port", "13080");
                strIncidentCount = SP.getString("incidentCount", "10");

                strSortOrder = SP.getString("sort_order", "falcon");
                assignmentGroup  = SP.getBoolean("assignment_group", false);
                decAsc = SP.getBoolean("dec_asc", false);

                String strDec = "descending";
                String strAssign = "assignee.name";
                if (assignmentGroup) strAssign = "assignment";
                if(decAsc) strDec = "ascending";

                // The connection URL - Building a parameterized URL
                String url = strServer + ":" + strPort + "/SM/9/rest/incidents";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("view", "expand")
                        .queryParam("count", strIncidentCount)
                        .queryParam("assignee.name", strUserName)
                        .queryParam("sort", strSortOrder+":"+strDec)
                        .queryParam("problem.status~", "Closed");
                String paramURL = builder.build().encode().toUri().toString();

                //Logging username, password and url
                Log.e("MainActivity", "Username: " + strUserName + " - Password: " + strPassword);
                Log.e("MainActivity", "url: " + paramURL);


                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(strUserName, strPassword);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(paramURL, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), String.class);

                return response.getBody().toString();


            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
//                notifyMe(e.getMessage());

            }

            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected void onPostExecute(final String res) {
                pDialog.dismiss();

            if (res != null) {
                SharedPreferences.Editor editor = SP.edit();
                editor.putString("offline", res);
                editor.commit();
                Log.e("Offline ", "response string: "+res);
            }
        }
    }

}