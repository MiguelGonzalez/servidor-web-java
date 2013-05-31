package servidorweb.controllers.swing;

import servidorweb.controllers.Controller;
import javax.swing.JPanel;
import servidorweb.swing.vistas.VistaMenuSuperior;

/**
 *
 * @author Miguel González Gómez
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
