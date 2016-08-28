package com.johngodoi.amil.shootgame;

/**
 * Created by john on 28/08/16.
 */
public class KillLog extends GameLog {
    private String killer;
    private String victim;
    private String deathMode;

    public String getKiller() {
        return killer;
    }

    public String getVictim() {
        return victim;
    }

    public void setKiller(String killer) {
        this.killer = killer;
    }

    public void setVictim(String victim) {
        this.victim = victim;
    }

    public void setDeathMode(String deathMode) {
        this.deathMode = deathMode;
    }

    public String getDeathMode() {
        return deathMode;
    }
}
