package colla.kernel.enumerations;

/**
 * Enum para ser usado no servidor e no cliente para que não
 * haja confusão quanto ao que deve ser usado ao enviar
 * comandos para o servidor
 */
public enum HostOps{
    TASK_RECEIVE,
    TASK_EXECUTE,
    DOWNLOAD_RESULT,
    PING,
    ACK;
}
