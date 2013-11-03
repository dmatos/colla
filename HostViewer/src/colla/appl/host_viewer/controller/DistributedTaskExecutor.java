/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.host_viewer.controller;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import colla.kernel.messages.toHost.TaskMessage;
import implementations.dm_kernel.user.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dmatos
 */
public class DistributedTaskExecutor {

    public DistributedTaskExecutor(){
        
    }
    
    protected JCL_result executeDistributedTask(CollAMessage collAMsg, CollAConsumer consumer) {
        TaskMessage taskMessage = (TaskMessage) collAMsg;
        CollATask task = taskMessage.getTask();

        User client = (User) taskMessage.getUser();
        String taskName = task.getTaskName();
        HashMap<String, CollAUser> group = taskMessage.getGroup();
        String groupName = taskMessage.getGroupName();

        task.setStarted();
        JCL_facade jcl = JCL_FacadeImpl.getInstance();                

        this.registerFiles(task);

        String ticket = "";

        //execute task

        JCL_result jclr = null;

        try {
            Object[] args = task.getArguments();
            /*System.err.println("class: " + cTask.getClassToExecute());
             System.err.println("method: " + cTask.getMethodToExecute());
             System.err.println(("Task: " + cTask.getTaskName() + "\n Executing: " + cTask.getClassToExecute()));*/
            //HostViewerController.getInstance().displayStatus("Task: " + cTask.getTaskID());
            ticket = jcl.execute(task.getClassToExecute(), task.getMethodToExecute(), args);
            //System.err.println("Recebeu o ticket " + ticket);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        //get result for task
        jclr = jcl.getResultBlocking(ticket);
        //System.err.println("Conseguiu resultado para o ticket " + ticket);
        task.setFinished();
        jcl.removeResult(ticket);

        return jclr;
    }   

    public void registerFiles(CollATask task) {

        JCL_facade jcl = JCL_FacadeImpl.getInstance();
        String taskName = task.getTaskName();

        File[] jarsToRegister = new File[task.getDependencies().size() + 1];
        /*
         * receive the buffer of file
         */
        byte[] taskBuffer = task.getTask();
        Map<String, byte[]> jars = task.getDependencies();
        String classToExecute = task.getClassToExecute();
        String methodToExecute = task.getMethodToExecute();        

        //gathering files into array
        try {
            //write temp file for task
            new File("../temp_files/").mkdir();
            new File("../temp_files/" + task.getTaskID() + "/").mkdir();
            File file = new File("../temp_files/" + task.getTaskID() + "/" + taskName);
            FileOutputStream fout = new FileOutputStream(file);
            DataOutputStream dout = new DataOutputStream(fout);
            dout.write(taskBuffer);
            dout.flush();

            //first element of the array of files must be the task
            //File file = new File(taskName);
            jarsToRegister[0] = file;

            //and then dependencies
            int jarCounter = 1;
            for (String fileName : jars.keySet()) {
                //write temp file for dependencies
                file = new File("../temp_files/" + task.getTaskID() + "/" + fileName);
                byte[] jar = jars.get(fileName);
                fout = new FileOutputStream(file);
                dout = new DataOutputStream(fout);
                dout.write(jar);
                dout.flush();
                //add dependencie to array of files
                jarsToRegister[jarCounter++] = file;
            }

            //close file streams
            dout.close();
            fout.close();

            //registering task in javaCaLa
            if (jcl.register(jarsToRegister, classToExecute)) {
                //System.err.println("jar registrado com sucesso");
            } else {
                //System.err.println("jar nao foi registrado com sucesso");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
