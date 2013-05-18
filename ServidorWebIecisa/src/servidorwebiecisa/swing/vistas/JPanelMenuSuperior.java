/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import servidorwebiecisa.swing.actions.ActionIniciarServidor;
import servidorwebiecisa.swing.actions.ActionPararServidor;
import servidorwebiecisa.swing.actions.ActionSalir;


/**
 *
 * @author Administrator
 */
public class JPanelMenuSuperior extends JPanel {
    
    private JButton btnSalir;
    private JButton btnIniciarServidor;
    private JButton btnPararServidor;
    
    public JPanelMenuSuperior() {
        initComponents();
        
        initInterfaz();
        
        initListeners();
    }
    
    private void initComponents() {
        btnSalir = new JButton(new ActionSalir());
        btnIniciarServidor = new JButton(new ActionIniciarServidor());
        btnPararServidor = new JButton(new ActionPararServidor());
    }
    
    private void initInterfaz() {
        setLayout(new BorderLayout());
        
        add(btnSalir, BorderLayout.EAST);
        
        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIzquierdo.add(btnIniciarServidor);
        panelIzquierdo.add(btnPararServidor);
        
        add(panelIzquierdo, BorderLayout.CENTER);
    }
    
    private void initListeners() {
        
    }
}
