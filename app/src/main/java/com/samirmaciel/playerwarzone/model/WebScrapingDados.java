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

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }




        try{
            System.out.println(doc.getElementsByClass("value"));
            System.out.println(doc.getElementsByClass("playtime").text());
            isConnect = true;
        }catch (Exception e){
            isConnect = false;
        }

        if(isConnect) {
            if (doc.getElementsByClass("value").size() > 0) {
                player.setWinsBR(doc.getElementsByClass("value").get(16).text());
                player.setKd(doc.getElementsByClass("value").get(7).text());
                player.setKills(doc.getElementsByClass("value").get(20).text());
                player.setDeaths(doc.getElementsByClass("value").get(21).text());
                player.setDowns(doc.getElementsByClass("value").get(23).text());
                player.setScore(doc.getElementsByClass("value").get(10).text());
                player.setLevel(doc.getElementsByClass("highlight-suptext").text());
                player.setPrestige(doc.getElementsByClass("highlight-text").text());
                player.setContracts(doc.getElementsByClass("value").get(28).text());
                player.setMatchs(doc.getElementsByClass("Matches").get(1).text());
                player.setGametime(doc.getElementsByClass("playtime").get(0).text());




                return player;
            } else {
                return null;
            }
        }else{
            return null;
        }
    }
}
