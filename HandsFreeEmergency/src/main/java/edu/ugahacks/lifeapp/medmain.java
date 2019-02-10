package edu.ugahacks.lifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.ugahacks.lifeapp.gifs.gif_bleed;
import edu.ugahacks.lifeapp.gifs.gif_choking;
import edu.ugahacks.lifeapp.gifs.gif_seizure;
import edu.ugahacks.lifeapp.gifs.gif_unconscious;

public class medmain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medmain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.button_seizure).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), gif_seizure.class)));
        findViewById(R.id.button_unconscious).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), gif_unconscious.class)));
        findViewById(R.id.butt_bleed).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), gif_bleed.class)));
        findViewById(R.id.butt_choke).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), gif_choking.class)));
    }

}
