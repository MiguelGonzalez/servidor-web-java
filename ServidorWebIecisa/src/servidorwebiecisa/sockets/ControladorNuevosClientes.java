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
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorwebiecisa.MainServidor;
import servidorwebiecisa.domain.ConfiguracionModel;
import servidorwebiecisa.domain.ServidorModel;

/**
 *
 * @author Administrator
 */
public class ControladorNuevosClientes extends Thread {

    private ServerSocket skServer;
    private ExecutorService executorServiceServer;
    private ControladorPeticionesCliente controladorCliente;
    private ConfiguracionModel config = ConfiguracionModel.getInstance();
    
    private ServidorModel servidor;
    

    public ControladorNuevosClientes(ServidorModel servidorModel, 
            ServerSocket skServer) {
        this.servidor = servidorModel;
        this.skServer = skServer;
        executorServiceServer = Executors.newFixedThreadPool(
                config.getNumPoolSockets());
    }
        
    @Override
    public void run() {
        while(servidor.isCorriendo()) {
            try {
                Socket skClient = skServer.accept();
                skClient.setSoTimeout(config.getKeepAliveTimeoutInMillis());
                
                controladorCliente =
                        new ControladorPeticionesCliente(servidor, skClient);
                
                executorServiceServer.execute(controladorCliente);
            } catch (IOException ex) {
                MainServidor.log.info("Servidor '" + servidor + "' parado");
            }
        }
    }
    
    public void iniciarServidorWeb() {
        servidor.setCorriendo(true);
        start();
    }
    
    public void pararServidorWeb() {
        System.out.println("Servidor parado");
        
        controladorCliente.dejarDeEscucharPeticiones();
        servidor.setCorriendo(false);
        
        try {
            executorServiceServer.shutdownNow();
            skServer.close();
        } catch (IOException ex) {
            Logger.getLogger(ControladorNuevosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

