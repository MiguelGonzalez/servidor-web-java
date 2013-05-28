/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.controllers.swing;

import servidorwebiecisa.controllers.Controller;
import javax.swing.JPanel;
import servidorwebiecisa.swing.vistas.VistaMenuSuperior;

/**
 *
 * @author Administrator
 */
public class MenuSuperiorConcrete extends Controller {
    
    private VistaMenuSuperior menuSuperior;
    
    public MenuSuperiorConcrete() {
        menuSuperior = null;
    }
    
    public void start() {
        menuSuperior = new VistaMenuSuperior();
    }
    
    public JPanel getPanelSuperior() {
        return menuSuperior;
    }
    
}
