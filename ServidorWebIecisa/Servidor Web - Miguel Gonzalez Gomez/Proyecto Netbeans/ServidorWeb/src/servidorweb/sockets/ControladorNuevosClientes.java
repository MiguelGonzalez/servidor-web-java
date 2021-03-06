package servidorweb.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorweb.MainServidor;
import servidorweb.domain.ConfiguracionModel;
import servidorweb.domain.ServidorModel;

/**
 *
 * @author Miguel González Gómez
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
        MainServidor.log.info("Servidor iniciado: " + servidor.getPath());
        
        servidor.setCorriendo(true);
        start();
    }
    
    public void pararServidorWeb() {
        MainServidor.log.info("Servidor parado: " + servidor.getPath());
        
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

