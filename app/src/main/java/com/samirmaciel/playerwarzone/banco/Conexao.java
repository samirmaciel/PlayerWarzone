package com.samirmaciel.playerwarzone.banco;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private static String name = "banco.db";
    private static int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_players = "CREATE TABLE players (integer id primary key autoincrement, string name, string platform, string level, string prestige, string winsbr, string kills," +
                "string deaths, string downs, string score, string contracts, string kd, string gametime, string matchs, string headshots); ";

        db.execSQL(sql_players);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
