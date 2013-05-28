/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.controllers.swing;

import javax.swing.JPanel;
import servidorwebiecisa.controllers.Controller;

import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.swing.vistas.VistaListadoServidores;

/**
 *
 * @author paracaidista
 */
public class PanelServidoresConcrete extends Controller {
    
    private VistaListadoServidores panelServidores;
    
    public PanelServidoresConcrete() {
        panelServidores = null;
    }
    
    public void start() {
        panelServidores = new VistaListadoServidores();
    }
    
    public JPanel getPanelServidores() {
        return panelServidores;
    }

    public ServidorModel getServidorSeleccionado() {
        return panelServidores.getSelectedModel();
    }
    
}
