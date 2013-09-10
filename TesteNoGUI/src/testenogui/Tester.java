/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testenogui;

import colla.appl.developer_viewer.*;
import colla.kernel.api.CollATask;
import colla.kernel.api.CollAUser;
import colla.kernel.util.Debugger.DebugInfo;
import colla.kernel.util.SAXReader;
import java.io.*;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author bruno
 */
public class Tester extends Observable implements Observer, Runnable {

    //private CollAUser user;
    private String userName;
    private Integer userPort;
    private String userPassword;
    private String serverIP;
    private Integer serverPort;
    private String machineIP;
    //private DevMicroServer microServer;
    private DeveloperViewer developerViewer;
    private DevViewerLogin devLogin;
    private DebugInfo debugInfo;
    private String userGroup; //Nome do grupo que irá receber as tasks de teste
    private File[] taskFiles; //arquivos de testes;
    private int taskFilesRR; //pra fazer Round-Robin nas tarefas;
    private File anex2; //arquivo de anexo para a task2.
    private HashMap<Long, TesterTask> tasksSended; //armazena informações sobre as tasks que foram enviadas para execução.
    private long sleepingTime; //Tempo de espera entre uma tarefa e outra
    private File logFileStarted; //Arquivo de log de tasks enviadas
    private File logFileFinished; //Arquivo de log de tasks finalizadas
    public boolean debug; // variável booleana para fazer com que o programa imprima mensagens para debug
    private long lifeTime; //variável que determina a duração da thread   

    public Tester(int id, boolean debug, long lifeTime) {
        this.userPort = new Integer(6060 + id);
        this.userName = "tester" + id;
        this.userPassword = this.userName;
        this.userGroup = "Teste" + id;
        this.debugInfo = new DebugInfo();
        this.debugInfo.setDebuggedName(this.userName);
        if (!readServerConfigurations()) {
            debug("Não foi possível ler o arquivo server_conf.xml");
        }
        try {
            this.devLogin = new DevViewerLogin();
            //Define uso de GUI como falso
            this.devLogin.useGUI(false);
            //adiciona essa classe como observer do devLogin
            this.devLogin.addObserver(this);
            //adiciona esta classe como observer do DeveloperViewer para poder pegar os resultados das tasks
            this.devLogin.addObserverToDeveloperViewer(this);
        } catch (ConfigException cnf) {
            debug(cnf);
        }
        try {
            this.taskFiles = new File[3];
            this.taskFiles[0] = new File("jar0.jar");
            this.taskFiles[1] = new File("jar1.jar");
            this.taskFiles[2] = new File("jar2.jar");
            this.taskFilesRR = 1;
            this.anex2 = new File("anex2.jar");
            this.tasksSended = new HashMap<Long, TesterTask>();
        } catch (Exception e) {
            debug("Não foi possível acessar os jars de testes", e);
        }
        this.sleepingTime = 60000;
        this.logFileStarted = new File("logS.csv");
        this.logFileFinished = new File("logF.csv");
        this.debug = debug;
        this.lifeTime = lifeTime;
    }

