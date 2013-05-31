package servidorweb.swing.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.controllers.ServidorWebConcrete;

/**
 *
 * @author Miguel González Gómez
 */
public class ActionIniciarServidor extends AbstractAction {

    private ServidorWebConcrete servidorWebConcrete;
    
    public ActionIniciarServidor() {
        super("Iniciar servidor", null);

        putValue(SHORT_DESCRIPTION, "Iniciar servidor");
        putValue(NAME, "Iniciar servidor");
                
        servidorWebConcrete = AlmacenControladores.getInstance().
                getController(ServidorWebConcrete.class);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        servidorWebConcrete.iniciarServidor();
    }    
}