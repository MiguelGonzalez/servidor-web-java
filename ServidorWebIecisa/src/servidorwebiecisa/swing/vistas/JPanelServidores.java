/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.vistas;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.swing.actions.ActionBorrarServidor;
import servidorwebiecisa.swing.actions.ActionEditarServidor;
import servidorwebiecisa.swing.actions.ActionIniciarServidor;
import servidorwebiecisa.swing.actions.ActionPararServidor;
import servidorwebiecisa.swing.models.JListServidoresModel;

/**
 *
 * @author paracaidista
 */
public class JPanelServidores extends JPanel {
    
    private JLabel lblMsgListado;
    private JListServidoresModel modelServidores;
    private JPopupMenu opcionesServidor;
    private JList lstServidores;
    
    public JPanelServidores() {
        initComponents();
        
        initInterfaz();
        
        initListeners();
    }
    
    private void initComponents() {
        lblMsgListado = new JLabel("Listado de servidores:");
        modelServidores = new JListServidoresModel();
        
        opcionesServidor = new JPopupMenu();
        opcionesServidor.add(new ActionIniciarServidor());
        opcionesServidor.add(new ActionPararServidor());
        opcionesServidor.add(new ActionEditarServidor());
        opcionesServidor.add(new ActionBorrarServidor());
        
        lstServidores = new JList(modelServidores);
       // lstServidores.setComponentPopupMenu(opcionesServidor);
    }
    
    private void initInterfaz() {
        setLayout(new BorderLayout());
        
        add(lblMsgListado, BorderLayout.NORTH);
        add(lstServidores, BorderLayout.CENTER);
    }
    
    private void initListeners() {
        lstServidores.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(e.getClickCount() == 1 &&
                        e.getButton() == MouseEvent.BUTTON3) {
                    lstServidores.setSelectedIndex(
                            lstServidores.locationToIndex(e.getPoint()));
                    opcionesServidor.show((JComponent)e.getSource(), 
                                    e.getX(), e.getY());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }
    
    public ServidorModel getSelectedModel() {
        return ServidorModel.class.cast(modelServidores.getElementAt(
                lstServidores.getSelectedIndex()));
    }
}