    @Override
    public void run() {
        //Cadastrar usuário
        this.signUpServer();
        debug("cadastrado");

        //Logar no servidor
        try {
            //O login retorna um DeveloperViewer
            this.developerViewer = this.devLogin.logInServer(this.userName, this.userPassword);
            debug("logado");
        } catch (Exception ex) {
            debug("nao pode logar no servidor", ex);
        }// fim do catch 
        try {
            debug("esperando 10s");
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            debug("Insonia", ex);
            System.out.println("Insonia");
        }//fim do catch

        //Cadastra grupo Teste+Id
        this.developerViewer.createGroup(this.userGroup);
        debug(this.userGroup + " created");

        //Envia tarefas para execução
        long lived = 0;
        Scanner s = new Scanner(System.in);// Pra debug
        // pra sempre
//        while (true) {
        // 10 vezes
        for(int i = 0; i < 10; i ++){
            //Espera algum tempo até enviar a próxima tarefa
            try {
                long st = (long) (Math.random() % sleepingTime + 1000);
                lived += st;
//                debug("sleeping time = " + st);
                // Pro tester não dormir somente um segundo eu mudo a linha abaixo para 1000
                // no caso eu vou ter um tester mandando tarefas por segundo!
                debug("espera 1.5 s pra mandar a próxima tarefa!");
                Thread.sleep(1500);
                //Leio qualquer coisa do teclado antes de enviar a próxima tarefa!
//                debug("esperando para enviar próxima tarefa...");
//                s.next();
                 /* Envia tarefa para execução. Pede o host e quando o host chegar o 
                 * microserver se encarrega de enviar a tarefa.
                 */
                System.err.println("sending task");
                this.sendTask();
//                if (lifeTime < lived) {
//                    Thread.sleep(300000);
//                    this.developerViewer.shutdown();
//                    break;
//                }
            } catch (Exception ex) {
                debug("Problema com o Thread.sleep()", ex);
            }

        }//fim do while
    }// fim do método run

    public void signUpServer() {
        this.devLogin.getUser().setName(this.userName);
        this.devLogin.getUser().setCompany("collaTester");
        this.devLogin.getUser().setCountry(" Brasil");
        this.devLogin.getUser().setEmail(this.userName + "@collatester.com");
        try {
            if (this.devLogin.signUpForServer(this.userPassword)) {
                this.debugInfo.clear();
                this.debugInfo.setInfo("- cadastrou no servidor");
                this.notifyObservers(this.debugInfo);
            } else {
                this.debugInfo.clear();
                this.debugInfo.setInfo("- cadastro nao pode ser realizado");
                this.notifyObservers(this.debugInfo);
            }
        } catch (Exception ex) {
            this.debugInfo.clear();
            this.debugInfo.setException(ex);
            this.debugInfo.setInfo("ex! Problema com o socket do cadastro no servidor.");
            this.notifyObservers(this.debugInfo);
        }


    }

    @Override
    public void notifyObservers(Object aspect) {
        this.setChanged();
        super.notifyObservers(aspect);
    }

    /**
     * Reads IP address and port number from a xml file to connect with the
     * server
     *
     * @return true if it was succesfully read, false otherwise
     */
    private boolean readServerConfigurations() {
        SAXReader reader = new SAXReader();
        try {
            reader.parse("server_conf.xml");
            this.serverIP = reader.getIPfromXML();
            this.serverPort = reader.getPortNumberFromXML();
        } catch (IOException io) {
            return false;
        } catch (ParserConfigurationException pce) {
            return false;
        } catch (SAXException sax) {
            return false;
        }
        return true;
    }

    /**
     * Solicita um host pro servidor.
     */
    public void sendTask() {
        ArrayList<File> dependencies = new ArrayList<File>();
        ArrayList<File> args = new ArrayList<File>();
        if (taskFilesRR == 2) {
            dependencies.add(anex2);
        }
        this.developerViewer.getAvailableHostsOnServer(
                this.taskFiles[taskFilesRR], dependencies, args,
                "Jar" + taskFilesRR, "execute", this.userGroup);//fim da chamada do método getAvailableHostsOnServer
        debug("get available hosts on server");

        //TODO Round-Robin
//        taskFilesRR++;
//        if (taskFilesRR > 2) {
//            taskFilesRR = 0;
//        }
    }

    /**
     * Seta o tempo inicial da tarefa e a insere em tasksSended.
     *
     * @param taskID Id da task que inicia
     * @param started Tempo em que a task inicia
     */
    public void taskStart(Long taskID, long started) {
        TesterTask tt = new TesterTask(taskID, started);
        tasksSended.put(taskID, tt);
    }

    /**
     * Seta o tempo final da tarefa em tasksSended.
     *
     * @param taskID Id da task que finalizou.
     * @param finished Tempo em que a task finalizou.
     */
    public void taskFinished(Long taskID, Long finished) {
        TesterTask tt = tasksSended.get(taskID);
        tt.finished(finished);
        tasksSended.put(taskID, tt);
    }

