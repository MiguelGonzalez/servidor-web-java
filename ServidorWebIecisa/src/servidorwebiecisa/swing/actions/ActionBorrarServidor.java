/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SELECTED_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorwebiecisa.RegisterControllers;
import servidorwebiecisa.swing.controladores.ControllerSwingPanelServidores;
import servidorwebiecisa.swing.controladores.ControllerSwingPrincipal;

/**
 *
 * @author paracaidista
 */
public class ActionBorrarServidor extends AbstractAction {

    private ControllerSwingPrincipal controllerSwing;
    
    public ActionBorrarServidor() {
        super("Borrar servidor", null);

        putValue(SHORT_DESCRIPTION, "Borrar servidor");
        putValue(NAME, "Borrar servidor");
        
        controllerSwing = RegisterControllers.getInstance().getController(
                ControllerSwingPrincipal.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.borrarServidor();
    }    
}
