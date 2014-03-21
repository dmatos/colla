package colla.kernel.enumerations;

/**
 * Enum to be used in all messages that will be sent to server
 * to avoid mistakes when sending a request
 */
public enum ServerOps{
	LOGIN,
	SIGN_UP,
	REFRESH,
	SEARCH_GROUPS,	
	GET_USER,
	CREATE_GROUP,
	REFRESH_GROUPS,
	GET_GROUPS,
	UPDATE_USER,
        UPDATE_HOST,
        UPDATE_WEIGHTED_HOST,
        DISCONNECT, 
	TEST_FOR_VALID_IP,
	TRANSMIT_MESSAGE,
        TRANSMIT_TASK,
        TRANSMIT_TASK_RESULT,
        TRANSMIT_START_NOTIFICATION,
	PING,
	PONG,
	MAP_THE_CONNECTION,
	UPDATE_CONTACTS_IN_GROUPS,
        JOIN_A_GROUP,
        CONFIRM_ADD_GROUP,
        UPDATE_GROUPS,
        HOST_LOGIN, 
        HOST_REGISTER, 
        GET_HOSTS,
        GET_AVAILABLE_HOSTS,
        GET_LIST_OF_ONLINE_HOSTS,
        HOST_DISCONNECT,
        GET_STORED_RESULTS,
        ACK;
}
