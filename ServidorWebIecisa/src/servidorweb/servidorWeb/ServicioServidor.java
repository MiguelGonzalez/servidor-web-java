package servidorweb.servidorWeb;

import servidorweb.domain.ServidorModel;
import servidorweb.http.HttpInputStream;
import servidorweb.http.HttpOutputStream;

/**
 * 
 * @author Miguel González Gómez
 */
public abstract class ServicioServidor {
    public abstract void init();
    public abstract void doPost(HttpInputStream inputStream, HttpOutputStream ouputStream);
    public abstract void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream);
    
    protected ServidorModel servidorModel;
    
    public ServicioServidor(ServidorModel servidorModel) {
        this.servidorModel = servidorModel;
    }
}
