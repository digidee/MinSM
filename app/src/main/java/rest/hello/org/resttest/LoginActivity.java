package rest.hello.org.resttest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.StringDef;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

/**
 * Created by digi on 15.11.2015.
 */
public class LoginActivity extends AppCompatActivity {


    //Initializating for  Preferences Data
    private SharedPreferences SP;
    String usr, pass;
    String strServer;
    String strPort;
    boolean demo;
    String url;
    boolean crash;

    EditText user_input, password_input;
    CheckBox demo_input;

    Object_Incident incident;

    //Progress bar
    ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Logging in ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);

        //Getting preference data
        SP = PreferenceManager.getDefaultSharedPreferences(this);



        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_input = (EditText) findViewById(R.id.login_user);
                password_input = (EditText) findViewById(R.id.login_password);
                demo_input = (CheckBox) findViewById(R.id.login_demo);
                usr = user_input.getText().toString();
                pass = password_input.getText().toString();
                demo = demo_input.isChecked();

                Log.e("LoginActivity", "Username: " + usr + " - Password: " + pass);

                if (!demo) new HttpRequestTask().execute();
                else {
                    SharedPreferences.Editor editor = SP.edit();
                    editor.putBoolean("demo", true);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

        });

    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                strServer = SP.getString("server", "http://192.168.0.26");
                strPort = SP.getString("port", "13080");

                // The connection URL - Building a parameterized URL
                String url = strServer + ":" + strPort + "/SM/9/rest/incidents";
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                        .queryParam("count", 1);
                String paramURL = builder.build().encode().toUri().toString();

                //Logging username, password and url
                Log.e("LoginActivity", "Username: " + usr + " - Password: " + pass);
                Log.e("LoginActivity", "url: " + paramURL);


                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Object_Incident> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication(usr, pass);
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(paramURL, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Object_Incident.class);

                return response.getStatusCode().value();

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
//                notifyMe(e.getMessage());
                crash = true;

            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Integer res) {
            super.onPostExecute(res);
            pDialog.dismiss();
            String snackMessage = "Could not connect to server";

            if(res!=null) {
                if (res == 200) {
                    SharedPreferences.Editor editor = SP.edit();
                    editor.putString("username", usr);
                    editor.putString("password", pass);
                    editor.putBoolean("demo", false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    snackMessage = "HTTP Error code: " + res;
                    Log.e("MainActivity", "HTTP Status code: " + res);
                }
            }

            Log.e("MainActivity", "HTTP Status code: " + res);
            Snackbar snack = Snackbar.make(getWindow().findViewById(R.id.view_login), snackMessage, Snackbar.LENGTH_LONG);
            View snackView = snack.getView();
            TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextAlignment(snackView.TEXT_ALIGNMENT_CENTER);
            snack.show();


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
        if (id == R.id.server_settings) {
            Intent i = new Intent(this, SettingsServerActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
