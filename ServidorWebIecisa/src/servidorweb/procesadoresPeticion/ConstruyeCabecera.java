package servidorweb.procesadoresPeticion;

import servidorweb.http.datasInput.Cabecera;

/**
 * 
 * @author Miguel González Gómez
 */
public class ConstruyeCabecera  {
    
    public ConstruyeCabecera() {
    }
    
    public Cabecera getCabecera(String peticionCliente) {
        String datosCabeceraFilas[] = peticionCliente.split("\n");
        
        return procesarDatosCabecera(datosCabeceraFilas);
    }
    
    private String formatearRecursoSolicitado(String recursoSolicitado) {
        return recursoSolicitado;
    }
    
    protected Cabecera procesarDatosCabecera(String[] datosCabeceraFilas) {
        Cabecera cabecera = new Cabecera();
        
        for(String linea : datosCabeceraFilas) {
            if(linea.trim().isEmpty()) {
                //Procesamos hasta un salto de línea que indica el inicio del contenido de la petición
                break;
            }
            
            if(linea.startsWith("GET")) {
                cabecera.action = cabecera.ACTION_GET;
                String restoLinea = linea.substring(4);
                int inicioProtocolo = restoLinea.indexOf("HTTP");
                String recursoSolicitado = restoLinea.substring(0, inicioProtocolo - 1);

                recursoSolicitado = formatearRecursoSolicitado(recursoSolicitado);
                
                cabecera.recursoSolicitado = recursoSolicitado;

                cabecera.protocoloHtpp = restoLinea.substring(inicioProtocolo, restoLinea.length() - 1);
            } else if(linea.startsWith("POST")) {
                cabecera.action = cabecera.ACTION_POST;

                String restoLinea = linea.substring(4);
                int inicioProtocolo = restoLinea.indexOf("HTTP");
                String recursoSolicitado = restoLinea.substring(0, inicioProtocolo - 1);

                recursoSolicitado = formatearRecursoSolicitado(recursoSolicitado);

                cabecera.recursoSolicitado = recursoSolicitado;

                cabecera.protocoloHtpp = restoLinea.substring(inicioProtocolo, restoLinea.length() - 1);
            } else if(linea.startsWith("Host:")) {
                cabecera.host = linea.substring(6);
            } else if(linea.startsWith("User-Agent:")) {
                cabecera.userAgent = linea.substring(12);
            } else if(linea.startsWith("Accept:")) {
                cabecera.accept = linea.substring(8);
            } else if(linea.startsWith("Accept-Language:")) {
                cabecera.acceptLanguage = linea.substring(17);
            } else if(linea.startsWith("Accept-Encoding:")) {
                cabecera.acceptEncoding = linea.substring(16);
            } else if(linea.startsWith("Connection:")) {
                cabecera.connection = linea.substring(12);
            }
        }
        
        return cabecera;
    }
}
