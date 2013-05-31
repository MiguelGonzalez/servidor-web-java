package servidorweb.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SELECTED_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.controllers.swing.OpcionesServidorConcrete;

/**
 *
 * @author Miguel González Gómez
 */
public class ActionOpcionesServidor extends AbstractAction {

    private OpcionesServidorConcrete controllerSwing;
    
    public ActionOpcionesServidor() {
        super("Opciones del servidor", null);

        putValue(SHORT_DESCRIPTION, "Opciones del servidor");
        putValue(NAME, "Opciones del servidor");
        putValue(MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_I));
        putValue(SELECTED_KEY, Boolean.TRUE);
        
        controllerSwing = AlmacenControladores.getInstance().getController(
                OpcionesServidorConcrete.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.opcionesServidor();
    }    
}