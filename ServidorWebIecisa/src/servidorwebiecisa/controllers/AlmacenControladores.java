/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.controllers;

import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class AlmacenControladores {
    
    private static AlmacenControladores INSTANCE = null;
    private HashMap<String, Controller> controladores;
    
    private AlmacenControladores() {
        controladores = new HashMap<>();
    }
    
    public static AlmacenControladores getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AlmacenControladores();
        }
        
        return INSTANCE;
    }
    
    public <T> T getController(Class classController) {
        return (T) classController.cast(controladores.get(classController.getName()));
    }
    
    public <T> T getController(String nameController, Class classController) {
        return (T) classController.cast(controladores.get(nameController));
    }
    
    public void addController(Controller controller) {
        controladores.put(controller.getClass().getName(), controller);
    }
    
    public void addController(String nameController, Controller controller) {
        controladores.put(nameController, controller);
    }
}
