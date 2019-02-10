package edu.ugahacks.lifeapp.gifs;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import edu.ugahacks.lifeapp.R;

public class gif_unconscious extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_unconscious);

        ImageView i = findViewById(R.id.img_uncon);
        Uri path = Uri.parse("android.resource://edu.ugahacks.lifeapp/drawable/cpr");
        Glide.with(getApplicationContext()).load(path).into(i);
    }
}
