/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.servidorWeb;

import servidorwebiecisa.domain.ServidorModel;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;

public abstract class ServicioServidor {
    public abstract void init();
    public abstract void doPost(HttpInputStream inputStream, HttpOutputStream ouputStream);
    public abstract void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream);
    
    protected ServidorModel servidorModel;
    
    public ServicioServidor(ServidorModel servidorModel) {
        this.servidorModel = servidorModel;
    }
}
