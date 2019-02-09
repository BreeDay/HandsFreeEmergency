package edu.ugahacks.lifeapp.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

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
            // TODO: send SMS to 911 via external method, direct user to instructional GIFs.

            // TODO: deprecate using the notification, maybe?
            // https://stackoverflow.com/a/47974065
            String CHANNEL_ID = "lifeapp_1";
            String name = "lifeapp_1";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            String d = "HandsFreeEmergency primary channel";
            mChannel.setDescription(d);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(alertText))
                    .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                    .setContentTitle("Successful SMS sent by HandsFreeEmergency!");

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);
            // notificationID allows you to update the notification later on.
            mNotificationManager.notify(3908, mBuilder.build());

            // TODO: not this.
            // the Intent is to provide survivors with a sense of pride and accomplishment #EAGames #ItsInYourBloodstream
            startActivity(new Intent(getApplicationContext(), Act_Text911.class));
        });
    }

}
