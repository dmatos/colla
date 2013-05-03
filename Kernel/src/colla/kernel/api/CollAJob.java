package colla.kernel.api;

/**
 * Jobs are activities performed by an user while connected to the server
 * and should be recorded in the CollASessions.
 * 
 * @author dmatos
 */
public interface CollAJob {
    
    /**
     * Sets a name for the job
     */
    abstract void setJobName();
    
    /**
     * 
     * @return name of job
     */
    abstract String getJobName();
    
    /**
     * Date and time should be given in the constructor.
     * @return the date and time at which the Job was started.     
     */
    abstract String getJobDateAndTime();
}
