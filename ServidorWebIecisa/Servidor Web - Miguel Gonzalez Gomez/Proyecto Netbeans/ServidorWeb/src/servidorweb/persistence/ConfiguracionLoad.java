package servidorweb.persistence;

import java.io.IOException;
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
import servidorweb.MainServidor;
import servidorweb.domain.ConfiguracionModel;
import servidorweb.domain.ServidorModel;

/**
 *
 * @author Miguel González Gómez
 */
public class ConfiguracionLoad {
    private int numPoolSockets;
    private int keepAliveTimeout;
    
    private List<ServidorModel> servidores;

    private ConfiguracionModel configuracionModel;
    
    public ConfiguracionLoad(ConfiguracionModel configuracionModel) {
        this.configuracionModel = configuracionModel;
    }
    
    public void startLoading() {
        constructModel();
        
        saveToRealModel();
    }
    
    private void constructModel() {
        servidores = new ArrayList<>();
        numPoolSockets = 32;
        keepAliveTimeout = 60;
        
        //Construimos el DOM con las preferencias
        DocumentBuilderFactory dbFactory;
        DocumentBuilder docBuilder;
        
        try
        {
            dbFactory = DocumentBuilderFactory.newInstance();
            docBuilder = dbFactory.newDocumentBuilder();
            
            Document xmlDoc  = docBuilder.parse(MainServidor.
                        propiedadesServidor.getProperty("nombreFicheroXML"));

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
                            
                            addServidorModel(valoresServidor);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | DOMException ex) {
            MainServidor.log.error("Error", ex);
        }
    }
    
    private void saveToRealModel() {
        configuracionModel.updateKeelAliveTimeout(keepAliveTimeout);
        configuracionModel.updateNumPoolSockets(numPoolSockets);
        configuracionModel.addServidores(servidores);
    }
    
    private void addServidorModel(Map<String, String> valoresServidor) {
        try {
            int port = Integer.parseInt(valoresServidor.get("port"));
            String path = valoresServidor.get("path");
            
            if(path == null || path.isEmpty()) {
                MainServidor.log.error("Error al cargar la configuración del servidor, path no válido.");
            } else {

                String pathRelativeServicio = valoresServidor.get("pathRelativeServicio");
                String classServicio = valoresServidor.get("classServicio");


                ServidorModel servidorModel = null;
                if(pathRelativeServicio != null && classServicio != null) {
                    servidorModel = new ServidorModel(port, path,
                            pathRelativeServicio, classServicio);
                } else {
                    servidorModel = new ServidorModel(port, path);
                }

                servidores.add(servidorModel);
            }
        } catch(NumberFormatException ex) {
            MainServidor.log.error("Error al cargar la configuración del servidor, puerto no válido.");
        }
    }
    
    private <T> T getElementNode(Element element, String nodeName,
            T defaultValue, Class<T> resultClass) {
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
}
