/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.http;

import servidorwebiecisa.http.datasInput.Cabecera;
import servidorwebiecisa.http.datasInput.Cookie;
import servidorwebiecisa.http.datasInput.Formulario;

/**
 *
 * @author Administrator
 */
public class HttpInputStream {
    private Cabecera cabeceraPeticion;
    private Formulario formularioPeticion;
    private Cookie cookiePeticion;
    
    public HttpInputStream(Cabecera cabeceraPeticion, Formulario formularioPeticion,
            Cookie cookiePeticion) {
        this.cabeceraPeticion = cabeceraPeticion;
        this.formularioPeticion = formularioPeticion;
        this.cookiePeticion = cookiePeticion;
    }
    
    public Cabecera getCabecera() {
        return cabeceraPeticion;
    }
    
    public Formulario getFormulario() {
        return formularioPeticion;
    }
    
    public Cookie getCookie() {
        return cookiePeticion;
    }
}
