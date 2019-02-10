package edu.ugahacks.lifeapp.activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import edu.ugahacks.lifeapp.MainActivity;
import edu.ugahacks.lifeapp.R;

public class Act_Text911 extends AppCompatActivity {

    private static final String TAG = "MESSAGE";
    TextToSpeech tts;
    String phone;
    String txtMessage;
    SmsManager manager = SmsManager.getDefault();
    PendingIntent pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texting911);
        setTitle("SMS Confirmation");

        ApplicationInfo ai = getApplicationContext().getApplicationInfo();
        String appName = ai.labelRes == 0 ? ai.nonLocalizedLabel.toString() : getApplicationContext().getString(ai.labelRes);
        String alertText = "[TEST ALERT. NO NEED TO RESPOND.] This is an automated alert message from " + appName + ".";

        // get the location information.
        Location l = MainActivity.l;
        if (l == null) {
            SystemClock.sleep(5000);
            Toast.makeText(getApplicationContext(), "Still obtaining location. Please wait a moment.", Toast.LENGTH_LONG).show();
            tts.speak("obtaining Location", TextToSpeech.QUEUE_FLUSH, null, null);
            return;
        }
        double lat = Objects.requireNonNull(l).getLatitude();
        double lon = Objects.requireNonNull(l).getLongitude();
        String pos = "";
        try {
            Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geo.getFromLocation(lat, lon, 1);
            if (addresses.size() > 0) {
                Address a = addresses.get(0);
                pos = "\"" + a.getFeatureName() + "\", "
                        + a.getAddressLine(0);
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Unable to obtain a location description. Defaulting to latitude and longitude", Toast.LENGTH_LONG).show();
            pos = "(" + lat + "," + lon + ")";
        }

        alertText += " There is a medically-afflicted victim located at "
                + pos
                + ". The victim is "
                + Act_Checklist.symptoms
                + "and was reported at "
                + DateFormat.getDateTimeInstance().format(new Date())
                + ".";
        Log.d("sms", alertText);
//        tts.speak(alertText, TextToSpeech.QUEUE_FLUSH, null, null);
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
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_light_normal_background)
                .setContentTitle("Successful SMS sent by HandsFreeEmergency!");

        tts = new TextToSpeech(getApplicationContext(), status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                if (result == TextToSpeech.LANG_MISSING_DATA ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("error", "This language is not supported.");
                } else {
                    tts.speak("Message has been sent", TextToSpeech.QUEUE_FLUSH, null, null);
                }
            } else
                Log.e("error", "Initialization failed!");
        });

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(3908, mBuilder.build());

        phone = "4703518052";
        txtMessage = "Testing";

        ArrayList<String> parts = manager.divideMessage(alertText);
        ArrayList<PendingIntent> sendList = new ArrayList<>();
        sendList.add(PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("SMS_SENT"), 0));
        ArrayList<PendingIntent> deliverList = new ArrayList<>();
        deliverList.add(PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("SMS_DELIVERED"), 0));

        Intent messageIntent = new Intent(Intent.ACTION_SENDTO);
        messageIntent.setData(Uri.parse(phone));
        messageIntent.putExtra("Message", alertText);
        pi = PendingIntent.getBroadcast(this, 0, new Intent(alertText), 0);
        Log.i("Output is: ", alertText);
        manager.sendMultipartTextMessage(phone, null, parts, sendList, deliverList);
        Log.i("Sending message...", alertText);
    }

    @Override
    protected void onPause() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }

}
