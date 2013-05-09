/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.http;

import java.util.List;
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
    private List<Cookie> cookiesPeticion;
    
    public HttpInputStream(Cabecera cabeceraPeticion, Formulario formularioPeticion,
            List<Cookie> cookiesPeticion) {
        this.cabeceraPeticion = cabeceraPeticion;
        this.formularioPeticion = formularioPeticion;
        this.cookiesPeticion = cookiesPeticion;
    }
    
    public Cabecera getCabecera() {
        return cabeceraPeticion;
    }
    
    public Formulario getFormulario() {
        return formularioPeticion;
    }
    
    public List<Cookie> getCookies() {
        return cookiesPeticion;
    }
    
    public Cookie getCookie(String nameCookie) {
        for(Cookie cookie : cookiesPeticion) {
            if(nameCookie.equals(cookie.nameCookie)) {
                return cookie;
            }
        }
        return null;
    }
}
