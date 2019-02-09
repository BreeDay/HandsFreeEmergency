package edu.ugahacks.lifeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.ugahacks.lifeapp.R;

public class Act_Trauma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Trauma / Emergency");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.trauma);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // "speak to 911"
        findViewById(R.id.speak911).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Act_Call911.class));
            }
        });

        // "offer aid"
        findViewById(R.id.help911).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Act_Checklist.class));
            }
        });

    }

}
