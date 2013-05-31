package servidorweb.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SELECTED_KEY;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.controllers.ServidorWebConcrete;

/**
 *
 * @author Miguel González Gómez
 */
public class ActionPararServidor extends AbstractAction {

    private ServidorWebConcrete servidorWebConcrete;
    
    public ActionPararServidor() {
        super("Parar servidor", null);

        putValue(SHORT_DESCRIPTION, "Parar servidor");
        putValue(NAME, "Parar servidor");
        putValue(MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_P));
        putValue(SELECTED_KEY, Boolean.TRUE);
        
        servidorWebConcrete = AlmacenControladores.getInstance().
                getController(ServidorWebConcrete.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        servidorWebConcrete.pararServidor();
    }    
}
