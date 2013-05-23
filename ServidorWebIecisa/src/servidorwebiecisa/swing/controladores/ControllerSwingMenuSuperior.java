/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.controladores;

import javax.swing.JPanel;
import servidorwebiecisa.swing.vistas.JPanelMenuSuperior;

/**
 *
 * @author Administrator
 */
public class ControllerSwingMenuSuperior extends AbstractController {
    
    private JPanelMenuSuperior menuSuperior;
    
    public ControllerSwingMenuSuperior() {
        menuSuperior = null;
    }
    
    public void start() {
        menuSuperior = new JPanelMenuSuperior();
    }
    
    public JPanel getPanelSuperior() {
        return menuSuperior;
    }
    
}
