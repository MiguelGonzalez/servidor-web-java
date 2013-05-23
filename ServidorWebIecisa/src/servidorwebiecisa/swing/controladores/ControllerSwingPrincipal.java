/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.controladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import servidorwebiecisa.RegisterControllers;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.swing.vistas.JFramePrincipal;
import servidorwebiecisa.swing.listeners.ListenerFramePrincipal;

/**
 *
 * @author Administrator
 */
public class ControllerSwingPrincipal extends AbstractController {
    
    private JFramePrincipal servidor;
    
    private ControllerSwingMenuSuperior controllerSwingMenuSuperior;
    private ControllerSwingPanelServidores controllerSwingPanelServidores;
    ControllerSwingCrearEditarServidor controllerSwingCrearEditarServidor;
    
    public ControllerSwingPrincipal() {
    }
    
    public void start() {
        inicializarInterfaz();
        
        controllerSwingMenuSuperior = new ControllerSwingMenuSuperior();
        controllerSwingPanelServidores = new ControllerSwingPanelServidores();
        controllerSwingCrearEditarServidor = new ControllerSwingCrearEditarServidor();
        
        controllerSwingMenuSuperior.start();
        controllerSwingPanelServidores.start();
        

        agregarComponentes();
    }
    
    public JFrame getFramePrincipal() {
        return servidor;
    }
    
    private void agregarComponentes() {
        System.out.println("Antes");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                servidor.setPanelSuperior(
                        controllerSwingMenuSuperior.getPanelSuperior());
                servidor.setPanelServidores(
                        controllerSwingPanelServidores.getPanelServidores());
            }
        });
    }
    
    private void inicializarInterfaz() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                servidor = new JFramePrincipal("Servidor web");
                servidor.setBounds(100,100,500,300);
                servidor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                servidor.setVisible(false);
                servidor.addWindowListener(
                        new ListenerFramePrincipal());
                servidor.setVisible(true);
            }
        });
    }
    
    public void salirAplicacion() {
        int res = JOptionPane.showConfirmDialog(servidor, 
                "¿Desea guardar la configuración actual?",
                "Guardar",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(res == JOptionPane.YES_OPTION) {
            guardarConfiguracion();
            saliendoAplicacion();
        } else if(res == JOptionPane.NO_OPTION) {
            saliendoAplicacion();
        }
    }
    
    private void guardarConfiguracion() {
        ConfiguracionModel.getInstance().saveConfiguracion();
    }
    
    private void saliendoAplicacion() {
        ServidorWebIecisa.log.info("Fin de la aplicación");
        System.exit(0);
    }
    
    public void nuevoServidor() {
        controllerSwingCrearEditarServidor.crearServidor();
    }
    
    public void editarServidor() {
        ServidorModel servidorModel = controllerSwingPanelServidores.getServidorSeleccionado();
        
        ControllerSwingPrincipal controllerSwing = RegisterControllers.getInstance().getController(
                        ControllerSwingPrincipal.class);
        if(servidorModel != null) {
            
            if(!servidorModel.isIniciado()) {
                controllerSwingCrearEditarServidor.editarServidor(servidorModel);
            } else {
                JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                        "El servidor está iniciado, páralo antes de borrrarlo.", "Borrar servidor", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                    "No ha seleccionado ningún servidor para editar.", "Editar servidor", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void iniciarServidor() {
        ServidorModel servidorModel = controllerSwingPanelServidores.getServidorSeleccionado();
        
        ControllerSwingPrincipal controllerSwing = RegisterControllers.getInstance().getController(
                    ControllerSwingPrincipal.class);
        if(!servidorModel.isIniciado()) {
            int res = JOptionPane.showConfirmDialog(controllerSwing.getFramePrincipal(), 
                    "¿Está seguro de querer borrar el servidor?",
                    "Borrar servidor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.OK_OPTION) {
                ServidorWebIecisa.log.info("Servidor iniciado");
            }
        } else {
            JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                    "El servidor ya está iniciado", "Iniciar servidor", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void pararServidor() {
        ServidorModel servidorModel = controllerSwingPanelServidores.getServidorSeleccionado();
        
        ControllerSwingPrincipal controllerSwing = RegisterControllers.getInstance().getController(
                    ControllerSwingPrincipal.class);
        if(servidorModel.isIniciado()) {
            int res = JOptionPane.showConfirmDialog(controllerSwing.getFramePrincipal(), 
                    "¿Está seguro de querer parar el servidor?",
                    "Parar servidor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.OK_OPTION) {
                ServidorWebIecisa.log.info("Servidor parado");

            }
        } else {
            JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                    "El servidor ya está parado", "Parar servidor", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void borrarServidor() {
        ServidorModel servidorModel = controllerSwingPanelServidores.getServidorSeleccionado();
        
        ControllerSwingPrincipal controllerSwing = RegisterControllers.getInstance().getController(
                    ControllerSwingPrincipal.class);
        if(!servidorModel.isIniciado()) {
            int res = JOptionPane.showConfirmDialog(controllerSwing.getFramePrincipal(), 
                    "¿Está seguro de querer borrar el servidor?",
                    "Borrar servidor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.OK_OPTION) {
                ConfiguracionModel.getInstance().removeServidor(servidorModel);
                ServidorWebIecisa.log.info("Servidor borrado");
            }
        } else {
            JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                    "El servidor está iniciado, páralo antes de borrrarlo.", "Borrar servidor", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
