package edu.ugahacks.lifeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.ugahacks.lifeapp.R;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient flpc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flpc = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        // TODO: implement this
        //        flpc.getLastLocation();


        Button b = findViewById(R.id.call911);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(i);
            }
        });

    }

}
