/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.vistas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class VistaFramePrincipal extends JFrame {
    
    private JPanel panelSuperior;
    private JPanel panelServidores;
    
    public VistaFramePrincipal(String tituloVentana) {
        super(tituloVentana);
        
        panelSuperior = null;
        panelServidores = null;
    }
    
    public void setPanelSuperior(JPanel panelSuperior) {
        if(this.panelSuperior != null) {
            getContentPane().remove(this.panelSuperior);
        }
        this.panelSuperior = panelSuperior;
        getContentPane().add(this.panelSuperior, BorderLayout.NORTH);
        
        getContentPane().validate();
    }
    
    public void setPanelServidores(JPanel panelServidores) {
        if(this.panelServidores != null) {
            getContentPane().remove(this.panelServidores);
        }
        this.panelServidores = panelServidores;
        getContentPane().add(this.panelServidores, BorderLayout.CENTER);
        
        getContentPane().validate();
    }
}
