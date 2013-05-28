/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorwebiecisa.controllers.AlmacenControladores;
import servidorwebiecisa.controllers.swing.FrameConcrete;

/**
 *
 * @author paracaidista
 */
public class ActionBorrarServidor extends AbstractAction {

    private FrameConcrete controllerSwing;
    
    public ActionBorrarServidor() {
        super("Borrar servidor", null);

        putValue(SHORT_DESCRIPTION, "Borrar servidor");
        putValue(NAME, "Borrar servidor");
        
        controllerSwing = AlmacenControladores.getInstance().getController(
                FrameConcrete.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.borrarServidor();
    }    
}
