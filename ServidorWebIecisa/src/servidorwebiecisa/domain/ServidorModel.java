/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.domain;

/**
 *
 * @author paracaidista
 */
public class ServidorModel {
    private int port;
    private String path;
    private String pathRelativeServicio;
    private String classServicio;
    
    private boolean iniciado;
    
    private boolean hasServicio;

    public ServidorModel(int port, String path,
            String pathRelativeServicio, String classServicio) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = pathRelativeServicio;
        this.classServicio = classServicio;
        
        iniciado = false;
    }

    public ServidorModel(int port, String path) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = "";
        this.classServicio = "";
        this.hasServicio = false;
        
        iniciado = false;
    }
    
    public boolean isServicio() {
        return hasServicio;
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
        return (iniciado)? "Iniciado - " : "Parado - " + path + ":" + port;
    }
    
    public boolean isIniciado() {
        return iniciado;
    }
    
    public void setEstado(boolean iniciado) {
        this.iniciado = iniciado;
    }

    public void updateTo(int port, String path, String pathRelativoServicio, String classServicio) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = pathRelativoServicio;
        this.classServicio = classServicio;
    }

    public void updateTo(int port, String path) {
        this.port = port;
        this.path = path;
        this.pathRelativeServicio = "";
        this.classServicio = "";
        
        hasServicio = false;
    }
}
