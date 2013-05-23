/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.domain;

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
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.persistence.ConfiguracionLoad;
import servidorwebiecisa.persistence.ConfiguracionSave;

public class ConfiguracionModel {
    
    private static ConfiguracionModel INSTANCE;
    
    private List<ConfiguracionModelListener> listeners;
    
    private int numPoolSockets;
    private int keepAliveTimeout;
    
    private List<ServidorModel> servidores;
    //private List<IServidorWeb> servidores;
    
    static {
        INSTANCE = null;
    }

    private ConfiguracionModel() {
        listeners = new ArrayList<>();
        servidores = new ArrayList<>();
        
        loadConfiguracion();
    }
    
    private void loadConfiguracion() {
        ConfiguracionLoad configuracionLoad = new
                ConfiguracionLoad(this);
        configuracionLoad.startLoading();
    }
   
    public void saveConfiguracion() {
        ConfiguracionSave configuracionSave = new ConfiguracionSave(this);
        configuracionSave.saveModel();
    }
    
    public static ConfiguracionModel getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfiguracionModel();
        }

        return INSTANCE;
    }
    
    public void updateNumPoolSockets(int numPoolSockets) {
        this.numPoolSockets = numPoolSockets;
    }
    
    public void updateKeelAliveTimeout(int keepAliveTimeout) {
        this.keepAliveTimeout = keepAliveTimeout;
    }
    
    public int getNumPoolSockets() {
        return numPoolSockets;
    }
    
    public int getKeepAliveTimeout() {
        return keepAliveTimeout * 1000;
    }
    
    public List<ServidorModel> getServidores() {
        return servidores;
    }
    
    public void addServidor(ServidorModel servidorModel) {
        servidores.add(servidorModel);
        
        List<ConfiguracionModelListener> copyListeners = new
                ArrayList<>(listeners);
        
        for(ConfiguracionModelListener listener : copyListeners) {
            listener.addedServidor(servidorModel);
        }
    }
    
    public void addServidores(List<ServidorModel> servidores) {
        for(ServidorModel servidorModel : servidores) {
            addServidor(servidorModel);
        }
    }
    
    public void removeServidor(ServidorModel servidorModel) {
        servidores.remove(servidorModel);
        
        List<ConfiguracionModelListener> copyListeners = new
                ArrayList<>(listeners);
        
        for(ConfiguracionModelListener listener : copyListeners) {
            listener.removedServidor(servidorModel);
        }
    }
    
    public void addListener(ConfiguracionModelListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(ConfiguracionModelListener listener) {
        listeners.remove(listener);
    }
}
