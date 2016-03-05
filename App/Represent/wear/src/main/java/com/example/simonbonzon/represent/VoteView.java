package com.example.simonbonzon.represent;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Simon Bonzon on 3/3/2016.
 */
public class VoteView extends RepresentativeView{

    TextView mLocationText;
    TextView mObamaVote;
    TextView mRomneyVote;

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
                        "CO","CT","DE","FL",
                        "GA","HI","ID","IL","IN",
                        "IA","KS","KY","LA","ME",
                        "MD","MA","MI","MN","MS",
                        "MO","MT","NE","NV","NH",
                        "NJ","NM","NY","NC","ND",
                        "OH","OK","OR","PA","RI",
                        "SC","SD","TN","TX","UT",
                        "VT","VA","WA","WV","WI","WY"
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_page);

        mLocationText = (TextView) findViewById(R.id.location);
        mObamaVote = (TextView) findViewById(R.id.obama_percent);
        mRomneyVote = (TextView) findViewById(R.id.romney_percent);

        //More sensor stuff
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        //End sensor stuff

        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();

        if (extras != null) {
            String location = extras.getString("REGION");
            mLocationText.setText(location);

            if(Objects.equals(extras.getString("REGION_TYPE"), "State")) {
                mRomneyVote.setText("37.12%");
                mObamaVote.setText("60.24%");
            }
        }

    }
}
