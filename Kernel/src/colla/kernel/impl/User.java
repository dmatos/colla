package colla.kernel.impl;

import colla.kernel.api.CollAGroup;
import colla.kernel.api.CollAHost;
import colla.kernel.api.CollAJob;
import colla.kernel.api.CollASession;
import colla.kernel.api.CollAUser;
import colla.kernel.util.TimeAndDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class User implements Serializable, Comparable<CollAUser>, CollAUser {

    private static final long serialVersionUID = 1L;
    private String name;
    private String ip;
    private int port;
    private HashMap<String, CollAHost> hosts;
    private HashMap<String, CollAGroup > groupsMap;
    private boolean online;
    private TimeAndDate time;
    private boolean validIP;
    private String email;
    private String country;
    private HashMap<String, String> activities;
    private HashMap<String, CollASession> sessions;
    private List<String> orderedSessions;
    private String company;


    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * Implements the comparition method that is necessary
     * to put objects of the class in a Set.
     * The order is given by the user names.
     */
    @Override
    public int compareTo(CollAUser user) {
        if (this.name == null)
            return -1;       
        if (this.name.equals(user.getName())) {
            return 0;
        } else if (this.getName().compareTo(user.getName()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    /*
     * construtor sem parametros
     */
    public User() {
        this.initializeUser();
    }

    /**
     * constructor with parameters
     * @param name username
     * @param ip IP address of the machine
     * @param port random port in which the program will be listening
     */
    public User(String name, String ip, int port) {
        this.initializeUser();
        this.name = name;
        this.ip = ip;
        this.port = port;
    }
    
    private final void initializeUser(){
        this.name = "";
        this.ip = "127.0.0.1";
        this.port = 0;
        this.hosts = new HashMap<String, CollAHost>();
        this.groupsMap = new HashMap<String, CollAGroup >();
        this.online = false;
        this.time = new TimeAndDate();
        this.validIP = false;
        this.email = "";
        this.country = "";
        this.company = "";
        this.activities = new HashMap<String, String>();
        this.sessions = new HashMap<String, CollASession>();
        this.orderedSessions = new ArrayList<String>();
    }

    @Override
    public Set<CollAHost> getHosts() {
        TreeSet<CollAHost> hostsSet = new TreeSet<CollAHost>();
        for(String hostName: this.hosts.keySet()){
            hostsSet.add(this.hosts.get(hostName));
        }
        return hostsSet;
    }

    @Override
    public void addHost(CollAHost host) {
        hosts.put(host.getName(), host);
    }
    
    @Override
    public CollAHost getHost(String hostName){
        return this.hosts.get(hostName);
    }

    /**
     * determines that the user have a public IP accessible over the internet
     */
    @Override
    public void validateIP() {
        this.validIP = true;
    }

    /**
     * determines that the machine doesn't have a public IP accessible over the internet
     * and must be behind a NAT
     */
    @Override
    public void invalidateIP() {
        this.validIP = false;
    }

    /**
     *
     * @return if the machine has a public IP accessible over the internet
     */
    @Override
    public boolean hasValidIP() {
        return this.validIP;
    }

    /**
     * sets username
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets machine IP address
     * @param ip IP address of the machine
     */
    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * sets the listening port of the machine
     * @param port the listening port
     */
    @Override
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * sets that the user is online
     */
    @Override
    public void setOnline() {
        this.online = true;
    }

    /**
     * sets that the user is offline
     */
    @Override
    public void setOffline() {
        this.online = false;
    }

    /**
     *
     * @return if user is online
     */
    @Override
    public boolean isOnline() {
        return this.online;
    }

    /**
     *
     * @return user name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return IP as seen by the connection on the side of the machine
     * where the program is running
     */
    @Override
    public String getIp() {
        return this.ip;
    }

    /**
     *
     * @return the listening port
     */
    @Override
    public int getPort() {
        return this.port;
    }

    /**
     * set the date and time when the connection with the server is estabilished
     */
    @Override
    public void setConnectionInitialized() {
        this.time.startTimer();
    }

    /**
     *
     * @return total time of connection with server
     */
    @Override
    public String getConnectionTotalTime() {
        return this.time.getTotalTime();
    }

    /**
     * adds a group for this user
     * @param group
     */
    @Override
    public void addGroup(String groupName, CollAGroup group) {
        this.groupsMap.put(groupName, group);
    }

    /**
     *
     * @return the groups of this user in a Map
     */
    @Override
    public HashMap<String, CollAGroup> getGroups() {
        return this.groupsMap;
    }

    /**
     * store in a String the actvities of the user on the server
     * @param date
     * @param activity
     */
    @Override
    public void recordActivities(String date, String activity) {
        String record = this.activities.get(date);
        if (record == null) {
            this.activities.put(date, activity + "\n");
        } else {
            record = record + activity + "\n";
            this.activities.put(date, record);
        }
    }

    /**
     *
     * @param map the map of activities that has a dates as key
     */
    @Override
    public void setActivities(HashMap<String, String> map) {
        this.activities = map;
    }

    /**
     *
     * @return a HashMap that has dates as keys
     */
    @Override
    public HashMap<String, String> getActivities() {
        return this.activities;
    }

    /**
     *
     * @param cntry country of the user
     */
    @Override
    public void setCountry(String cntry) {
        this.country = cntry;
    }

    /**
     *
     * @return a String containing the user country
     */
    @Override
    public String getCountry() {
        return this.country;
    }

    /**
     *
     * @param mail user e-mail
     */
    @Override
    public void setEmail(String mail) {
        this.email = mail;
    }

    /**
     *
     * @return user e-mail
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * 
     * @param ses a new session started by the user
     */
    @Override
    public void addSession(CollASession ses) {
        sessions.put(ses.getSessionDateAndTime(), ses);
        orderedSessions.add(ses.getSessionDateAndTime());
    }

    /**
     * 
     * @return an HashMap containing all the user sessions that
     * has date and time of session as keys
     */
    @Override
    public HashMap<String, CollASession> getSessions() {
        return sessions;
    }

    /**
     * 
     * @return last session started by user
     */
    @Override
    public Session getLastSession() {
        if(this.sessions.size() > 0)
            return (Session) sessions.get(this.orderedSessions.get(this.orderedSessions.size()-1));
        CollASession session = new Session();
        this.addSession(session);
        return (Session) sessions.get(this.orderedSessions.get(this.orderedSessions.size()-1));
    }


    /**
     * record a job for the present session
     * @param job 
     */
    @Override
    public void addJob(CollAJob job) {
        Session session = this.getLastSession();        
        session.addJob(job);
        this.sessions.put(session.getSessionDateAndTime(), session);
    }
    
    @Override 
    public List<String> getOrderedSessions(){
        return this.orderedSessions;
    }

    @Override
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String getCompany() {
       return this.company;
    }
}
