/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.controladores;

import servidorwebiecisa.RegisterControllers;

/**
 *
 * @author Administrator
 */
public abstract class AbstractController {
    public AbstractController() {
        registerController();
    }
    
    private void registerController() {
        RegisterControllers.getInstance().addController(this);
    }
}
