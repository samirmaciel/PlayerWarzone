package com.samirmaciel.playerwarzone.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samirmaciel.playerwarzone.banco.Conexao;
import com.samirmaciel.playerwarzone.model.Player;

public class PlayerDAO {
    private SQLiteDatabase banco;

    public PlayerDAO(Context contexto) {
        Conexao conexao = new Conexao(contexto);
        banco = conexao.getWritableDatabase();
    }

    public void inserirPlayer(Player p){
        ContentValues values = new ContentValues();
        values.put("nickname", p.getNickname());
        values.put("platform", p.getPlatform());
        values.put("level", p.getLevel());
        values.put("prestige", p.getPrestige());
        values.put("winsbr", p.getWinsBR());
        values.put("kills", p.getKills());
        values.put("deaths", p.getDeaths());
        values.put("downs", p.getDowns());
        values.put("score", p.getScore());
        values.put("contracts", p.getContracts());
        values.put("kd", p.getKd());
        values.put("gametime", p.getGametime());
        values.put("matchs", p.getMatchs());
        values.put("headshots", p.getHeadshots());

        long num = banco.insert("players", null, values);
    }

    public void limparBanco(){
        banco.delete("players", null, null);
    }

    public Player buscarPlayer(){
        Cursor c = banco.query("players", new String[]{"id", "nickname", "platform", "level", "pretige", "winsbr", "kills", "deaths", "downs", "score", "contracts", "kd", "gametime", "matchs", "headshots"}, null, null, null, null, null);
        while(c.moveToNext()){
            Player p = new Player(c.getString(1), c.getString(2));
            p.setId(c.getInt(0));
            p.setLevel(c.getString(3));
            p.setPrestige(c.getString(4));
            p.setWinsBR(c.getString(5));
            p.setKills(c.getString(6));
            p.setDeaths(c.getString(7));
            p.setDowns(c.getString(8));
            p.setScore(c.getString(9));
            p.setContracts(c.getString(10));
            p.setKd(c.getString(11));
            p.setGametime(c.getString(12));
            p.setMatchs(c.getString(13));
            p.setHeadshots(c.getString(14));

            return p;
        }

        return null;
    }


}
