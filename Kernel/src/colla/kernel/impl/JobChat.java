/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAJob;
import colla.kernel.util.TimeAndDate;
import java.io.Serializable;

/**
 *
 * @author dmatos
 */
public class JobChat implements CollAJob, Serializable {

    public JobChat() {
        TimeAndDate time = new TimeAndDate();
        this.jobStartedAt = time.getSimpleDate() + " " + time.getHour();
        this.setJobName();
    }

    @Override
    public void setJobName() {
        this.jobName = "Chat";

    }

    @Override
    public String getJobName() {
        return this.jobName;
    }

    @Override
    public String getJobDateAndTime() {
        return this.jobStartedAt;
    }
    
    private static final long serialVersionUID = 1L;
    private String jobStartedAt;
    private String jobName;
}
