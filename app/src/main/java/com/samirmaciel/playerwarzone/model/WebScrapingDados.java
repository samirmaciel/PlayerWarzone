package com.samirmaciel.playerwarzone.model;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.samirmaciel.playerwarzone.model.Player;
import com.samirmaciel.playerwarzone.view.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScrapingDados extends AsyncTask<Void, Void, Player> {

    private Player player = null;

    public WebScrapingDados(Player player) {
        this.player = player;


    }



    @Override
    protected void onPreExecute() {

        MainActivity.exbirProgresso(true);

    }

    @Override
    protected void onPostExecute(Player player) {
        MainActivity.exbirProgresso(false);

    }

    @Override
    protected Player doInBackground(Void... voids) {
        boolean isConnect = false;

        String[] split = player.getNickname().split("#");
        String nick = split[0];
        String codigo = split[1];

        Document doc = null;
        String url = "https://cod.tracker.gg/warzone/profile/"+ player.getPlatform() + "/" + nick + "%23"+ codigo + "/overview";
        String urlMatchs = "https://cod.tracker.gg/warzone/profile/atvi/dezk%236971848/matches";

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }




        try{
            System.out.println(doc.getElementsByClass("value"));
            isConnect = true;
        }catch (Exception e){
            isConnect = false;
        }

        if(isConnect) {
            if (doc.getElementsByClass("value").size() > 0) {
                player.setWinsBR(doc.getElementsByClass("value").get(15).text());
                player.setKd(doc.getElementsByClass("value").get(1).text());
                return player;
            } else {
                return null;
            }
        }else{
            return null;
        }
    }
}
