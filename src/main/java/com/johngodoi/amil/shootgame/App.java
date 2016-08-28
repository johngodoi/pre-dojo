package com.johngodoi.amil.shootgame;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Ranking ranking = null;
        try {
            ranking = new Ranking(new StatisticsProcessor("src/main/resources/game.log"));
        } catch (FileNotFoundException e) {
            System.out.println("Log not found");
            e.printStackTrace();
            System.exit(15);
        }
        System.out.println( "Resultados do Jogo" );
        System.out.println("Ranking de assassinos:");
        System.out.printf(ranking.getKillerRanking());
        System.out.println("Ranking de mortes:");
        System.out.printf(ranking.getVictimRanking());
    }
}
