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
import servidorwebiecisa.ConfiguracionServidor;
import servidorwebiecisa.ServidorWebIecisa;

/**
 *
 * @author Administrator
 */
public class ControladorNuevosClientes extends Thread {

    private ServerSocket skServer;
    private boolean corriendo;
    private ExecutorService executorServiceServer;
    private ConfiguracionServidor config = ConfiguracionServidor.getInstance();
    
    private int aliveTimeOut;
    

    public ControladorNuevosClientes(ServerSocket skServer) {
        this.skServer = skServer;
        
        
        aliveTimeOut = config.getKeepAliveTimeout();
        
        executorServiceServer = Executors.newFixedThreadPool(config.getNumPoolSockets());
    }
        
    @Override
    public void run() {
        corriendo = true;
        
        while(corriendo) {
            try {
                Socket skClient = skServer.accept();
                skClient.setSoTimeout(aliveTimeOut);
                
                
                ControladorPeticionesCliente controladorCliente =
                        new ControladorPeticionesCliente(skClient);
                
                executorServiceServer.execute(controladorCliente);

                System.out.println("Aceptado nuevo cliente");
            } catch (IOException ex) {
                ServidorWebIecisa.log.error(ex);
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

