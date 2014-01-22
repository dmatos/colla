/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collatester;

import colla.appl.developer_viewer.DevViewerLogin;
import colla.appl.developer_viewer.DeveloperViewerController;
import colla.appl.developer_viewer.exceptions.DeveloperControllerInitializationException;
import colla.kernel.api.CollAUser;
import colla.kernel.impl.User;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmatos
 */
public class CollATester extends Thread {

    private String password;
    private CollAUser user;
    private boolean sendTasks;
    private boolean isDistributed;
    private TaskType taskType;
    private static CollATester collaTester = null;

    public enum TaskType {

        LOW_CPU_LOW_RAM("jar0.jar", "Jar0", "execute"),
        MEDIUM_CPU_LOW_RAM("filename", "class", "methdo"),
        HIGH_CPU_LOW_RAM("filename", "class", "methdo"),
        LOW_CPU_MEDIUM_RAM("filename", "class", "methdo"),
        MEDIUM_CPU_MEDIUM_RAM("filename", "class", "methdo"),
        HIGH_CPU_MEDIUM_RAM("filename", "class", "methdo"),
        LOW_CPU_HIGH_RAM("filename", "class", "methdo"),
        MEDIUM_CPU_HIGH_RAM("filename", "class", "methdo"),
        HIGH_CPU_HIGH_RAM("filename", "class", "methdo");
        private final File file;
        private final String classToExecute;
        private final String method;

        private TaskType(String jarName, String classToExecute, String method) {
            this.file = new File(jarName);
            this.classToExecute = classToExecute;
            this.method = method;
        }

        public File getTaskFile() {
            return this.file;
        }

        public String getClassToExecute() {
            return this.classToExecute;
        }

        public String getMethod() {
            return this.method;
        }
    }

    /**
     *
     * @param userName an unique username
     */
    private CollATester(String userName, String password) {
        this.password = password;
        this.user = new User();
        this.user.setName(userName);
        this.user.setCountry("Brazil");
        this.user.setEmail("test@mail.com");
        this.user.setCompany("TEST");
        this.sendTasks = false;
        this.taskType = TaskType.LOW_CPU_LOW_RAM;
    }

    public static CollATester getCollATester() {
        return collaTester;
    }

    public static CollATester createCollATester(String userName, String password) {
        if (collaTester != null) {
            collaTester.cancelTaskSending();
        }
        collaTester = new CollATester(userName, password);
        return collaTester;
    }

    public void setDistributed(boolean sendDistributed) {
        this.isDistributed = sendDistributed;
    }

    public DeveloperViewerController registerClient() throws Exception {
        DeveloperViewerController devViewer = null;
        DevViewerLogin devLogin = new DevViewerLogin();
        devLogin.setUser(user);

        devLogin.signUpForServer(password);

        try {
            devViewer = devLogin.logInServer(this.user.getName(), this.password);
            Thread.sleep(2000);
            devViewer.createGroup(this.user.getName());
        } catch (Exception ex) {
        }

        return devViewer;
    }

    public void setTaskType(TaskType type) {
        this.taskType = type;
    }

    public void cancelTaskSending() {        
        this.sendTasks = false;
        try {
            DeveloperViewerController devViewer = 
                    DeveloperViewerController.getInstance();
            devViewer.shutdown();
        } catch (DeveloperControllerInitializationException ex) {
            Logger.getLogger(CollATester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * @todo escrever observer para o resultado das tasks e também assinalar
     * qual task foi enviada.
     * 
     */ 
    @Override
    public void run() {
        this.sendTasks = true;
        while (this.sendTasks) {
            File file = this.taskType.getTaskFile();
            String method = this.taskType.getMethod();
            String classToExecute = this.taskType.getClassToExecute();
            try {
                DeveloperViewerController devViewer =
                        DeveloperViewerController.getInstance();
                System.out.println("sending task");
                devViewer.getAvailableHostsOnServer(file,
                        new ArrayList<File>(), new ArrayList<File>(),
                        classToExecute, method, this.user.getName(),
                        null, this.isDistributed);
                System.out.println("task sent");
                Thread.sleep(5000);
            } catch (Exception ex) {
                this.sendTasks = false;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //arg0 username
        //arg1 type of task
        //arg2 execution time in minutes (0 for undefined)

        if (args.length < 2) {
            System.err.println("Insufficient number or arguments.");
        }

        CollATester tester = CollATester.createCollATester(args[0], "123456");

        int typeOfTask = new Integer(args[1]);
        int executionTime = new Integer(args[2]);

        switch (typeOfTask) {
            case 1:
                tester.setTaskType(TaskType.LOW_CPU_LOW_RAM);
                break;
            case 2:
                tester.setTaskType(TaskType.LOW_CPU_MEDIUM_RAM);
                break;
            case 3:
                tester.setTaskType(TaskType.LOW_CPU_HIGH_RAM);
                break;
            case 4:
                tester.setTaskType(TaskType.MEDIUM_CPU_LOW_RAM);
                break;
            case 5:
                tester.setTaskType(TaskType.MEDIUM_CPU_MEDIUM_RAM);
                break;
            case 6:
                tester.setTaskType(TaskType.MEDIUM_CPU_HIGH_RAM);
                break;
            case 7:
                tester.setTaskType(TaskType.HIGH_CPU_LOW_RAM);
                break;
            case 8:
                tester.setTaskType(TaskType.HIGH_CPU_MEDIUM_RAM);
                break;
            case 9:
                tester.setTaskType(TaskType.HIGH_CPU_HIGH_RAM);
                break;
        }


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                CollATester temp = CollATester.getCollATester();
                if (temp != null) {
                    temp.cancelTaskSending();
                }
            }
        });

        try {
            if (tester.registerClient() != null) {
                tester.start();
            } else {
                System.err.println("Unable to register a new client.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            if (executionTime > 0) {
                Thread.sleep(executionTime * 60000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(CollATester.class.getName()).log(Level.SEVERE, null, ex);
        }
        tester.cancelTaskSending();
        try {
            tester.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(CollATester.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
}
