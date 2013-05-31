package servidorweb.controllers.swing;

import javax.swing.JOptionPane;
import servidorweb.controllers.Controller;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.domain.ConfiguracionModel;
import servidorweb.domain.ServidorModel;
import servidorweb.swing.vistas.VistaOpcionesServidor;
import servidorweb.swing.vistas.VistaOpcionesServidorWeb;

/**
 *
 * @author Miguel González Gómez
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
