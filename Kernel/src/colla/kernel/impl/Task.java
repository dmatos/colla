/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollATask;
import colla.kernel.api.CollATicket;
import colla.kernel.util.TimeAndDate;
import interfaces.kernel.JCL_result;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class Task implements CollATask, Serializable {

    private static final long serialVersionUID = 1L;
    private TimeAndDate time;
    private String methodToExecute;
    private String name;
    private byte[] task;
    private byte[] fileResult;
    private String classToExecute;
    private Map<String, byte[]> dependencies;
    private String startTime;
    private String finishTime;
    //private String totalTime;
    private Object result;
    private String owner;
    private String group;
    private Boolean started;
    private Boolean finished;
    private List<byte[]> parameters;
    private List<String> parametersFilename;
    private Boolean hasTicket;
    private CollATicket ticket;
    private Long taskID;
    private Date schedule;
    private Boolean distributedTask;

    public Task() {
        this.methodToExecute = "";
        this.name = "";
        this.task = null;
        this.classToExecute = "";
        this.dependencies = new HashMap<String, byte[]>();
        this.parameters = new ArrayList<byte[]>();
        time = new TimeAndDate();
        this.result = "waiting result";
        this.finishTime = "not finished yet";
        this.started = false;
        this.finished = false;
        this.parametersFilename = new ArrayList<String>();
        this.hasTicket = false;
        this.taskID = new Long(0);
        this.schedule = null;
    }

    @Override
    public void setTaskName(String name) {
        this.name = name;
    }

    @Override
    public String getTaskName() {
        return this.name;
    }

    @Override
    public void setTask(File taskFile) throws Exception {
        FileInputStream fiin = new FileInputStream(taskFile);
        DataInputStream diin = new DataInputStream(fiin);
        this.task = new byte[(int) taskFile.length()];
        diin.read(this.task);
        diin.close();
        fiin.close();
    }

    @Override
    public byte[] getTask() {
        return this.task;
    }

    @Override
    public void setClassToExecute(String className) {
        this.classToExecute = className;
    }

    @Override
    public String getClassToExecute() {
        return this.classToExecute;
    }

    @Override
    public void setMethodToExecute(String methodName) {
        this.methodToExecute = methodName;
    }

    @Override
    public String getMethodToExecute() {
        return this.methodToExecute;
    }

    @Override
    public void addDependency(File file) throws Exception {
        FileInputStream fiin = new FileInputStream(file);
        DataInputStream diin = new DataInputStream(fiin);
        byte fileBuffer[] = new byte[(int) file.length()];
        diin.read(fileBuffer);
        this.dependencies.put(file.getName(), fileBuffer);
        diin.close();
        fiin.close();
    }

    @Override
    public Map<String, byte[]> getDependencies() {
        return this.dependencies;
    }

    @Override
    public void setStarted() {
        if (!this.started) {
            this.started = true;
            time.startTimer();
            this.startTime = time.getStartDate() + " " + time.getHour();
        }
    }

    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public void setFinished() {
        if (!this.finished) {
            this.finished = true;
            time.stopTimer();
            this.finishTime = time.getEndDate() + " " + time.getHour();
        }
    }

    @Override
    public String getTotalTime() {
        return time.getTotalTime();
    }

    @Override
    public String getInitialTime() {
        return this.startTime;
    }

    @Override
    public String getFinalTime() {
        return this.finishTime;
    }

    @Override
    public Long getTotalTimeInNanoS() {
        return time.getTotalTimeInNanoS();
    }

    @Override
    public void setResult(JCL_result jclr) throws Exception {
        if (jclr == null) {
            result = "could not execute task";
        } else if (jclr.getErrorResult() == null) {
            this.result = jclr.getCorrectResult();
            if (this.result instanceof File) {
                File file = (File) this.result;
                FileInputStream fiin = new FileInputStream(file);
                DataInputStream diin = new DataInputStream(fiin);
                this.fileResult = new byte[(int) file.length()];
                diin.read(this.fileResult);
                diin.close();
                fiin.close();
            }
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            jclr.getErrorResult().printStackTrace(pw);
            result = sw.toString();
        }
    }

    @Override
    public Object getResult() {
        return this.result;
    }

    @Override
    public File getFileFromResult() throws Exception {
        if (this.result instanceof File) {
            File temp = (File) this.result;
            FileOutputStream fout = new FileOutputStream(temp);
            DataOutputStream dout = new DataOutputStream(fout);
            dout.write(this.fileResult);
            dout.flush();
            dout.close();
            fout.close();
            return temp;
        }
        return null;
    }

    @Override
    public void setOwner(String name) {
        this.owner = name;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public void addArgument(File file) throws Exception {
        FileInputStream fiin = new FileInputStream(file);
        DataInputStream diin = new DataInputStream(fiin);
        byte fileBuffer[] = new byte[(int) file.length()];
        diin.read(fileBuffer);
        this.parameters.add(fileBuffer);
        this.parametersFilename.add(file.getName());
        diin.close();
        fiin.close();
    }

    @Override
    public Object[] getArguments() throws Exception {
        if (this.parameters.size() > 0) {
            Object[] args = new Object[this.parameters.size()];
            for (int i = 0; i < this.parameters.size(); i++) {
                FileOutputStream fout = new FileOutputStream(parametersFilename.get(i));
                DataOutputStream dout = new DataOutputStream(fout);
                dout.write(this.parameters.get(i));
                dout.flush();
                File f = new File(this.parametersFilename.get(i));
                args[i] = f;
                dout.close();
                fout.close();
            }
            return args;
        }
        return null;
    }

    @Override
    public boolean hasTicket() {
        return this.hasTicket;
    }

    @Override
    public CollATicket getTicket() {
        return this.ticket;
    }

    @Override
    public void setTicket(CollATicket ticket) {
        this.ticket = ticket;
        this.hasTicket = true;
    }

    @Override
    public boolean removeTicket() {
        if (this.hasTicket) {
            this.ticket = null;
            this.hasTicket = false;
            return true;

        }
        return false;
    }

    @Override
    public void setTaskID(Long id) {
        this.taskID = id;
    }

    @Override
    public Long getTaskID() {
        return this.taskID;
    }

    @Override
    public void clean() {
        this.classToExecute = null;
        for (String name : this.dependencies.keySet()) {
            @SuppressWarnings("unused")
            byte[] b = this.dependencies.remove(name);
            b = null;
        }
        this.dependencies.clear();
        this.dependencies = null;
        this.fileResult = null;
        this.finishTime = null;
        this.group = null;
        this.methodToExecute = null;
        this.name = null;
        this.owner = null;
        for (int i = 0; i < parameters.size(); i++) {
            @SuppressWarnings("unused")
            byte[] b = parameters.remove(i);
            b = null;
        }
        this.parameters.clear();
        this.parameters = null;
        for (int i = 0; i < parametersFilename.size(); i++) {
            @SuppressWarnings("unused")
            String s = parametersFilename.remove(i);
            s = null;
        }
        this.parametersFilename.clear();
        this.parametersFilename = null;
        this.result = null;
        this.startTime = null;
        this.task = null;
    }

    @Override
    public Date getSchedule() {
        return this.schedule;
    }

    @Override
    public void setSchedule(Date date) {
        this.schedule = date;
    }
    
    @Override
    public boolean hasSchedule(){
        if(this.schedule == null)
            return false;
        return true;
    }
    
    @Override
    public void setDistributedMode(Boolean isDisbributed){
        this.distributedTask = isDisbributed;
    }

    @Override
    public boolean isDistributed() {
        return this.distributedTask;
    }
}
