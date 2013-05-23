package servidorwebiecisa;

import java.io.IOException;
import java.util.Properties;
import servidorwebiecisa.swing.controladores.ControllerSwingPrincipal;
import javax.swing.JFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServidorWebIecisa extends JFrame {
    
    public final static Logger log = Logger.getLogger("ServidorWebIecisa");
    public final static Properties propiedadesServidor = new Properties();;
    public static final String VERSION = "0.1";
    
    private ControllerSwingPrincipal controllerSwing;
    
    public static void main(String[] args) {
        //Configuramos el logger con los valores iniciales
        PropertyConfigurator.configure(ServidorWebIecisa.class.getResource(
            "log4j.properties"));
        
        try {
            propiedadesServidor.load(ServidorWebIecisa.class.getResourceAsStream("propiedadesServidor.properties"));
        } catch (IOException ex) {
            //Propiedades por defecto
            propiedadesServidor.setProperty("nombreFicheroXML", "servidorWebIecisa.xml");
            
            log.error("Error cargando propiedades", ex);
        }
        
        log.info("Versión: " + VERSION);
        
        
        ServidorWebIecisa servidorWebIecisa = new
                ServidorWebIecisa();
        servidorWebIecisa.start();
        
        
    }
    
    public ServidorWebIecisa() {
        
    }
    
    public void start() {
        controllerSwing = new ControllerSwingPrincipal();
        
        controllerSwing.start();
    }
}
