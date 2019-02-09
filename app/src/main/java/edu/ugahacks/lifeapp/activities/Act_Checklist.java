package edu.ugahacks.lifeapp.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import edu.ugahacks.lifeapp.MainActivity;
import edu.ugahacks.lifeapp.R;

public class Act_Checklist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("What are the victim's conditions?");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.checklistSubmit).setOnClickListener(v -> {
            ApplicationInfo ai = getApplicationContext().getApplicationInfo();
            String appName = ai.labelRes == 0 ? ai.nonLocalizedLabel.toString() : getApplicationContext().getString(ai.labelRes);
            String alertText = "This is an automated alert message from " + appName + ".";

            Location l = MainActivity.l;
            if (l == null) {
                SystemClock.sleep(5000);
                Toast.makeText(getApplicationContext(), "Still obtaining location. Please wait a moment.", Toast.LENGTH_LONG).show();
                return;
            }
            double lat = Objects.requireNonNull(l).getLatitude();
            double lon = Objects.requireNonNull(l).getLongitude();

            alertText += " There is a medically-afflicted victim located at (" + lat + ", " + lon + "). The victim is ";

            CheckBox boxIsConscious = findViewById(R.id.boxIsConscious);
            CheckBox boxIsBleeding = findViewById(R.id.boxIsBleeding);
            CheckBox boxHasPulse = findViewById(R.id.boxHasPulse);
            CheckBox boxIsCold = findViewById(R.id.boxIsCold);
            CheckBox boxIsDecapitated = findViewById(R.id.boxIsDecapitated);

            if (boxIsConscious.isChecked()) {
                alertText += "conscious, ";
            } else {
                alertText += "not conscious, ";
            }

            if (boxIsBleeding.isChecked()) {
                alertText += "bleeding, ";
            } else {
                alertText += "not bleeding, ";
            }

            if (boxHasPulse.isChecked()) {
                alertText += "has a pulse, ";
            } else {
                alertText += "has no pulse, ";
            }

            if (boxIsCold.isChecked()) {
                alertText += "is cold to the touch, ";
            } else {
                alertText += "is a normal temperature, ";
            }

            if (boxIsDecapitated.isChecked()) {
                alertText += "has lost their head, ";
            } else {
                alertText += "still has their head on, ";
            }

            alertText += "and was reported at " + DateFormat.getDateTimeInstance().format(new Date()) + ".";

            Log.d("sms", alertText);

            // the Intent is to provide survivors with a sense of pride and accomplishment
            startActivity(new Intent(getApplicationContext(), Act_GIF.class)); // TODO: alert operators of conditions
        });
    }

}
