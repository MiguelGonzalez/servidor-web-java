/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.http.datasInput;

import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public class Formulario {
    
    public HashMap<String, String> datosFormulario;
    
    public Formulario() {
        datosFormulario = new HashMap<>();
    }
    
    public String get(String clave) {
        return datosFormulario.get(clave);
        
    }
}
