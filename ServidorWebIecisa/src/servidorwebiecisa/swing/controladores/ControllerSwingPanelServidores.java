/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.controladores;

import javax.swing.JPanel;
import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.swing.vistas.JPanelServidores;

/**
 *
 * @author paracaidista
 */
public class ControllerSwingPanelServidores extends AbstractController {
    
    private JPanelServidores panelServidores;
    
    public ControllerSwingPanelServidores() {
        panelServidores = null;
    }
    
    public void start() {
        panelServidores = new JPanelServidores();
    }
    
    public JPanel getPanelServidores() {
        return panelServidores;
    }

    public ServidorModel getServidorSeleccionado() {
        return panelServidores.getSelectedModel();
    }
    
}
