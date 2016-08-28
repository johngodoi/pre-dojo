package com.johngodoi.amil.shootgame;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.johngodoi.amil.shootgame.LogFiles.GAME_LOG;
import static com.johngodoi.amil.shootgame.LogFiles.VALID_KILL_LOG;
import static com.johngodoi.amil.shootgame.LogFiles.WORLD_KILL_LOG;

/**
 * Created by john on 28/08/16.
 */
public class LogReaderTest {


    @Test
    public void readFirstLine() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(GAME_LOG)));
        GameLog log = logReader.nextLog();

        Assert.assertEquals("String DateTime is not setted","23/04/2013 15:34:22",log.getDateTime());
        Assert.assertEquals("String Info is not setted","New match 11348965 has started",log.getInfo());
    }

    @Test
    public void readAllLines() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(GAME_LOG)));
        List<GameLog> logs = logReader.logs();

        Assert.assertEquals("Quantity of logs loaded is not right",4,logs.size());
        Assert.assertTrue("Log should be for a match type", logs.get(0) instanceof MatchLog);
        Assert.assertEquals("String DateTime is not setted","23/04/2013 15:34:22",logs.get(0).getDateTime());
        Assert.assertEquals("String Info is not setted","New match 11348965 has started",logs.get(0).getInfo());
        Assert.assertTrue("Log should be for a kill type", logs.get(1) instanceof KillLog);
        Assert.assertEquals("String DateTime is not setted","23/04/2013 15:36:04",logs.get(1).getDateTime());
        Assert.assertEquals("String Info is not setted","Roman killed Nick using M16",logs.get(1).getInfo());
        Assert.assertTrue("Log should be for a kill type", logs.get(2) instanceof KillLog);
        Assert.assertEquals("String DateTime is not setted","23/04/2013 15:36:33",logs.get(2).getDateTime());
        Assert.assertEquals("String Info is not setted","<WORLD> killed Nick by DROWN",logs.get(2).getInfo());
        Assert.assertTrue("Log should be for a match type", logs.get(3) instanceof MatchLog);
        Assert.assertEquals("String DateTime is not setted","23/04/2013 15:39:22",logs.get(3).getDateTime());
        Assert.assertEquals("String Info is not setted","Match 11348965 has ended",logs.get(3).getInfo());
    }

    @Test
    public void readValidKill() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(VALID_KILL_LOG)));
        KillLog killLog = (KillLog) logReader.nextLog();
        Assert.assertEquals("Date logged is wrong", new Date(23,04,2013,15,36,04),killLog.getDate());
        Assert.assertEquals("Wrong killer", "Roman", killLog.getKiller());
        Assert.assertEquals("Wrong victim", "Nick", killLog.getVictim());
        Assert.assertEquals("Wrong death mode", "M16", killLog.getDeathMode());
    }

    @Test
    public void readWorldKill() throws Exception {
        LogReader logReader = new LogReader(new Scanner(new File(WORLD_KILL_LOG)));
        KillLog killLog = (KillLog) logReader.nextLog();
        Assert.assertEquals("Date logged is wrong", new Date(23,04,2013,15,36,33),killLog.getDate());
        Assert.assertEquals("Wrong killer", "<WORLD>", killLog.getKiller());
        Assert.assertEquals("Wrong victim", "Nick", killLog.getVictim());
        Assert.assertEquals("Wrong death mode", "DROWN", killLog.getDeathMode());
    }
}
