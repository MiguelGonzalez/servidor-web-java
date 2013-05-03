/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import servidorwebiecisa.servidorWeb.IServidorWeb;
import servidorwebiecisa.servidorWeb.ServidorWeb;

public class ConfiguracionServidor {
    
    private static ConfiguracionServidor INSTANCE;
    
    private int numPoolSockets;
    private int keepAliveTimeout;
    private List<IServidorWeb> servidores;
    
    static {
        INSTANCE = null;
    }
    
    private ConfiguracionServidor() {
        servidores = new ArrayList<>();
        
        //Construimos el DOM con las preferencias
        DocumentBuilderFactory dbFactory;
        DocumentBuilder docBuilder;
        
        try
        {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();
            
            //Si se agrega al fichero y este existe tenemos que cargar primero el XML en memoria
            URL preferencesUrl = 
                    ConfiguracionServidor.class.getResource("configServer.xml");
            
            Document xmlDoc  = docBuilder.parse(preferencesUrl.openStream());

            Element header = xmlDoc.getDocumentElement();
            
            Integer iNumPoolSockets = getElementNode(header, "numPoolSockets", 
                    new Integer(32), Integer.class);
            numPoolSockets = iNumPoolSockets.intValue();
            
            Integer ikeepAliveTimeout = getElementNode(header, "keepAliveTimeOut", 
                    new Integer(60), Integer.class);
            keepAliveTimeout = ikeepAliveTimeout.intValue();
            
            NodeList listElements = header.getElementsByTagName("servidores");
            if(listElements != null && listElements.getLength() == 1) {
                Node childServidor = listElements.item(0);
                
                if(childServidor != null) {
                    NodeList listServidores = childServidor.getChildNodes();
                    
                    
                    for(int i=0; i<listServidores.getLength(); i++) {
                        Node servidor = listServidores.item(i);
                        Map<String, String> valoresServidor = new HashMap<>();
                        if(servidor.getNodeType() == Node.ELEMENT_NODE) {
                            NodeList atributos = listServidores.item(i).getChildNodes();
                        
                            for(int j=0; j<atributos.getLength(); j++) {
                                Node atributo = atributos.item(j);

                                if(atributo.getNodeType() == Node.ELEMENT_NODE) {
                                    valoresServidor.put(atributo.getNodeName(),
                                            atributo.getTextContent());
                                }
                            }
                            System.out.println(valoresServidor.get("path"));
                            servidores.add(new ServidorWeb(valoresServidor));
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | DOMException ex) {
            ServidorWebIecisa.log.error("Error", ex);
        }
    }
    
    private <T> T getElementNode(Element element, String nodeName, T defaultValue, Class<T> resultClass) {
        T response = null;
        
        NodeList listElements = element.getElementsByTagName(nodeName);
        
        if(listElements != null && listElements.getLength() == 1) {
            Node childValue = listElements.item(0);

            if(childValue != null) {
                Node nodeValor = childValue.getFirstChild();

                if(nodeValor != null) {
                    String value = nodeValor.getNodeValue( );

                    if(value != null) {
                        if(Integer.class.equals(resultClass)) {
                            response = resultClass.cast(new Integer(value));
                        } else {
                            response = resultClass.cast(value);
                        }
                    }
                }
            }
        }
        
        if(response == null) {
            return defaultValue;
        } else {
            return response;
        }
    }
    
    public static ConfiguracionServidor getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfiguracionServidor();
        }

        return INSTANCE;
    }
    
    public int getNumPoolSockets() {
        return numPoolSockets;
    }
    
    public int getKeepAliveTimeout() {
        return keepAliveTimeout * 1000;
    }
    
    public List<IServidorWeb> getServidores() {
        return servidores;
    }
}
