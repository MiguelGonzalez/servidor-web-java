package servidorwebiecisa;

import servidorwebiecisa.sockets.ControladorNuevosClientes;
import java.io.IOException;
import java.net.ServerSocket;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServidorWebIecisa {
    
    public final static Logger log = Logger.getLogger("ServidorWebIecisa");
    public static final String VERSION = "0.1";
    
    private ServerSocket skServer;
    private int numPuertoServidor = 9090;
    private int estadoServidor = 0;
    
    
    public static void main(String[] args) {
        //Configuramos el logger con los valores iniciales
        PropertyConfigurator.configure(ServidorWebIecisa.class.getResource(
            "log4j.properties"));
        
        log.info("Versión: " + VERSION);
        
        ServidorWebIecisa servidor = new ServidorWebIecisa();
        servidor.start();
        log.info("Servidor arrancado");
    }
    
    public ServidorWebIecisa() {
        
    }
    
    public void start() {
        estadoServidor = 1;
        
        try {
            skServer = new ServerSocket(numPuertoServidor);

            ControladorNuevosClientes controladorNuevosClientes = new
                    ControladorNuevosClientes(skServer);
            controladorNuevosClientes.empezarAEscuchar();
            
        } catch(IOException ex) {
            ServidorWebIecisa.log.error(ex);
        }
    }
    
    public void parar() {
        estadoServidor = 0;
    }
    
}
