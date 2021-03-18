package com.samirmaciel.playerwarzone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebScrapingDados web = new WebScrapingDados("samir", "xbl");

        List<String> lista = new ArrayList<>();

        try {
            lista = web.execute().get();
            for(int x = 0; x <= lista.size() - 1; x++){
                System.out.println(lista.get(x));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}