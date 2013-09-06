
package colla.kernel.api;

/**
 * A session starts when an user connects to the server. 
 * All Jobs started by an user should be recorded in his current session.
 * 
 * @author dmatos
 */
public interface CollASession {
    
    /**
     * 
     * @param job A CollAJob started in the current session.
     */
    abstract void addJob(CollAJob job);
    
    /**
     * 
     * @return an array containing all the jobs that a client 
     * ran.
     */
    abstract CollAJob[] getJobs();
    
    /**
     * Sets an identification number for the session
     */
    abstract void setSessionID(long id);
    
    /**
     * 
     * @return the identification number of session
     */
    abstract  long getSessionID();
    
    /**
     * 
     * @return a String representing the date and time that the
     * session has started. Date and time must be given as arguments in the constructor.
     */
    abstract String getSessionDateAndTime();
}
