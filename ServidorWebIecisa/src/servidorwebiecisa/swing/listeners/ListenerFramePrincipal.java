package servidorwebiecisa.swing.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import servidorwebiecisa.RegisterControllers;
import servidorwebiecisa.swing.controladores.ControllerSwingPrincipal;

/**
 *
 * @author Administrator
 */
public class ListenerFramePrincipal extends WindowAdapter {
    
    private ControllerSwingPrincipal controller;
    
    public ListenerFramePrincipal() {
        controller = RegisterControllers.getInstance().getController(
                ControllerSwingPrincipal.class);
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        controller.salirAplicacion();
    }
}
