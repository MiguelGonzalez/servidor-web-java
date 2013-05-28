package servidorwebiecisa.controllers;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import javax.swing.JOptionPane;
import servidorwebiecisa.MainServidor;
import servidorwebiecisa.controllers.swing.FrameConcrete;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.sockets.ControladorNuevosClientes;

public class ServidorWebConcrete extends Controller {
    
    private FrameConcrete controllerSwing;
    
    private HashMap<ServidorModel, ControladorNuevosClientes> servidoresEnEjecucion;
    
    public void start() {
        servidoresEnEjecucion = new HashMap<>();
        
        controllerSwing = new FrameConcrete();
        controllerSwing.start();
    }

    public void salirAplicacion() {
        int res = JOptionPane.showConfirmDialog(controllerSwing.getFramePrincipal(),
                "¿fDesea guardar la configuración actual?", "Guardar",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == JOptionPane.YES_OPTION) {
            guardarConfiguracion();
            pararServidores();
            saliendoAplicacion();
        } else if (res == JOptionPane.NO_OPTION) {
            saliendoAplicacion();
        }
    }

    public void pararServidor() {
        ServidorModel servidorModel = controllerSwing.getServidorModelSeleccionado();
        if (servidorModel.isCorriendo()) {
            int res = JOptionPane.showConfirmDialog(controllerSwing.getFramePrincipal(),
                    "¿Está seguro de querer parar el servidor?",
                    "Parar servidor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                pararServidor(servidorModel);
                MainServidor.log.info("Servidor parado");
            }
        } else {
            JOptionPane.showMessageDialog(
                    controllerSwing.getFramePrincipal(),
                    "El servidor ya está parado",
                    "Parar servidor", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void iniciarServidor() {
        ServidorModel servidorModel = controllerSwing.getServidorModelSeleccionado();
        if (!servidorModel.isCorriendo()) {
            int res = JOptionPane.showConfirmDialog(
                    controllerSwing.getFramePrincipal(),
                    "¿Está seguro de querer iniciar el servidor?",
                    "Iniciar servidor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.OK_OPTION) {
                lanzarServidor(servidorModel);
                MainServidor.log.info("Servidor iniciado");
            }
        } else {
            JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                    "El servidor ya está iniciado", "Iniciar servidor",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void lanzarServidor(ServidorModel servidorModel) {
        try {
            ServerSocket skServer = new ServerSocket(servidorModel.getPort());
            
            ControladorNuevosClientes controladorNuevosClientes = new
                    ControladorNuevosClientes(servidorModel, skServer);
            
            servidoresEnEjecucion.put(servidorModel, controladorNuevosClientes);
            
            controladorNuevosClientes.iniciarServidorWeb();
        } catch(IOException ex) {
            MainServidor.log.error("", ex);
        }
    }
    
    private void pararServidor(ServidorModel servidorModel) {
        if(servidoresEnEjecucion.containsKey(servidorModel)) {
            servidoresEnEjecucion.get(servidorModel).pararServidorWeb();
        }
    }

    private void pararServidores() {
        for(ServidorModel servidorModel : servidoresEnEjecucion.keySet()) {
            pararServidor(servidorModel);
        }
    }
    
    private void saliendoAplicacion() {
        MainServidor.log.info("Fin de la aplicaci\u00f3n");
        System.exit(0);
    }
    

    private void guardarConfiguracion() {
        ConfiguracionModel.getInstance().saveConfiguracion();
    }
}
