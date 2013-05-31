package servidorweb.controllers;

/**
 *
 * @author Miguel González Gómez
 */
public abstract class Controller {
    public Controller() {
        registerController();
    }
    
    private void registerController() {
        AlmacenControladores.getInstance().addController(this);
    }
}
