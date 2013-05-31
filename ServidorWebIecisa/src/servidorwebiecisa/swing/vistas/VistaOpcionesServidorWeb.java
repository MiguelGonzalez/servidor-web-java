/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.vistas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ServidorModel;
import static servidorwebiecisa.swing.vistas.VistaOpcionesServidor.ACEPTAR;
import static servidorwebiecisa.swing.vistas.VistaOpcionesServidor.CANCELAR;

/**
 *
 * @author paracaidista
 */
public class VistaOpcionesServidorWeb extends JDialog {
    public static final int CANCELAR = 0;
    public static final int ACEPTAR = 1;
    
    private int action = 0;

    private JTextField txtNumPoolSockets;
    private JTextField txtTimeKeepAlive;
    
    private ConfiguracionModel configuracionModel;
    
    public VistaOpcionesServidorWeb(Frame parent, ConfiguracionModel configuracionModel) {
        super();
        
        this.configuracionModel = configuracionModel;
        
        init();
        
        setLocationRelativeTo(parent);
    }
    
    public VistaOpcionesServidorWeb(ConfiguracionModel configuracionModel) {
        super();
        
        this.configuracionModel = configuracionModel;
        
        init();
        
        setLocationRelativeTo(null);
    }
    
    private void init() {
        Container contentPane = getContentPane();
        setSize(470, 200);
        setTitle("Opciones del servidor web");
        setModal(true);
        contentPane.setLayout(new BorderLayout());
        
        JLabel lblPoolSockets = new JLabel("Número del pool de sockets para cada servidor");
        JLabel lblKeepAlive = new JLabel("Tiempo de KeepAlive de cada conexión (segs)");
        
        txtNumPoolSockets = new JTextFieldNumerico(
                Integer.toString(
                configuracionModel.getNumPoolSockets()));
        txtTimeKeepAlive = new JTextFieldNumerico(
                Integer.toString(
                configuracionModel.getKeepAliveTimeoutInMillis() / 1000));

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        
        /*
         * CENTRO
         */
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 25;
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0,0,0);
        panelCentro.add(lblPoolSockets, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0, 0,0,200);
        panelCentro.add(txtNumPoolSockets, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0, 0,0,0);
        panelCentro.add(lblKeepAlive, c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0,0,200);
        panelCentro.add(txtTimeKeepAlive, c);
        
        contentPane.add(panelCentro, BorderLayout.CENTER);
        
        /*
         * PANEL INFERIOR
         */
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(btnCancelar);
        panelInferior.add(btnAceptar);
        contentPane.add(panelInferior, BorderLayout.SOUTH);
        
        btnAceptar.addActionListener(new PulsadoAceptar());
        btnCancelar.addActionListener(new PulsadoCancelar());
    }
    
    private class PulsadoAceptar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            aceptarAccion();
        }
    }
    
    private void aceptarAccion() {
        if(estanTextosIntroducidos()) {
                action = ACEPTAR;
                setVisible(false);
        } else {
            JOptionPane.showMessageDialog(getRootPane(),
                "Faltan datos por rellenar", 
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private class PulsadoCancelar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            action = CANCELAR;
            setVisible(false);
        }
    }
    
    private boolean estanTextosIntroducidos() {
        return (
                !txtNumPoolSockets.getText().isEmpty() &&
                !txtTimeKeepAlive.getText().isEmpty()
               );
    }
    
    public int getAction() {
        return action;
    }
    
    
    public int getNumPoolSockets() {
        return Integer.parseInt(txtNumPoolSockets.getText());
    }
    
    public int getTimeKeepAlive() {
        return Integer.parseInt(txtTimeKeepAlive.getText());
    }
}
