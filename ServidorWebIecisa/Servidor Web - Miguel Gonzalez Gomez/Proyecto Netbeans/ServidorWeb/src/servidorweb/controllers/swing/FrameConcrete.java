package servidorweb.controllers.swing;

import servidorweb.controllers.Controller;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import servidorweb.MainServidor;
import servidorweb.controllers.AlmacenControladores;
import servidorweb.domain.ConfiguracionModel;
import servidorweb.domain.ServidorModel;
import servidorweb.swing.vistas.VistaFramePrincipal;
import servidorweb.swing.listeners.ListenerFramePrincipal;

/**
 *
 * @author Miguel González Gómez
 */
public class FrameConcrete extends Controller {
    
    private VistaFramePrincipal servidor;
    
    private MenuSuperiorConcrete menuSuperior;
    private PanelServidoresConcrete panelServidores;
    private OpcionesServidorConcrete opcionesServidor;
    
    public FrameConcrete() {
    }
    
    public void start() {
        inicializarInterfaz();
        
        menuSuperior = new MenuSuperiorConcrete();
        panelServidores = new PanelServidoresConcrete();
        opcionesServidor = new OpcionesServidorConcrete();
        
        menuSuperior.start();
        panelServidores.start();
        

        agregarComponentes();
    }
    
    public JFrame getFramePrincipal() {
        return servidor;
    }
    
    public ServidorModel getServidorModelSeleccionado() {
        return panelServidores.getServidorSeleccionado();
    }
    
    private void agregarComponentes() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                servidor.setPanelSuperior(
                        menuSuperior.getPanelSuperior());
                servidor.setPanelServidores(
                        panelServidores.getPanelServidores());
            }
        });
    }
    
    private void inicializarInterfaz() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                servidor = new VistaFramePrincipal("Servidor web");
                servidor.setBounds(100,100,500,300);
                servidor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                servidor.setVisible(false);
                servidor.addWindowListener(
                        new ListenerFramePrincipal());
                servidor.setVisible(true);
            }
        });
    }
    
    public void nuevoServidor() {
        opcionesServidor.crearServidor();
    }
    
    public void editarServidor() {
        ServidorModel servidorModel = panelServidores.getServidorSeleccionado();
        
        FrameConcrete controllerSwing = AlmacenControladores.getInstance().getController(
                        FrameConcrete.class);
        if(servidorModel != null) {
            
            if(!servidorModel.isCorriendo()) {
                opcionesServidor.editarServidor(servidorModel);
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
    
    public void borrarServidor() {
        ServidorModel servidorModel = panelServidores.getServidorSeleccionado();
        
        FrameConcrete controllerSwing = AlmacenControladores.getInstance().getController(
                    FrameConcrete.class);
        if(!servidorModel.isCorriendo()) {
            int res = JOptionPane.showConfirmDialog(controllerSwing.getFramePrincipal(), 
                    "¿Está seguro de querer borrar el servidor?",
                    "Borrar servidor",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(res == JOptionPane.OK_OPTION) {
                ConfiguracionModel.getInstance().removeServidor(servidorModel);
                MainServidor.log.info("Servidor borrado");
            }
        } else {
            JOptionPane.showMessageDialog(controllerSwing.getFramePrincipal(),
                    "El servidor está iniciado, páralo antes de borrrarlo.", "Borrar servidor", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
