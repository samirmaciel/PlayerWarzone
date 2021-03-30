package com.samirmaciel.playerwarzone.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.samirmaciel.playerwarzone.R;
import com.samirmaciel.playerwarzone.dao.PlayerDAO;
import com.samirmaciel.playerwarzone.model.Player;
import com.samirmaciel.playerwarzone.model.WebScrapingDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button btnEntrar;
    private EditText inputNick;
    private Spinner spinnerPlatform;
    private ImageView backgroundImage, btnMute;
    private MediaPlayer backgroundsong, btnEntrarSound;
    private static ProgressBar loadEnter;
    public Player playerR;
    private int[]  bgs = {R.drawable.playerwarzone_background2, R.drawable.playerwarzone_background3, R.drawable.playerwarzone_background4};

    private String platform = "PLATAFORMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnEntrarSound = MediaPlayer.create(MainActivity.this, R.raw.entrandosound);
        backgroundsong = MediaPlayer.create(MainActivity.this, R.raw.backgroundmusicwarzone);
        backgroundsong.start();

        checkPlayerSalvo();

        backgroundImage = findViewById(R.id.background_Homeimage);
        loadEnter = findViewById(R.id.loadEnter);
        btnMute = findViewById(R.id.soundMute);
        btnEntrar =  findViewById(R.id.btnEntrar);
        inputNick =  findViewById(R.id.inputNick);
        spinnerPlatform = findViewById(R.id.spinnerPlatform);

        backgroundAletario();

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


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundsong.stop();
                btnEntrarSound.start();
                entrar();
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


    private boolean checkCampos(){
        if(!inputNick.getText().toString().equalsIgnoreCase("")){
            if(!platform.equalsIgnoreCase("PLATAFORMA")){
                return true;
            }else{
                Toast.makeText(getApplicationContext(), "Selecione a plataforma!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Preencha o NOME ID!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static void exbirProgresso(boolean visibility){
        loadEnter.setVisibility(visibility ? View.VISIBLE : View.GONE );

    }

    private void backgroundAletario(){
        Random random = new Random();
        int bgSelecionado = random.nextInt(bgs.length);
        int idBG = bgs[bgSelecionado];
        backgroundImage.setImageResource(idBG);
    }

    private void entrar(){
        if(checkCampos()) {
            Intent intent = new Intent(MainActivity.this, StatusActivity.class);
            try {

                Player player = checkPlayer(getPlayer());

                if (!(player == null)) {
                    Toast.makeText(getApplicationContext(), player.getNickname() + " entrando...", Toast.LENGTH_SHORT).show();
                    intent.putExtra("nickName", player.getNickname());
                    intent.putExtra("platform", player.getPlatform());
                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_esquerda);
                    ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
                    //finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Player não encontrado!", Toast.LENGTH_SHORT).show();
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            return;
        }
    }

    private void checkPlayerSalvo(){
        PlayerDAO dao = new PlayerDAO(getApplicationContext());
        if(dao.buscarPlayer() == null){
            return;
        }else{
            backgroundsong.stop();
            Intent intent = new Intent(MainActivity.this, StatusActivity.class);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_esquerda);
            ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
            finish();

        }
    }


}