/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import servidorwebiecisa.swing.actions.ActionCrearServidor;
import servidorwebiecisa.swing.actions.ActionOpcionesServidor;
import servidorwebiecisa.swing.actions.ActionSalir;


/**
 *
 * @author Administrator
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
