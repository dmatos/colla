package colla.kernel.enumerations;

/**
 * enum to standardize the operations with Client 
 */
public enum ClientOps {
	MESSAGE,
	DO_REFRESH,
	PING,
	PONG,
	RECEIVE_REFRESH,
	SEARCH_RESULTS,
	RECEIVE_A_GROUP,
        LIST_GROUPS,
	RECEIVE_GROUPS, 
        GROUP_CREATION_CONFIRMATION, 
        RECEIVE_HOST,
        SEND_TASK,
        RECEIVE_AVAILABLE_HOSTS,
        RECEIVE_TASK_RESULT,
        TASK_START_NOTIFICATION,
        RECEIVE_STORED_RESULTS,
        ACK;
}
