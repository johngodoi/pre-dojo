package com.johngodoi.amil.shootgame;

import java.util.Date;

/**
 * Created by john on 28/08/16.
 */
public class GameLog {
    private String dateTime;
    private String info;
    private Date date;

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getInfo() {
        return info;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
