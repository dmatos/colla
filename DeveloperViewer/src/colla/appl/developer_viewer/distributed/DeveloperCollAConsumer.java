/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.appl.developer_viewer.distributed;

import colla.kernel.api.CollAMessage;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.api.GenericConsumer;
import colla.kernel.api.GenericResource;
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
import org.openide.util.Exceptions;

/**
 *
 * @author dmatos
 */
public class DeveloperCollAConsumer<S extends CollAMessage> extends GenericConsumer<S> {

    public DeveloperCollAConsumer(GenericResource<S> re, DistributedTaskController controller) {
        super(re);
        this.controller = controller;
    }

    @Override
    protected void doSomething(S collAMsg) {
        TaskMessage taskMessage = (TaskMessage) collAMsg;
        CollATask task = taskMessage.getTask();

        if (task.hasSchedule()) {
            this.controller.scheduleTask(taskMessage);
        } else {
            User client = (User) taskMessage.getUser();
            String taskName = task.getTaskName();
            HashMap<String, CollAUser> group = taskMessage.getGroup();
            String groupName = taskMessage.getGroupName();

            //running task
            JCL_result jclr = this.executeTask(task);
            
            //@todo conversar com joubert sobre jcl sem host e sem server
            
            if (jclr.getErrorResult() == null) {
                System.err.println(jclr.getCorrectResult().toString());
            } else {
                //jclr.getErrorResult().printStackTrace();
            }
            try {
                // Sending a result back
                task.setResult(jclr);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
            this.controller.sendResultBack(groupName, group, task, taskName);
            this.deleteDir(new File("../" + task.getTaskID()));
        }
    }

    public JCL_result executeTask(CollATask task) {
        task.setStarted();
        JCL_facade jcl = JCL_FacadeImpl.getInstance();

        File[] jarsToRegister = new File[task.getDependencies().size() + 1];

        /*
         * receive the buffer of file
         */
        byte[] taskBuffer = task.getTask();
        String taskName = task.getTaskName();
        Map<String, byte[]> jars = task.getDependencies();
        String classToExecute = task.getClassToExecute();
        String methodToExecute = task.getMethodToExecute();


        //gathering files into array
        try {
            //write temp file for task
            new File("../" + task.getTaskID() + "/").mkdir();
            File file = new File("../" + task.getTaskID() + "/" + taskName);
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
                file = new File("../" + task.getTaskID() + "/" + fileName);
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

    

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    private DistributedTaskController controller;
}
