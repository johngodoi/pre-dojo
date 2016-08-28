package com.johngodoi.amil.shootgame;

import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by john on 28/08/16.
 */
public class Ranking {
    private final StatisticsProcessor statisticsProcessor;

    public Ranking(StatisticsProcessor statisticsProcessor) {
        statisticsProcessor.process();
        this.statisticsProcessor = statisticsProcessor;
    }

    public String getKillerRanking() {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> killers = this.statisticsProcessor.getKillers();
        killers.values().stream().sorted().forEach( kills ->
                killers.keySet().stream().forEach(killer -> {if(killers.get(killer)==kills)
                    sb.append(killer + " - " + kills + '\n');})
        );
        return sb.toString();
    }

    public String getVictimRanking() {
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> victimKills = this.statisticsProcessor.getVictimKills();
        victimKills.values().stream().sorted().forEach( deaths ->
                victimKills.keySet().stream().forEach(victim -> {if(victimKills.get(victim)==deaths)
                    sb.append(victim + " - " + deaths + '\n');})
        );
        return sb.toString();
    }
}
