package servidorweb.http.datasInput;

import java.util.HashMap;

/**
 *
 * @author Miguel González Gómez
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
