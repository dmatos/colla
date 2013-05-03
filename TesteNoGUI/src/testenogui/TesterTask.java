/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testenogui;

/**
 * Classe que salva o id da task e os tempos iniciais e finais da tarefa.
 * @author bruno
 */
public class TesterTask{
    private long taskId;//Id da task
    private long started; //Tempo de inicio da task
    private long finished; //Tempo de fim da task    
    private long time; //Tempo de duração da task

    /**
     * Inicia uma nova tarefa.
     * @param taskId ID da tarefa a ser iniciada.
     * @param started Tempo de início da tarefa.
     */
    public TesterTask( long taskId, long started ){
        this.taskId = taskId;
        this.started = started;
        this.finished = 0;
        this.time = 0;
    }

    public long getTime(){
        return time;
    }

    public long getFinished(){
        return finished;
    }

    public long getStarted(){
        return started;
    }

    public long getTaskId(){
        return taskId;
    }

    public void finished( long finished ){
        this.finished = finished;
        this.time = this.finished - this.started;
    }        
}
