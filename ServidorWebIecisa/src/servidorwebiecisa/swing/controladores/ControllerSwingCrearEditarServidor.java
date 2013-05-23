/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.controladores;

import servidorwebiecisa.RegisterControllers;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.swing.vistas.JDialogCrearEditarServidor;

/**
 *
 * @author paracaidista
 */
public class ControllerSwingCrearEditarServidor extends AbstractController {
    
    
    ControllerSwingPrincipal controllerPrincipal;
    
    public ControllerSwingCrearEditarServidor() {
        controllerPrincipal = RegisterControllers.
                getInstance().getController(
                ControllerSwingPrincipal.class);
    }
    
    public void crearServidor() {
        JDialogCrearEditarServidor dialogNuevoServidor = new
                JDialogCrearEditarServidor(controllerPrincipal.getFramePrincipal());
        
        dialogNuevoServidor.setVisible(true);
        
        if(dialogNuevoServidor.getAction() == dialogNuevoServidor.ACEPTAR) {
            int port = dialogNuevoServidor.getPort();
            String path = dialogNuevoServidor.getPath();
            
            ServidorModel servidorModel;

            if(dialogNuevoServidor.isServicio()) {
                String pathRelativoServicio = dialogNuevoServidor.getPathRelativoServicio();
                String classServicio = dialogNuevoServidor.getClassServicio();
                
                servidorModel = new ServidorModel(port, path,
                        pathRelativoServicio, classServicio);
            } else {
                servidorModel = new ServidorModel(port, path);
            }
            
            ConfiguracionModel.getInstance().addServidor(servidorModel);
        }
    }

    void editarServidor(ServidorModel servidorModel) {
        JDialogCrearEditarServidor dialogNuevoServidor = new
                JDialogCrearEditarServidor(controllerPrincipal.getFramePrincipal(),
                servidorModel);
        
        dialogNuevoServidor.setVisible(true);
        
        if(dialogNuevoServidor.getAction() == dialogNuevoServidor.ACEPTAR) {
            int port = dialogNuevoServidor.getPort();
            String path = dialogNuevoServidor.getPath();
            
            if(dialogNuevoServidor.isServicio()) {
                String pathRelativoServicio = dialogNuevoServidor.getPathRelativoServicio();
                String classServicio = dialogNuevoServidor.getClassServicio();

                servidorModel.updateTo(port, path,
                        pathRelativoServicio, classServicio);
            } else {
                servidorModel.updateTo(port, path);
            }
        }
    }
}
