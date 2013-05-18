/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.controllers;

import servidorwebiecisa.RegisterControllers;

/**
 *
 * @author Administrator
 */
public abstract class Controller {
    public Controller() {
        registerController();
    }
    
    private void registerController() {
        RegisterControllers.getInstance().addController(this);
    }
}
