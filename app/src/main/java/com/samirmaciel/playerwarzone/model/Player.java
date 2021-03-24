package com.samirmaciel.playerwarzone.model;

import androidx.annotation.NonNull;

public class Player {

    private int id;
    private String nickname;
    private String platform;
    private String level;
    private String prestige;
    private String winsBR;
    private String kills;
    private String deaths;
    private String downs;
    private String score;
    private String contracts;
    private String kd;
    private String gametime;
    private String matchs;

    public Player(String nickname, String platform) {
        this.nickname = nickname;
        this.platform = platform;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }

    public String getWinsBR() {
        return winsBR;
    }

    public void setWinsBR(String winsBR) {
        this.winsBR = winsBR;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getDowns() {
        return downs;
    }

    public void setDowns(String downs) {
        this.downs = downs;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContracts() {
        return contracts;
    }

    public void setContracts(String contracts) {
        this.contracts = contracts;
    }

    public String getKd() {
        return kd;
    }

    public void setKd(String kd) {
        this.kd = kd;
    }

    public String getGametime() {
        return gametime;
    }

    public void setGametime(String gametime) {
        this.gametime = gametime;
    }

    public String getMatchs() {
        return matchs;
    }

    public void setMatchs(String matchs) {
        this.matchs = matchs;
    }


    @NonNull
    @Override
    public String toString() {
        return getNickname();
    }
}
