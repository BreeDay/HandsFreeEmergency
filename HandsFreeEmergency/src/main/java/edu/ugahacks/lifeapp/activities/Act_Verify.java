package edu.ugahacks.lifeapp.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import edu.ugahacks.lifeapp.R;
import edu.ugahacks.lifeapp.activities.Act_Text911;

public class Act_Verify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify);

        Random r = new Random();
        int code = r.nextInt((9999 - 1000) + 1) + 1000;

        TextView tv = findViewById(R.id.codeView);
        tv.setText("" + code);

        FloatingActionButton b = findViewById(R.id.buttonSubmitCode);
        b.setOnClickListener(e -> {
            EditText et = findViewById(R.id.enterCode);
            if (Integer.parseInt(et.getText().toString()) == code) {
                startActivity(new Intent(getApplicationContext(), Act_Checklist.class));
            } else {
                et.setText("");
                Toast.makeText(getApplicationContext(), "Incorrect code. Try again.", Toast.LENGTH_LONG).show();
            }
        });

    }

}
