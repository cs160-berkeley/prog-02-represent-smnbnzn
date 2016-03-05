package com.example.simonbonzon.represent;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String DISTRICT = "/District";
    private static final String ZIPCODE = "/ZipCode";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases

        /* Following sends intent to RepView, but is unused. Add resources from phone for RepView to use */

        if( messageEvent.getPath().equalsIgnoreCase( DISTRICT ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, RepresentativeView.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION_TYPE", "District");
            intent.putExtra("LOCATION", value);
            Log.d("T", "about to start watch RepresentativeView with LOCATION_TYPE: District");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( ZIPCODE )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, RepresentativeView.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION_TYPE", "ZipCode");
            intent.putExtra("LOCATION", value);
            Log.d("T", "about to start watch RepresentativeView with LOCATION_TYPE: Zip Code");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}