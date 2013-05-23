/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.domain;

/**
 *
 * @author paracaidista
 */
public interface ConfiguracionModelListener {
    public void addedServidor(ServidorModel servidorModel);
    public void removedServidor(ServidorModel servidorModel);
}
