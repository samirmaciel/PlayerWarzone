package com.samirmaciel.playerwarzone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.samirmaciel.playerwarzone.R;
import com.samirmaciel.playerwarzone.model.Player;
import com.samirmaciel.playerwarzone.model.WebScrapingDados;

import java.util.concurrent.ExecutionException;

public class StatusActivity extends AppCompatActivity {

    private MediaPlayer entrouSound;
    private ImageView prestigeImage;

    private TextView wins, kills, deaths, downs, kd, level, score, gametime, prestige, contracts, matchs, headshots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        prestigeImage = findViewById(R.id.prestigeImage);
        entrouSound = MediaPlayer.create(StatusActivity.this, R.raw.entrousound);
        entrouSound.start();


        String nickname = getIntent().getExtras().getString("nickName");
        String platform = getIntent().getExtras().getString("platform");

        Player playerLogado = getPlayer(nickname, platform);


        wins = findViewById(R.id.textWinbr);
        kills = findViewById(R.id.textKills);
        deaths = findViewById(R.id.textDeaths);
        downs = findViewById(R.id.textDowns);
        kd = findViewById(R.id.textKd);
        level = findViewById(R.id.textLevel);
        score = findViewById(R.id.textScore);
        gametime = findViewById(R.id.textGametime);
        prestige = findViewById(R.id.textPrestige);
        contracts = findViewById(R.id.textContracts);
        matchs = findViewById(R.id.textMatchs);
        headshots = findViewById(R.id.textHeadshots);
        wins.setText(nickname);

        wins.setText("Wins: " + playerLogado.getWinsBR());
        kills.setText("Matadas: " + playerLogado.getKills());
        deaths.setText("Mortes: " + playerLogado.getDeaths());
        downs.setText("Derrubadas: " + playerLogado.getDowns());
        kd.setText("K/D: " + playerLogado.getKd());
        score.setText("Pontos: " + playerLogado.getScore());
        prestige.setText(playerLogado.getPrestige());
        level.setText(playerLogado.getLevel());
        contracts.setText("Contratos: " + playerLogado.getContracts());
        matchs.setText(playerLogado.getMatchs());
        gametime.setText(playerLogado.getGametime());
    }

    public Player getPlayer(String nick, String platform){
        Player player = new Player(nick, platform);

        WebScrapingDados web = new WebScrapingDados(player);

        try {
            Player playerLogado = web.execute().get();
            return playerLogado;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }



    }
}