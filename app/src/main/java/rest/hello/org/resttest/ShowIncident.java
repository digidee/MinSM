package rest.hello.org.resttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by digi on 06.11.2015.
 */
public class ShowIncident extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.incident_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Synchronizing with Service Manager", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }

        });

        Intent intent = getIntent();
        String im = intent.getStringExtra("im");
        String title = intent.getStringExtra("title");
        String status = intent.getStringExtra("status");
        String description = intent.getStringExtra("description");
        String service = intent.getStringExtra("service");

        TextView imView = (TextView) findViewById(R.id.im_value);
        TextView titleView = (TextView) findViewById(R.id.title_value);
        TextView statusView = (TextView) findViewById(R.id.status_value);
        TextView descView = (TextView) findViewById(R.id.description_value);
        TextView serviceView = (TextView) findViewById(R.id.service_value);

        imView.setText(im);
        titleView.setText(title);
        statusView.setText(status);
        descView.setText(description);
        serviceView.setText(service);

    }
}