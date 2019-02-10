package edu.ugahacks.lifeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import java.util.Locale;

import edu.ugahacks.lifeapp.R;

public class Act_Checklist extends AppCompatActivity {

    public static String symptoms;

    // CRUCIAL: define null to be 0
    public final static int NULL = 0;
    TextToSpeech tts;

    // ESSENTIAL: define 0 to be null
    public final static String ZERO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("What are the victim's conditions?");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        tts = new TextToSpeech(getApplicationContext(), i -> {
            if(i != TextToSpeech.ERROR){
                tts.setLanguage(Locale.US);
                //tts.setPitch(3);
                tts.setSpeechRate(-1);
                tts.speak("What is the victim's condition?" +
                        "Does the victim have a pulse? " +
                        "Is the victim  conscious, bleeding, cold or Decapitated", TextToSpeech.QUEUE_FLUSH, null, null);

            }
        });

        CheckBox boxIsConscious = findViewById(R.id.boxIsConscious);
        CheckBox boxIsBleeding = findViewById(R.id.boxIsBleeding);
        CheckBox boxHasPulse = findViewById(R.id.boxHasPulse);
        CheckBox boxIsCold = findViewById(R.id.boxIsCold);
        CheckBox boxIsDecapitated = findViewById(R.id.boxIsDecapitated);

        symptoms = "";

        if (boxIsConscious.isChecked()) {
            symptoms += "conscious, ";
        } else {
            symptoms += "not conscious, ";
        }

        if (boxIsBleeding.isChecked()) {
            symptoms += "bleeding, ";
        } else {
            symptoms += "not bleeding, ";
        }

        if (boxHasPulse.isChecked()) {
            symptoms += "has a pulse, ";
        } else {
            symptoms += "has no pulse, ";
        }

        if (boxIsCold.isChecked()) {
            symptoms += "is cold to the touch, ";
        } else {
            symptoms += "is a normal temperature, ";
        }

        if (boxIsDecapitated.isChecked()) {
            symptoms += "has lost their head, ";
        } else {
            symptoms += "still has their head on, ";
        }

        findViewById(R.id.checklistSubmit).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Act_Verify.class)));
    }

}