    public void addLogTaskStarted(CollATask result) {
        try {
            Long taskID = result.getTaskID();
            TesterTask tt = tasksSended.get(taskID);
            FileWriter out = new FileWriter(logFileStarted, true);
            PrintWriter printLog = new PrintWriter(out);
            printLog.println(taskID + " " + result.getOwner() + " " + result.getTaskName() + " "
                    + tt.getTime() + " " + result.getResult());
            printLog.flush();
            printLog.close();
            out.close();
            this.debugInfo.clear();
            this.debugInfo.setInfo(taskID + " " + result.getOwner() + " " + result.getTaskName() + " "
                    + tt.getTime() + " " + result.getResult());
            this.notifyObservers(this.debugInfo);
        } catch (IOException ex) {
            this.debugInfo.clear();
            this.debugInfo.setInfo("Problema com addLog()");
            this.debugInfo.setException(ex);
            this.notifyObservers(this.debugInfo);
        }
    }

    public void addLogTaskFinished(CollATask result) {
        try {
            Long taskID = result.getTaskID();
            TesterTask tt = tasksSended.get(taskID);
            FileWriter out = new FileWriter(logFileFinished, true);
            PrintWriter printLog = new PrintWriter(out);
            printLog.println(taskID + " " + result.getOwner() + " " + result.getTaskName() + " "
                    + tt.getTime() + " " + result.getResult());
            printLog.flush();
            printLog.close();
            out.close();
            this.debugInfo.clear();
            this.debugInfo.setInfo(taskID + " " + result.getOwner() + " " + result.getTaskName() + " "
                    + tt.getTime() + " " + result.getResult());
            this.notifyObservers(this.debugInfo);
        } catch (IOException ex) {
            this.debugInfo.clear();
            this.debugInfo.setInfo("Problema com addLog()");
            this.debugInfo.setException(ex);
            this.notifyObservers(this.debugInfo);
        }
    }

    public CollAUser getUser() {
        if (this.developerViewer != null) {
            return this.developerViewer.getUser();
        }
        if (this.devLogin != null) {
            return this.devLogin.getUser();
        }
        return null;
    }

    @Override
    public void update( Observable subject, Object interest ){
        if( interest instanceof CollATask ){
            CollATask task = ( CollATask ) interest;
            //se a tarefa ainda não foi concluida, não possui resultado.
            if( !task.isFinished() ){
                Long taskID = task.getTaskID();
                Long started = System.nanoTime();
                TesterTask tt = new TesterTask( taskID, started );
                this.tasksSended.put( taskID, tt );
                this.addLogTaskStarted( task );
            }
            //tarefa foi conluida, possui resultado.
            else{
                String receiver = this.userName;
                Long taskID = task.getTaskID();
                Long finished = System.nanoTime();
                // User pode receber resultados de outros membros do seu grupo. Portanto preciso checar se a task é dele mesmo.
                if( receiver.equals( task.getOwner() ) ){
                    this.taskFinished( taskID, finished );
                    this.addLogTaskFinished( task );
                }
            }
        }else{
            //Se o interest não for dele, passa para os seus observadores
            this.notifyObservers( interest );
        }
    }//fim do método update

    private void debug(String info, Exception ex) {
        this.debugInfo.clear();
        this.debugInfo.setInfo(info);
        this.debugInfo.setException(ex);
        this.notifyObservers(this.debugInfo);
        System.out.println(info);
        ex.printStackTrace();
    }

    private void debug(String info) {
        this.debugInfo.clear();
        this.debugInfo.setInfo(info);
        this.notifyObservers(this.debugInfo);
        System.out.println(info);
    }

    private void debug(Exception ex) {
        this.debugInfo.clear();
        this.debugInfo.setException(ex);
        this.notifyObservers(this.debugInfo);
        ex.printStackTrace();
    }
}
