package com.samirmaciel.playerwarzone;

import android.os.AsyncTask;

import com.samirmaciel.playerwarzone.model.Player;

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
    protected Player doInBackground(Void... voids) {

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


        boolean isConnect = false;

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
