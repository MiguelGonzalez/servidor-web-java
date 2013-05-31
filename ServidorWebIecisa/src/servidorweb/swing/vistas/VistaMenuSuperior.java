package servidorweb.swing.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import servidorweb.swing.actions.ActionCrearServidor;
import servidorweb.swing.actions.ActionOpcionesServidor;
import servidorweb.swing.actions.ActionSalir;


/**
 *
 * @author Miguel González Gómez
 */
public class VistaMenuSuperior extends JPanel {
    
    private JButton btnSalir;
    private JButton btnCrearServidor;
    private JButton btnOpcionesServidor;
    
    public VistaMenuSuperior() {
        initComponents();
        
        initInterfaz();
        
        initListeners();
    }
    
    private void initComponents() {
        btnSalir = new JButton(new ActionSalir());
        btnCrearServidor = new JButton(new ActionCrearServidor());
        
        btnOpcionesServidor = new JButton(new ActionOpcionesServidor());
    }
    
    private void initInterfaz() {
        setLayout(new BorderLayout());
        
        add(btnSalir, BorderLayout.EAST);
        
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIzquierdo.add(btnCrearServidor);
        panelIzquierdo.add(btnOpcionesServidor);
        
        add(panelIzquierdo, BorderLayout.CENTER);
    }
    
    private void initListeners() {
        
    }
}
