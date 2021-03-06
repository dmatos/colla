/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colla.kernel.util;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * XML Reader for configuration files
 *
 * @author dmatos
 */
public class ServerConfReader extends DefaultHandler {

    private String ipAddress;
    private Integer portNumber;
    private String secondaryIPAddress;
    private Integer secondaryPortNumber;
    private final String IPADDRESS = "IPAddress";
    private final String PORTNUMBER = "portNumber";

    /**
     * Constutor que inicializa os objetos necessários para fazer o parse
     * do arquivo xml
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void parse(String arquivo) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        parser.parse(arquivo, this);
    }

    /**
     * Indica que o parser achou o início do documento XML. Este evento não
     * lhe passa qualquer informação, apenas indica que o parser vai começar
     * a escanear o arquivo XML.
     */
    @Override
    public void startDocument() {
        //System.out.println("Starting to read XML");
    }

    /**
     * Indica que o parser achou e fim do documento XML.
     */
    @Override
    public void endDocument() {
        //System.out.println("Reading of XML has ended succesfully");
    }

    /**
     * Indica que o parser achou o início de uma tag (tag de abertura/início).
     * Este evento fornece o nome do elemento, o nome e valor dos atributos
     * deste elemento, e também pode fornecer as informações sobre o namespace.
     */
    @Override
    public void startElement(String uri, String localName, String tag, Attributes atributos) {
        if(tag.equals(IPADDRESS)){
            ipAddress = atributos.getValue("ip");
            secondaryIPAddress = atributos.getValue("secondaryIP");
        }
        if(tag.equals(PORTNUMBER)){
            portNumber = Integer.parseInt(atributos.getValue("port"));
            secondaryPortNumber = Integer.parseInt(atributos.getValue("secondaryPort"));
        }        
    }
    
    
    /**
     * 
     * @return secondary server IP address as read from the xml file
     */
    public String getSecondaryIPAddressFromXML(){
        return this.secondaryIPAddress;
    }
    
    /**
     * Returns the IP address found in the xml file
     */
    public String getIPfromXML(){
        return ipAddress;
    }    
    
    /**
     * Returns the port number found in the xml file
     */
    public Integer getPortNumberFromXML(){
        return portNumber;
    }
    
    /**
     * 
     * @return secondary server port number as read from the xml file
     */
    public Integer getSecondaryPortNumberFromXML(){
        return secondaryPortNumber;
    }
}
