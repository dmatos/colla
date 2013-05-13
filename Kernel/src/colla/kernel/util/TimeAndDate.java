/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author dmatos
 */
public class TimeAndDate implements Serializable {

    private static final long serialVersionUID = 1L;
    private long seconds,
                 minutes,
                 hour;
    private Date date;
    private SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    private long begining,
            end;
    private long elapsedTime;
    private String totalTime;
    private boolean finished;

    public TimeAndDate() {
        date = new Date();
        elapsedTime = 0;
        begining = 0;
        end = 0;
        hour = 0;
        minutes = 0;
        seconds = 0;
        this.finished = false;
    }

    public void startTimer() {
        this.finished = false;
        begining = System.nanoTime();
    }

    /**
     * 
     * @return elapsed time in nanoseconds
     */
    private Long getElapsedTime() {        
        this.elapsedTime = System.nanoTime() - begining;
        return elapsedTime;
    }

    //retorna no formato dd-mm-aaaa
    public String getSimpleDate() {
        return simpleDate.format(new Date());
    }

    public String getHour() {
        GregorianCalendar gcalendar = new GregorianCalendar();
        String horario = "";
        horario = horario + gcalendar.get(Calendar.HOUR_OF_DAY) + ":" + gcalendar.get(Calendar.MINUTE) + ":" + gcalendar.get(Calendar.SECOND);
        return horario;
    }

    public String getStartDate() {
        return date.toString();
    }

    public String getEndDate() {
        return date.toString();
    }

    public void stopTimer() {
        if (!this.finished) {
            this.finished = true;
            this.end = System.nanoTime();
            long total = getElapsedTime() / 1000000000L; //nanoseconds to seconds
            seconds = total % 60L;
            minutes = (total / 60L) % 60L;
            hour = (total / 360L);
            totalTime = "";
            if (hour < 10) {
                totalTime = totalTime + "0" + hour;
            } else {
                totalTime = totalTime + hour;
            }
            totalTime = totalTime + ":";
            if (minutes < 10) {
                totalTime = totalTime + "0" + minutes;
            } else {
                totalTime = totalTime + minutes;
            }
            totalTime = totalTime + ":";
            if (seconds < 10) {
                totalTime = totalTime + "0" + seconds;
            } else {
                totalTime = totalTime + seconds;
            }
        }
    }

    public String getTotalTime() {
        if(this.finished)
            return this.totalTime;
        return "not finished";
    }

    public Long getTotalTimeInNanoS() {
        return this.end - this.begining;
    }
}
