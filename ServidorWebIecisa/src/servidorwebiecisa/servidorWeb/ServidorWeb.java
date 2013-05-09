/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.servidorWeb;

import java.util.Map;
import servidorwebiecisa.http.datasInput.Cabecera;
import servidorwebiecisa.procesadoresPeticion.ProcesarContentTypePeticion;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;
import servidorwebiecisa.http.datasInput.Formulario;

/**
 *
 * @author Administrator
 */
public class ServidorWeb extends IServidorWeb {

    public ServidorWeb(Map<String, String> valores) {
        super(valores);
    }
    
    @Override
    public void doPost(HttpInputStream inputStream, HttpOutputStream ouputStream) {
        
    }

    @Override
    public void doGet(HttpInputStream inputStream, HttpOutputStream ouputStream) {
        
    }

    @Override
    public void init() {
        
    }
    
}
