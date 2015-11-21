package rest.hello.org.resttest;

/**
 * Created by digi on 16.11.2015.
 */
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TabFragmentResolve extends Fragment {


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
    EditText resolveText;

    Object_Incident incident;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_resolve, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Bundle bundle = getArguments();
        im = bundle.getString("im", "");

        resolveText = (EditText) getView().findViewById(R.id.resolve_text);

        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        strUserName = SP.getString("username", "falcon");
        strPassword = SP.getString("password", "");
        strServer = SP.getString("server", "http://192.168.0.26");
        strPort = SP.getString("port", "13080");
        demo = SP.getBoolean("demo", false);
        // The connection URL - Building a parameterized URL
        url = strServer + ":" + strPort + "/SM/9/rest/incidents/" + im + "/action/resolve";

        Button resolveButton = (Button) getView().findViewById(R.id.resolve_button);
        resolveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                JSONTestobject = JSONBUILDER(resolveText.getText().toString());
                if (!demo) new HttpRequestTask().execute();
                else{

                    Snackbar snack = Snackbar.make(view," Incident resolved", Snackbar.LENGTH_LONG);
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

            Snackbar snack = Snackbar.make(getView().findViewById(R.id.view_incident_resolve_id),incident.getMessages().toString(), Snackbar.LENGTH_LONG);
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
            incJson.put("resolution", n);
            incJson.put("resolution.code", "Solved by Workaround");
            json.put("Incident", incJson);
            return json.toString();
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage());
            return null;
        }
    }
}

