package servidorweb.swing.vistas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import servidorweb.domain.ServidorModel;

/**
 *
 * @author Miguel González Gómez
 */
public class VistaOpcionesServidor extends JDialog {
    public static final int CANCELAR = 0;
    public static final int ACEPTAR = 1;
    
    private int action = 0;
    
    private JTextFieldNumerico txtPort;
    private JTextField txtPath;
    private JTextField txtPathRelativeServicio;
    private JTextField txtClass;
    
    private ServidorModel servidorModel;
    
    public VistaOpcionesServidor(Frame parent) {
        super();
        
        init();
        
        setLocationRelativeTo(parent);
    }
    
    public VistaOpcionesServidor() {
        super();
        
        init();
        
        setLocationRelativeTo(null);
    }
    
    public VistaOpcionesServidor(Frame parent, ServidorModel servidorModel) {
        super();
        
        this.servidorModel = servidorModel;
        
        init();
        
        setLocationRelativeTo(parent);
    }
    
    public VistaOpcionesServidor(ServidorModel servidorModel) {
        super();
        
        this.servidorModel = servidorModel;
        
        init();
        
        setLocationRelativeTo(null);
    }
    
    private void init() {
        Container contentPane = getContentPane();
        setSize(470, 200);
        setTitle("Crear servidor");
        setModal(true);
        contentPane.setLayout(new BorderLayout());
        
        JLabel lblPort = new JLabel("Puerto");
        JLabel lblPath = new JLabel("Path estático");
        JLabel lblPathRelativo = new JLabel("Path relativo al servicio");
        JLabel lblClass = new JLabel("Clase del servicio");
        
        txtPort = new JTextFieldNumerico(10);
            txtPath = new JTextField(20);
            txtPathRelativeServicio = new JTextField(20);
            txtClass = new JTextField(20);
        if(servidorModel != null) {
            txtPort.setText(Integer.toString(servidorModel.getPort()));
            txtPath.setText(servidorModel.getPath());
            txtPathRelativeServicio.setText(servidorModel.getPathRelativeServicio());
            txtClass.setText(servidorModel.getClassServicio());
        }
        
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
        c.gridwidth = 1;
        panelCentro.add(lblPort, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        panelCentro.add(txtPort, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        panelCentro.add(lblPath, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        panelCentro.add(txtPath, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        panelCentro.add(lblPathRelativo, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        panelCentro.add(txtPathRelativeServicio, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        panelCentro.add(lblClass, c);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        panelCentro.add(txtClass, c);
        
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
                !txtPort.getText().isEmpty() &&
                !txtPath.getText().isEmpty() &&
                (
                    (txtPathRelativeServicio.getText().isEmpty() &&
                    txtClass.getText().isEmpty())
                ||
                    (!txtPathRelativeServicio.getText().isEmpty() &&
                    !txtClass.getText().isEmpty())
                )
                
               );
    }
    
    public int getAction() {
        return action;
    }
    
    public boolean isServicio() {
        return !txtClass.getText().isEmpty();
    }
    
    public int getPort() {
        return Integer.parseInt(txtPort.getText());
    }
    
    public String getPath() {
        return txtPath.getText();
    }
    
    public String getPathRelativoServicio() {
        return txtPathRelativeServicio.getText();
    }
    
    public String getClassServicio() {
        return txtClass.getText();
    }
}
