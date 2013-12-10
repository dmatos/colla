/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.tasks;

import colla.kernel.api.CollAHost;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollATicket;
import interfaces.kernel.JCL_result;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dmatos
 */
public class SentTask implements CollATask{
    
    public SentTask(CollATask task){
        this.task = task;
        this.hosts = new ArrayList<CollAHost>(5);
    }        
    
    public void addHosts(List<CollAHost> hostList){
        this.hosts.addAll(hostList);
    }
    
    public List<CollAHost> getHostsList(){
        return this.hosts;
    }
    
    public CollATask getEmbededTask(){
        return this.task;
    }
    
    /**
     * 
     * @return last host to which this task has been sent to.
     */
    public CollAHost getActualHost(){
        CollAHost host = this.hosts.get(0);
        return host;
    }
    
    /**
     * 
     * @return next available host to send this task.
     */
    public CollAHost getNextHost(){
        CollAHost host = null;
        this.hosts.remove(0);
        host= this.hosts.get(0);                
        return host;        
    }

    @Override
    public void setTaskName(String name) {
        task.setTaskName(name);
    }

    @Override
    public String getTaskName() {
       return task.getTaskName();
    }

    @Override
    public void setTask(File taskFile) throws Exception {
        this.task.setTask(taskFile);
    }

    @Override
    public byte[] getTask() {
        return this.task.getTask();
    }

    @Override
    public void setClassToExecute(String className) {
        task.setClassToExecute(className);
    }

    @Override
    public String getClassToExecute() {
        return task.getClassToExecute();
    }

    @Override
    public void setMethodToExecute(String methodName) {
        this.task.setMethodToExecute(methodName);
    }

    @Override
    public String getMethodToExecute() {
        return this.task.getMethodToExecute();
    }

    @Override
    public void addDependency(File file) throws Exception {
       this.task.addDependency(file);
    }

    @Override
    public Map<String, byte[]> getDependencies() {
        return this.task.getDependencies();
    }

    @Override
    public void addArgument(File file) throws Exception {
        this.task.addArgument(file);
    }

    @Override
    public Object[] getArguments() throws Exception {
        return this.task.getArguments();
    }

    @Override
    public void setStarted() {
       this.task.setStarted();
    }

    @Override
    public void setFinished() {
        this.task.setFinished();
    }

    @Override
    public boolean isFinished() {
        return this.task.isFinished();
    }

    @Override
    public String getTotalTime() {
       return this.task.getTotalTime();
    }

    @Override
    public String getInitialTime() {
       return this.task.getInitialTime();
    }

    @Override
    public String getFinalTime() {
        return this.task.getFinalTime();
    }

    @Override
    public Long getTotalTimeInNanoS() {
        return this.task.getTotalTimeInNanoS();
    }

    @Override
    public void setResult(JCL_result jclr) throws Exception {
        this.task.setResult(jclr);
    }

    @Override
    public File getFileFromResult() throws Exception {
        return this.task.getFileFromResult();
    }

    @Override
    public Object getResult() {
        return this.task.getResult();
    }

    @Override
    public void setOwner(String name) {
        this.task.setOwner(name);
    }

    @Override
    public String getOwner() {
        return this.task.getOwner();
    }

    @Override
    public boolean hasTicket() {
        return this.task.hasTicket();
    }

    @Override
    public void setTicket(CollATicket ticket) {
        this.task.setTicket(ticket);
    }

    @Override
    public CollATicket getTicket() {
        return this.task.getTicket();
    }

    @Override
    public boolean removeTicket() {
       return this.task.removeTicket();
    }

    @Override
    public void setTaskID(Long id) {
        this.task.setTaskID(id);
    }

    @Override
    public Long getTaskID() {
        return this.task.getTaskID();
    }

    @Override
    public Date getSchedule() {
        return this.task.getSchedule();
    }

    @Override
    public void setSchedule(Date date) {
        this.task.setSchedule(date);
    }

    @Override
    public boolean hasSchedule() {
        return this.task.hasSchedule();
    }

    @Override
    public void setDistributedMode(Boolean isDistributed) {
        this.task.setDistributedMode(isDistributed);
    }

    @Override
    public boolean isDistributed() {
        return this.task.isDistributed();
    }
    
    @Override
    public String getGroup(){
        return this.task.getGroup();
    }

    @Override
    public void clear() {
        this.task.clear();
        this.task = null;
        this.hosts.clear();
    }        
    
    private CollATask task;    
    private List<CollAHost> hosts;
    
}
