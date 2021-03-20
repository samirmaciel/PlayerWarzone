package com.samirmaciel.playerwarzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.samirmaciel.playerwarzone.model.Player;

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

    private String platform = "PLATAFORMA";

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

        spinnerPlatform.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItem().equals("XBOX")){
                    platform = "xbl";
                }else if (parent.getSelectedItem().equals("PS")){
                    platform = "psn";
                }else if(parent.getSelectedItem().equals("ACTIVISION")){
                    platform = "atvi";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                platform = (String) parent.getSelectedItem();
            }
        });

        backgroundsong = MediaPlayer.create(MainActivity.this, R.raw.backgroundmusicwarzone);
        backgroundsong.start();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkNick()) {
                    if (checkSpinner()) {
                        Intent intent = new Intent(MainActivity.this, StatusActivity.class);
                        try {
                            Player player = checkPlayer(getPlayer());
                            if(!(player == null)){
                                intent.putExtra("nickName", player.getNickname());
                                intent.putExtra("platform", player.getPlatform());
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Player não encontrado!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Selecione a plataforma!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Digite seu nick!", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

    private Player getPlayer(){

        Player player = new Player(inputNick.getText().toString(), platform);


        return player;


    }

    private Player checkPlayer(Player player) throws ExecutionException, InterruptedException {
        WebScrapingDados scraping = new WebScrapingDados(player);

        Player playerRecuperado = scraping.execute().get();

        if(!(playerRecuperado == null)){
            return playerRecuperado;
        }else{
            return null;
        }
    }

    private boolean checkSpinner(){
        if(platform.equalsIgnoreCase("PLATAFORMA")){
            return false;
        }else{
            return true;
        }
    }

    private boolean checkNick(){
        if(inputNick.getText().toString().equalsIgnoreCase("")){
            return false;
        }else{
            return true;
        }
    }
}