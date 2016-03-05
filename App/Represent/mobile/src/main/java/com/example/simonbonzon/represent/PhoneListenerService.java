package com.example.simonbonzon.represent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
//private static final String TOAST = "/send_toast";
    private static final String DISTRICT = "/District";
    private static final String ZIPCODE = "/ZipCode";
    private static final String LEE = "/Barbara Lee";
    private static final String FEINSTEIN = "/Dianne Feinstein";
    private static final String BOXER = "/Barbara Boxer";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase( DISTRICT ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, CongressionalView.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION_TYPE", "District");
            intent.putExtra("LOCATION", value);
            Log.d("T", "about to start mobile CongressionalView with LOCATION_TYPE: District");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( ZIPCODE )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION_TYPE", "ZipCode");
            intent.putExtra("LOCATION", value);
            Log.d("T", "about to start mobile CongressionalView with LOCATION_TYPE: Zip Code");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( LEE )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, DetailedView.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("PERSON", "Lee");
            Log.d("T", "about to start watch DetailedView with PERSON: Lee");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( FEINSTEIN )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, DetailedView.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("PERSON", "Feinstein");
            Log.d("T", "about to start watch DetailedView with PERSON: Feinstein");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( BOXER )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, DetailedView.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("PERSON", "Boxer");
            Log.d("T", "about to start watch DetailedView with PERSON: Boxer");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}
