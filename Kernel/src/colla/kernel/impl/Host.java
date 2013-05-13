package colla.kernel.impl;

import colla.kernel.api.CollAHost;
import colla.kernel.util.SystemProperties;
import colla.kernel.util.TimeAndDate;
import java.io.Serializable;
import java.util.HashMap;

public class Host implements Serializable, Comparable<CollAHost>, CollAHost {

    private static final long serialVersionUID = 1L;
    private String name;
    private String nameUser;
    private String ip;
    private int port;
    private boolean online;
    private TimeAndDate tempoConexao;
    private boolean validIP;
    private String country;
    private HashMap<String, String> activities;
    private String systemProperties;

    /**
     * Constructor without parameters
     */
    public Host() {
        this.name = null;
        this.nameUser = null;
        this.ip = null;
        this.port = -1;
        this.online = false;
        this.tempoConexao = new TimeAndDate();
        this.validIP = false;
        this.country = null;
        this.activities = new HashMap<String, String>();
        systemProperties = new SystemProperties().getSystemProperties();
    }

    /**
     * Constructor with a few arguments
     * @param name Name of the host
     * @param ip Ip of the host
     * @param port port of the host
     */
    public Host(String name, String ip, int port) {
        this.name = name;
        this.nameUser = null;
        this.ip = ip;
        this.port = port;
        this.online = false;
        this.tempoConexao = new TimeAndDate();
        this.validIP = false;
        this.country = null;
        this.activities = new HashMap<String, String>();
        systemProperties = new SystemProperties().getSystemProperties();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     * Implementa o método de comparação necessário para
     * se colocar objetos da classe dentro de um Set.
     * A ordem é dada pelo nome dos usuários.
     */
    @Override
    public int compareTo(CollAHost host) {

        if (this.name.equals(host.getName())) {
            return 0;
        } else if (this.getName().compareTo(host.getName()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String getSystemProperties() {
        return systemProperties;
    }

    @Override
    public void validateIP() {
        this.validIP = true;
    }

    /*
     * determina que o usuário não tem um IP
     * públic e deve estar atrás de um NAT
     */
    @Override
    public void invalidateIP() {
        this.validIP = false;
    }

    /*
     * retorna true se o usuário não está atrás de um NAT
     * e false caso contrário
     */
    @Override
    public boolean hasValidIP() {
        return this.validIP;
    }

    /*
     * Determina o nome do usuário
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    /*
     * Determina o ip do usuário
     */
    @Override
    public void setIp(String ip) {
        this.ip = ip;
    }

    /*
     * determina porta TCP da conexão
     */
    @Override
    public void setPort(int port) {
        this.port = port;
    }

    /*
     * determina que o usuário está online no momento
     */
    @Override
    public void setOnline() {
        this.online = true;
    }

    /*
     * determina que o usuário está offline no momento
     */
    @Override
    public void setOffline() {
        this.online = false;
    }

    /*
     * retorna true se usuário estiver conectado
     * e false caso contrário
     */
    @Override
    public boolean IsOnline() {
        return this.online;
    }

    /*
     * retorna nome utilizado pelo usuário
     */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getNameUser() {
        return this.nameUser;
    }

    /*
     * retorna ip local da máquina do usuário que pode ser
     * um ip válido na internet ou não.
     * Não confundir com ip utilizado na internet
     */
    @Override
    public String getIp() {
        return this.ip;
    }

    /*
     * retorna porta utilizada na conexão TCP
     */
    @Override
    public int getPort() {
        return this.port;
    }

    /*
     * marca data do inicio da conexão
     */
    @Override
    public void setInicioConexao() {
        this.tempoConexao.startTimer();
    }

    /*
     * retorna o tempo total de conexão
     */
    @Override
    public String getTempoTotalConexao() {
        return this.tempoConexao.getTotalTime();
    }

    /**
     * store in a String the actvities of the user on the server
     *
     * @param date
    param
     *                                                                                                     activity
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

    /*
     * determina país do usuário
     */
    @Override
    public void setCountry(String cntry) {
        this.country = cntry;
    }

    /*
     * retorna país do usuário
     */
    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String toString() {
        String s = "";
        s = s + "nameUser: " + this.getNameUser() + "\n";
        s = s + "name: " + this.getName() + "\n";
        s = s + "country: " + this.getCountry() + "\n";
        s = s + "ip: " + this.getIp() + "\n";
        s = s + "port: " + this.getPort() + "\n";
        s = s + "validIp: " + this.validIP + "\n";
        return s;
    }   
}