package colla.kernel.enumerations;

/**
 * Enum para ser usado no servidor e no cliente para que não
 * haja confusão quanto ao que deve ser usado ao enviar
 * comandos para o servidor
 */
public enum HostOps{
    TASK_RECEIVE,
    TASK_EXECUTE,
    TASK_EXECUTE_DISTRIBUTED,
    TASK_CANCEL,
    DOWNLOAD_RESULT,
    RECEIVE_ONLINE_HOSTS,
    REGISTER_FILE,
    PING,
    ACK;
}
