/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.controllers.swing;

import javax.swing.JOptionPane;
import servidorwebiecisa.controllers.Controller;
import servidorwebiecisa.controllers.AlmacenControladores;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.swing.vistas.VistaOpcionesServidor;
import servidorwebiecisa.swing.vistas.VistaOpcionesServidorWeb;

/**
 *
 * @author paracaidista
 */
public class OpcionesServidorConcrete extends Controller {
    
    FrameConcrete controllerPrincipal;
    
    public OpcionesServidorConcrete() {
        controllerPrincipal = AlmacenControladores.
                getInstance().getController(
                FrameConcrete.class);
    }
    
    public void crearServidor() {
        VistaOpcionesServidor dialogNuevoServidor = new
                VistaOpcionesServidor(controllerPrincipal.getFramePrincipal());
        
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
        VistaOpcionesServidor dialogNuevoServidor = new
                VistaOpcionesServidor(controllerPrincipal.getFramePrincipal(),
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

    public void opcionesServidor() {
        ConfiguracionModel configuracion = ConfiguracionModel.getInstance();
        VistaOpcionesServidorWeb dialogNuevoServidor = new
                VistaOpcionesServidorWeb(controllerPrincipal.getFramePrincipal(),
                configuracion);
        
        dialogNuevoServidor.setVisible(true);
        
        if(dialogNuevoServidor.getAction() == dialogNuevoServidor.ACEPTAR) {
            int numPoolSockets = dialogNuevoServidor.getNumPoolSockets();
            int timeKeepAlive = dialogNuevoServidor.getTimeKeepAlive();
            
            configuracion.updateKeelAliveTimeout(timeKeepAlive);
            configuracion.updateNumPoolSockets(numPoolSockets);
            
            JOptionPane.showMessageDialog(controllerPrincipal.getFramePrincipal(),
                "Para que los cambios sean efectivos debe reiniciar la aplicación", 
                "Cambios guardados",
                JOptionPane.WARNING_MESSAGE);
        }
    }
}
