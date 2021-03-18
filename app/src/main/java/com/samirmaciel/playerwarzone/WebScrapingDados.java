package com.samirmaciel.playerwarzone;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebScrapingDados extends AsyncTask<Void, Void, List<String>> {

    private String nickName;
    private String plataforma;

    public WebScrapingDados(String nickName, String plataforma) {
        this.nickName = nickName;
        this.plataforma = plataforma;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        Document doc = null;
        String url = "https://cod.tracker.gg/warzone/profile/" + plataforma + "/" + nickName + "/overview";
        String urlMatchs = "https://cod.tracker.gg/warzone/profile/atvi/dezk%236971848/matches";

        try {
            doc = Jsoup.connect(urlMatchs).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> listaValores = new ArrayList<>();
        /*for(int x = 0; x < 6; x++){
            listaValores.add(doc.getElementsByClass("value").get(x).text());
        }
        return listaValores;*/

        for (int x = 0; x < 4; x++){
            listaValores.add(doc.getElementsByAttribute("href").get(x).toString());
        }

        return listaValores;
    }
}
