package servidorweb.controllers.swing;

import javax.swing.JPanel;
import servidorweb.controllers.Controller;

import servidorweb.domain.ServidorModel;
import servidorweb.swing.vistas.VistaListadoServidores;

/**
 *
 * @author Miguel González Gómez
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
