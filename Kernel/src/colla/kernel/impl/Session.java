/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.util.TimeAndDate;
import colla.kernel.api.CollAJob;
import colla.kernel.api.CollASession;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class Session implements CollASession, Serializable {
	
	public Session() {
        TimeAndDate time = new TimeAndDate();
        jobs = new CollAJob[20];
        counter = 0;
        maximum = 20;
        this.dateAndTime = time.getSimpleDate()+" "+time.getHour();
    }

    @Override
    public void addJob(CollAJob job) {

        if (counter < maximum) {
            jobs[counter++] = job;
        } else {
            CollAJob[] temp = new CollAJob[2 * maximum];
            System.arraycopy(job, 0, temp, 0, counter);
            jobs = new CollAJob[2 * maximum];
            System.arraycopy(temp, 0, job, 0, counter);
            maximum = 2 * maximum;
            jobs[counter++] = job;
        }
                System.out.println("Adding job: "+jobs[counter-1].getJobName());
    }

    @Override
    public CollAJob[] getJobs() {
        return jobs;
    }

    @Override
    public void setSessionID(long id) {
        this.sessionID = id;
    }

    @Override
    public long getSessionID() {
        return this.sessionID;
    }

    @Override
    public String getSessionDateAndTime() {
        return this.dateAndTime;
    }    
    
    private String dateAndTime;
    private CollAJob[] jobs;
    private int counter;
    private int maximum;
    private long sessionID;
    private static final long serialVersionUID = 1L;
}
