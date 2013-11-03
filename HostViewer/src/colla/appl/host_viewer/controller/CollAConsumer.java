package colla.appl.host_viewer.controller;

import colla.appl.host_viewer.exceptions.HostControllerInitializationException;
import colla.kernel.api.*;
import colla.kernel.enumerations.HostOps;
import colla.kernel.impl.User;
import colla.kernel.messages.toClient.TaskResultMsg;
import colla.kernel.messages.toHost.CancelTask;
import colla.kernel.messages.toHost.ListOnlineHostsMsg;
import colla.kernel.messages.toHost.RegisterFileMsg;
import colla.kernel.messages.toHost.TaskMessage;
import colla.kernel.messages.toServer.RetrieveOnlineHostsMsg;
import colla.kernel.messages.toServer.TransmitResultMsg;
import implementations.sm_kernel.JCL_FacadeImpl;
import interfaces.kernel.JCL_facade;
import interfaces.kernel.JCL_result;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CollAConsumer<S extends CollAMessage> extends GenericConsumer<S> {

    private CollAHost host;
    private final int timeout = 0;
    private String serverIPaddress;
    private int serverPortNumber;
    private DistributedTaskExecutor distributedExecutor;

    public CollAConsumer(GenericResource<S> re, String serverIP, Integer serverPortNumber) {
        super(re);
        try {
            this.host = HostViewerController.getInstance().getHost();
        } catch (HostControllerInitializationException ex) {
            Logger.getLogger(CollAConsumer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.serverIPaddress = serverIP;
            this.serverPortNumber = serverPortNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.distributedExecutor = new DistributedTaskExecutor();
    }

    @Override
    protected void doSomething(S collAMessage) {
        HostOps operation;

        try {
            operation = (HostOps) collAMessage.getOperation();
            //System.err.println(operation.toString());
            switch (operation) {
                case PING: {
                }
                break;
                case TASK_EXECUTE: {
                    TaskMessage taskMessage = (TaskMessage) collAMessage;
                    CollATask task = taskMessage.getTask();

                    if (task.hasSchedule()) {
                        HostViewerController.getInstance().scheduleTask(taskMessage);
                    } else {
                        User client = (User) taskMessage.getUser();
                        String taskName = task.getTaskName();
                        HashMap<String, CollAUser> group = taskMessage.getGroup();
                        String groupName = taskMessage.getGroupName();

                        //running task
                        JCL_result jclr = this.executeTask(task);
                        HostViewerController.getInstance().displayStatus("done!!! ");
                        if (jclr.getErrorResult() == null) {
                            System.err.println(jclr.getCorrectResult().toString());
                        } else {
                            //jclr.getErrorResult().printStackTrace();
                        }
                        // Sending a result to client
                        HostViewerController.getInstance().displayStatus("Sending result back...");
                        task.setResult(jclr);
                        this.sendResultBack(groupName, group, task, taskName);
                        HostViewerController.getInstance().deleteDir(new File("../" + task.getTaskID()));
                    }
                }
                break; // end case TASK_EXECUTE
                case TASK_EXECUTE_DISTRIBUTED: {
                    TaskMessage taskMessage = (TaskMessage) collAMessage;
                    CollATask task = taskMessage.getTask();

                    if (task.hasSchedule()) {
                        HostViewerController.getInstance().scheduleTask(taskMessage);
                    } else {
                        User client = (User) taskMessage.getUser();
                        String taskName = task.getTaskName();
                        HashMap<String, CollAUser> group = taskMessage.getGroup();
                        String groupName = taskMessage.getGroupName();

                        //running task
                        JCL_result jclr = this.distributedExecutor.executeDistributedTask(taskMessage, this);
                        HostViewerController.getInstance().displayStatus("done!!! ");
                        if (jclr.getErrorResult() == null) {
                            System.err.println(jclr.getCorrectResult().toString());
                        } else {
                            //jclr.getErrorResult().printStackTrace();
                        }
                        // Sending a result to client
                        HostViewerController.getInstance().displayStatus("Sending result back...");
                        task.setResult(jclr);
                        this.sendResultBack(groupName, group, task, taskName);
                        HostViewerController.getInstance().deleteDir(new File("../temp_files/" + task.getTaskID()));
                    }
                }
                break; // end case TASK_EXECUTE-DISTRIBUTED
                case TASK_CANCEL: {
                    CancelTask msg = (CancelTask) collAMessage;
                    long taskID = msg.getTaskID();
                    HostViewerController.getInstance().cancelScheduledTask(taskID);
                }
                break;
                case REGISTER_FILE: {
                    RegisterFileMsg registerFilesMsg = (RegisterFileMsg) collAMessage;
                    TaskMessage taskMessage = registerFilesMsg.getTaskMsg();
                    CollATask task = taskMessage.getTask();
                    this.distributedExecutor.registerFiles(task);
                }
                break;
                /*case DOWNLOAD_RESULT: {
                 DownloadResultMsg msg = (DownloadResultMsg) collAMessage;
                 Integer ticket = msg.getTicket();
                 CollATask result = hostViewer.getStoredResult(ticket);
                 result.removeTicket();
                 ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
                 output.writeObject(result);
                 output.flush();
                 }
                 break;*/
                default:
                    System.err.println("Operation not supported: " + operation.toString());
            }

        } catch (SocketException se) {
            //se.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
           // cnfe.printStackTrace();
        } catch (IOException io) {
            //io.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }// end of doSomething

    /**
     * Send result back to all members of the group
     *
     * @param group hashmap of username to User
     * @param jclr result of javaCaLa
     * @param taskName name of the task
     */
    public void sendResultBack(String groupName, HashMap<String, CollAUser> group, CollATask task, String taskName) {
        for (String userName : group.keySet()) {
            if (group.get(userName).hasValidIP()) {
                sendResultBackToValidIPClient(groupName, group.get(userName), task);
                try {
                    HostViewerController.getInstance().displayStatus("Result sent back to valid ip.");
                } catch (HostControllerInitializationException ex) {
                    Logger.getLogger(CollAConsumer.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                sendResultBackToInvalidIPClient(groupName, group.get(userName), task);
            }
        }
    }

    public void distributeTaskFiles(TaskMessage taskMsg) {
        //ask for online hosts to server
        RetrieveOnlineHostsMsg rOnlineMsg = new RetrieveOnlineHostsMsg(host.getName());
        Socket socket;
        try {
            socket = new Socket(InetAddress.getByName(serverIPaddress), serverPortNumber);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(rOnlineMsg);
            output.flush();
            //ACK
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ListOnlineHostsMsg incomingMsg = (ListOnlineHostsMsg) input.readObject();

            RegisterFileMsg registerFileMsg = new RegisterFileMsg(taskMsg);
            try {
                for (CollAHost h : incomingMsg.getHosts()) {
                    if (!h.getName().equals(host.getName())) {
                        Socket s = new Socket(InetAddress.getByName(h.getIp()), h.getPort());
                        ObjectOutputStream outp = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(registerFileMsg);
                        output.flush();
                        //ACK
                        ObjectInputStream inp = new ObjectInputStream(socket.getInputStream());
                        inp.readObject();
                    }
                }
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(CollAConsumer.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CollAConsumer.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CollAConsumer.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Send a result back to a user with valid ip
     *
     * @param groupName the name of the user group
     * @param client the user to send the result
     * @param task the task that generated the result
     */
    public void sendResultBackToValidIPClient(String groupName, CollAUser client, CollATask task) {
        if (client.isOnline() && client.getPort() > 0) {
            try {
                TaskResultMsg outgoingOfClient = new TaskResultMsg(HostViewerController.getInstance().getHost().getName(), task.getTaskName(), task);
                outgoingOfClient.setGroupName(groupName);
                //System.err.println("sending result to valid");

                Socket socket = new Socket(InetAddress.getByName(client.getIp()), client.getPort());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(outgoingOfClient);
                output.flush();
                //ACK
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                input.readObject();
                socket.close();
            } catch (Exception e) {
                //e.printStackTrace();
                //sendResultBackToInvalidIPClient(groupName, client, task);
            }
        }
    }

    /**
     * Send a result back to a user with invalid ip
     *
     * @param groupName the name of the user group
     * @param client the user to send the result
     * @param task the task that generated the result
     */
    public void sendResultBackToInvalidIPClient(String groupName, CollAUser client, CollATask task) {
        //System.err.println("sending result to invalid");
        if (client.isOnline()) {
            try {
                TransmitResultMsg outgoing = new TransmitResultMsg(client, task.getTaskName(), task);
                outgoing.setGroupName(groupName);
                Socket socket = new Socket(InetAddress.getByName(serverIPaddress), serverPortNumber);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(outgoing);
                output.flush();
                //ACK
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                input.readObject();
            } catch (Exception e) {
                try {
                    HostViewerController.getInstance().displayStatus("Error: couldn't send result back!");

                } catch (HostControllerInitializationException ex) {
                    Logger.getLogger(CollAConsumer.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }

    /**
     * Executes an task.
     *
     * @param cTask An object of CollATask that has been executed on the host.
     * @return An JCL_result, que é um resultado obtido pelo jcl.
     */
    public JCL_result executeTask(CollATask cTask) {
        cTask.setStarted();
        JCL_facade jcl = JCL_FacadeImpl.getInstance();

        File[] jarsToRegister = new File[cTask.getDependencies().size() + 1];

        /*
         * receive the buffer of file
         */
        byte[] taskBuffer = cTask.getTask();
        String taskName = cTask.getTaskName();
        Map<String, byte[]> jars = cTask.getDependencies();
        String classToExecute = cTask.getClassToExecute();
        String methodToExecute = cTask.getMethodToExecute();


        //gathering files into array
        try {
            //write temp file for task
            new File("../temp_files/").mkdir();
            new File("../temp_files/" + cTask.getTaskID() + "/").mkdir();
            File file = new File("../temp_files/" + cTask.getTaskID() + "/" + taskName);
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
                file = new File("../temp_files/" + cTask.getTaskID() + "/" + fileName);
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
            //e.printStackTrace();
        }

        String ticket = "";

        //execute task

        JCL_result jclr;

        try {
            Object[] args = cTask.getArguments();
            /*System.err.println("class: " + cTask.getClassToExecute());
             System.err.println("method: " + cTask.getMethodToExecute());
             System.err.println(("Task: " + cTask.getTaskName() + "\n Executing: " + cTask.getClassToExecute()));*/
            HostViewerController.getInstance().displayStatus("Task: " + cTask.getTaskID());
            ticket = jcl.execute(cTask.getClassToExecute(), cTask.getMethodToExecute(), args);
            //System.err.println("Recebeu o ticket " + ticket);
        } catch (Exception e) {
            //e.printStackTrace();
        }

        //get result for task
        jclr = jcl.getResultBlocking(ticket);
        //System.err.println("Conseguiu resultado para o ticket " + ticket);
        cTask.setFinished();
        jcl.removeResult(ticket);

        return jclr;

    }// end of method executeTask
}
