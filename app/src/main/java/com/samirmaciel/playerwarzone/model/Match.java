package com.samirmaciel.playerwarzone.model;

public class Match {
    private String gamename;
    private String kills;
    private String damage;
    private String totalxp;
    private String deaths;

    public Match(String gamename, String kills, String damage, String totalxp, String deaths) {
        this.gamename = gamename;
        this.kills = kills;
        this.damage = damage;
        this.totalxp = totalxp;
        this.deaths = deaths;
    }

    public String getKills() {
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getTotalxp() {
        return totalxp;
    }

    public void setTotalxp(String totalxp) {
        this.totalxp = totalxp;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}
