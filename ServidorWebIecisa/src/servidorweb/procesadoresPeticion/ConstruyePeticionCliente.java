package servidorweb.procesadoresPeticion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import servidorweb.MainServidor;
import servidorweb.http.HttpInputStream;
import servidorweb.http.HttpOutputStream;
import servidorweb.http.datasInput.Cabecera;
import servidorweb.http.datasInput.Cookie;
import servidorweb.http.datasInput.Formulario;

/**
 * 
 * @author Miguel González Gómez
 */
public class ConstruyePeticionCliente {
    
    private ConstruyeCabecera procesarCabeceraPeticion;
    private ContruyeFormulario procesarFormularioPeticion;
    private ContruyeCookie procesarCookiePeticion;
    
    public ConstruyePeticionCliente() {
        procesarCabeceraPeticion = new ConstruyeCabecera();
        procesarFormularioPeticion = new ContruyeFormulario();
        procesarCookiePeticion = new ContruyeCookie();
    }
    
    private Cabecera cabeceraPeticion;
    private Formulario formularioPeticion;
    private List<Cookie> cookiesPeticion;
    
    public HttpInputStream procesarHttpInputStream(DataInputStream streamInput) throws IOException {
        StringBuilder strPeticionCliente = getPeticionCliente(streamInput);
        
        cabeceraPeticion = procesarCabeceraPeticion.getCabecera(strPeticionCliente.toString());
        formularioPeticion = procesarFormularioPeticion.getFormulario(strPeticionCliente.toString());
        cookiesPeticion = procesarCookiePeticion.getCookie(strPeticionCliente.toString());

        return new HttpInputStream(cabeceraPeticion,
                formularioPeticion, cookiesPeticion);
    }
    
    public HttpOutputStream procesarHttpOuputStream(DataOutputStream streamOuput) {
        return new HttpOutputStream(streamOuput);
    }
    
    private StringBuilder getPeticionCliente(DataInputStream streamInput) throws IOException {
        //Leemos la petición del navegador
        StringBuilder strPeticion = new StringBuilder();

        int available;

        //Si no ha recibido datos al principio de la conexión esperamos por ellos
        byte datosPreconexion[] = new byte[1];
        boolean preconexion = false;
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            MainServidor.log.error(ex);
        }
        
        if (streamInput.available() == 0) {
            streamInput.read(datosPreconexion);
            preconexion = true;
        }

        if (preconexion) {
            strPeticion.append(new String(datosPreconexion));
        }

        while ((available = streamInput.available()) > 0) {
            byte datos[] = new byte[available];
            streamInput.read(datos);
            strPeticion.append(new String(datos));
        }

        return strPeticion;
    }
}
