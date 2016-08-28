package com.johngodoi.amil.shootgame;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 28/08/16.
 */
public class StatisticsProcessor {
    private final LogReader logReader;
    private Date beginDate;
    private Date endDate;
    private Map<String, Integer> victimKills;
    private Map<String, Integer> killers;

    public StatisticsProcessor(LogReader logReader) {
        this.logReader=logReader;
        this.victimKills = new HashMap<String, Integer>();
        this.killers = new HashMap<String, Integer>();
    }

    public StatisticsProcessor(String gameLog) throws FileNotFoundException {
        this(new LogReader(gameLog));
    }

    public void process() {
        List<GameLog> logs = logReader.logs();
        for(GameLog log:logs)
            process(log);
    }

    private void process(GameLog log) {
        if(log instanceof MatchLog){
            extractMatchInfo(log);
        } else if(log instanceof KillLog){
            KillLog killLog = (KillLog) log;
            extractVictimInfo(killLog);
            extractKillerInfo(killLog);
        }
    }

    private void extractKillerInfo(KillLog killLog) {
        String killer = killLog.getKiller();
        if(killer.compareTo("<WORLD>")==0)return;
        if(killers.containsKey(killer)){
            killers.put(killer, killers.get(killer) + 1);
        } else {
            killers.put(killer, 1);
        }
    }

    private void extractVictimInfo(KillLog killLog) {
        if(victimKills.containsKey(killLog.getVictim())) {
            victimKills.put(killLog.getVictim(), victimKills.get(killLog.getVictim()) + 1);
        } else {
            victimKills.put(killLog.getVictim(), 1);
        }
    }

    private void extractMatchInfo(GameLog log) {
        String info = log.getInfo().toLowerCase();
        if(info.contains("started")){
            this.beginDate = log.getDate();
        }
        if(info.contains("ended")){
            this.endDate = log.getDate();
        }
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Map<String, Integer> getVictimKills() {
        return victimKills;
    }

    public Map<String, Integer> getKillers() {
        return killers;
    }
}
