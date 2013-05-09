/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import static servidorwebiecisa.ServidorWebIecisa.log;
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
                            
                            addServidor(valoresServidor);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | DOMException ex) {
            ServidorWebIecisa.log.error("Error", ex);
        }
    }
    
    private void addServidor(Map<String, String> valoresServidor) {
        
        String jarServicio = valoresServidor.get("jarServicio");
        String classServicio = valoresServidor.get("classServicio");
        
        if(jarServicio != null && classServicio != null) {
            try {
               // URLClassLoader classLoader = ((URLClassLoader) ClassLoader.
                //        getSystemClassLoader());
                
                //Method methodAdd = URLClassLoader.class.getDeclaredMethod("addURL",
                //        new Class[]{URL.class});

                
                System.out.println(jarServicio);
                File fileJar = new File(jarServicio);
                
                URL urlJar = new URL("file://" + fileJar.toURI().getPath());
                
                System.out.println("Nombre de la clase: " + classServicio);
                URLClassLoader child = new URLClassLoader (new URL[]{urlJar}, this.getClass().getClassLoader());
                Class classToLoad = Class.forName(classServicio, true, child);
                //Method method = classToLoad.getDeclaredMethod ("myMethod");
                IServidorWeb servidorWeb = IServidorWeb.class.cast(classToLoad.newInstance());
                //Object result = method.invoke (instance);
                
                
                
                

                //methodAdd.invoke(classLoader, new Object[] { url} );
                //methodAdd.setAccessible(true);
                /*
                URLClassLoader loader = new URLClassLoader(new URL[]{url}, null);
                
                
                
                
                
                IServidorWeb servidorWeb = IServidorWeb.class.cast(Class.forName(classServicio, true, loader));*/
                
                servidores.add(servidorWeb);
                
                System.out.println("Servidor cargado dinámicamente");
            
            } catch (SecurityException ex) {
                log.error(ex);
            
            } catch (IllegalArgumentException ex) {
                log.error(ex);
            
            } catch (ClassNotFoundException ex) {
                log.error(ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ConfiguracionServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConfiguracionServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ConfiguracionServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ConfiguracionServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            servidores.add(new ServidorWeb(valoresServidor));
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
