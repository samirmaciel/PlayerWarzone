package com.samirmaciel.playerwarzone.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.samirmaciel.playerwarzone.R;
import com.samirmaciel.playerwarzone.model.Player;
import com.samirmaciel.playerwarzone.model.WebScrapingDados;

import java.util.concurrent.ExecutionException;

public class StatusActivity extends AppCompatActivity {

    private MediaPlayer entrouSound;
    private ImageView prestigeImage;
    private Button btnTrocarPlayer;
    private TextView wins, kills, deaths, downs, kd, level, score, gametime, prestige, contracts, matchs, textNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        btnTrocarPlayer = findViewById(R.id.btnTrocarPlayer);
        prestigeImage = findViewById(R.id.prestigeImage);
        entrouSound = MediaPlayer.create(StatusActivity.this, R.raw.entrousound);
        entrouSound.start();


        String nickname = getIntent().getExtras().getString("nickName");
        String platform = getIntent().getExtras().getString("platform");

        Player playerLogado = getPlayer(nickname, platform);

        String[] p = playerLogado.getPrestige().split(" ");
        int prestigeN = Integer.parseInt(p[1]);

        setPrestigeImage(prestigeN);

        textNick = findViewById(R.id.textNick);
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
        wins.setText(nickname);

        textNick.setText(playerLogado.getNickname());
        wins.setText("Wins: " + playerLogado.getWinsBR());
        kills.setText("Matou: " + playerLogado.getKills());
        deaths.setText("Morreu: " + playerLogado.getDeaths());
        downs.setText("Derrubadas: " + playerLogado.getDowns());
        kd.setText("K/D: " + playerLogado.getKd());
        score.setText("Pontos: " + playerLogado.getScore());
        prestige.setText(playerLogado.getPrestige());
        level.setText(playerLogado.getLevel());
        contracts.setText("Contratos: " + playerLogado.getContracts());
        matchs.setText("Partidas: " + playerLogado.getMatchs());
        gametime.setText("Tempo de jogo: " + playerLogado.getGametime());

        btnTrocarPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatusActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setPrestigeImage(int prestige) {
        switch (prestige){
            case 1:
                prestigeImage.setImageResource(R.drawable.p1);
                break;
            case 2:
                prestigeImage.setImageResource(R.drawable.p2);
                break;
            case 3:
                prestigeImage.setImageResource(R.drawable.p3);
                break;
            case 4:
                prestigeImage.setImageResource(R.drawable.p4);
                break;
            case 5:
                prestigeImage.setImageResource(R.drawable.p5);
                break;
            case 6:
                prestigeImage.setImageResource(R.drawable.p6);
                break;
            case 7:
                prestigeImage.setImageResource(R.drawable.p7);
                break;
            case 8:
                prestigeImage.setImageResource(R.drawable.p8);
                break;
            case 9:
                prestigeImage.setImageResource(R.drawable.p9);
                break;
            case 10:
                prestigeImage.setImageResource(R.drawable.p10);
                break;
            case 11:
                prestigeImage.setImageResource(R.drawable.p11);
                break;
        }
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