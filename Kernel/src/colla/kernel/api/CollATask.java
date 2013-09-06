package colla.kernel.api;

import interfaces.kernel.JCL_result;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * A task is an application to submit to run on the hosts. It will return a result, if any,
 * or a ticket to fetch a result from some host.
 * 
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public interface CollATask {
    
    /**
     * Sets task name.
     * @param name title of task
     */
    public void setTaskName(String name);
    
    /**
     * Gets task name. 
     * @return the name of task
     */
    public String getTaskName();
    
    /**
     * Sets a Jar file to run on the CollAHosts.
     * @param task a Jar file containing an application      
     */
    public void setTask(File task) throws Exception;
    
    /**
     * Gets task data as an array of bytes
     * @return task data as an array of bytes
     */
    public byte[] getTask();
    
    /**
     * Sets the name of the class to run.
     * @param className name of Class to be executed
     */
    public void setClassToExecute(String className);
    
    /**
     * Gets name of the class to run.
     * @return class name to be executed
     */
    public String getClassToExecute();
    
    /**
     * Sets name of the method to run.
     * @param methodName name of method to run.
     */
    public void setMethodToExecute(String methodName);
    
    /**
     * @return the name of the method to be executed
     */
    public String getMethodToExecute();
    
    /**
     * Adds a dependency file to the task.
     * 
     * @param file dependency file
     * @throws Exception 
     */
    public void addDependency(File file) throws Exception;
    
    /**
     * Gets a map containing dependencies in which keys are the file names.
     * @return a map containing the dependency files.
     */
    public Map<String, byte[]> getDependencies();
    
    /**
     * Adds a argument file to task.
     * @param file argument file.
     * @throws Exception 
     */
    public void addArgument(File file) throws Exception;
    
    /**
     * Gets all argument files.
     * @return argument files
     * @throws Exception 
     */
    public Object[] getArguments() throws Exception;
    
    /**
     * Defines the task as started
     */
    public void setStarted();
    
    /**
     * Defines the task as completed
     */
    public void setFinished();
    
    /**
     * Is the task finished?
     */
    public boolean isFinished();
    
    /**
     * @return elapsed time of execution.	
     */
    public String getTotalTime();
    
    /**
     * @return current time when task begins to run
     */
    public String getInitialTime();
    
    /**
     * @return current time when task is finished
     */
    public String getFinalTime();
    
    /**
     * @return elapsed time of execution in nanoseconds.
     */
    public Long getTotalTimeInNanoS();
    
    /**
     * Sets the result returned by the application, if any.
     * @param jclr the result of task that is an object of JCL_result from javaCá&Lá
     * @throws Exception 
     */
    public void setResult(JCL_result jclr) throws Exception;
    
    /**
     * 
     * @return a file generated as a result of the application.
     * @throws Exception 
     */
    public File getFileFromResult() throws Exception;
    
    /**     
     * @return application result
     */
    public Object getResult();
    
    /**
     * Sets name of user who submitted the task.
     * @param name name of user who submitted the task.
     */
    public void setOwner(String name);
    
    /**
     * @return name of user who submitted the task
     */
    public String getOwner();
    
    /**
     * Checks if the task has a ticket to fetch a result, otherwise
     * the result is already included to the task.
     * 
     * @return true if it has a ticket, false otherwise
     */
    public boolean hasTicket();
    
    /**
     * 
     * @param ticket A ticket to make the result of an application available to group members who submitted the task.
     */
    public void setTicket(CollATicket ticket);
    
    /**
     * 
     * @return a ticket to permit that group members who submitted the task can fetch the result of 
     * an application from the host.
     */
    public CollATicket getTicket();
    
    /**
     * Removes a ticket previously set
     * 
     * @return true if it has a ticket, false otherwise
     */
    public boolean removeTicket();
    
    /**
     * 
     * @param id identification number of the task
     */
    public void setTaskID(Long id);
    
    /**
     * 
     * @return the task ID
     */
    public Long getTaskID();
    
    /**
     * 
     * @return task schedule
     */
    public Date getSchedule();
    
    /**
     * 
     * @param date Date to schedule task to run. It might be null so the task 
     * will run as soon as a host is available.
     */
    public void setSchedule(Date date);
    
    /**
     * 
     * @return true if the task has a schedule, false otherwise
     */
    public boolean hasSchedule();
    
    /**
     * Destructor
     */
    public void clean();
    
}
