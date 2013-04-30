/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.http.datasInput.Cabecera;
import servidorwebiecisa.http.HttpInputStream;
import servidorwebiecisa.http.HttpOutputStream;
import servidorwebiecisa.http.datasInput.Cookie;
import servidorwebiecisa.http.datasInput.Formulario;
import servidorwebiecisa.procesadoresPeticion.ProcesarCabeceraPeticion;
import servidorwebiecisa.procesadoresPeticion.ProcesarContentTypePeticion;
import servidorwebiecisa.procesadoresPeticion.ProcesarCookiePeticion;
import servidorwebiecisa.procesadoresPeticion.ProcesarFormularioPeticion;
import servidorwebiecisa.procesadoresPeticion.ProcesarPeticion;
import servidorwebiecisa.servidorWeb.IServidorWeb;
import servidorwebiecisa.servidorWeb.ServidorWeb;

/**
 *
 * @author Administrator
 */
public class ControladorPeticionesCliente implements Runnable {

    private Socket skClient;
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean corriendo;
    private DataInputStream streamInput;
    private DataOutputStream streamOuput;
    
    private IServidorWeb iServidorWeb = null;
    private ProcesarPeticion procesarPeticion;

    public ControladorPeticionesCliente(Socket skClient) throws IOException {
        this.skClient = skClient;
        
        inputStream = this.skClient.getInputStream();
        streamInput = new DataInputStream(inputStream);
        outputStream = this.skClient.getOutputStream();
        streamOuput = new DataOutputStream(outputStream);
        
        procesarPeticion = new ProcesarPeticion();
    }

    @Override
    public void run() {
        corriendo = true;
        iServidorWeb = new ServidorWeb();
        iServidorWeb.init();
        
        while (corriendo) {
            try {
                while(corriendo) {
                    HttpInputStream input = procesarPeticion.procesarHttpInputStream(streamInput);
                    HttpOutputStream output = procesarPeticion.procesarHttpOuputStream(streamOuput);
                    
                    if (input.getCabecera().action == Cabecera.ACTION_GET) {
                        iServidorWeb.doGet(input, output);
                    } else if (input.getCabecera().action == Cabecera.ACTION_POST) {
                        iServidorWeb.doPost(input, output);
                    }
                }   
            } catch(IOException ex) {
                corriendo = false;
                ServidorWebIecisa.log.error(ex);
            }
            try {
                streamInput.close();
            } catch (IOException ex) {
                ServidorWebIecisa.log.error(ex);
            }
            try {
                inputStream.close();
            } catch (IOException ex) {
                ServidorWebIecisa.log.error(ex);
            }
            try {
                streamOuput.close();
            } catch (IOException ex) {
                ServidorWebIecisa.log.error(ex);
            }
            try {
                outputStream.close();
            } catch (IOException ex) {
                ServidorWebIecisa.log.error(ex);
            }

            corriendo = false;
        }
    }
    

    public void dejarDeEscucharPeticiones() {
        corriendo = false;
    }

}
