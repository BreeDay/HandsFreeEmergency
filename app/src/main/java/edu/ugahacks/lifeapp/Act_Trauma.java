package edu.ugahacks.lifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Act_Trauma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Trauma / Emergency");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_trauma);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // "speak to 911"
        Button b = findViewById(R.id.speak911);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Act_911.class);
                startActivity(i);
            }
        });
    }

}
