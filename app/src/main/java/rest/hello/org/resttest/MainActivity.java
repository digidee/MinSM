package rest.hello.org.resttest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Incident> {


        @Override
        protected Incident doInBackground(Void... params) {
            try {
                // The connection URL
                String url = "http://192.168.0.23:13080/SM/9/rest/incidents";

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Incident> response;

                // Populate the HTTP Basic Authentitcation header with the username and password
                HttpAuthentication authHeader = new HttpBasicAuthentication("falcon", "");
                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setAuthorization(authHeader);
                requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Incident.class);

                Log.e("MainActivity", response.getBody().getCount().toString());

                return response.getBody();

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Incident incident) {
            TextView incidentCount = (TextView) findViewById(R.id.totalcount_value);
            TextView incidentName = (TextView) findViewById(R.id.resourcename_value);
            incidentCount.setText(incident.getCount().toString());
            incidentName.setText(incident.getResourceName());

            Log.e("MainActivity", "count: "+incident.getCount());
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
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_refresh) {
            new HttpRequestTask().execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
