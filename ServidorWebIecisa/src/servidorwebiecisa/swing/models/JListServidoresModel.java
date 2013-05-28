/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.swing.models;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ConfiguracionModelListener;
import servidorwebiecisa.domain.ServidorModel;

/**
 *
 * @author paracaidista
 */
public class JListServidoresModel  extends AbstractListModel implements ListModel, ConfiguracionModelListener  {

    private List<ServidorModel> servidores;
    
    private ConfiguracionModel configuracionModel;
    
    public JListServidoresModel() {
        configuracionModel = ConfiguracionModel.
                getInstance();
        
        servidores = configuracionModel.getServidores();
        
        initListener();
    }
    
    private void initListener() {
        configuracionModel.addListener(this);
    }
    
    @Override
    public int getSize() {
        return servidores.size();
    }

    @Override
    public Object getElementAt(int index) {
        return servidores.get(index);
    }

    @Override
    public void addedServidor(ServidorModel servidorModel) {
        fireIntervalAdded(this, servidores.size(), servidores.size());
    }

    @Override
    public void removedServidor(ServidorModel servidorModel) {
        fireIntervalRemoved(this, servidores.size(), servidores.size());
    }
    
}
