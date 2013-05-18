/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import servidorwebiecisa.RegisterControllers;
import servidorwebiecisa.swing.controladores.ControllerSwingPrincipal;

/**
 *
 * @author Administrator
 */
public class ActionSalir extends AbstractAction {

    private ControllerSwingPrincipal controllerSwing;
    
    public ActionSalir() {
        super("Salir", null);

        putValue(SHORT_DESCRIPTION, "Salir");
        putValue(NAME, "Salir");
        putValue(MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_S));
        putValue(SELECTED_KEY, Boolean.TRUE);
        
        controllerSwing = RegisterControllers.getInstance().getController(
                ControllerSwingPrincipal.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.salirAplicacion();
    }    
}
