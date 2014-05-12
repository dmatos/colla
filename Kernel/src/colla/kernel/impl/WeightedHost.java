/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.impl;

import colla.kernel.api.CollAHost;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author dmatos
 */
public class WeightedHost implements Comparable<WeightedHost>, Serializable {

    private Long weight;
    private Long increment;
    private String owner;
    private String hostName;

    public WeightedHost(CollAHost host){
        this.weight = host.getWeight();
        this.increment = 0L;
        this.owner = host.getNameUser();
        this.hostName = host.getName();
    }
    
    @Override
    public int compareTo(WeightedHost wHost) {
        if(this.owner.equals(wHost.owner) && this.hostName.equals(wHost.hostName))
            return 0;
        Long temp = this.weight + increment - wHost.weight - wHost.increment;
        int result = temp.intValue();        
        return result;        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.weight);
        hash = 97 * hash + Objects.hashCode(this.increment);
        hash = 97 * hash + Objects.hashCode(this.owner);
        hash = 97 * hash + Objects.hashCode(this.hostName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WeightedHost other = (WeightedHost) obj;
        if (!Objects.equals(this.owner, other.owner)) {
            return false;
        }
        if (!Objects.equals(this.hostName, other.hostName)) {
            return false;
        }
        return true;
    }
    
    public void setIncrement(Long increment){
        this.increment = increment;
    }
    
    public void setWeight(Long weight){
        /*
         * Once a weight is set the increment becomes unnecessary.
         */
        this.weight = weight;
        this.increment = 0L;
    }
    
    public String getHostOwner(){
        return this.owner;
    }
    
    public String getHostName(){
        return this.hostName;
    }
    
    /*public static void main(String args[]){
        
        System.out.println("testing");
        
        PriorityQueue<WeightedHost> q = new PriorityQueue<>();
        
        CollAHost h1 = new Host();
        h1.setName("h1");
        h1.setNameUser("h1owner");
        
        CollAHost h2 = new Host();
        h1.setName("h2");
        h1.setNameUser("h2owner");
        
        WeightedHost w1 = new WeightedHost(h1);
        
        w1.setWeight(Long.MIN_VALUE);
        
        q.add(w1);
         q.add(w1);
        
        w1 = new WeightedHost(h1);
        
        w1.setWeight(Long.MIN_VALUE);           
        
             
        
        System.out.println("Offered itens: "+q.size());        
        
        q.clear();        
    }
    // */
}
