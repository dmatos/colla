/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Diogo Matos <dmatos88 at gmail.com>
 */
public class NetworkDevices {

    /**
     * 
     * @return an array with all IPv4 IP addresses found through network devices
     * @throws SocketException 
     */
    public List<String> getIPs() throws SocketException {

        List<String> ipList = new LinkedList<String>();

        /*
         * loop to search IP address of the machine through network interfaces
         */
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

        for (NetworkInterface netint : Collections.list(nets)) {
            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
            for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                //System.out.println("InetAddress : " + inetAddress);
                if (!inetAddress.isLoopbackAddress()
                        && !inetAddress.toString().substring(1).startsWith("127")
                        && inetAddress.toString().substring(1).contains(".")) {
                    ipList.add(inetAddress.toString().substring(1));
                }
            }
        }
        
        return ipList;
    }
}
