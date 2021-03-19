package com.samirmaciel.playerwarzone;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText inputNick;
    private Spinner spinnerPlatform;
    private ImageView backgroundImage;
    private ImageView btnMute;
    private MediaPlayer backgroundsong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMute = findViewById(R.id.soundMute);
        btnEntrar =  findViewById(R.id.btnEntrar);
        inputNick =  findViewById(R.id.inputNick);
        spinnerPlatform = findViewById(R.id.spinnerPlatform);

        List<String> listaPlatform = new ArrayList<>();

        listaPlatform.add("PLATAFORMA");
        listaPlatform.add("XBOX");
        listaPlatform.add("PS");
        listaPlatform.add("ACTIVISION");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.spinner_item, listaPlatform);
        spinnerPlatform.setAdapter(adapter);

        backgroundsong = MediaPlayer.create(MainActivity.this, R.raw.backgroundmusicwarzone);
        backgroundsong.start();

        btnMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(backgroundsong.isPlaying()){
                    backgroundsong.pause();
                    btnMute.setImageResource(R.drawable.ic_baseline_music_note_24);
                }else if(backgroundsong.isPlaying() == false){
                    backgroundsong.start();
                    btnMute.setImageResource(R.drawable.ic_baseline_music_off_24);
                }
            }
        });


    }
}