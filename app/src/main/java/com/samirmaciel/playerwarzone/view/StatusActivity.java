package com.samirmaciel.playerwarzone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;

import com.samirmaciel.playerwarzone.R;

public class StatusActivity extends AppCompatActivity {

    private MediaPlayer entrouSound;
    private ImageView prestigeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        prestigeImage = findViewById(R.id.prestigeImage);
        entrouSound = MediaPlayer.create(StatusActivity.this, R.raw.entrousound);
        entrouSound.start();
    }
}