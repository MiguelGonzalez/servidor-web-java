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
import servidorwebiecisa.controllers.AlmacenControladores;
import servidorwebiecisa.controllers.ServidorWebConcrete;

/**
 *
 * @author Administrator
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
