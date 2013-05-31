package servidorweb.swing.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.controllers.ServidorWebConcrete;

/**
 *
 * @author Miguel González Gómez
 */
public class ActionSalir extends AbstractAction {

    private ServidorWebConcrete servidorWebConcrete;
    
    public ActionSalir() {
        super("Salir", null);

        putValue(SHORT_DESCRIPTION, "Salir");
        putValue(NAME, "Salir");
        putValue(MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_S));
        putValue(SELECTED_KEY, Boolean.TRUE);
        
        servidorWebConcrete = AlmacenControladores.getInstance().
                getController(ServidorWebConcrete.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        servidorWebConcrete.salirAplicacion();
    }    
}
