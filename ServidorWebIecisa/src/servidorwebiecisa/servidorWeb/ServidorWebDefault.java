/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.servidorWeb;

import java.util.Map;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;

/**
 *
 * @author Administrator
 */
public class ServidorWebDefault extends IServidorWeb {

    public ServidorWebDefault(Map<String, String> valores) {
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
