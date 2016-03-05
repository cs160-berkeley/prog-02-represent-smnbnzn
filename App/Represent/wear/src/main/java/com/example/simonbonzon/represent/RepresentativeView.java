package com.example.simonbonzon.represent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Simon Bonzon on 3/3/2016.
 */
public class RepresentativeView extends MainActivity {

    Intent receivedIntent;

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
        setContentView(R.layout.representative_view);

        receivedIntent = getIntent();

        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        SampleGridPagerAdapter adapter = new SampleGridPagerAdapter(this, getFragmentManager());
        pager.setAdapter(adapter);

        //More sensor stuff
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        //End sensor stuff

        //Fragment splash = adapter.instantiateItem(pager, 0, 0);

    }

    public class RepFragment extends CardFragment {

        String name;
        String title;
        String party;
        String region;


        public RepFragment(CardFragment fragment, String label, String description) {
            super.create(label, description);
            String name;
            String title;
            String party;
            String region;
        }

/*
        public RepFragment(String name, String title, String party, String region) {
            this.name = name;
            this.title = title;
            this.party = party;
            this.region = region;
            this.KEY_TEXT = title + " " + party;
            this.KEY_TITLE = name;
        }
*/


        @Override
        public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //View mRootView = super.onCreateContentView(inflater, container, savedInstanceState);
            View mRootView = inflater.inflate(R.layout.rep_page, null);
            TextView nameText = (TextView) mRootView.findViewById(R.id.name);
            TextView titleText = (TextView) mRootView.findViewById(R.id.title);
            TextView partyText = (TextView) mRootView.findViewById(R.id.party);
            ImageView photo = (ImageView) mRootView.findViewById(R.id.photo);
            if(Objects.equals(name, "Barbara Lee")){
                nameText.setText("Barbara Lee");
                titleText.setText("Representative");
                partyText.setText("(D)");
                photo.setImageDrawable(getResources().getDrawable(R.drawable.lee, null));
            } else if(Objects.equals(name, "Dianne Feinstein")) {
                nameText.setText("Dianne FeinStein");
                titleText.setText("Senator");
                partyText.setText("(D)");
                photo.setImageDrawable(getResources().getDrawable(R.drawable.feinstein, null));
            } else if(Objects.equals(name, "Barbara Boxer")) {
                nameText.setText("Barbara Boxer");
                titleText.setText("Senator");
                partyText.setText("(D)");
                photo.setImageDrawable(getResources().getDrawable(R.drawable.boxer, null));
            }
            mRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent sendWearIntent = new Intent(getBaseContext(), VoteView.class);
                    if(title=="Senator") {
                        sendWearIntent.putExtra("REGION_TYPE", "State");
                    } else {
                        sendWearIntent.putExtra("REGION_TYPE", "District");
                    }
                    sendWearIntent.putExtra("REGION", region);
                    startActivity(sendWearIntent);

                    Intent sendMobileIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    /*
                    if(title=="Senator") {
                        sendMobileIntent.putExtra("REGION_TYPE", "State");
                    } else {
                        sendMobileIntent.putExtra("REGION_TYPE", "District");
                    }
                    sendMobileIntent.putExtra("REGION", region);
                    */
                    sendMobileIntent.putExtra("NAME", name);
                    Log.d("T", "sending intent from repView with name: " + name);
                    startService(sendMobileIntent);

                }

            });
            return mRootView;
        }
    }

    public class SampleGridPagerAdapter extends FragmentGridPagerAdapter {

        private final Context mContext;
        private List mRows;

        public SampleGridPagerAdapter(Context ctx, FragmentManager fm) {
            super(fm);
            mContext = ctx;
        }

        private final int[] BG_IMAGES = new int[] {
                R.drawable.lee,
                R.drawable.feinstein,
                R.drawable.boxer,
        };

        // A simple container for static data in each page
        private class Page {
            // static resources
            String name;
            String title;
            String party;
            String region;
            int cardGravity = Gravity.BOTTOM;
            boolean expansionEnabled = false;
            float expansionFactor = 1.0f;
            int expansionDirection = CardFragment.EXPAND_DOWN;

            public Page(String name, String title, String party, String region) {
                this.name = name;
                this.title = title;
                this.party = party;
                this.region = region;
            }

        }

        // Create a set of pages in a 2D array
        private final Page[][] PAGES = {
                {
                        new Page("Barbara Lee", "Representative", "(D)", receivedIntent.getExtras().getString("LOCATION")),
                },
                {
                        new Page("Dianne Feinstein", "Senator", "(D)", "California"),
                },
                {
                        new Page("Barbara Boxer", "Senator", "(D)", "California"),
                },
        };

        // Override methods in FragmentGridPagerAdapter
        // Obtain the UI fragment at the specified position
        @Override
        public Fragment getFragment(int row, int col) {
            Page page = PAGES[row][col];
            String name = page.name;
            String title = page.title;
            String party = page.party;
            String region = page.region;

            String description = title + " " + party;
            //CardFragment fragment1 = CardFragment.create(name, title + " " + party);
            RepFragment fragment = new RepFragment(RepFragment.create(name, description), name, description);

            fragment.name = name;
            fragment.title = title;
            fragment.party = party;
            fragment.region = region;

            //Advanced settings (card gravity, card expansion/scrolling)
            fragment.setCardGravity(page.cardGravity);
            fragment.setExpansionEnabled(page.expansionEnabled);
            fragment.setExpansionDirection(page.expansionDirection);
            fragment.setExpansionFactor(page.expansionFactor);
            return fragment;
        }

        //Obtain the background image for the specific page
        @Override
        public Drawable getBackgroundForPage(int row, int column) {
            /*
            if( row == 0) {
                // Place image at specified position
                return mContext.getResources().getDrawable(R.drawable.lee, null);
            } if (row == 1) {
                return mContext.getResources().getDrawable(R.drawable.feinstein, null);
            } if (row == 2) {
                return mContext.getResources().getDrawable(R.drawable.boxer, null);
            } else {*/
                // Default to background image for row
                return GridPagerAdapter.BACKGROUND_NONE;
            //}
        }

        // Obtain the number of pages (vertical)
        @Override
        public int getRowCount() {
            return PAGES.length;
        }

        // Obtain the number of pages (horizontal)
        @Override
        public int getColumnCount(int rowNum) {
            return PAGES[rowNum].length;
        }
    }
}
