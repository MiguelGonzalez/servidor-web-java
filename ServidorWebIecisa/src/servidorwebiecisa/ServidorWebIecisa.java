package servidorwebiecisa;

import servidorwebiecisa.sockets.ControladorNuevosClientes;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import servidorwebiecisa.servidorWeb.IServidorWeb;

public class ServidorWebIecisa {
    
    public final static Logger log = Logger.getLogger("ServidorWebIecisa");
    public static final String VERSION = "0.1";
    
    private List<ServerSocket> skServers;
    private ConfiguracionServidor configuracion;
    
    
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
        configuracion = ConfiguracionServidor.getInstance();
        
        skServers = new ArrayList<>();
    }
    
    public void start() {
        for(IServidorWeb servidor : configuracion.getServidores()) {
            try {
                ServerSocket skServer = new ServerSocket(servidor.getPort());
                
                skServers.add(skServer);
                
                ControladorNuevosClientes controladorNuevosClientes = new
                        ControladorNuevosClientes(servidor, skServer);
                servidor.init();
                controladorNuevosClientes.empezarAEscuchar();

            } catch(IOException ex) {
                ServidorWebIecisa.log.error(ex);
            }
        }
    }
}
