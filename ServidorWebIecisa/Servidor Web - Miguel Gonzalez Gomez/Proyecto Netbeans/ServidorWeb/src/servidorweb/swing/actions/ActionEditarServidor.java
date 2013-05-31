package servidorweb.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SELECTED_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.controllers.swing.FrameConcrete;

/**
 *
 * @author Miguel González Gómez
 */
public class ActionEditarServidor extends AbstractAction {

    private FrameConcrete controllerSwing;
    
    public ActionEditarServidor() {
        super("Editar servidor", null);

        putValue(SHORT_DESCRIPTION, "Editar servidor");
        putValue(NAME, "Editar servidor");
        putValue(MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_I));
        putValue(SELECTED_KEY, Boolean.TRUE);
        
        controllerSwing = AlmacenControladores.getInstance().getController(
                FrameConcrete.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        controllerSwing.editarServidor();
    }    
}