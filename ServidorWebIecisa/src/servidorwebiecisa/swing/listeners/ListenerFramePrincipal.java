package servidorwebiecisa.swing.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import servidorwebiecisa.controllers.AlmacenControladores;
import servidorwebiecisa.controllers.ServidorWebConcrete;

/**
 *
 * @author Administrator
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
