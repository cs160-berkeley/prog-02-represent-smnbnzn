package com.example.simonbonzon.represent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mUseLocation;
    private EditText mZipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
        mUseLocation = (TextView) findViewById(R.id.useLocationButton);
        mZipCode = (EditText) findViewById(R.id.zip_code);

        mUseLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                sendWearIntent.putExtra("LOCATION_TYPE", "District");
                sendWearIntent.putExtra("LOCATION", "CA, Dist. 13");
                startService(sendWearIntent);

                Intent sendMobileIntent = new Intent(getBaseContext(), CongressionalView.class);
                sendMobileIntent.putExtra("LOCATION_TYPE", "District");
                sendMobileIntent.putExtra("LOCATION", "CA, Dist. 13");
                startActivity(sendMobileIntent);
            }
        });

        mZipCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    CharSequence zip = v.getText();
                    String zipString = zip.toString();

                    Intent sendWearIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                    sendWearIntent.putExtra("LOCATION_TYPE", "ZipCode");
                    sendWearIntent.putExtra("LOCATION", "Zip Code: " + zipString);
                    startService(sendWearIntent);

                    Intent sendMobileIntent = new Intent(getBaseContext(), CongressionalView.class);
                    sendMobileIntent.putExtra("LOCATION_TYPE", "ZipCode");
                    sendMobileIntent.putExtra("LOCATION", "Zip Code: " + zipString);
                    startActivity(sendMobileIntent);

                    handled = true;
                }
                return handled;
            }
        });
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

        return super.onOptionsItemSelected(item);
    }
}
