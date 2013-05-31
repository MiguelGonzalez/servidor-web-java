/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.domain;

import java.util.ArrayList;
import java.util.List;
import servidorwebiecisa.persistence.ConfiguracionLoad;
import servidorwebiecisa.persistence.ConfiguracionSave;

public class ConfiguracionModel {
    
    private static ConfiguracionModel INSTANCE;
    
    private List<ConfiguracionModelListener> listeners;
    
    private int numPoolSockets;
    private int keepAliveTimeout;
    
    private List<ServidorModel> servidores;

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
    
    public synchronized void updateNumPoolSockets(int numPoolSockets) {
        this.numPoolSockets = numPoolSockets;
    }
    
    public synchronized void updateKeelAliveTimeout(int keepAliveTimeout) {
        this.keepAliveTimeout = keepAliveTimeout;
    }
    
    public int getNumPoolSockets() {
        return numPoolSockets;
    }
    
    public int getKeepAliveTimeoutInMillis() {
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
