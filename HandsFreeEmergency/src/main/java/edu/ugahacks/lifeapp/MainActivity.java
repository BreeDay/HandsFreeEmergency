package edu.ugahacks.lifeapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import edu.ugahacks.lifeapp.activities.Act_Trauma;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Error";
    private FusedLocationProviderClient flpc;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    String phone;
    String txtMessage;
    public static Location l;

    /**
     * Determines what to do upon the user selecting to allow or deny the necessary permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(getApplicationContext(), "Location denied; exiting application.", Toast.LENGTH_SHORT).show();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Hands-Free Emergency");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            flpc = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            final Task t = flpc.getLastLocation();
            t.addOnSuccessListener(this, o -> {
                // *should* be a Location.
                l = (Location) o;
                Toast.makeText(getApplicationContext(), "Location obtained!", Toast.LENGTH_SHORT).show();
            });
            Toast.makeText(getApplicationContext(), "Obtaining your location...", Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.call911).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Act_Trauma.class)));

    }

    protected void smsText() {
        txtMessage = "This is a Test";
        phone = "(650)-555-1212";

        Intent messageIntent = new Intent(Intent.ACTION_SENDTO);
        messageIntent.setData(Uri.parse(phone));
        messageIntent.putExtra("Message", txtMessage);
        Log.i("Output is : ", txtMessage);
        if (messageIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(messageIntent);
            Log.i("Is there a message?  ", txtMessage);
        } else {
            Log.d(TAG, "No message");
        }

    }


}
