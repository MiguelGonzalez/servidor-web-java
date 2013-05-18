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
import servidorwebiecisa.swing.controladores.ControllerSwingPrincipal;

/**
 *
 * @author Administrator
 */
public class ActionIniciarServidor extends AbstractAction {

    private ControllerSwingPrincipal controllerSwing;
    
    public ActionIniciarServidor() {
        super("Iniciar servidor", null);

        putValue(SHORT_DESCRIPTION, "Iniciar servidor");
        putValue(NAME, "Iniciar servidor");
        putValue(MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_I));
        putValue(SELECTED_KEY, Boolean.TRUE);
        
        controllerSwing = RegisterControllers.getInstance().getController(
                ControllerSwingPrincipal.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.iniciarServidor();
    }    
}