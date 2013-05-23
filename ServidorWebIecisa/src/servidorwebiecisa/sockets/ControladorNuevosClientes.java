/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.ServidorWebIecisa;
import servidorwebiecisa.servidorWeb.IServidorWeb;

/**
 *
 * @author Administrator
 */
public class ControladorNuevosClientes extends Thread {

    private ServerSocket skServer;
    private boolean corriendo;
    private ExecutorService executorServiceServer;
    private ConfiguracionModel config = ConfiguracionModel.getInstance();
    
    private IServidorWeb servidor;
    

    public ControladorNuevosClientes(IServidorWeb servidor, 
            ServerSocket skServer) {
        this.servidor = servidor;
        this.skServer = skServer;
        executorServiceServer = Executors.newFixedThreadPool(config.getNumPoolSockets());
    }
        
    @Override
    public void run() {
        corriendo = true;
        
        while(corriendo) {
            try {
                Socket skClient = skServer.accept();
                skClient.setSoTimeout(config.getKeepAliveTimeout());
                
                
                ControladorPeticionesCliente controladorCliente =
                        new ControladorPeticionesCliente(servidor, skClient);
                
                executorServiceServer.execute(controladorCliente);
            } catch (IOException ex) {
                ServidorWebIecisa.log.error("", ex);
            }
        }
        executorServiceServer.shutdownNow();
    }
    
    public void empezarAEscuchar() {
        start();
    }
    
    public void pararDeEscuchar() {

        corriendo = false;
    }
}

