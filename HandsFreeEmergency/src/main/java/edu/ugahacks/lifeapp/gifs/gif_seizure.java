package edu.ugahacks.lifeapp.gifs;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import edu.ugahacks.lifeapp.R;

public class gif_seizure extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif);

        ImageView i = findViewById(R.id.gif_seizure);
        Uri path = Uri.parse("android.resource://edu.ugahacks.lifeapp/drawable/seizure");
        Glide.with(getApplicationContext()).load(path).into(i);
    }
}
