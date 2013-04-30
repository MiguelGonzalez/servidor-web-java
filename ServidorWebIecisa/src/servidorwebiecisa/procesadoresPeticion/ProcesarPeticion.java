package servidorwebiecisa.procesadoresPeticion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;
import servidorwebiecisa.http.datasInput.Cabecera;
import servidorwebiecisa.http.datasInput.Cookie;
import servidorwebiecisa.http.datasInput.Formulario;

public class ProcesarPeticion {
    
    private ProcesarCabeceraPeticion procesarCabeceraPeticion;
    private ProcesarFormularioPeticion procesarFormularioPeticion;
    private ProcesarCookiePeticion procesarCookiePeticion;
    private ProcesarContentTypePeticion procesarContentTypePeticion;
    
    public ProcesarPeticion() {
        procesarCabeceraPeticion = new ProcesarCabeceraPeticion();
        procesarFormularioPeticion = new ProcesarFormularioPeticion();
        procesarCookiePeticion = new ProcesarCookiePeticion();
        procesarContentTypePeticion = new ProcesarContentTypePeticion();
    }
    
    private Cabecera cabeceraPeticion;
    private Formulario formularioPeticion;
    private Cookie cookiePeticion;
    
    public HttpInputStream procesarHttpInputStream(DataInputStream streamInput) throws IOException {
        StringBuilder strPeticionCliente = getPeticionCliente(streamInput);
        
        cabeceraPeticion = procesarCabeceraPeticion.getCabecera(strPeticionCliente.toString());
        formularioPeticion = procesarFormularioPeticion.getFormulario(strPeticionCliente.toString());
        cookiePeticion = procesarCookiePeticion.getCookie(strPeticionCliente.toString());

        return new HttpInputStream(cabeceraPeticion,
                formularioPeticion, cookiePeticion);
    }
    
    public HttpOutputStream procesarHttpOuputStream(DataOutputStream streamOuput) {
        String contentType = procesarContentTypePeticion.getContentType(cabeceraPeticion.recursoSolicitado);
        return new HttpOutputStream(streamOuput, contentType);
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
            ServidorWebIecisa.log.error(ex);
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

        if(strPeticion.length() < 10) {
            System.out.println(strPeticion.toString());
        } else {
            if(strPeticion.length() == 0) {
                System.out.println("NO HA ESCRITO NADA EL CLIENTE");
            } else {
                System.out.println(strPeticion.substring(0, 10));
            }
        }
        
        
        return strPeticion;
    }
}
