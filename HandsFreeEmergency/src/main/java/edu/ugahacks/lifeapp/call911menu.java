package edu.ugahacks.lifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.ugahacks.lifeapp.activities.Act_Text911;

public class call911menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call911menu);


        Button btn1 = findViewById(R.id.button);

        btn1.setOnClickListener(v -> {
                    Log.i("ambulance", "switching to 911 ambulance call");
                    Intent int1 = new Intent(call911menu.this, Act_Text911.class);
                    startActivity(int1);

                });

        Button btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(v -> {
            Log.i("police no danger", "switching to contacting police when in no danger");
            Intent int2 = new Intent(call911menu.this, Act_Text911.class);
            startActivity(int2);

        });

        Button btn3 = findViewById(R.id.button3);

        btn3.setOnClickListener(v -> {
            Log.i("ambulance and police", "switching to calling 911 for ambulance and police");
            Intent int3 = new Intent(call911menu.this, Act_Text911.class);
            startActivity(int3);

        });

        Button btn4 = findViewById(R.id.button4);

        btn4.setOnClickListener(v -> {
            Log.i("police danger", "switching to calling 911 when in danger");
            Intent int4 = new Intent(call911menu.this, Act_Text911.class);
            startActivity(int4);

        });

    }



}
