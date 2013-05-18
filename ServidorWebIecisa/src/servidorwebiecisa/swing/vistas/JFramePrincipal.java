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
public class JFramePrincipal extends JFrame {
    
    private JPanel panelSuperior;
    
    public JFramePrincipal(String tituloVentana) {
        super(tituloVentana);
        
        panelSuperior = null;
    }
    
    
    public void setPanelSuperior(JPanel panelSuperior) {
        if(this.panelSuperior != null) {
            getContentPane().remove(this.panelSuperior);
        }
        this.panelSuperior = panelSuperior;
        getContentPane().add(this.panelSuperior, BorderLayout.NORTH);
        
        getContentPane().repaint();
    }
    
}
