/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.enumerations;

/**
 *
 * @author dmatos
 */
public enum ActivityID {   
    
    SIGNUP("01","Sign up to server"),
    LOGIN("02","Log in on server"),
    DISCONNECT("03","Disconnect from server"),
    START_CHAT("04","Chat with: "),
    TIMEOUT("05","Connection timeout"),
    CREATE_GROUP("06","creation of group: "),
    JOIN_GROUP("07","joined to the group: "),
    REFRESH_REQUEST("08","request for refresh");
    
    private final String activityID;
    private final String activityText;
    
    ActivityID(String id, String activity){
        activityID = id;
        activityText = activity;
    }   
    
    /**
     * 
     * @return an int for the ID of the activity
     */
    public int getID(){
        return Integer.parseInt(activityID);
    }
    
    /**
     * 
     * @return a String that represents the ID textual definition
     */
    public String getActivity(){     
       return activityText;
    }    
    
}
