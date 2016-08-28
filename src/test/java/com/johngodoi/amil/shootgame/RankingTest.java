package com.johngodoi.amil.shootgame;

import org.junit.Assert;
import org.junit.Test;

import static com.johngodoi.amil.shootgame.LogFiles.GAME_LOG;

/**
 * Created by john on 28/08/16.
 */
public class RankingTest {
    @Test
    public void killerRanking() throws Exception {
        Ranking ranking = new Ranking(new StatisticsProcessor(GAME_LOG));
        Assert.assertEquals("Roman - 1"+'\n',ranking.getKillerRanking());
    }
    @Test
    public void victimRankin() throws Exception {
        Ranking ranking = new Ranking(new StatisticsProcessor(GAME_LOG));
        Assert.assertEquals("Nick - 2"+'\n',ranking.getVictimRanking());
    }
}
