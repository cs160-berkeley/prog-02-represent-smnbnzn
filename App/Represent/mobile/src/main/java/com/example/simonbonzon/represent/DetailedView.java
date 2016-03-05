package com.example.simonbonzon.represent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by Simon Bonzon on 3/1/2016.
 */
public class DetailedView extends CongressionalView {

    String person;
    final String LEE = "Lee";
    final String FEINSTEIN = "Feinstein";
    final String BOXER = "Boxer";

    ImageView photo;
    TextView title;
    TextView name;
    TextView party;
    TextView endOfTerm;
    TextView committee1;
    TextView committee2;
    TextView bill1;
    TextView bill2;
    TextView billDetails1;
    TextView billDetails2;

    RelativeLayout relativeLayout;

    TextView more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            person = extras.getString("PERSON");
        }

        photo = (ImageView) findViewById(R.id.photo);
        title = (TextView) findViewById(R.id.title);
        name = (TextView) findViewById(R.id.name);
        party = (TextView) findViewById(R.id.party);
        endOfTerm = (TextView) findViewById(R.id.date);
        committee1 = (TextView) findViewById(R.id.committee1);
        committee2 = (TextView) findViewById(R.id.committee2);
        bill1 = (TextView) findViewById(R.id.bill1);
        bill2 = (TextView) findViewById(R.id.bill2);
        billDetails1 = (TextView) findViewById(R.id.bill1_details);
        billDetails2 = (TextView) findViewById(R.id.bill2_details);

        relativeLayout = (RelativeLayout) findViewById(R.id.relative_layout);

        more = (TextView) findViewById(R.id.more);

        if (Objects.equals(person, LEE)) {
            //Generates another sponsored bill
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView bill3 = new TextView(v.getContext());
                    bill3.setText(R.string.lee_bill3);
                    bill3.setTextColor(Color.parseColor("#000000"));
                    bill3.setTextSize(24);
                    bill3.setId(R.id.bill3);
                    RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params3.addRule(RelativeLayout.BELOW, R.id.bill2_details);
                    bill3.setPadding(25, 0, 0, 0);

                    relativeLayout.addView(bill3, params3);

                    TextView bill3Details = new TextView(v.getContext());
                    bill3Details.setText(R.string.lee_bill3_details);
                    bill3Details.setTextColor(Color.parseColor("#7e7e7e"));
                    bill3Details.setTextSize(18);
                    bill3Details.setId(R.id.bill3_details);
                    RelativeLayout.LayoutParams paramsDetails = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    paramsDetails.addRule(RelativeLayout.BELOW, R.id.bill3);
                    bill3Details.setPadding(25, 0, 0, 0);

                    relativeLayout.addView(bill3Details, paramsDetails);

                    RelativeLayout.LayoutParams moreParams = (RelativeLayout.LayoutParams) more.getLayoutParams();
                    moreParams.removeRule(RelativeLayout.BELOW);
                    moreParams.addRule(RelativeLayout.BELOW, R.id.bill3_details);
                    more.setLayoutParams(moreParams);

                }
            });
        }

        if (Objects.equals(person, FEINSTEIN)) {
            photo.setImageResource(R.drawable.feinstein);
            title.setText(R.string.senator);
            name.setText(R.string.feinstein_name);
            endOfTerm.setText(R.string.feinstein_end_of_term);
            committee1.setText(R.string.appropriations);
            committee2.setText(R.string.intelligence);
            bill1.setText(R.string.feinstein_bill1);
            billDetails1.setText(R.string.feinstein_bill1_details);
            bill2.setText(R.string.feinstein_bill2);
            billDetails2.setText(R.string.feinstein_bill2_details);

            //Insert additional committees
            TextView committee3 = new TextView(this);
            committee3.setText(R.string.judiciary);
            committee3.setTextColor(Color.parseColor("#000000"));
            committee3.setTextSize(24);
            committee3.setId(R.id.committee3);
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params3.addRule(RelativeLayout.BELOW, R.id.committee2);
            committee3.setPadding(25, 0, 0, 0);

            TextView committee4 = new TextView(this);
            committee4.setText(R.string.rules);
            committee4.setTextColor(Color.parseColor("#000000"));
            committee4.setTextSize(24);
            committee4.setId(R.id.committee4);
            RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params4.addRule(RelativeLayout.BELOW, R.id.committee3);
            committee4.setPadding(25, 0, 0, 0);

            relativeLayout.addView(committee3, params3);
            relativeLayout.addView(committee4, params4);

            TextView billsSponsored = (TextView) findViewById(R.id.bills_sponsored);
            RelativeLayout.LayoutParams billsParams = (RelativeLayout.LayoutParams) billsSponsored.getLayoutParams();
            billsParams.removeRule(RelativeLayout.BELOW);
            billsParams.addRule(RelativeLayout.BELOW, R.id.committee4);
            billsSponsored.setLayoutParams(billsParams);

            //Generates another sponsored bill
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView bill3 = new TextView(v.getContext());
                    bill3.setText(R.string.feinstein_bill3);
                    bill3.setTextColor(Color.parseColor("#000000"));
                    bill3.setTextSize(24);
                    bill3.setId(R.id.bill3);
                    RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params3.addRule(RelativeLayout.BELOW, R.id.bill2_details);
                    bill3.setPadding(25, 0, 0, 0);

                    relativeLayout.addView(bill3, params3);

                    TextView bill3Details = new TextView(v.getContext());
                    bill3Details.setText(R.string.feinstein_bill3_details);
                    bill3Details.setTextColor(Color.parseColor("#7e7e7e"));
                    bill3Details.setTextSize(18);
                    bill3Details.setId(R.id.bill3_details);
                    RelativeLayout.LayoutParams paramsDetails = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    paramsDetails.addRule(RelativeLayout.BELOW, R.id.bill3);
                    bill3Details.setPadding(25, 0, 0, 0);

                    relativeLayout.addView(bill3Details, paramsDetails);

                    RelativeLayout.LayoutParams moreParams = (RelativeLayout.LayoutParams) more.getLayoutParams();
                    moreParams.removeRule(RelativeLayout.BELOW);
                    moreParams.addRule(RelativeLayout.BELOW, R.id.bill3_details);
                    more.setLayoutParams(moreParams);

                }
            });
        }

        if (Objects.equals(person, BOXER)) {
            photo.setImageResource(R.drawable.boxer);
            title.setText(R.string.senator);
            name.setText(R.string.boxer_name);
            endOfTerm.setText(R.string.boxer_end_of_term);
            committee1.setText(R.string.ethics);
            committee2.setText(R.string.environment);
            bill1.setText(R.string.boxer_bill1);
            billDetails1.setText(R.string.boxer_bill1_details);
            bill2.setText(R.string.boxer_bill2);
            billDetails2.setText(R.string.boxer_bill2_details);

            //Insert additional committees
            TextView committee3 = new TextView(this);
            committee3.setText(R.string.foreign);
            committee3.setTextColor(Color.parseColor("#000000"));
            committee3.setTextSize(24);
            committee3.setId(R.id.committee3);
            RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params3.addRule(RelativeLayout.BELOW, R.id.committee2);
            committee3.setPadding(25, 0, 0, 0);

            relativeLayout.addView(committee3, params3);

            TextView billsSponsored = (TextView) findViewById(R.id.bills_sponsored);
            RelativeLayout.LayoutParams billsParams = (RelativeLayout.LayoutParams) billsSponsored.getLayoutParams();
            billsParams.removeRule(RelativeLayout.BELOW);
            billsParams.addRule(RelativeLayout.BELOW, R.id.committee3);
            billsSponsored.setLayoutParams(billsParams);

            //Generates another sponsored bill
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView bill3 = new TextView(v.getContext());
                    bill3.setText(R.string.boxer_bill3);
                    bill3.setTextColor(Color.parseColor("#000000"));
                    bill3.setTextSize(24);
                    bill3.setId(R.id.bill3);
                    RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params3.addRule(RelativeLayout.BELOW, R.id.bill2_details);
                    bill3.setPadding(25, 0, 0, 0);

                    relativeLayout.addView(bill3, params3);

                    TextView bill3Details = new TextView(v.getContext());
                    bill3Details.setText(R.string.boxer_bill3_details);
                    bill3Details.setTextColor(Color.parseColor("#7e7e7e"));
                    bill3Details.setTextSize(18);
                    bill3Details.setId(R.id.bill3_details);
                    RelativeLayout.LayoutParams paramsDetails = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    paramsDetails.addRule(RelativeLayout.BELOW, R.id.bill3);
                    bill3Details.setPadding(25, 0, 0, 0);

                    relativeLayout.addView(bill3Details, paramsDetails);

                    RelativeLayout.LayoutParams moreParams = (RelativeLayout.LayoutParams) more.getLayoutParams();
                    moreParams.removeRule(RelativeLayout.BELOW);
                    moreParams.addRule(RelativeLayout.BELOW, R.id.bill3_details);
                    more.setLayoutParams(moreParams);

                }
            });
        }

    }

/*
    public TextView textViewGen(Context context, float size, String color) {
        TextView returnView = new TextView(context);
        returnView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        returnView.setTextSize(size);
        returnView.setTextColor(Color.parseColor(color));
        return returnView;
    }*/

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