package com.example.simonbonzon.represent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import junit.framework.Test;

import org.w3c.dom.Text;

/**
 * Created by Simon Bonzon on 2/28/2016.
 */
public class CongressionalView extends MainActivity {

    TextView mLocationText;

    ImageView mLeePhoto;
    TextView mLeeEmail;
    TextView mLeeWebsite;
    TextView mLeeInfo;

    ImageView mFeinsteinPhoto;
    TextView mFeinsteinEmail;
    TextView mFeinsteinWebsite;
    TextView mFeinsteinInfo;

    ImageView mBoxerPhoto;
    TextView mBoxerEmail;
    TextView mBoxerWebsite;
    TextView mBoxerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congressional_view);

        //Set location text
        mLocationText = (TextView) findViewById(R.id.congressional_location);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            String location = extras.getString("LOCATION");
            mLocationText.setText(location);
        }

        //Set onClickHandlers
        mLeePhoto = (ImageView) findViewById(R.id.lee_photo);
        mLeeEmail = (TextView) findViewById(R.id.lee_email);
        mLeeWebsite = (TextView) findViewById(R.id.lee_website);
        mLeeInfo = (TextView) findViewById(R.id.lee_info);
        final String leeEmail =  "https://lee.house.gov/contact-the-office/email-me";
        final String leeWebsite = "https://lee.house.gov/";

        mFeinsteinPhoto = (ImageView) findViewById(R.id.feinstein_photo);
        mFeinsteinEmail = (TextView) findViewById(R.id.feinstein_email);
        mFeinsteinWebsite = (TextView) findViewById(R.id.feinstein_website);
        mFeinsteinInfo = (TextView) findViewById(R.id.feinstein_info);
        final String feinsteinEmail = "https://www.feinstein.senate.gov/public/index.cfm/e-mail-me";
        final String feinsteinWebsite = "http://www.feinstein.senate.gov/public/";

        mBoxerPhoto = (ImageView) findViewById(R.id.boxer_photo);
        mBoxerEmail = (TextView) findViewById(R.id.boxer_email);
        mBoxerWebsite = (TextView) findViewById(R.id.boxer_website);
        mBoxerInfo = (TextView) findViewById(R.id.boxer_info);
        final String boxerEmail = "https://www.boxer.senate.gov/?p=shareyourviews";
        final String boxerWebsite = "https://www.boxer.senate.gov/";

        mLeePhoto.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent sendMobileIntent = new Intent(getBaseContext(), DetailedView.class);
                 sendMobileIntent.putExtra("PERSON", "Lee");
                 startActivity(sendMobileIntent);
             }
        });

        mLeeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendMobileIntent = new Intent(getBaseContext(), DetailedView.class);
                sendMobileIntent.putExtra("PERSON", "Lee");
                startActivity(sendMobileIntent);
            }
        });

        mLeeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(leeEmail));
                startActivity(browserIntent);
            }
        });

        mLeeWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(leeWebsite));
                startActivity(browserIntent);
            }
        });

        mFeinsteinPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendMobileIntent = new Intent(getBaseContext(), DetailedView.class);
                sendMobileIntent.putExtra("PERSON", "Feinstein");
                startActivity(sendMobileIntent);
            }
        });

        mFeinsteinInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendMobileIntent = new Intent(getBaseContext(), DetailedView.class);
                sendMobileIntent.putExtra("PERSON", "Feinstein");
                startActivity(sendMobileIntent);
            }
        });

        mFeinsteinEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(feinsteinEmail));
                startActivity(browserIntent);
            }
        });

        mFeinsteinWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(feinsteinWebsite));
                startActivity(browserIntent);
            }
        });

        mBoxerPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendMobileIntent = new Intent(getBaseContext(), DetailedView.class);
                sendMobileIntent.putExtra("PERSON", "Boxer");
                startActivity(sendMobileIntent);
            }
        });

        mBoxerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendMobileIntent = new Intent(getBaseContext(), DetailedView.class);
                sendMobileIntent.putExtra("PERSON", "Boxer");
                startActivity(sendMobileIntent);
            }
        });

        mBoxerEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(boxerEmail));
                startActivity(browserIntent);
            }
        });

        mBoxerWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(boxerWebsite));
                startActivity(browserIntent);
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
