/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorwebiecisa.RegisterControllers;
import servidorwebiecisa.swing.controladores.ControllerSwingPanelServidores;
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
                
        controllerSwing = RegisterControllers.getInstance().getController(
                ControllerSwingPrincipal.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.iniciarServidor();
    }    
}