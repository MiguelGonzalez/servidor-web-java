package servidorweb.procesadoresPeticion;

import java.util.ArrayList;
import java.util.List;
import servidorweb.http.datasInput.Cookie;

/**
 *
 * @author Miguel González Gómez
 */
public class ContruyeCookie {
    public ContruyeCookie() {
        
    }
    
    public List<Cookie> getCookie(String peticionCliente) {
        String datosCabeceraFilas[] = peticionCliente.split("\n");
        
        return procesarDatosCookie(datosCabeceraFilas);
    }
    
    protected List<Cookie> procesarDatosCookie(String[] datosCabeceraFilas) {
        List<Cookie> cookiesRecibidas = new ArrayList<>();
        for(String linea : datosCabeceraFilas) {
            if(linea.trim().isEmpty()) {
                //Procesamos hasta un salto de línea que indica el inicio del contenido de la petición
                break;
            }
            
            if(linea.startsWith("Cookie")) {
                String restoLinea = linea.substring(7).trim();
                
                String[] nombreValor = restoLinea.split("=");
                
                if(nombreValor.length == 2) {
                    Cookie cookie = new Cookie(nombreValor[0], nombreValor[1]);
                    
                    cookiesRecibidas.add(cookie);
                }
            }
        }

        return cookiesRecibidas;
    }
}
