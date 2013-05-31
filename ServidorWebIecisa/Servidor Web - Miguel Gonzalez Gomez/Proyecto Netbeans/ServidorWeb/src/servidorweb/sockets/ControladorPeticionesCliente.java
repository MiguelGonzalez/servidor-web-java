package servidorweb.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import servidorweb.MainServidor;
import servidorweb.domain.ServidorModel;
import servidorweb.http.datasInput.Cabecera;
import servidorweb.http.HttpInputStream;
import servidorweb.http.HttpOutputStream;
import servidorweb.procesadoresPeticion.ConstruyePeticionCliente;
import servidorweb.servidorWeb.ServicioServidor;

/**
 *
 * @author Miguel González Gómez
 */
public class ControladorPeticionesCliente implements Runnable {

    private Socket skClient;
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean corriendo;
    private DataInputStream streamInput;
    private DataOutputStream streamOuput;
    
    private ConstruyePeticionCliente construirPeticion;
    private ServidorModel servidorModel;

    public ControladorPeticionesCliente(ServidorModel servidorModel,
            Socket skClient) throws IOException {
        this.servidorModel = servidorModel;
        this.skClient = skClient;
        
        inputStream = this.skClient.getInputStream();
        streamInput = new DataInputStream(inputStream);
        outputStream = this.skClient.getOutputStream();
        streamOuput = new DataOutputStream(outputStream);
        
        construirPeticion = new ConstruyePeticionCliente();
    }

    @Override
    public void run() {
        corriendo = true;
        
        while (corriendo) {
            try {
                while(corriendo) {
                    HttpInputStream input = construirPeticion.
                            procesarHttpInputStream(streamInput);
                    HttpOutputStream output = construirPeticion.
                            procesarHttpOuputStream(streamOuput);
                    
                    procesarPeticionCliente(input, output);
                }   
            } catch(IOException ex) {
                corriendo = false;
                MainServidor.log.info("Conexión con el cliente cerrada");
            }
        }
    }
    
    public void dejarDeEscucharPeticiones() {
        corriendo = false;
        cerrarConexion();
    }
    
    private void procesarPeticionCliente(HttpInputStream input, 
            HttpOutputStream output) {
        if(servidorModel.hasServicio()) {
            procesarPeticionClienteConServicio(input, output);
        } else {
            procesarRespuestaEstatico(input, output);
        }
    }
    
    private void procesarPeticionClienteConServicio(HttpInputStream input, 
            HttpOutputStream output) {
        if(isPeticionAServicio(input.getCabecera().recursoSolicitado,
                servidorModel.getPathRelativeServicio())) {
            procesarPeticionServicio(input, output);
        } else {
            procesarRespuestaEstatico(input, output);
        }
    }
    
    private void procesarPeticionServicio(HttpInputStream input, 
            HttpOutputStream output) {
        ServicioServidor servicio = servidorModel.getServicioServidor();
        if (input.getCabecera().action == Cabecera.ACTION_GET) {
            servicio.doGet(input, output);
        } else if (input.getCabecera().action == Cabecera.ACTION_POST) {
            servicio.doPost(input, output);
        }
    }
    
    private void procesarRespuestaEstatico(HttpInputStream input, 
            HttpOutputStream output) {
        File fichRecurso = new File(servidorModel.getPath() +
                input.getCabecera().recursoSolicitado);
        output.servirEstatico(fichRecurso);
    }
    
    private boolean isPeticionAServicio(String recursoSolicitado,
            String pathRelativeServicio) {
        return recursoSolicitado.contains(pathRelativeServicio);
    }
    
    private void cerrarConexion() {
        try {
            streamInput.close();
        } catch (IOException ex) {
            MainServidor.log.error("", ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            MainServidor.log.error("", ex);
        }
        try {
            streamOuput.close();
        } catch (IOException ex) {
            MainServidor.log.error("", ex);
        }
        try {
            outputStream.close();
        } catch (IOException ex) {
            MainServidor.log.error("", ex);
        }
    }

}
