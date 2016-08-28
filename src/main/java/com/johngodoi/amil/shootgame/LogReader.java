package com.johngodoi.amil.shootgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by john on 28/08/16.
 */
public class LogReader {
    private final Scanner scanner;

    public LogReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public LogReader(String gameLog) throws FileNotFoundException {
        this(new Scanner(new File(gameLog)));
    }

    public GameLog nextLog() {
        String line = scanner.nextLine();
        GameLog log;
        String[] logSplited = line.split("-");
        String info = logSplited[1].trim();
        String dateTime = logSplited[0].trim();
        log = instantiateLog(info);
        setUpDate(log, dateTime);
        log.setInfo(info);
        return log;
    }

    private void setUpDate(GameLog log, String dateTime) {
        log.setDateTime(dateTime);
        String[] dateSplited = log.getDateTime().split("[/, ,:]");
        log.setDate(new Date(new Integer(dateSplited[0]),new Integer(dateSplited[1]),new Integer(dateSplited[2]),new Integer(dateSplited[3]),new Integer(dateSplited[4]),new Integer(dateSplited[5])));
    }

    private GameLog instantiateLog(String info) {
        GameLog log;
        if(info.toLowerCase().contains("match")){
            log = new MatchLog();
        } else if(info.toLowerCase().contains("killed")){
            KillLog killLog = new KillLog();
            killLog.setKiller(info.split(" ")[0]);
            killLog.setVictim(info.split(" ")[2]);
            killLog.setDeathMode(info.split(" ")[4]);
            log = killLog;
        } else log = new GameLog();
        return log;
    }

    public List<GameLog> logs() {
        List<GameLog> logs = new ArrayList<GameLog>();
        while (scanner.hasNext()){
            logs.add(nextLog());
        }
        return logs;
    }
}
