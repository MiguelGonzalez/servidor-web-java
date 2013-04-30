/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa;

public class ConfiguracionServidor {
    
    private static ConfiguracionServidor INSTANCE;
    
    static {
        INSTANCE = null;
    }
    
    private ConfiguracionServidor() {
        
    }
    
    public static ConfiguracionServidor getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ConfiguracionServidor();
        }
        
        return INSTANCE;
    }
    
    public int getNumPoolSockets() {
        return 32;
    }
    
    public int getKeepAliveTimeout() {
        return 60 * 1000;
    }
    
    public String getPathIni() {
        return "F:\\Documents and Settings\\Administrator\\Desktop\\PaginaWeb\\";
    }
}
