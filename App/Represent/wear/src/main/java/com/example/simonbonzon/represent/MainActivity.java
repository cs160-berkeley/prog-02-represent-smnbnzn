package com.example.simonbonzon.represent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends WearableActivity {

    TextView mLocationText;
    RelativeLayout mRelativeLayout;

    //Sets up motion sensor, from
    // http://stackoverflow.com/questions/2317428/android-i-want-to-shake-it

    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
            if (mAccel > 2) {
                int[] stateArray = new int[] {
                        7,1,9,4,53,
                        7,5,1,27,14,
                        2,2,18,9,4,
                        4,6,6,2,8,
                        9,14,8,4,8,
                        1,3,4,2,12,
                        3,27,13,1,16,
                        5,5,18,2,7,
                        1,9,36,4,1,
                        11,10,3,8,1
                };
                String[] states = new String[] {
                        "AL","AK","AZ","AR","CA",
                        "CO","CT","DE","FL","GA",
                        "HI","ID","IL","IN","IA",
                        "KS","KY","LA","ME","MD",
                        "MA","MI","MN","MS","MO",
                        "MT","NE","NV","NH","NJ",
                        "NM","NY","NC","ND","OH",
                        "OK","OR","PA","RI","SC",
                        "SD","TN","TX","UT","VT",
                        "VA","WA","WV","WI","WY"
                };
                int rand = 0;
                int randState = 0;
                int randDist = 0;
                String state;
                rand = ThreadLocalRandom.current().nextInt(0, 50);
                randState = stateArray[rand];
                randDist = ThreadLocalRandom.current().nextInt(0, randState);
                state = states[randState];
                String location = state + ", Dist. " + (randDist+1);

                Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
                sendWearIntent.putExtra("LOCATION_TYPE", "District");
                sendWearIntent.putExtra("LOCATION", location);
                sendWearIntent.putExtra("STATE", state);
                sendWearIntent.putExtra("DISTRICT", randDist + 1);
                startActivity(sendWearIntent);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    Sensor mSigMotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //More sensor stuff
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        //End sensor stuff

        mLocationText = (TextView) findViewById(R.id.location_text);

        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();

        if (extras != null) {
            String location = extras.getString("LOCATION");
            mLocationText.setText(location);
        }

        mRelativeLayout = (RelativeLayout) findViewById(R.id.container);


        //TriggerListener mListener;

        //mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //mSigMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        //mTextView = (TextView)findViewById(R.id.text);
        //mListener = new TriggerListener(this, mTextView);


        //mRelativeLayout.

        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String location;

                if (extras != null) {
                    location = extras.getString("LOCATION");

                    Intent sendWearIntent = new Intent(getBaseContext(), RepresentativeView.class);
                    sendWearIntent.putExtra("LOCATION_TYPE", "District");
                    sendWearIntent.putExtra("LOCATION", location);
                    startActivity(sendWearIntent);

                    Intent sendMobileIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    sendMobileIntent.putExtra("LOCATION_TYPE", "District");
                    sendMobileIntent.putExtra("LOCATION", location);
                    startService(sendMobileIntent);
                }
            }

        });

    }

}
