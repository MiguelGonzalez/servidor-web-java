/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa;

import java.util.HashMap;
import servidorwebiecisa.swing.controladores.AbstractController;

/**
 *
 * @author Administrator
 */
public class RegisterControllers {
    
    private static RegisterControllers INSTANCE = null;
    private HashMap<String, AbstractController> controladores;
    
    private RegisterControllers() {
        controladores = new HashMap<>();
    }
    
    public static RegisterControllers getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RegisterControllers();
        }
        
        return INSTANCE;
    }
    
    public <T> T getController(Class classController) {
        return (T) classController.cast(controladores.get(classController.getName()));
    }
    
    public <T> T getController(String nameController, Class classController) {
        return (T) classController.cast(controladores.get(nameController));
    }
    
    public void addController(AbstractController controller) {
        controladores.put(controller.getClass().getName(), controller);
    }
    
    public void addController(String nameController, AbstractController controller) {
        controladores.put(nameController, controller);
    }
}
