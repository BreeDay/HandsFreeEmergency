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

public class safeprompt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safeprompt);
        Button btn5 = findViewById(R.id.button5);

        btn5.setOnClickListener(v -> {
            Log.i("Yes it is safe", "switching to intervention for when it is safe");
            Intent int5 = new Intent(safeprompt.this, medmain.class);
            startActivity(int5);

        });

        Button btn6 = findViewById(R.id.button6);

        btn6.setOnClickListener(v -> {
            Log.i("No it is not safe", "switching to intervention for when it is not safe");
            Intent int6 = new Intent(safeprompt.this, medmain.class);
            startActivity(int6);

        });

            }

    }


