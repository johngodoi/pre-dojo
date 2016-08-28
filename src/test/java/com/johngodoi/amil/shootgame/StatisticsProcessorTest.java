package com.johngodoi.amil.shootgame;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import static com.johngodoi.amil.shootgame.LogFiles.FINAL_LOG;
import static com.johngodoi.amil.shootgame.LogFiles.GAME_LOG;
import static com.johngodoi.amil.shootgame.LogFiles.INITIAL_LOG;
import static com.johngodoi.amil.shootgame.LogFiles.VALID_KILL_LOG;
import static com.johngodoi.amil.shootgame.LogFiles.WORLD_KILL_LOG;

/**
 * Created by john on 28/08/16.
 */
public class StatisticsProcessorTest {

    @Test
    public void extractInfoFromInitialMatchLog() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(INITIAL_LOG)));
        StatisticsProcessor statisticsProcessor = new StatisticsProcessor(logReader);
        statisticsProcessor.process();
        Assert.assertEquals("Initial Date is wrong",new Date(23,04,2013,15,34,22),statisticsProcessor.getBeginDate());
    }

    @Test
    public void extractInfoFromFinalMatchLog() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(FINAL_LOG)));
        StatisticsProcessor statisticsProcessor = new StatisticsProcessor(logReader);
        statisticsProcessor.process();
        Assert.assertEquals("Final Date is wrong",new Date(23,04,2013,15,39,22),statisticsProcessor.getEndDate());
    }

    @Test
    public void extractInfoFromValidKillLog() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(VALID_KILL_LOG)));
        StatisticsProcessor statisticsProcessor = new StatisticsProcessor(logReader);
        statisticsProcessor.process();
        Map<String,Integer> victimKills = statisticsProcessor.getVictimKills();
        Assert.assertEquals("Victims quantity is wrong", 1, victimKills.keySet().size());
        Assert.assertEquals("Nick should be a victim and he should have died once", 1, victimKills.get("Nick").intValue());

        Map<String,Integer> killers = statisticsProcessor.getKillers();
        Assert.assertEquals("Killers quantity is wrong", 1, killers.keySet().size());
        Assert.assertEquals("Roman should have killed someone once", 1, killers.get("Roman").intValue());
    }

    @Test
    public void extractInfoFromWorldKillLog() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(WORLD_KILL_LOG)));
        StatisticsProcessor statisticsProcessor = new StatisticsProcessor(logReader);
        statisticsProcessor.process();
        Map<String,Integer> victimKills = statisticsProcessor.getVictimKills();
        Assert.assertEquals("Victims quantity is wrong", 1, victimKills.keySet().size());
        Assert.assertEquals("Nick should be a victim and he should have died once", 1, victimKills.get("Nick").intValue());

        Map<String,Integer> killers = statisticsProcessor.getKillers();
        Assert.assertEquals("here is no killer", 0, killers.keySet().size());
    }

    @Test
    public void extractInfoFromWholeLog() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(GAME_LOG)));
        StatisticsProcessor statisticsProcessor = new StatisticsProcessor(logReader);
        statisticsProcessor.process();

        Assert.assertEquals("Initial Date is wrong",new Date(23,04,2013,15,34,22),statisticsProcessor.getBeginDate());
        Assert.assertEquals("Final Date is wrong",new Date(23,04,2013,15,39,22),statisticsProcessor.getEndDate());
        Map<String,Integer> victimKills = statisticsProcessor.getVictimKills();
        Assert.assertEquals("Victims quantity is wrong", 1, victimKills.keySet().size());
        Assert.assertEquals("Nick should be a victim and he should have died twice", 2, victimKills.get("Nick").intValue());

        Map<String,Integer> killers = statisticsProcessor.getKillers();
        Assert.assertEquals("Killers quantity is wrong", 1, killers.keySet().size());
        Assert.assertEquals("Roman should have killed someone once", 1, killers.get("Roman").intValue());
    }
}
