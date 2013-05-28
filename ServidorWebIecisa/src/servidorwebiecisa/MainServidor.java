/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorwebiecisa;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import servidorwebiecisa.controllers.ServidorWebConcrete;

/**
 *
 * @author paracaidista
 */
public class MainServidor {
    public static final Properties propiedadesServidor = new Properties();
    public static final String VERSION = "0.1";
    public static final Logger log = Logger.getLogger("ServidorWebIecisa");

    public static void main(String[] args) {
        PropertyConfigurator.configure(MainServidor.class.
                getResource("log4j.properties"));
        try {
            propiedadesServidor.load(MainServidor.class.
                    getResourceAsStream("propiedadesServidor.properties"));
        } catch (IOException ex) {
            setPropiedadesDefault();
            log.error("Error cargando propiedades", ex);
        }
        log.info("Versi\u00f3n: " + MainServidor.VERSION);
        ServidorWebConcrete servidorWebIecisa = new ServidorWebConcrete();
        servidorWebIecisa.start();
    }
    
    private static void setPropiedadesDefault() {
        propiedadesServidor.setProperty("nombreFicheroXML",
                "servidorWebIecisa.xml");
    }
}
