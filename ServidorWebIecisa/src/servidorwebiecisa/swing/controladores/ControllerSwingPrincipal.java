/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.controladores;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.controllers.Controller;
import servidorwebiecisa.swing.vistas.JFramePrincipal;
import servidorwebiecisa.swing.listeners.ListenerFramePrincipal;

/**
 *
 * @author Administrator
 */
public class ControllerSwingPrincipal extends Controller {
    private JFramePrincipal servidor;
    private JPanel panelSuperior;
    
    public ControllerSwingPrincipal() {
    }
    
    public void start() {
        inicializarInterfaz();
        
        ControllerSwingMenuSuperior controllerSwingMenu = new ControllerSwingMenuSuperior();
        
        controllerSwingMenu.start();
        
        panelSuperior = controllerSwingMenu.getPanelSuperior();
        
        servidor.setPanelSuperior(
                panelSuperior);
        
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
                "¿Desea salir de la aplicación?",
                "Salir",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(res == JOptionPane.OK_OPTION) {
            ServidorWebIecisa.log.info("Fin de la aplicación");
            System.exit(0);
        }
    }
    
    public void iniciarServidor() {
        ServidorWebIecisa.log.info("Servidor iniciado");
    }
    
    public void pararServidor() {
        int res = JOptionPane.showConfirmDialog(servidor, 
                "¿Está seguro de querer parar el servidor?",
                "Parar servidor",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(res == JOptionPane.OK_OPTION) {
            ServidorWebIecisa.log.info("Servidor parado");
            
        }
    }
}
