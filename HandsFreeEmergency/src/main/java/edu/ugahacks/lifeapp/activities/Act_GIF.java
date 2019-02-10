package edu.ugahacks.lifeapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import edu.ugahacks.lifeapp.R;

public class Act_GIF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif);

        // TODO: insert relevant GIFs here instead of cats.
        ImageView i = findViewById(R.id.anim_1);
        Glide.with(getApplicationContext()).load("https://media.giphy.com/media/OmK8lulOMQ9XO/giphy.gif").into(i);

    }
}
