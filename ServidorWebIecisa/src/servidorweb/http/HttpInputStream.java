package servidorweb.http;

import java.util.List;
import servidorweb.http.datasInput.Cabecera;
import servidorweb.http.datasInput.Cookie;
import servidorweb.http.datasInput.Formulario;

/**
 *
 * @author Miguel González Gómez
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
