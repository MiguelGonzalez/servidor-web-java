package servidorweb.swing.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.controllers.ServidorWebConcrete;

/**
 *
 * @author Miguel González Gómez
 */
public class ListenerFramePrincipal extends WindowAdapter {
    
    private ServidorWebConcrete servidorWebConcrete;
    
    public ListenerFramePrincipal() {
        servidorWebConcrete = AlmacenControladores.getInstance().
                getController(ServidorWebConcrete.class);
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        servidorWebConcrete.salirAplicacion();
    }
}
