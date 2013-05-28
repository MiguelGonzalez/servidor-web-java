/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.controllers;

/**
 *
 * @author Administrator
 */
public abstract class Controller {
    public Controller() {
        registerController();
    }
    
    private void registerController() {
        AlmacenControladores.getInstance().addController(this);
    }
}
