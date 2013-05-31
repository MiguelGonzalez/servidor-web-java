package servidorweb.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorweb.servidorWeb.ServicioServidor;

/**
 *
 * @author Miguel González Gómez
 */
public class ServidorModel {
    private int port;
    private String path;
    private String pathRelativeServicio;
    private String classServicio;
    
    private ServicioServidor servicioServidor;
    
    private boolean corriendo;
    
    public ServidorModel(int port, String path,
            String pathRelativeServicio, String classServicio) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = pathRelativeServicio;
        this.classServicio = classServicio;
        
        corriendo = false;
        
        limpiarPathRelativoServicio();
        construirServicioServidor();
    }

    public ServidorModel(int port, String path) {
        this.port = port;
        this.path = path;
        
        corriendo = false;
    }
    
    public ServicioServidor getServicioServidor() {
        return servicioServidor;
    }
    
    private void limpiarPathRelativoServicio() {
        if(pathRelativeServicio.length() > 0 && pathRelativeServicio.startsWith("/")) {
            pathRelativeServicio = pathRelativeServicio.substring(1);
        }
        if(pathRelativeServicio.length() > 0 && pathRelativeServicio.endsWith("/")) {
            pathRelativeServicio = pathRelativeServicio.substring(0, 
                    pathRelativeServicio.length() - 1);
        }
    }
    
    private void construirServicioServidor() {
        if(hasServicio()) {
            servicioServidor = loadClassServicioWeb();
        }
    }
    
    private ServicioServidor loadClassServicioWeb() {
        ServicioServidor servidorWeb = null;
        try {
            Class<?> classServidor = Class.forName(classServicio);
            Class[] ctorArgs = new Class[1];
            ctorArgs[0] = getClass();
            Constructor<?> constructor = classServidor.getConstructor(ctorArgs);
            
            servidorWeb = (ServicioServidor) classServidor.cast(
                    constructor.newInstance(this));
        } catch (InstantiationException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ServidorModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return servidorWeb;
    }
    
    public boolean hasServicio() {
        return pathRelativeServicio != null &&
                classServicio != null &&
                !pathRelativeServicio.isEmpty() &&
                !classServicio.isEmpty();
    }
    
    public int getPort() {
        return port;
    }
    
    public String getPath() {
        return path;
    }
    
    public String getPathRelativeServicio() {
        return pathRelativeServicio;
    }
    
    public String getClassServicio() {
        return classServicio;
    }
    
    @Override
    public String toString() {
        return (corriendo) ?
                "Iniciado - " + path + ":" + port
                :
                "Parado - " + path + ":" + port;
    }
    
    public boolean isCorriendo() {
        return corriendo;
    }
    
    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }
    
    public void updateTo(int port, String path, String pathRelativoServicio, String classServicio) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = pathRelativoServicio;
        this.classServicio = classServicio;
        
        limpiarPathRelativoServicio();
        construirServicioServidor();
    }

    public void updateTo(int port, String path) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = "";
        this.classServicio = "";
                
        limpiarPathRelativoServicio();
        construirServicioServidor();
    }
}
